package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.PathSegment;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLineAuto extends CommandGroup {

    public CrossLineAuto() {
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
    	
    	// little dodge to the right so we don't drive into power cubes if we're in the center
    	addSequential(new RunPath(new Path(
    			new PathSegment(t -> 
    			/* {"start":{"x":0,"y":160},"mid1":{"x":17,"y":161},"mid2":{"x":11,"y":180},"end":{"x":30,"y":180}} */
    			(3 + 108 * t + -111 * Math.pow(t, 2))/ (51 + -138 * t + 144 * Math.pow(t, 2)) 
    			, 39)
    			), 0.3));
    	addSequential(new RunPath(Paths.straightLength(120), 0.4));
    	addSequential(new AutoUnStart());
    }
}
