package org.usfirst.frc.team340.robot.commands.auto;

import java.util.function.Function;

import org.usfirst.frc.team340.robot.commands.claw.ClawShootScore;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawOpen;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterSwitchAutoFast extends CommandGroup {
	public static Function<Double, Double> defaultSpeed = x -> {
		if (x < 0.15) {
			return 0.4;
		} else if (x < 0.75) {
			return 0.8;
		} else {
			return 0.15;
		}
	};
	public CenterSwitchAutoFast(Path path) {
		this(path, defaultSpeed);
	}
	
    public CenterSwitchAutoFast(Path path, Function<Double, Double> speed) {
    	this(path, speed, 2.95);
    }
    
    public CenterSwitchAutoFast(Path path, Function<Double, Double> speed, double initialTimeout) {

    	addSequential(new ManualClawClose(), 0.05);
    	addParallel(new ManualElevatorTiltForward(), 0.5);
//    	addSequential(new WaitCommand(0.5));
    	
    	
//    	addSequential(new RunPath(path, x -> {
//    		if(x < 0.15) {
//    			return 0.6;
//    		} else if (x < 0.85) {
//    			return 0.75;
//    		} else {
//    			return 0.15;
//    		}
//    	}), 4.5);
    	addSequential(new RunPath(path, speed), initialTimeout);
//    	addSequential(new WaitCommand(0.1));
    	addSequential(new ClawShootScore(0.33), 0.2);
//    	addSequential(new WaitCommand(0.45));
    	addSequential(new ManualClawOpen());
    	addSequential(new WaitCommand(0.1));
//    	addSequential(new RunPath(Paths.straightLength(10), -0.9), 0.5);
//    	addParallel(new ElevatorGoToBottom(), 1.5);
//    	addSequential(new RunPath(Paths.straightLength(73), -0.4), 2.5);
//    	addSequential(new ManualClawClose());
    }
}
