package org.usfirst.frc.team340.robot.commands.claw;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawAcquireCube extends Command {

	private int goodSamples = 0;
	private double speed = RobotMap.CLAW_WHEEL_ACQUIRE_SPEED_VBUS;
    public ClawAcquireCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(RobotMap.CLAW_WHEEL_ACQUIRE_SPEED_VBUS);
    }
    public ClawAcquireCube(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    	goodSamples = 0;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("CLAW ACQUIRE CUBE STARTING");
    	Robot.claw.setRedLEDs(true);
    	Robot.claw.setGreenLEDs(false);
    	Robot.claw.spinWheelsIn(speed);
    	Robot.claw.neutral();
    	goodSamples = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.claw.spinWheelsIn(speed);
//    	Robot.claw.neutral();
    	if(Robot.claw.isCubePresent()) {
//    		Robot.claw.setRedLEDs(false);
        	Robot.claw.setGreenLEDs(true);
//    		Robot.claw.close();
    		
    		goodSamples += Robot.claw.getNumCubeSensors();
    	} else {
    		goodSamples = 0;
    	}
    	if(goodSamples > 3) {
    		Robot.claw.close();
    	}
    	if(goodSamples > 20) {
    		Robot.claw.setRedLEDs(false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return goodSamples >= 30;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.setRedLEDs(false);
    	Robot.claw.setGreenLEDs(false);
    	Robot.claw.close();
    	Robot.claw.stopWheels();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
