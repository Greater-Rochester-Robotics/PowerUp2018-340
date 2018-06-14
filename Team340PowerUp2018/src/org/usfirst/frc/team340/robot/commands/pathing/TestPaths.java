package org.usfirst.frc.team340.robot.commands.pathing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.usfirst.frc.team340.robot.commands.pathing.PathSegment.Vec2;

class TestPaths {

	@SuppressWarnings("deprecation")
	private Path oldStylePath = new Path(new PathSegment(t -> 
		/* {"start":{"x":20,"y":86},"mid1":{"x":107,"y":33},"mid2":{"x":353,"y":39},"end":{"x":227,"y":94}} */
		(-159 + 354 * t + -30 * Math.pow(t, 2))/ (261 + 954 * t + -1593 * Math.pow(t, 2)) 
		, 300));
	
	private final PathSegment newStyleSegment = new PathSegment( 
			/* {"start":{"x":20,"y":86},"mid1":{"x":107,"y":33},"mid2":{"x":353,"y":39},"end":{"x":227,"y":94}} */
			new Vec2(20, 86), new Vec2(107, 33), new Vec2(353, 39), new Vec2(227, 94) 
			, 300);
	
	private Path newStylePath = new Path(newStyleSegment);
	@SuppressWarnings("deprecation")
	private Path newStylePathWithStraight = new Path(new PathSegment(t -> {return 0.0;}, 100), newStyleSegment);
	
	private final int STRAIGHT_LENGTH = 100;
	private final int CURVE_LENGTH = 300;
	
	private final double EPSILON = 0.000001;
	
	public static double dydx(Path path, double s) {
		PathSegment segment = path.getPathAtDistance(s);
		return segment.getDerivative().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
	}
	
	public static double curvature(Path path, double s) {
		PathSegment segment = path.getPathAtDistance(s);
		System.out.println("s: " + (s - path.getTotalOfCompletedPaths(s))/segment.getLength());
		return segment.getCurvature().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
	}
	
	@Test
	void TestConstruction() {
		assertNotNull(oldStylePath);
		assertNotNull(oldStylePath.getTotalLength());
		assertEquals(oldStylePath.getTotalLength(), CURVE_LENGTH);
		
		assertNotNull(newStylePath);
		assertNotNull(newStylePath.getTotalLength());
		assertEquals(newStylePath.getTotalLength(), CURVE_LENGTH);
		
		assertNotNull(newStylePathWithStraight);
		assertNotNull(newStylePathWithStraight.getTotalLength());
		assertEquals(STRAIGHT_LENGTH + CURVE_LENGTH, newStylePathWithStraight.getTotalLength());
		assertEquals(STRAIGHT_LENGTH, newStylePathWithStraight.getPathAtDistance(50).getLength());
		assertEquals(CURVE_LENGTH, newStylePathWithStraight.getPathAtDistance(250).getLength());
		assertEquals(0, newStylePathWithStraight.getTotalOfCompletedPaths(50));
		assertEquals(STRAIGHT_LENGTH, newStylePathWithStraight.getTotalOfCompletedPaths(250));
	}
	
	@Test
	void TestEquality() {
		assertEquals(oldStylePath.getTotalLength(), newStylePath.getTotalLength());
		assertEquals(dydx(oldStylePath, 0), dydx(newStylePath, 0), EPSILON);
		assertEquals(dydx(oldStylePath, CURVE_LENGTH/2), dydx(newStylePath, CURVE_LENGTH/2), EPSILON);
		assertEquals(dydx(oldStylePath, CURVE_LENGTH-1), dydx(newStylePath, CURVE_LENGTH-1), EPSILON);
		
		assertEquals(0.0, dydx(newStylePathWithStraight, 0), EPSILON);
		assertEquals(dydx(oldStylePath, CURVE_LENGTH-1), dydx(newStylePathWithStraight, STRAIGHT_LENGTH + CURVE_LENGTH - 1), EPSILON);
	}
	
	@Test
	void testCurvature() {
		assertEquals(curvature(oldStylePath, 50), 0.0001, EPSILON);
		assertEquals(0.0085506, curvature(newStylePath, 0), EPSILON);
		assertEquals(0.002973717945, curvature(newStylePath, 150), EPSILON);
		assertEquals(0.14308931, curvature(newStylePath, 235), EPSILON);
	}
	
	@Test
	void testSpeedGen() {
		// construct generator
		SpeedGenerator generator = new SpeedGenerator(0.3, 0.8, 0.03, 75, 20, 10);
		assertNotNull(generator);
		
		// sanity checks on generator
		assertEquals(0.3, generator.getMinSpeed(), EPSILON);
		assertEquals(0.8, generator.getMaxSpeed(), EPSILON);
		assertEquals(0.03, generator.getAccel(), EPSILON);
		assertEquals(75, generator.getRadius1(), EPSILON);
		assertEquals(20, generator.getRadius2(), EPSILON);
		assertEquals(10, generator.getLookahead(), EPSILON);
		
		// generate speed function for newStylePath
		Function<Double, Double> speedFunction = generator.getSpeedFunction(newStylePath);
		assertNotNull(speedFunction);
		
		// test speed function based on newStylePath
		assertEquals(0.8, speedFunction.apply(0.19), EPSILON);
		assertEquals(0.3, speedFunction.apply(0.8), EPSILON);
		assertEquals(0.56959438, speedFunction.apply(0.03), 0.001);
		assertEquals(0.47972958, speedFunction.apply(0.98), 0.001);
		assertEquals(0.67407401, speedFunction.apply(0.65), 0.001);
		assertEquals(0.77359451, speedFunction.apply(0.88), 0.001);
		
		// generate speed function for newStylePath
		speedFunction = generator.getSpeedFunction(newStylePathWithStraight);
		assertNotNull(speedFunction);
		
		// test speed function based on newStylePath
		assertEquals(0.8, speedFunction.apply(0.4), EPSILON);
		assertEquals(0.8, speedFunction.apply(0.2525), EPSILON);
		assertEquals(0.3, speedFunction.apply(0.8125), EPSILON);
		assertEquals(0.59954931, speedFunction.apply(0.975), 0.001);
		assertEquals(0.67407401, speedFunction.apply(0.736768), 0.1);
	}

}
