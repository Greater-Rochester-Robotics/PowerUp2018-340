package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class testautomovement extends CommandGroup {

    public testautomovement() {
//    	addParallel(new ElevatorGoToBottom(), 2);
    	addSequential(new RunPath(Paths.straightLength(18), -0.3), 2.5);
    	addSequential(new ElevatorGoToBottom(), 2);
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
    }
}
