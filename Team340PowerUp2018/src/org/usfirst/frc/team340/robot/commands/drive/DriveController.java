package org.usfirst.frc.team340.robot.commands.drive;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <h1><i>Drive</h1> (Limited Time Command Edition)</i><br>
 * <br>
 * Allows the robo-guy to go <b>nyoom</b> around the not-called-a-field-this-year.<br>
 * <i>This command will be implemented at a later time<i>
 */
public class DriveController extends Command {

	/**
	 * Takes the drivebase and uses it to drive. Pretty self-explanatory, I suppose
	 */
    public DriveController() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
