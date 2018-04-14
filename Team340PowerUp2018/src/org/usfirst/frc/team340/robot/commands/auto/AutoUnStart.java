package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.commands.elevator.ElevatorTiltForward;
import org.usfirst.frc.team340.robot.commands.manual.ManualClawClose;
import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorGoToPosition;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorResetEncoderToStarting;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoUnStart extends CommandGroup {

    public AutoUnStart(int height) {
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
    	addSequential(new ElevatorResetEncoderToStarting());
    	addSequential(new WaitCommand(0.25));
    	addSequential(new ElevatorTiltForward());
    	addSequential(new WaitCommand(0.69));
    	addSequential(new ElevatorGoToPosition(height));
    }
    
    public AutoUnStart() {
    	this(RobotMap.ELEVATOR_STARTING_POSITION_HEIGHT);
    }
}
