package org.usfirst.frc.team340.robot.commands.pathing;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team340.robot.commands.CommandConsumer;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An animation has keyframes (robot motions, in the form of commands) that occur at certain points (distances along a path)
 * @author Ryan
 *
 */
public class Animation extends ArrayList<Keyframe>{
	
	public Animation(Keyframe... keyframes) {
		super(Arrays.asList(keyframes));
	}
	public void animate(double position) {
		for(Keyframe kf : this) {
			kf.animate(position);
		}
	}
}
