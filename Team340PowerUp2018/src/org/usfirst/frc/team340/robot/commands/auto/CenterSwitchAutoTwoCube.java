package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.claw.ClawAcquireCube;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawOpen;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawWheelsIn;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawWheelsStop;
import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterSwitchAutoTwoCube extends CommandGroup {
	
	public CenterSwitchAutoTwoCube(Path firstCube, Path backwardsPath) {
		this(firstCube, backwardsPath, firstCube);
	}
	
    public CenterSwitchAutoTwoCube(Path firstCube, Path backwardsPath, Path thirdPath) { 	
//    	addSequential(new CenterSwitchAutoFast(firstCube), 10);
//    	addParallel(new ClawAcquireCubeOpenClaw());
//    	addSequential(new RunPath(aquireCube, 0.35), 3);
//    	addSequential(new ClawAcquireCube(0.85), 1.0);
//    	addSequential(new WaitCommand(0.3));
//    	addSequential(new ClawAcquireCube(1.0), 1.0);
//    	addSequential(new ManualClawClose());
//    	addSequential(new WaitCommand(0.25));
//    	addParallel(new ClawAcquireCube(0.2), 1.0);
//    	addSequential(new RunPath(backUpFromSecond, -0.5));
//    	addSequential(new WaitCommand(0.3));
//    	addParallel(new ElevatorGoToPosition(969), 1.5);
//    	addSequential(new RunPath(Paths.straightLength(88), 0.5069), 2.69);
//    	addSequential(new ClawShootScore(0.3069), 1);
//    	addSequential(new WaitCommand(1.0));
//    	addSequential(new ManualClawOpen());
    	
    	
    	addSequential(new CenterSwitchAutoFast(firstCube), 10);
    	addSequential(new RunPath(backwardsPath, x -> {
    		return -CenterSwitchAutoFast.defaultSpeed.apply(x);
    	}), 3.2);
//    	addSequential(new RunPath(Paths.straightLength(8), -0.5), 0.5);
    	addSequential(new ManualClawOpen(), 0.5);
    	addParallel(new ElevatorGoToBottom());
    	addParallel(new ManualClawWheelsIn());
    	addSequential(new RunPath(Paths.straightLength(55), 0.5));
    	addSequential(new ManualClawClose(), 0.5);
    	addSequential(new ManualClawWheelsStop(), 0.5);
    	addSequential(new WaitCommand(0.15));
    	addSequential(new ClawAcquireCube(), 1.25);
    	addSequential(new WaitCommand(0.15));
    	addSequential(new ElevatorGoToPosition(169)	, 0.8);
    	addParallel(new ElevatorGoToPosition(1400));
    	addSequential(new RunPath(Paths.straightLength(50), -0.6), 2);
    	addSequential(new CenterSwitchAutoFast(thirdPath));
    }
}
