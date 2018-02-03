package org.usfirst.frc.team340.robot.commands.manual;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualElevatorUp extends Command {

    public ManualElevatorUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("[" + getClass().getName() + "] -Initializing-");
    	Robot.elevator.goUp(RobotMap.ELEVATOR_FULLSPEED_VBUS);
    	System.out.println("[" + getClass().getName() + "] Elevator raising at " + RobotMap.ELEVATOR_FULLSPEED_VBUS + "% speed");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("[" + getClass().getName() + "] -Executing-");
    	System.out.println("[" + getClass().getName() + "] Elevator at position " + Robot.elevator.getPosition());
    	System.out.println("[" + getClass().getName() + "] Elevator brake set to " + Robot.elevator.getBrake());
    	System.out.println("[" + getClass().getName() + "] Elevator bottom switch engaged? " + Robot.elevator.isAtBottom());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("[" + getClass().getName() + "] -Ending-");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("[" + getClass().getName() + "] -Interrupting-");
    	end();
    }
}
