package org.usfirst.frc.team340.robot.commands.auto;

import java.util.function.Function;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.commands.claw.ClawShootScore;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.Paths.FROM_RIGHT;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightSideEasyAuto extends CommandGroup {
	private final Function<Double, Double> speedFast = x -> {
		if(x < 0.1) {
			return 0.3;
		} else if (x < 0.85) {
			return 0.6969;
		} else {
			return 0.25;
		}
	};
	private final Function<Double, Double> speedSlow = x -> {
		if(x < 0.3) {
			return 0.3;
		} else if (x < 0.75) {
			return 0.4;
		} else {
			return 0.25;
		}
	};
	
	private final String FMS = "LLL";
	
    public RightSideEasyAuto() {
    	addSequential(new ManualClawClose(), 0.1);
    	addSequential(new ManualElevatorTiltForward(), 0.5);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ElevatorGoToBottom(), 1.5);
    	addSequential(new WaitCommand(0.5));
    	addParallel(new ElevatorGoToPosition(300), 1);
    	addSequential(new RunPath(Paths.choose(FMS, 1, 
    			Paths.choose(FMS, 0, FROM_RIGHT.SWITCH_LEFT /*left switch path*/, FROM_RIGHT.SWITCH_RIGHT)
    			, FROM_RIGHT.SCALE_RIGHT), speedFast), 9.5);
    	addSequential(new WaitCommand(0.5));
    	addParallel(new ElevatorGoToPosition((int) Robot.choose(FMS, 1, 969, 3000)));
    	addSequential(new RunPath(Paths.choose(FMS, 1, 
    			Paths.choose(FMS, 0, FROM_RIGHT.SCALE_LEFT_FINISH /* left switch path*/, FROM_RIGHT.SWITCH_RIGHT_FINISH)
    			, FROM_RIGHT.SCALE_RIGHT_FINISH), speedSlow), 3.5);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ClawShootScore((double) Robot.choose(FMS, 1, 0.3369, 1.0)), 1);
    }
}
