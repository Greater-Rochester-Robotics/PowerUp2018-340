package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.drive.DriveController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * <h1><i>Drive</i></h1>
 * The drive base responsible for nothing the robot from place to place
 */
public class Drive extends Subsystem {

	//Makes math easier when deciding what speed to set the motors to
	private double leftMotorSpeed;
	private double rightMotorSpeed;
	    
	private static Talon drive1Right;
	private static Talon drive2Right;
	private static Talon drive3Right;
	private static Talon drive1Left;
	private static Talon drive2Left;
	private static Talon drive3Left;
	
	private static Encoder encoderRight; //FIXME: set the distance/pulse ratio
	private static Encoder encoderLeft; //FIXME: set the distance/pulse ratio
	
	/**
	 * Moves the robo-machine around the <s>field</s> arcade. Weeeeeeee
	 */
	public Drive() {
		leftMotorSpeed = 0;
    	rightMotorSpeed = 0;
    	
    	drive1Right = new Talon(RobotMap.DRIVE_TALONSR_RIGHT_1_CHANNEL);
    	drive2Right = new Talon(RobotMap.DRIVE_TALONSR_LEFT_2_CHANNEL);
    	drive3Right = new Talon(RobotMap.DRIVE_TALONSR_LEFT_3_CHANNEL);
    	drive1Left = new Talon(RobotMap.DRIVE_TALONSR_LEFT_1_CHANNEL);
     	drive2Left = new Talon(RobotMap.DRIVE_TALONSR_LEFT_2_CHANNEL);
     	drive3Left = new Talon(RobotMap.DRIVE_TALONSR_LEFT_3_CHANNEL);
    	
    	drive1Left.setInverted(true);
    	drive2Left.setInverted(true);
    	drive3Left.setInverted(true);
    	
    	encoderRight = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER_CHANNEL_A, RobotMap.DRIVE_RIGHT_ENCODER_CHANNEL_B);
    	encoderLeft = new Encoder(RobotMap.DRIVE_LEFT_ENCODER_CHANNEL_A, RobotMap.DRIVE_LEFT_ENCODER_CHANNEL_B);
	}
	
	/**
	 * Starts the {@link DriveController} command as soon as the robot starts. That way, it can... ya know...
	 * drive. With a controller. ASAP.
	 */
	public void initDefaultCommand() {
        setDefaultCommand(new DriveController());
    }
	
	/**
	 * @return right encoder's count
	 */
	public int getRightEncoder() {
    	return encoderRight.get();
	}
	
	/**
	 * @return left encoder's count
	 */
	public int getLeftEncoder() {
		return encoderLeft.get();
	}
	
	/**
	 * @return right encoder's distance
	 */
	public double getRightDistance() {
    	return encoderRight.getDistance();
	}
	
	/**
	 * @return left encoder's distance
	 */
	public double getLeftDistance() {
		return encoderLeft.getDistance();
	}
	
	/**
     * Set the drive left speed
     * @param speed percentage of full
     * speed to go at [-1 ~ 1]
     */
    public void setDriveLeft(double speed) {
    	if(speed < -1) {
    		speed = -1;
    	} else if(speed > 1) {
    		speed = 1;
    	}
    	drive1Left.set(speed);
    	drive2Left.set(speed);
    	drive3Left.set(speed);
    }
    
    /**
     * Set the drive right speed; accounts
     * for negation
     * @param speed percentage of full
     * speed to go at [-1 ~ 1]
     */
    public void setDriveRight(double speed) {
    	if(speed < -1) {
    		speed = -1;
    	} else if(speed > 1) {
    		speed = 1;
    	}
    	
    	drive1Right.set(speed);
    	drive2Right.set(speed);
    	drive3Right.set(speed);
    }
    
    /**
     * Set both drive rails to the same
     * speed
     * @param speed the speed to set both
     * rails to
     */
    public void setBothDrive(double speed) {
    	setBothDrive(speed, speed);
    }
    
    /**
     * Set each drive rail's speed
     * separately
     * @param lSpeed left speed
     * @param rSpeed right speed
     */
    public void setBothDrive(double lSpeed, double rSpeed) {
    	setDriveLeft(lSpeed);
    	setDriveRight(rSpeed);
    }
    
    /**
     * One joystick drive mode.
     * 
     * @param moveValue
     * @param rotateValue
     */
    public void arcadeDrive(double moveValue, double rotateValue) {
		if (moveValue > 0.0) {
		    if (rotateValue > 0.0) {
		    	leftMotorSpeed = moveValue - rotateValue;
		    	rightMotorSpeed = Math.max(moveValue, rotateValue);
		    } else {
		    	leftMotorSpeed = Math.max(moveValue, -rotateValue);
		    	rightMotorSpeed = moveValue + rotateValue;
		    }
		} else {
		    if (rotateValue > 0.0) {
		    	leftMotorSpeed = -Math.max(-moveValue, rotateValue);
		    	rightMotorSpeed = moveValue + rotateValue;
		    } else {
		    	leftMotorSpeed = moveValue - rotateValue;
		    	rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
		    }
		}

		setBothDrive(leftMotorSpeed, rightMotorSpeed);
    }

    /**
     * Stop both drive rails
     */
    public void stop() {
    	setBothDrive(0);
    }
}
