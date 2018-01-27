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
	
	//Claw Wheel Speeds
	public static final double CLAW_WHEEL_FULLSPEED_VBUS = 1.0;
	public static final double CLAW_WHEEL_HOLDSPEED_VBUS = 0.5;
	public static final double CLAW_ALIGNMENT_WAIT_S = 0.5;
	
	//Drive Devices
	public static final int DRIVE_TALONSR_RIGHT_CHANNEL = 4;
	public static final int DRIVE_TALONSR_LEFT_CHANNEL = 8;
	
	//Elevator Devices
	public static final int ELEVATOR_BUMP_SWITCH_PORT =7;
	public static final int ELEVATOR_ENCODER_PORTA = 5;
	public static final int ELEVATOR_ENCODER_PORTB = 6;
	public static final int ELEVATOR_TALONSRX_A_ID = 11;
	public static final int ELEVATOR_TALONSRX_B_ID = 13;
	
	//Elevator Positions
	public static final int ELEVATOR_TRAVEL_POSTION_TICKS = 10;
}