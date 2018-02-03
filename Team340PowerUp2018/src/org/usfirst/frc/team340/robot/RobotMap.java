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
	
	//PWM Channels
	public static final int DRIVE_TALONSR_LEFT_1_CHANNEL = 0;
	public static final int DRIVE_TALONSR_LEFT_2_CHANNEL = 1;
	public static final int DRIVE_TALONSR_LEFT_3_CHANNEL = 2;
	public static final int DRIVE_TALONSR_RIGHT_1_CHANNEL = 3;
	public static final int DRIVE_TALONSR_RIGHT_2_CHANNEL = 4;
	public static final int DRIVE_TALONSR_RIGHT_3_CHANNEL = 5;
	
	//CAN ID's
	public static final int ELEVATOR_TALONSRX_A_ID = 1;
	public static final int ELEVATOR_TALONSRX_B_ID = 2;
	public static final int ELEVATOR_TALONSRX_C_ID = 3;
	public static final int CLAW_WHEEL_LEFT_CHANNEL = 4;
	public static final int CLAW_WHEEL_RIGHT_CHANNEL = 5;
	
	//Digital Channels
	public static final int DRIVE_ENCODERA_CHANNEL_A = 0; //TODO: assume left, but find if left or right
	public static final int DRIVE_ENCODERA_CHANNEL_B = 1; //TODO: assume left, but find if left or right
	public static final int DRIVE_ENCODERB_CHANNEL_A = 2; //TODO: assume right, but find if left or right
	public static final int DRIVE_ENCODERB_CHANNEL_B = 3; //TODO: assume right, but find if left or right
	public static final int CLAW_CUBE_SENSOR_CHANNEL = 4;
	
	//Solenoid Channels
	public static final int ELEVATOR_BRAKE_CHANNEL_A = 0;
	public static final int ELEVATOR_BRAKE_CHANNEL_B = 1;
	public static final int ELEVATOR_TILT_CHANNEL_A = 2;
	public static final int ELEVATOR_TILT_CHANNEL_B = 3;
	public static final int CLAW_SOLENOID_CLOSE_CHANNEL = 4;
	public static final int CLAW_SOLENOID_OPEN_CHANNEL = 5;
	
	
	
	//Claw Devices
	
	//Claw Wheel Speeds
	public static final double CLAW_WHEEL_FULLSPEED_VBUS = 1.0;
	public static final double CLAW_WHEEL_HOLDSPEED_VBUS = 0.5;
	public static final double CLAW_ALIGNMENT_WAIT_S = 0.5;
	
	//Drive Devices
	//Elevator Devices
	
	//Elevator Positions
	public static final int ELEVATOR_TRAVEL_POSITION_TICKS = 10;
	
	//Elevator Speeds
	public static final double ELEVATOR_FULLSPEED_VBUS = 1.0;
	public static final double ELEVATOR_DELTA_TICS_TO_SLOW_DOWN = 150;
	public static final double ELEVATOR_KP = (1023)/ELEVATOR_DELTA_TICS_TO_SLOW_DOWN;
	public static final int ELEVATOR_MAX_TICS = 800;
	public static final double ELEVATOR_RAMP_TIME_S = 0.5;
	public static final int ELEVATOR_TICS_PER_INCH = 10;
	public static final int ELEVATOR_TOLERANCE_TICS = 1 * ELEVATOR_TICS_PER_INCH;
	public static final double ELEVATOR_MIN_SPEED_UP_VBUS = .15;
	public static final double ELEVATOR_MAX_SPEED_UP_VBUS = 1;
	public static final double ELEVATOR_MIN_SPEED_DOWN_VBUS = .15;
	public static final double ELEVATOR_MAX_SPEED_DOWN_VBUS = 1;
}
