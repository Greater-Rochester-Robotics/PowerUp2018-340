/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.commands.ClawReSecureCube;
import org.usfirst.frc.team340.robot.commands.groups.AcquireCube;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	//I copied this from last year since we will need it soon anyways
	
	//DRIVER
		private Joystick driver = new Joystick(0);
		private Button driverA = new JoystickButton(driver, 1);
		private Button driverB = new JoystickButton(driver, 2);
		/*private Button driverX = new JoystickButton(driver, 3);
		private Button driverY = new JoystickButton(driver, 4);
		private Button driverLB = new JoystickButton(driver, 5);
		private Button driverRB = new JoystickButton(driver, 6);
		private Button driverBack = new JoystickButton(driver, 7);
		private Button driverStart = new JoystickButton(driver, 8);
		private Button driverLS = new JoystickButton(driver, 9);
		private Button driverRS = new JoystickButton(driver, 10);
		private Button driverDPadUp = new DPad(driver, Direction.up);
		private Button driverDPadDown = new DPad(driver, Direction.down);
		private Button driverDPadRight = new DPad(driver, Direction.right);
		private Button driverDPadLeft = new DPad(driver, Direction.left);
		private Button driverRT = new JoyTrigger(driver, Axis.RIGHT_TRIGGER.getAxis(), .2);*/
		
		//CO-DRIVER
		/*private Joystick coDriver = new Joystick(1);
		private Button coDriverA = new JoystickButton(coDriver, 1);
		private Button coDriverB = new JoystickButton(coDriver, 2);
		private Button coDriverX = new JoystickButton(coDriver, 3);
		private Button coDriverY = new JoystickButton(coDriver, 4);
		private Button coDriverLB = new JoystickButton(coDriver, 5);
		private Button coDriverRB = new JoystickButton(coDriver, 6);
		private Button coDriverBack = new JoystickButton(coDriver, 7);
		private Button coDriverStart = new JoystickButton(coDriver, 8);
		private Button coDriverLS = new JoystickButton(coDriver, 9);
		private Button coDriverRS = new JoystickButton(coDriver, 10);*/
		
public OI () {
		
		//Buttons
		driverA.whenPressed(new AcquireCube());
		driverB.whenPressed(new ClawReSecureCube());
	}
}
