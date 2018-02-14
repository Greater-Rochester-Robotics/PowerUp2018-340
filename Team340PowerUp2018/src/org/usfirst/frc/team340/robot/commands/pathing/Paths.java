package org.usfirst.frc.team340.robot.commands.pathing;

public class Paths {
	public static class FROM_CENTER {
		public static final Path SWITCH_RIGHT = new Path(
				new PathSegment(t -> 
				(0 + 366 * t + -378 * Math.pow(t, 2))/ (153 + -324 * t + 342 * Math.pow(t, 2))
				, 124.9));
		public static final Path SWITCH_LEFT = new Path(
				new PathSegment(t -> 
				(-42 + -192 * t + 264 * Math.pow(t, 2))/ (153 + -324 * t + 342 * Math.pow(t, 2))
				, 121.5));
	}
	
	public static Path choosePath(String fms, int pos, Path left, Path right) {
		if(fms.substring(pos, pos + 1).toLowerCase().equals("l")) {
			return left;
		} else {
			return right;
		}
	}
}
