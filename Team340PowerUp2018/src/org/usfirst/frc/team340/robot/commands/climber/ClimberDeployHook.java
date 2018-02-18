package org.usfirst.frc.team340.robot.commands.climber;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberDeployHook extends Command {
    public ClimberDeployHook() {
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.deploy();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
}
