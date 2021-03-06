package org.usfirst.frc.team340.robot.commands.manual;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualClawWheelsOut extends Command {

    public ManualClawWheelsOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("[" + getClass().getName() + "] -Initailizing-");
    	Robot.claw.spinWheelsOut(RobotMap.CLAW_WHEEL_FULLSPEED_VBUS);
    	System.out.println("[" + getClass().getName() + "] Claw wheels spun out");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("[" + getClass().getName() + "] -Executing-");
    	System.out.println("[" + getClass().getName() + "] Claw left wheels @ " + Robot.claw.getLeftSRX().get());
    	System.out.println("[" + getClass().getName() + "] Claw right wheels @ " + Robot.claw.getRightSRX().get());
    	System.out.println("[" + getClass().getName() + "] Claw status: " + Robot.claw.getClawStatus());
    	System.out.println("[" + getClass().getName() + "] Claw cube status: " + Robot.claw.isCubePresent());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("[" + getClass().getName() + "] -Ending-");
    	Robot.claw.stopWheels();
    	System.out.println("[" + getClass().getName() + "] Claw wheels stopped");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("[" + getClass().getName() + "] -Interrupting-");
    	end();
    }
}
