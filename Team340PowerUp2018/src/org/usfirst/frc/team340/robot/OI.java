/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.commands.GoToStartingConfig;
import org.usfirst.frc.team340.robot.commands.auto.CenterSwitchAutoTwoCube;
import org.usfirst.frc.team340.robot.commands.auto.SingleCubeFarScale;
import org.usfirst.frc.team340.robot.commands.auto.TwoCubeEasy;
import org.usfirst.frc.team340.robot.commands.auto.testautomovement;
import org.usfirst.frc.team340.robot.commands.claw.ClawAcquireCube;
import org.usfirst.frc.team340.robot.commands.claw.ClawAcquireCubeOpenClaw;
import org.usfirst.frc.team340.robot.commands.claw.ClawShootScore;
import org.usfirst.frc.team340.robot.commands.claw.ClawStopWheels;
import org.usfirst.frc.team340.robot.commands.climber.ClimberClimb;
import org.usfirst.frc.team340.robot.commands.climber.ClimberDeployForks;
import org.usfirst.frc.team340.robot.commands.climber.ClimberDeployHook;
import org.usfirst.frc.team340.robot.commands.climber.ClimberLockForks;
import org.usfirst.frc.team340.robot.commands.climber.ClimberRetract;
import org.usfirst.frc.team340.robot.commands.climber.ClimberStop;
import org.usfirst.frc.team340.robot.commands.drive.DriveStop;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorRaiseCube;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorResetEncoderToStarting;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorStop;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorTiltBackward;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawOpen;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorBrakeDisengage;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorBrakeEngage;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.pathing.Paths.FROM_CENTER;
import org.usfirst.frc.team340.robot.commands.pathing.Paths.FROM_LEFT_PORTAL;
import org.usfirst.frc.team340.robot.commands.pathing.Animation;
import org.usfirst.frc.team340.robot.commands.pathing.Keyframe;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

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
	private Button coDriverDPadUp = new DPad(coDriver, DPad.Direction.up);
	private Button coDriverDPadDown = new DPad(coDriver, DPad.Direction.down);
	private Button coDriverDPadRight = new DPad(coDriver, DPad.Direction.right);
	private Button coDriverDPadLeft = new DPad(coDriver, DPad.Direction.left);
	private Button coDriverRT = new JoyTrigger(coDriver, Axis.RIGHT_TRIGGER.getAxis(), .2);
	
	//Climber - positive climb/roll-in; negative unclimb/roll-out
	public OI () {
		
		//Buttons
		driverA.whenPressed(new ClawAcquireCube());
		driverA.whenReleased(new ClawStopWheels()); //Also closes!!!
		driverX.whenPressed(new ClawShootScore());
		driverB.whenPressed(new ClawAcquireCubeOpenClaw());
		driverB.whenReleased(new ClawStopWheels());
		
//		driverLB.whenPressed(new RunPath(Paths.straightLength(13*12), x -> {
//			if(x < 0.3) {
//				return 0.5;
//			} else if (x < 0.6){
//				return 0.9;
//			} else {
//				return 0.4;
//			}
//		}, new Animation(
//				new Keyframe(new ElevatorResetEncoderToStarting(), 0.0),
//				new Keyframe(new ElevatorTiltForward(), 0.1),
//				new Keyframe(new ElevatorGoToPosition(2600), 0.5)
//				)));
//		driverLB.whenPressed(new ElevatorResetEncoderToStarting());
//		driverLB.whenReleased(new DriveStop());
		
		driverX.whenReleased(new ClawStopWheels()); //Also closes!!!
		driverY.whenPressed(new ManualClawOpen());

		coDriverRB.whenPressed(new ManualElevatorTiltForward());
		coDriverLB.whenPressed(new ElevatorTiltBackward());
		
		coDriverDPadRight.whenPressed(new ClimberDeployHook());
		coDriverDPadLeft.whenPressed(new ClimberRetract());
		coDriverDPadDown.whenPressed(new ClimberClimb(1.0));
		coDriverDPadDown.whenReleased(new ClimberStop());
		coDriverDPadUp.whenPressed(new ClimberClimb(-1.0));
		coDriverDPadUp.whenReleased(new ClimberStop());
//		coDriverStart.whenPressed(new ClimberDeployForks());
//		coDriverBack.whenPressed(new ClimberLockForks());
		
		coDriverA.whenPressed(new ElevatorGoToPosition(RobotMap.ELEVATOR_TRAVEL_POSITION_HEIGHT));
		coDriverY.whenReleased(new ElevatorStop());
		coDriverB.whenPressed(new ElevatorGoToPosition(RobotMap.ELEVATOR_SCALE_MID_HEIGHT));
		coDriverY.whenPressed(new ElevatorGoToPosition(RobotMap.ELEVATOR_SCALE_MAX_HEIGHT));
//		coDriverA.whenPressed(new CenterSwitchAutoTwoCube(FROM_CENTER.SWITCH_LEFT, FROM_CENTER.LEFT_SECOND_CUBE_FORWARD, FROM_CENTER.LEFT_SECOND_CUBE_BACKWARDS));
//		coDriverA.whenReleased(new DriveStop());
//		coDriverB.whenPressed(new TwoCubeEasy(FROM_LEFT_PORTAL.SCALE_LEFT_TRAVEL, FROM_LEFT_PORTAL.SCALE_LEFT_FINISH, FROM_LEFT_PORTAL.SECOND_CUBE, 90, 0.369, 0.3069));
//		coDriverB.whenReleased(new DriveStop());
//		coDriverY.whenPressed(new SingleCubeFarScale(FROM_LEFT_PORTAL.SCALE_RIGHT_TRAVEL, FROM_LEFT_PORTAL.SCALE_RIGHT_FINISH, 3000, 0.369));
//		coDriverY.whenPressed(new DriveStop());
		
		coDriverRT.whenPressed(new ElevatorGoToBottom());
		
//		coDriverBack.whenPressed(new ManualElevatorBrakeDisengage());
//		coDriverStart.whenPressed(new ManualElevatorBrakeEngage());
		driverStart.whenPressed(new GoToStartingConfig());
		driverStart.whenReleased(new ElevatorStop());
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
	    return (driver.getRawAxis(axis.getAxis()) < -.1 || driver.getRawAxis(axis.getAxis()) > .1) ? driver.getRawAxis(axis.getAxis()) : 0;
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
	    return (coDriver.getRawAxis(axis.getAxis()) < -.1 || coDriver.getRawAxis(axis.getAxis()) > .1) ? coDriver.getRawAxis(axis.getAxis()) : 0;
	}
}
