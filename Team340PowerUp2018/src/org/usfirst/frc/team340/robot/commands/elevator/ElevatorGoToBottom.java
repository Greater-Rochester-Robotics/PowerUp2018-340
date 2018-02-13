package org.usfirst.frc.team340.robot.commands.elevator;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorGoToBottom extends Command {

    public ElevatorGoToBottom() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.goDown(RobotMap.ELEVATOR_GO_BOTTOM_VBUS);
    	Robot.elevator.setTiltForward();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("pos = " + Robot.elevator.getPosition() + ", state = " + Robot.elevator.isAtBottom() + ", speed = " + Robot.elevator.getSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.isAtBottom();
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
