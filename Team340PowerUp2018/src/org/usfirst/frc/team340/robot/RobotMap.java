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
	public static final int RIGHT_DRIVE_ENCODER_A = 3;
	public static final int RIGHT_DRIVE_ENCODER_B = 2;
	public static final int ELEVATOR_BUMP = 4;
	
	//Claw wheel speeds
	public static final double CLAW_WHEEL_FULLSPEED_VBUS = 1.0;
	public static final double CLAW_WHEEL_HOLDSPEED_VBUS = 0.5;
	public static final double CLAW_ALIGNMENT_WAIT_S = 0.5;
	
	//Elevator Positions
	public static final int ELEVATOR_TRAVEL_POSTION_TICKS = 10;
}