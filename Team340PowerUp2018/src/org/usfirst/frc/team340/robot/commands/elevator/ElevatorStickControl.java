package org.usfirst.frc.team340.robot.commands.elevator;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.OI.Axis;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorStickControl extends Command {

	private double brakeTime = 0.0;
	private boolean stopping = false;
	
    public ElevatorStickControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	brakeTime = 0.0;
    	stopping = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	brakeTime = 0.0;
    	stopping = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		
		double speed = -Robot.oi.getCoDriverAxis(Axis.LEFT_Y);
    	
		if (speed < 0) {
			stopping = false;
			speed *= 0.60;
			if(Robot.elevator.isAtBottom()) {
				speed = 0;
			} if(Robot.elevator.getPosition() < 300) {
				speed *= 0.50;
			}
		} else {
			speed *= 0.60;
			if(Robot.elevator.getPosition() > 3000 || stopping) {
				speed = 0;
				stopping = true;
			} else if(Robot.elevator.getPosition() > 2700) {
				speed *= 0.50;
			} else {
				stopping = false;
			}
		}	
		
    	Robot.elevator.talonA.set(speed);
		
		if (speed == 0) {
//			Robot.elevator.setBrakeEngaged();
			
			if (Robot.oi.getCoDriverAxis(Axis.LEFT_Y) >= 0) {
				Robot.elevator.setBrakeEngaged();
			} else if (timeSinceInitialized() - brakeTime > 0.4) {
				Robot.elevator.setBrakeEngaged();
			}
			
		} else {
			brakeTime = timeSinceInitialized();
			Robot.elevator.setBrakeDisengaged();
		}
		
//		System.out.println("\n\nTalon: " + Robot.elevator.talonA.get());
//		System.out.println("Joystick: " + Robot.oi.getCoDriverAxis(Axis.LEFT_Y));
//		System.out.println("Encoder: " + Robot.elevator.getPosition());
//		System.out.println("speed: " + speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
