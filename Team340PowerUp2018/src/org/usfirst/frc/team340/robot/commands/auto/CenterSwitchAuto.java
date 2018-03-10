package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.claw.ClawShootScore;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterSwitchAuto extends CommandGroup {
	
    public CenterSwitchAuto(Path path) {
    	addSequential(new ManualClawClose(), 0.1);
    	addSequential(new ManualElevatorTiltForward(), 0.5);
    	addSequential(new WaitCommand(0.2));
    	addSequential(new ElevatorGoToBottom(), 1.5);
    	addSequential(new WaitCommand(0.15));
    	addParallel(new ElevatorGoToPosition(969), 1);	
    	/*addSequential(new RunPath(Paths.choose(Robot.FMSData(), 0, FROM_CENTER.SWITCH_LEFT, FROM_CENTER.SWITCH_RIGHT), x -> {
    		if(x < 0.3) {
    			return 0.3;
    		} else if (x < 0.75) {
    			return 0.5069;
    		} else {
    			return 0.25;
    		
    	}), 4.5);*/
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
    	addSequential(new ClawShootScore(0.60), 1);
    }
}
