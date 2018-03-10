package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.claw.ClawShootScore;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawOpen;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterSwitchAutoFast extends CommandGroup {
	
    public CenterSwitchAutoFast(Path path) {
    	addSequential(new ManualClawClose(), 0.1);
    	addSequential(new ManualElevatorTiltForward(), 0.5);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new RunPath(path, x -> {
    		if(x < 0.15) {
    			return 0.7;
    		} else if (x < 0.85) {
    			return 0.80;
    		} else {
    			return 0.15;
    		}
    	}), 4.5);
    	addSequential(new WaitCommand(0.1));
    	addSequential(new ClawShootScore(0.4), 1);
    	addSequential(new WaitCommand(1.0));
    	addSequential(new ManualClawOpen());
    	addSequential(new RunPath(Paths.straightLength(40), -0.3), 2.5);
    	addSequential(new ManualClawClose());
    	addSequential(new ElevatorGoToBottom(), 1.5);
    }
}
