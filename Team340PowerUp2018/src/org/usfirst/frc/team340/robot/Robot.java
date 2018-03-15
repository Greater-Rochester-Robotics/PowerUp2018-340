/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team340.robot;

import org.usfirst.frc.team340.robot.commands.auto.CenterSwitchAuto;
import org.usfirst.frc.team340.robot.commands.auto.CenterSwitchAutoFast;
import org.usfirst.frc.team340.robot.commands.auto.CenterSwitchAutoTwoCube;
import org.usfirst.frc.team340.robot.commands.auto.SingleCube;
import org.usfirst.frc.team340.robot.commands.auto.SingleCubeFarScale;
import org.usfirst.frc.team340.robot.commands.auto.TwoCubeEasy;
import org.usfirst.frc.team340.robot.commands.pathing.Paths.FROM_CENTER;
import org.usfirst.frc.team340.robot.commands.pathing.Paths.FROM_LEFT_PORTAL;
import org.usfirst.frc.team340.robot.commands.pathing.Paths.FROM_RIGHT_PORTAL;
import org.usfirst.frc.team340.robot.subsystems.Claw;
import org.usfirst.frc.team340.robot.subsystems.Climber;
import org.usfirst.frc.team340.robot.subsystems.Drive;
import org.usfirst.frc.team340.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Drive drive;
	public static Claw claw;
	public static Climber climber;
	public static Elevator elevator;
	public static OI oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public static final boolean isCompBot = true;
	
	public static DigitalInput autoSwitchA = new DigitalInput(RobotMap.AUTO_SWITCH_A);
	public static DigitalInput autoSwitchB = new DigitalInput(RobotMap.AUTO_SWITCH_B);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drive = new Drive();
		claw = new Claw();
		climber = new Climber();
		elevator = new Elevator();
		//SUBSYSTEMS BEFORE THIS LINE, OI AFTER
		oi = new OI();
		
		// put USB camera on SmartDashboard
		CameraServer.getInstance().startAutomaticCapture();
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
//		SmartDashboard.putNumber("PracticeToCompMultiplier", 1.7657);
//		SmartDashboard.putNumber("CompToPracticeMultiplier", 0.5663);
//		SmartDashboard.setPersistent("PracticeToCompMultiplier");
//		SmartDashboard.setPersistent("CompToPracticeMultiplier");
	}
	
	/*public static double getPracticeToCompMultiplier() {
		if(!isCompBot) {
			return 1.8678;
		}
		return 1;
//		return SmartDashboard.getNumber("PracticeToCompMultiplier", 1.0);
	}
	public static double getCompToPracticeMultiplier() {
		if(!isCompBot) {
			return 0.535;
		}
		return 1;
//		return SmartDashboard.getNumber("CompToPracticeMultiplier", 1.0);
	}*/
	
	public static String FMSData() {
		return DriverStation.getInstance().getGameSpecificMessage();
	}
	
	public static boolean getAutoSwitchA() {
		return !autoSwitchA.get();
	}
	public static boolean getAutoSwitchB() {
		return !autoSwitchB.get();
	}
	public static String getStart() {
		if(getAutoSwitchA()) {
			return "L";
		} else if (getAutoSwitchB()) {
			return "R";
		}
		return "C";
		
//		return "C";
	}
	
	public static Object choose(String fms, int pos, Object left, Object right) {
		SmartDashboard.putString("FMS", fms);
		if(fms.substring(pos, pos + 1).toLowerCase().equals("l")) {
			return left;
		} else {
			return right;
		}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putBoolean("isCubePresent", Robot.claw.isCubePresent());
		SmartDashboard.putNumber("Drive Left Encoder", Robot.drive.getLeftEncoder());
		SmartDashboard.putNumber("Drive right encoder", Robot.drive.getRightEncoder());
		SmartDashboard.putNumber("Yaw", Robot.drive.getYaw());
		SmartDashboard.putBoolean("Auto Switch A", getAutoSwitchA());
		SmartDashboard.putBoolean("Auto Switch B", getAutoSwitchB());
		SmartDashboard.putString("Auto Start Position", getStart());
	}

	private static String start = "R"; // L[eft] C[enter] or R[ight]
	private static final int cubes = 1; // 1/2/3 cubes etc
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
//		m_autonomousCommand = m_chooser.getSelected();
		String fms = FMSData();
		start = getStart();
		switch(fms) {
			case "RRR":
				if(start.equals("R") && cubes == 1) {
					m_autonomousCommand = new TwoCubeEasy(FROM_RIGHT_PORTAL.SCALE_RIGHT_TRAVEL, FROM_RIGHT_PORTAL.SCALE_RIGHT_FINISH, FROM_RIGHT_PORTAL.SECOND_CUBE, -90, 0.50, 0.50);
//					m_autonomousCommand = new SingleCube(FROM_RIGHT_PORTAL.SCALE_RIGHT_TRAVEL, FROM_RIGHT_PORTAL.SCALE_RIGHT_FINISH, 3000, 0.369);
//					m_autonomousCommand = new SingleCube(FROM_RIGHT.SCALE_RIGHT, FROM_RIGHT.SCALE_RIGHT_FINISH, 3000, 1.0);
				} else if (start.equals("L") &&  cubes == 1) {
					m_autonomousCommand = new SingleCubeFarScale(FROM_LEFT_PORTAL.SCALE_RIGHT_TRAVEL, FROM_LEFT_PORTAL.SCALE_RIGHT_FINISH, 3000, 0.369);
				} else if (start.equals("C")) {
//					m_autonomousCommand = new CenterSwitchAutoFast(FROM_CENTER.SWITCH_RIGHT);
//					m_autonomousCommand = new CenterSwitchAutoTwoCube(FROM_CENTER.SWITCH_LEFT, FROM_CENTER.LEFT_SECOND_CUBE_FORWARD, FROM_CENTER.LEFT_SECOND_CUBE_BACKWARDS);
					m_autonomousCommand = new CenterSwitchAutoTwoCube(FROM_CENTER.SWITCH_RIGHT, FROM_CENTER.RIGHT_SECOND_CUBE_FORWARD, FROM_CENTER.RIGHT_SECOND_CUBE_BACKWARDS);
				}
				break;
			case "LLL":
				if(start.equals("R") && cubes == 1) {
//					m_autonomousCommand = new SingleCube(FROM_RIGHT.SWITCH_LEFT, FROM_RIGHT.SWITCH_LEFT_FINISH, 969, 0.3069);
					m_autonomousCommand = new SingleCubeFarScale(FROM_RIGHT_PORTAL.SCALE_LEFT_TRAVEL, FROM_RIGHT_PORTAL.SCALE_LEFT_FINISH, 3000, 0.3069);
				} else if (start.equals("L") &&  cubes == 1) {
					m_autonomousCommand = new TwoCubeEasy(FROM_LEFT_PORTAL.SCALE_LEFT_TRAVEL, FROM_LEFT_PORTAL.SCALE_LEFT_FINISH, FROM_LEFT_PORTAL.SECOND_CUBE, 90, 0.369, 0.3069);
//					m_autonomousCommand = new SingleCube(FROM_LEFT_PORTAL.SCALE_LEFT_TRAVEL, FROM_LEFT_PORTAL.SCALE_LEFT_FINISH, 3000, 0.369);
				} else if (start.equals("C")) {
//					m_autonomousCommand = new CenterSwitchAutoFast(FROM_CENTER.SWITCH_LEFT);
					m_autonomousCommand = new CenterSwitchAutoTwoCube(FROM_CENTER.SWITCH_LEFT, FROM_CENTER.LEFT_SECOND_CUBE_FORWARD, FROM_CENTER.LEFT_SECOND_CUBE_BACKWARDS);
				}
				break;
			case "RLR":
				if(start.equals("R") && cubes == 1) {
					m_autonomousCommand = new SingleCube(FROM_RIGHT_PORTAL.SCALE_LEFT_TRAVEL, FROM_RIGHT_PORTAL.SCALE_LEFT_FINISH, 3000, 0.3069);
//					m_autonomousCommand = new SingleCube(FROM_RIGHT_PORTAL.SWITCH_RIGHT_TRAVEL, FROM_RIGHT_PORTAL.SWITCH_RIGHT_FINISH, 969, 0.3069);
//					m_autonomousCommand = new SingleCube(FROM_RIGHT.SWITCH_RIGHT, FROM_RIGHT.SWITCH_RIGHT_FINISH, 969, 0.3069);
			} else if (start.equals("L")) {
					m_autonomousCommand = new SingleCube(FROM_LEFT_PORTAL.SCALE_LEFT_TRAVEL, FROM_LEFT_PORTAL.SCALE_LEFT_FINISH, 3000, 0.369);
				}else if (start.equals("C")) {
//					m_autonomousCommand = new CenterSwitchAutoTwoCube(FROM_CENTER.SWITCH_LEFT, FROM_CENTER.LEFT_SECOND_CUBE_FORWARD, FROM_CENTER.LEFT_SECOND_CUBE_BACKWARDS);
					m_autonomousCommand = new CenterSwitchAutoTwoCube(FROM_CENTER.SWITCH_RIGHT, FROM_CENTER.RIGHT_SECOND_CUBE_FORWARD, FROM_CENTER.RIGHT_SECOND_CUBE_BACKWARDS);
				}
				break;
			case "LRL":
				if(start.equals("R") && cubes == 1) {
					m_autonomousCommand = new SingleCube(FROM_RIGHT_PORTAL.SCALE_RIGHT_TRAVEL, FROM_RIGHT_PORTAL.SCALE_RIGHT_FINISH, 3000, 0.39);
				} else if (start.equals("L") &&  cubes == 1) {
//					m_autonomousCommand = new SingleCube(FROM_LEFT_PORTAL.SWITCH_LEFT_TRAVEL, FROM_LEFT_PORTAL.SWITCH_LEFT_FINISH, 969, 0.3069);
					m_autonomousCommand = new SingleCube(FROM_LEFT_PORTAL.SCALE_RIGHT_TRAVEL, FROM_LEFT_PORTAL.SCALE_RIGHT_FINISH, 3000, 0.369);
				} else if (start.equals("C")) {
					m_autonomousCommand = new CenterSwitchAutoTwoCube(FROM_CENTER.SWITCH_LEFT, FROM_CENTER.LEFT_SECOND_CUBE_FORWARD, FROM_CENTER.LEFT_SECOND_CUBE_BACKWARDS);
				}
				break;
				
		}
			

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putBoolean("isCubePresent", Robot.claw.isCubePresent());
		SmartDashboard.putNumber("Drive Left Encoder", Robot.drive.getLeftEncoder());
		SmartDashboard.putNumber("Drive right encoder", Robot.drive.getRightEncoder());
		SmartDashboard.putNumber("Yaw", Robot.drive.getYaw());
		SmartDashboard.putNumber("Elevator encoder", Robot.elevator.getPosition());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("isCubePresent", Robot.claw.isCubePresent());
		SmartDashboard.putNumber("Drive Left Encoder", Robot.drive.getLeftEncoder());
		SmartDashboard.putNumber("Drive right encoder", Robot.drive.getRightEncoder());
		SmartDashboard.putNumber("Elevator encoder", Robot.elevator.getPosition());
		SmartDashboard.putBoolean("Brake", Robot.elevator.getBrake());
		
//		System.out.println("\t\t\t\t\t\telev enc = "+elevator.getPosition());
//		System.out.println("\t\t\t\t\t\tis at bottom? "+elevator.isAtBottom());
		
//		System.out.println("DT Left= " + Robot.drive.getLeftEncoder());
//		System.out.println("DT Right= " + Robot.drive.getRightEncoder());
//		SmartDashboard.putNumber("Yaw", Robot.drive.getYaw());
//		System.out.println("IMU= " + Robot.drive.getYaw());
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
