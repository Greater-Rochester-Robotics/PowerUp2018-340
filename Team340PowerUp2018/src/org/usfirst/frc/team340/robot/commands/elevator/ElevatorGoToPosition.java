package org.usfirst.frc.team340.robot.commands.elevator;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorGoToPosition extends Command {
	private int pos;
	
    public ElevatorGoToPosition(int position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	pos = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.setBrakeDisengaged();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(timeSinceInitialized() > 0.5) {
    		Robot.elevator.setPosition(pos);
//    	}
    	System.out.println("pos = " + Robot.elevator.getPosition() + ", state = " + Robot.elevator.isAtBottom() + ", speed = " + Robot.elevator.getSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.isAtToleratedPosition(pos);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
