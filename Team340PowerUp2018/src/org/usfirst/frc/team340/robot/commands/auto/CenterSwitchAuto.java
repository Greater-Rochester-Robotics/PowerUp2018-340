package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.claw.ClawDropScore;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;
import org.usfirst.frc.team340.robot.commands.pathing.Paths.FROM_CENTER;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterSwitchAuto extends CommandGroup {

    public CenterSwitchAuto() {
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
    	addSequential(new ManualClawClose(), 0.1);
    	addSequential(new ManualElevatorTiltForward(), 0.5);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ElevatorGoToBottom(), 1.5);
    	addSequential(new WaitCommand(0.5));
    	addParallel(new ElevatorGoToPosition(1169), 1);
    	addSequential(new RunPath(Paths.choosePath("RLR", 0, FROM_CENTER.SWITCH_LEFT, FROM_CENTER.SWITCH_RIGHT), x -> {
    		if(x < 0.3) {
    			return 0.3;
    		} else if (x < 0.75) {
    			return 0.5069;
    		} else {
    			return 0.25;
    		}
    	}), 4.5);
    	
    	addSequential(new ClawDropScore(), 1);
    }
}
