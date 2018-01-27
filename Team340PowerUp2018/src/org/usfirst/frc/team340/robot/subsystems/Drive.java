package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	private static Talon driveRight;
	private static Talon driveLeft;
	
	public Drive () {
    	driveRight = new Talon(RobotMap.DRIVE_TALONSR_RIGHT_CHANNEL);
    	driveLeft = new Talon(RobotMap.DRIVE_TALONSR_LEFT_CHANNEL);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void arcadeDrive(double speed, double rSpeed) {
		
	}
}