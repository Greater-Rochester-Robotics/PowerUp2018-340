/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawNeutral;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawOpen;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawWheelsIn;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawWheelsOut;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawWheelsStop;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorDown;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorStop;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//I copied this from last year since we will need it soon anyways
	
	//DRIVER
	private Joystick driver = new Joystick(0);
	private Button driverA = new JoystickButton(driver, 1);
	private Button driverB = new JoystickButton(driver, 2);
	private Button driverX = new JoystickButton(driver, 3);
	private Button driverY = new JoystickButton(driver, 4);
	private Button driverLB = new JoystickButton(driver, 5);
	private Button driverRB = new JoystickButton(driver, 6);
	private Button driverBack = new JoystickButton(driver, 7);
	private Button driverStart = new JoystickButton(driver, 8);
	private Button driverLS = new JoystickButton(driver, 9);
	private Button driverRS = new JoystickButton(driver, 10);
	private Button driverDPadUp = new DPad(driver, DPad.Direction.up);
	private Button driverDPadDown = new DPad(driver, DPad.Direction.down);
	private Button driverDPadRight = new DPad(driver, DPad.Direction.right);
	private Button driverDPadLeft = new DPad(driver, DPad.Direction.left);
	private Button driverRT = new JoyTrigger(driver, Axis.RIGHT_TRIGGER.getAxis(), .2);
	
	//CO-DRIVER
	private Joystick coDriver = new Joystick(1);
	private Button coDriverA = new JoystickButton(coDriver, 1);
	private Button coDriverB = new JoystickButton(coDriver, 2);
	private Button coDriverX = new JoystickButton(coDriver, 3);
	private Button coDriverY = new JoystickButton(coDriver, 4);
	private Button coDriverLB = new JoystickButton(coDriver, 5);
	private Button coDriverRB = new JoystickButton(coDriver, 6);
	private Button coDriverBack = new JoystickButton(coDriver, 7);
	private Button coDriverStart = new JoystickButton(coDriver, 8);
	private Button coDriverLS = new JoystickButton(coDriver, 9);
	private Button coDriverRS = new JoystickButton(coDriver, 10);
		
	public OI () {
		
		//Buttons
		driverA.whenPressed(new ManualClawClose());
		driverY.whenPressed(new ManualClawNeutral());
		driverX.whenPressed(new ManualClawOpen());
		driverRB.whenPressed(new ManualClawWheelsIn());
		driverRB.whenReleased(new ManualClawWheelsStop());
		driverLB.whenPressed(new ManualClawWheelsOut());
		driverLB.whenReleased(new ManualClawWheelsStop());
		
		coDriverA.whenPressed(new ManualElevatorDown());
		coDriverY.whenPressed(new ManualElevatorUp());
		coDriverY.whenReleased(new ManualElevatorStop());
		coDriverA.whenReleased(new ManualElevatorStop());
	}
	
	/**
	 * Enumerates the raw numbers assigned to
	 * the stick axes
	 */
	public enum Axis {
	    LEFT_X(0),
	    LEFT_Y(1),
	    LEFT_TRIGGER(2),
	    RIGHT_TRIGGER(3),
	    RIGHT_X(4),
	    RIGHT_Y(5);
	    
	    private int axis;
	    
	    private Axis(int axis) {
	    	this.axis = axis;
	    }
	    
	    public int getAxis() {
	    	return axis;
	    }
	}
	
	/**
	 * Get the raw value of the any of
	 * the driver's axes
	 * @param axis the axis
	 * @return the raw axis value, or 0 if
	 * in the range [-.05, .05]
	 * @see Axis
	 */
	public double getDriverAxis(Axis axis) {
	    return (driver.getRawAxis(axis.getAxis()) < -.05 || driver.getRawAxis(axis.getAxis()) > .05) ? driver.getRawAxis(axis.getAxis()) : 0;
	}
	
	/**
	 * Get the raw value of the any of
	 * the co-driver's axes
	 * @param axis the axis
	 * @return the raw axis value, or 0 if
	 * in the range [-.05, .05]
	 * @see Axis
	 */
	public double getCoDriverAxis(Axis axis) {
	    return (coDriver.getRawAxis(axis.getAxis()) < -.05 || coDriver.getRawAxis(axis.getAxis()) > .05) ? coDriver.getRawAxis(axis.getAxis()) : 0;
	}
}
