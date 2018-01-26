package org.usfirst.frc.team340.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team340.robot.commands.*;
/**
 *
 */
public class Travel extends CommandGroup {

    public Travel() {
    	addSequential(new CloseClaw());
    	addSequential(new ClawGoAbovePosition());
    }
}
