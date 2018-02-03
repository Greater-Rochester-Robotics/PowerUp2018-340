package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	//Makes math easier when deciding what speed to set the motors to
	private double leftMotorSpeed;
	private double rightMotorSpeed; 
	    
	private static Talon driveRight;
	private static Talon driveLeft;
	
	private static Encoder encoderA;
	private static Encoder encoderB;
	
	public Drive () {
		leftMotorSpeed = 0;
    	rightMotorSpeed = 0;
    	
    	drive1Right = new Talon(RobotMap.DRIVE_TALONSR_RIGHT_1_CHANNEL);
    	drive1Left = new Talon(RobotMap.DRIVE_TALONSR_LEFT_1_CHANNEL);
    	
    	driveLeft.setInverted(true);
    	
    	encoderA = new Encoder(RobotMap.DRIVE_ENCODERA_CHANNEL_A, RobotMap.DRIVE_ENCODERA_CHANNEL_B);
    	encoderB = new Encoder(RobotMap.DRIVE_ENCODERB_CHANNEL_A, RobotMap.DRIVE_ENCODERB_CHANNEL_B);
	}
	
	public int driveRightEncoder() {
    	return encoderA.get();
	}
	public int driveLeftEncoder() {
		return encoderB.get();
	}
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    	driveLeft.set(speed);
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
    	driveRight.set(speed);
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

    public void goStop() {
    	setBothDrive(0);
    }
}
