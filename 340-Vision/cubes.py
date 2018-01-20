from picamera.array import PiRGBArray
from picamera import PiCamera

from networktables import NetworkTables as nt

import time
import cv2
import numpy as np
import math

import socket

# camera setup

IMG_WIDTH = 320
IMG_HEIGHT = 240

camera = PiCamera(resolution=str(IMG_WIDTH) + "x" + str(IMG_HEIGHT), framerate = 60) # use 60 fps to avoid issues with rolling shutter
#camera.exposure_compensation = -25 # not sure if necessary
#camera.exposure_mode = 'off' # avoid automatic exposure compensation
#camera.shutter_speed = 5555 # low shutter speed to lower lighting besides the retroreflective tape
rawCapture = PiRGBArray(camera)
nt.initialize(server='roborio-424-frc.local')

time.sleep(3) # camera warmup, position setup

UDP_IP = "0.0.0.0"
UDP_PORT = 5803

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((UDP_IP, UDP_PORT))

startTime = time.time() # for timing purposes

# kernel for dilate/erode
kernel = np.ones((10,10), np.uint8);

#font for debug drawing
font = cv2.FONT_HERSHEY_SIMPLEX

# main execution

color = 0;

vi = nt.getTable('vision')

for image in camera.capture_continuous(rawCapture, format="bgr", use_video_port=True):
    startTime = time.time()
    # now grab frame and store it
    frame = image.array
    rawCapture.truncate(0) # important

        
    # convert images to HSV color spectrum
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    # hsv thresholds
    lower = np.array([21,90,83])
    upper = np.array([39,255,255])

    thresh = cv2.inRange(hsv, lower, upper)
    
    # dilate and erode
    dilation = cv2.dilate(thresh, kernel, iterations = 1)
    
    erode = cv2.erode(dilation, kernel, iterations = 1)
    
    thresh = erode

    # find contours
    im2, contours, hierarchy = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    centerx = -1
    centery = 0
    width = -1
    contour = -1

    # sock.sendto(str(160),   ("roborio-424-frc.local", 5803))
    debug = frame

    # filter contours
    for i in range(0, len(contours)):
         if cv2.contourArea(contours[i]) > 1500:
            x,y,w,h = cv2.boundingRect(contours[i])
            cv2.drawContours(debug, contours, i, (0, 0, 255), 2)

            cbb = cv2.contourArea(contours[i])/(w*h)
            cv2.putText(debug, str(cbb)[:5], (x+w/2, y+h/2), font, 0.5, (255, 0, 0), 1, cv2.LINE_AA)
            # closest to center ?
            if(abs(IMG_WIDTH/2 - (x + w/2)) < abs(IMG_WIDTH/2 - centerx)):
                width = w
                centerx = x + w/2 # info about the target
                centery = y + h/2 # info about the target
                contour = i


    print(centerx)
    vi.putNumber('centerx', centerx);
    vi.putNumber('width', width);
    sock.sendto(str(centerx) + "," + str(width),   ("roborio-424-frc.local", 5803))

    print("--- %s seconds processing ---" % (time.time() - startTime))
    # save debug images
    cv2.imwrite("cube_orig.jpg", frame)

    cv2.drawContours(frame, contours, -1, (0, 0, 255), 2) # draw all contours in red
    cv2.imwrite('cube_found.jpg', frame) 
    cv2.imwrite('cube_debug.jpg', debug) 
    # cv2.imwrite("cube_dilate.jpg", dilation)
    # cv2.imwrite("cube_erode.jpg", erode)


print("--- %s seconds capturing ---" % (time.time() - startTime))
processingStart = time.time();

# end main execution

print("--- %s seconds processing ---" % (time.time() - processingStart))
print("--- %s seconds total ---" % (time.time() - startTime))

camera.close(); # important
"""
while True:
    led.fill((255, 0, 0))
    led.update()
    time.sleep(DELAY)
    led.fill((0, 255, 0))
    led.update()
    time.sleep(DELAY)
    led.fill((0, 0, 255))
    led.update()
    time.sleep(DELAY)
"""
