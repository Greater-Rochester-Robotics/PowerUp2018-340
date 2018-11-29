package org.usfirst.frc.team340.robot.commands.drive;

import org.usfirst.frc.team340.robot.OI.Axis;
import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <h1><i>Drive</h1> (Limited Time Command Edition)</i><br>
 * <br>
 * Allows the robo-guy to go <b>nyoom</b> around the not-called-a-field-this-year.<br>
 * <i>This command will be implemented at a later time<i>
 */
public class DriveController extends Command {
	private final double moveSlowScale = .5;
	private final double turnSlowScale = .5;

	/**
	 * Takes the drivebase and uses it to drive. Pretty self-explanatory, I suppose
	 */
    public DriveController() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setBothDrive(0); //Let's not be moving before we start trying to move
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    if(/*Math.abs(Robot.oi.getDriverAxis(Axis.LEFT_X)) > 0.1 || //At least 10% away from center of left X
    			Math.abs(Robot.oi.getDriverAxis(Axis.LEFT_Y)) > 0.1) { //At least 10% away from center of left Y
    			Robot.drive.arcadeDrive(Robot.oi.getDriverAxis(Axis.LEFT_Y), //Movement speed
    					Robot.oi.getDriverAxis(Axis.LEFT_X)); //Rotation speed
    	} else if(*/Math.abs(Robot.oi.getDriverAxis(Axis.RIGHT_X)) > 0.1 || //At least 10% away from center of right X
    			Math.abs(Robot.oi.getDriverAxis(Axis.RIGHT_Y)) > 0.1) { //At least 10% away from center of right Y
    			Robot.drive.arcadeDrive(Robot.oi.getDriverAxis(Axis.RIGHT_Y) * moveSlowScale,  //Move speed (slowed)
    					Robot.oi.getDriverAxis(Axis.RIGHT_X) * turnSlowScale); //Rotation speed (slowed) 
    	} else {
    		
    		Robot.drive.arcadeDrive(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setBothDrive(0); //If we shant be driving, then stop driving
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end(); //On the off-chance that this command gets killed prematurely, revert to safe state
    }
}
