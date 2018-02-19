package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends Command {

	private final double P = 10;
	private double speed = 0.0;
	private double angle = 0.0;
	
    public TurnToAngle(double angle, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.speed = speed;
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetIMU();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = angle - Robot.drive.getYaw();
    	double setSpeed = error / (P / Math.abs(speed));
    	if (setSpeed > 1) {
    		setSpeed = 1;
    	}
    	if (setSpeed < -1) {
    		setSpeed = -1;
    	}
    	SmartDashboard.putNumber("setspeed", setSpeed);
    	Robot.drive.setBothDrive(-setSpeed, setSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(angle - Robot.drive.getYaw()) < 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("DONE");
    	Robot.drive.setBothDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.setBothDrive(0);
    }
}
