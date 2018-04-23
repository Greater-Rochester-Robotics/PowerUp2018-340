package org.usfirst.frc.team340.robot.commands.auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForGameData extends Command {
	private char robotSide;
	private char switchSide;
	private static int checks = 0;
	
    public WaitForGameData(char side) {
    	robotSide = side;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switchSide = DriverStation.getInstance().getGameSpecificMessage().toCharArray()[0];
    	checks++;
    	
    	if(robotSide == switchSide) {
    		System.out.println("Stuff good. Checks: " + checks);
    	} else {
    		System.out.println("Problems - robot @ " + robotSide + ", switch @ " + switchSide + "on check #" + checks);
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
