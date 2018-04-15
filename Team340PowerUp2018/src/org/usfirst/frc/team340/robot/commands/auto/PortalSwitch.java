package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.claw.ClawAcquireCube;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawOpen;
import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class PortalSwitch extends CommandGroup {

    public PortalSwitch(Path firstCube, Path backwardsPath, Path thirdPath) {
    	addSequential(new CenterSwitchAutoFast(firstCube, x -> {
    		if (x < 0.15) {
    			return 0.4;
    		} else if (x < 0.8) {
    			return 0.85;
    		} else {
    			return 0.2;
    		}
    	}, 4.2), 10);
    	addSequential(new RunPath(backwardsPath, x -> {
    		return -CenterSwitchAutoFast.defaultSpeed.apply(x);
    	}), 2.9);
    	addParallel(new ManualClawOpen());
    	addSequential(new ElevatorGoToBottom());
    	addSequential(new RunPath(Paths.straightLength(50), 0.65));
    	addParallel(new ClawAcquireCube());
    	addSequential(new WaitCommand(0.25));
    	addSequential(new RunPath(Paths.straightLength(50), -0.6), 1.8);
    	addSequential(new ElevatorGoToPosition(1400));
    	addSequential(new CenterSwitchAutoFast(thirdPath));
    }
}
