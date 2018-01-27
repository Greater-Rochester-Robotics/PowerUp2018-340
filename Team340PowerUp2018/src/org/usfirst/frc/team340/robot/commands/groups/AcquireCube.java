package org.usfirst.frc.team340.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.*;
/**
 *
 */
public class AcquireCube extends CommandGroup {

    public AcquireCube() {
    	addSequential(new ClawNeutral());
    	addSequential(new ClawAcquireCube());
    	addSequential(new ClawAlignCube(),RobotMap.CLAW_ALIGNMENT_WAIT_S);
    	addSequential(new ClawSecureCube());
    }
}
