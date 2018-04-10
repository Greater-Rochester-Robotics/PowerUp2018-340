package org.usfirst.frc.team340.robot.commands.elevator;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorResetEncoderToStarting extends Command {

	/**
	 * Run this if the robot is starting in its starting configuration.
	 */
    public ElevatorResetEncoderToStarting() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.setEncoderPosition(RobotMap.ELEVATOR_STARTING_POSITION_HEIGHT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.setEncoderPosition(RobotMap.ELEVATOR_STARTING_POSITION_HEIGHT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.setEncoderPosition(RobotMap.ELEVATOR_STARTING_POSITION_HEIGHT);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.setEncoderPosition(RobotMap.ELEVATOR_STARTING_POSITION_HEIGHT);
    }
}
