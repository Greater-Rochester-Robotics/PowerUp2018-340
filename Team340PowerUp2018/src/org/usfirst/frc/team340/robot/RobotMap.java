/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team340.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//////////////////////////////////////////
	// SRX SWITCHED 5 AND 1 IN SILVERLIGHT! //
	//////////////////////////////////////////
	
	//PWM Channels
	public static final int DRIVE_TALONSR_LEFT_CHANNEL = 0;
	public static final int DRIVE_TALONSR_RIGHT_CHANNEL = 1;
	public static final int CLIMBER_HOCKEYSTICK = 2;
	
	//CAN IDs
	public static final int CLAW_WHEEL_LEFT_CHANNEL = 1; //E on prac, A on comp
	public static final int CLAW_WHEEL_RIGHT_CHANNEL = 2; //B on both (the claws may not be correct, in intake and outtake)
	public static final int CLIMBER_TALONSRX_A_ID = 3; //C on prac, F on comp
	public static final int CLIMBER_TALONSRX_B_ID = 4; //D on prac, G on comp
	public static final int ELEVATOR_TALONSRX_A_ID = 8; //H on prac, D on comp
	public static final int ELEVATOR_TALONSRX_B_ID = 7; //G on prac, C on comp
	public static final int ELEVATOR_TALONSRX_C_ID = 6; //F on prac, E on comp
	
	
	//Digital Channels
	public static final int DRIVE_RIGHT_ENCODER_CHANNEL_A = 1; //TODO: assume left, but find if left or right
	public static final int DRIVE_RIGHT_ENCODER_CHANNEL_B = 0; //TODO: assume left, but find if left or right
	public static final int DRIVE_LEFT_ENCODER_CHANNEL_A = 3; //TODO: assume right, but find if left or right
	public static final int DRIVE_LEFT_ENCODER_CHANNEL_B = 2; //TODO: assume right, but find if left or right
	public static final int CLAW_CUBE_SENSOR_CHANNEL_A = 8;
	public static final int CLAW_CUBE_SENSOR_CHANNEL_B = 5;
	public static final int AUTO_SWITCH_A = 7;
	public static final int AUTO_SWITCH_B = 6;
	
	//Solenoid Channels
	public static final int ELEVATOR_BRAKE_CHANNEL = 1; // switched with climber
	public static final int ELEVATOR_TILT_CHANNEL_A = 3;
	public static final int ELEVATOR_TILT_CHANNEL_B = 2;
	public static final int CLAW_SOLENOID_CLOSE_CHANNEL = 6;
	public static final int CLAW_SOLENOID_OPEN_CHANNEL = 5;
	public static final int CLIMBER_DEPLOY_CHANNEL= 7; // switched with elevator on practice
	public static final int LED_RED_CHANNEL = 4;
	public static final int LED_GREEN_CHANNEL = 0;
	public static final int FORKS_CHANNEL = 0; // same as leds since we are removing them
	
	//Claw Devices
	
	//Claw Wheel Speeds
	public static final double CLAW_WHEEL_FULLSPEED_VBUS = 1.0;
	public static final double CLAW_WHEEL_SHOOTSPEED_VBUS = 0.55; // 0.5
	public static final double CLAW_WHEEL_HOLDSPEED_VBUS = 0.5;
	public static final double CLAW_ALIGNMENT_WAIT_S = 0.5;
	public static final double CLAW_WHEEL_ACQUIRE_SPEED_VBUS = 0.69;
	
	//Drive Devices https://github.com/Greater-Rochester-Robotics/PowerUp2018-340/pull/3/conflict?name=Team340PowerUp2018%252Fsrc%252Forg%252Fusfirst%252Ffrc%252Fteam340%252Frobot%252FRobot.java&ancestor_oid=2c7989ea654531f39920c9a6eb9f47216ed5b60b&base_oid=9ecb5ba05aa08eb99b1a0da2218a49da04a05033&head_oid=6ba5c8f7cf684de51a04da79700566a3f7ea5564
	//Elevator Devices
	
	//Elevator Positions
	public static final int ELEVATOR_STARTING_POSITION_HEIGHT = 1420;
	public static final int ELEVATOR_TRAVEL_POSITION_HEIGHT = 100;
	public static final int ELEVATOR_SCALE_MID_HEIGHT = 1000; //2600
	public static final int ELEVATOR_SCALE_MAX_HEIGHT = 3000;
	public static final int ELEVATOR_SCALE_MIN_HEIGHT = 2240;
	
	//Elevator Speeds
	public static final double ELEVATOR_FULLSPEED_VBUS = 1.0;
	public static final double ELEVATOR_DELTA_TICS_TO_SLOW_DOWN = 300;
	public static final double ELEVATOR_KP = (1023)/ELEVATOR_DELTA_TICS_TO_SLOW_DOWN;
	public static final int ELEVATOR_MAX_TICS = 3125;
	public static final double ELEVATOR_RAMP_TIME_S = 0.5;
	public static final int ELEVATOR_TICS_PER_INCH = 10;
	public static final int ELEVATOR_TOLERANCE_TICS = 1 * ELEVATOR_TICS_PER_INCH;
	public static final double ELEVATOR_MIN_SPEED_UP_VBUS = .3;
	public static final double ELEVATOR_MAX_SPEED_UP_VBUS = 1;
	public static final double ELEVATOR_MIN_SPEED_DOWN_VBUS = .15;
	public static final double ELEVATOR_MAX_SPEED_DOWN_VBUS = 1;
	public static final double ELEVATOR_GO_BOTTOM_VBUS = .75;
}
