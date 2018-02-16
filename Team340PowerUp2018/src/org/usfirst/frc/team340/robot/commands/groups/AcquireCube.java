package org.usfirst.frc.team340.robot.commands.groups;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.claw.ClawAcquireCube;
import org.usfirst.frc.team340.robot.commands.claw.ClawAlignCube;
import org.usfirst.frc.team340.robot.commands.claw.ClawNeutral;
import org.usfirst.frc.team340.robot.commands.claw.ClawSecureCube;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class AcquireCube extends CommandGroup {

    public AcquireCube() {
    	addSequential(new ClawNeutral());
    	addSequential(new ClawAcquireCube());
    	addSequential(new ClawAlignCube(), RobotMap.CLAW_ALIGNMENT_WAIT_S);
    	addSequential(new ClawSecureCube());
    }
}
