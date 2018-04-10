package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.claw.ClawAcquireCube;
import org.usfirst.frc.team340.robot.commands.claw.ClawAcquireCubeOpenClaw;
import org.usfirst.frc.team340.robot.commands.claw.ClawShootScore;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawOpen;
import org.usfirst.frc.team340.robot.commands.pathing.Animation;
import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterSwitchAutoTwoCube extends CommandGroup {
	
    public CenterSwitchAutoTwoCube(Path firstCube, Path aquireCube, Path backUpFromSecond) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new CenterSwitchAutoFast(firstCube), 10);
    	addParallel(new ClawAcquireCubeOpenClaw());
    	addSequential(new RunPath(aquireCube, 0.35), 3);
    	addSequential(new ClawAcquireCube(0.85), 1.0);
    	addSequential(new WaitCommand(0.3));
    	addSequential(new ClawAcquireCube(1.0), 1.0);
    	addSequential(new ManualClawClose());
    	addSequential(new WaitCommand(0.25));
    	addParallel(new ClawAcquireCube(0.2), 1.0);
    	addSequential(new RunPath(backUpFromSecond, -0.5));
    	addSequential(new WaitCommand(0.3));
    	addParallel(new ElevatorGoToPosition(969), 1.5);
    	addSequential(new RunPath(Paths.straightLength(88), 0.5069), 2.69);
    	addSequential(new ClawShootScore(0.3069), 1);
    	addSequential(new WaitCommand(1.0));
    	addSequential(new ManualClawOpen());
    }
}
