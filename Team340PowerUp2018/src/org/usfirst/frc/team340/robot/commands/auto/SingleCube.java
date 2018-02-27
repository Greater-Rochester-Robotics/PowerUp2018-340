package org.usfirst.frc.team340.robot.commands.auto;

import java.util.function.Function;

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
public class SingleCube extends CommandGroup {
	private final Function<Double, Double> speedFast = x -> {
		if(x < 0.1) {
			return 0.35;
		} else if (x < 0.85) {
			return 0.7569;
		} else {
			return 0.25;
		}
	};
	private final Function<Double, Double> speedSlow = x -> {
		if(x < 0.3) {
			return 0.3;
		} else if (x < 0.75) {
			return 0.35;
		} else {
			return 0.25;
		}
	};
	
    public SingleCube(Path travelPath, Path finishPath, int elevatePosition, double shootSpeed) {
    	addSequential(new ManualClawClose(), 0.1);
    	addSequential(new ManualElevatorTiltForward(), 0.5);
    	addSequential(new WaitCommand(0.35));
    	addSequential(new ElevatorGoToBottom(), 1.5);
//    	addSequential(new WaitCommand(0.5));
    	addParallel(new ElevatorGoToPosition(300), 1);
    	addSequential(new RunPath(travelPath, speedFast), 9.5);
    	addSequential(new WaitCommand(0.25));
    	addParallel(new ElevatorGoToPosition(elevatePosition));
    	addSequential(new RunPath(finishPath, speedSlow), 3.5);
    	addSequential(new WaitCommand(0.25));
    	addSequential(new ClawShootScore(shootSpeed), 1);
    }
}
