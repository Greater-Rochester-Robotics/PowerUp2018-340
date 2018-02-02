package org.usfirst.frc.team340.robot.commands.elevator;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorGoDown extends Command {

    public ElevatorGoDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.goDown(1);
    	Robot.elevator.setTiltForward();
    	Robot.elevator.getPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
