package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitSwitch extends Command {
	private char robotSide;
	private char switchSide;
	
    public WaitSwitch(char side) {
    	robotSide = side;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switchSide = DriverStation.getInstance().getGameSpecificMessage().toCharArray()[0];
    	
    	if(robotSide == switchSide) {
    		System.out.println("Stuff good");
    	} else {
    		System.out.println("Problems");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return robotSide == switchSide;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
