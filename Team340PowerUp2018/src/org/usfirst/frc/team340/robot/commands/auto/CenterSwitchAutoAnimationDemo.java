package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.claw.ClawShootScore;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToBottom;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawOpen;
import org.usfirst.frc.team340.robot.commands.manual.ManualElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.pathing.Animation;
import org.usfirst.frc.team340.robot.commands.pathing.Keyframe;
import org.usfirst.frc.team340.robot.commands.pathing.Path;
import org.usfirst.frc.team340.robot.commands.pathing.Paths;
import org.usfirst.frc.team340.robot.commands.pathing.RunPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterSwitchAutoAnimationDemo extends CommandGroup {

	private final Animation shotgunAndRaise = new Animation(
			new Keyframe(new ManualClawClose(), 0.0),
			new Keyframe(new ManualElevatorTiltForward(), 0.03),
			new Keyframe(new ElevatorGoToBottom(), 0.1),
			new Keyframe(new ElevatorGoToPosition(969), 0.7)
			);
	
    public CenterSwitchAutoAnimationDemo(Path path) {
    	/* The following commands have been replaced by the animation: 
    	 
    	addSequential(new ManualClawClose(), 0.1);
    	addSequential(new ManualElevatorTiltForward(), 0.5);
    	addSequential(new WaitCommand(0.2));
    	addSequential(new ElevatorGoToBottom(), 1.5);
    	addSequential(new WaitCommand(0.15));
    	addParallel(new ElevatorGoToPosition(969), 1);
    		
    	*/
    	
    	addSequential(new RunPath(path, x -> {
    		if(x < 0.15) {
    			return 0.7;
    		} else if (x < 0.85) {
    			return 0.80;
    		} else {
    			return 0.15;
    		}
    	}, shotgunAndRaise), 4.5);
    	
    	addSequential(new WaitCommand(0.1));
    	addSequential(new ClawShootScore(0.60), 1);
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ManualClawOpen());
    	addSequential(new WaitCommand(0.2));
    	addSequential(new RunPath(Paths.straightLength(85), -0.4), 2.5);
    	addSequential(new ManualClawClose());
    	addSequential(new ElevatorGoToBottom(), 1.5);
    }
}
