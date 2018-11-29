package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FarSideNoScore extends CommandGroup {

    public FarSideNoScore(Path crossShortPath) {
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
    	addSequential(new ManualClawClose());
    	addSequential(new RunPath(crossShortPath, x->{
    		if(x < 0.1) {
    			return 0.5;
    		} else if (x < 0.85) {
    			return 0.69;
    		} else {
    			return 0.30;
    		}
    	}), 10);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new AutoUnStart());
    }
}
