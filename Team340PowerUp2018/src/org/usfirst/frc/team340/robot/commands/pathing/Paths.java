package org.usfirst.frc.team340.robot.commands.pathing;

public class Paths {
	public static class FROM_CENTER {
		public static final Path SWITCH_RIGHT = new Path(
			new PathSegment(t -> 
			/* {"start":{"x":0,"y":50},"mid1":{"x":46,"y":48},"mid2":{"x":51,"y":109},"end":{"x":112,"y":108}} */
			(-6 + 378 * t + -375 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
			, 131));
		public static final Path SWITCH_LEFT = new Path(
				new PathSegment(t -> 
				/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":99},"mid2":{"x":51,"y":30},"end":{"x":112,"y":32}} */
				(-3 + -408 * t + 417 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)) 
				, 137));
	}
	
	public static class FROM_RIGHT {
		public static final Path SCALE_RIGHT = new Path(
				new PathSegment(t -> 
				/* {"start":{"x":0,"y":100},"mid1":{"x":45,"y":97},"mid2":{"x":67,"y":150},"end":{"x":203,"y":150}} */
				(-9 + 336 * t + -327 * Math.pow(t, 2))/ (135 + -138 * t + 411 * Math.pow(t, 2)) 
				, 212));
		public static final Path SCALE_RIGHT_FINISH = new Path(
				new PathSegment(t -> 
				/* {"start":{"x":0,"y":100},"mid1":{"x":19,"y":102},"mid2":{"x":42,"y":85},"end":{"x":60,"y":76}} */
				(6 + -114 * t + 81 * Math.pow(t, 2))/ (57 + 24 * t + -27 * Math.pow(t, 2)) 
				, 66)
				);  
		public static final Path SWITCH_RIGHT = new Path(
			new PathSegment(t -> 
			/* {"start":{"x":0,"y":100},"mid1":{"x":51,"y":96},"mid2":{"x":25,"y":142},"end":{"x":144,"y":136}} */
			(-12 + 300 * t + -306 * Math.pow(t, 2))/ (153 + -462 * t + 666 * Math.pow(t, 2)) 
			, 153)
		);
		public static final Path SWITCH_RIGHT_FINISH = new Path(
				new PathSegment(t -> 
				/* {"start":{"x":0,"y":100},"mid1":{"x":20,"y":90},"mid2":{"x":24,"y":86},"end":{"x":24,"y":64}} */
				(-30 + 36 * t + -72 * Math.pow(t, 2))/ (60 + -96 * t + 36 * Math.pow(t, 2)) 
				, 48)
			);
	}
	
	public static Object choose(String fms, int pos, Object left, Object right) {
		if(fms.substring(pos, pos + 1).toLowerCase().equals("l")) {
			return left;
		} else {
			return right;
		}
	}
}
