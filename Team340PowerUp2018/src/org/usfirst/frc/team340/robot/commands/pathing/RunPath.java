package org.usfirst.frc.team340.robot.commands.pathing;

import java.util.function.Function;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class RunPath extends CommandGroup {
	private final double arcDivisor = 15;

	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
	private double length = -1;
	
	private boolean reset = true;
	
	private Path path;
	
	private Function<Double, Double> speed;
	
	private Animation animation;
	
    public RunPath(Path path, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.path = path;
    	this.leftSpeed = -speed;
    	this.rightSpeed = -speed;
    	this.speed = x -> speed;
    	this.setInterruptible(false);
    }
    
    public RunPath(Path path, Function<Double, Double> speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.path = path;
    	this.speed = speed;
    	this.leftSpeed = speed.apply(0.0);
    	this.rightSpeed = speed.apply(0.0);
//    	this.setInterruptible(false);
    }
    
    public RunPath(Path path, Function<Double, Double> speed, Animation animation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.path = path;
    	this.speed = speed;
    	this.leftSpeed = speed.apply(0.0);
    	this.rightSpeed = speed.apply(0.0);
    	this.animation = animation;

    	
 
    	this.setInterruptible(false);
    }
    
	public double dydx(double s) {
		PathSegment segment = path.getPathAtDistance(s);
		return segment.getDerivative().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
	}

    // Called just before this Command runs the first time
    protected void initialize() {	
    	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	Robot.drive.resetBothEncoders();
    	Robot.drive.resetIMU();
    	System.out.println("RUNPATH INIT");
    }
    
    private double getDistance() {
    	return Math.abs((Robot.drive.getRightEncoder() + Robot.drive.getLeftEncoder())/2);
    }
    
    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double nextSlope = dydx(getDistance());
    	
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	
//    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
//    	System.out.println("Encoder: " + getDistance() + " dydx: " + dydx(getDistance()));
    	return angle;
    }
    
    public double speed() {
//    	System.out.println(-speed.apply(getDistance()/path.getTotalLength()));
    	return -speed.apply(getDistance()/path.getTotalLength());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = -deltaAngle(Robot.drive.getYaw());
    	
    	leftSpeed = speed();
    	rightSpeed = speed();
    	
//    	System.out.println("error: " + error);
    	if(Math.abs(getDistance()) > 3) {
    		double speed = leftSpeed;
        	Robot.drive.setBothDrive(
        			(leftSpeed+((error)/(arcDivisor/Math.abs(speed)))), 
        			(rightSpeed-(((error)/(arcDivisor/Math.abs(speed))))));
        	
        	// animate based off of distance, from 0.0 to 1.0
        	if(animation != null) {
        		animation.animate(getDistance() / path.getTotalLength());
        		
        		for(Keyframe kf : animation) {
//            		addSequential(kf.getCommandConsumer().getCommand());
            	}
        	}
        	
    	} else {
        	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	try {
//        	System.out.println(path.getPathAtDistance(Robot.drive.getRightDistance()).getLength());
            return Math.abs(getDistance()) > (path.getTotalLength());
    	} catch (Exception e) {
    		System.err.println("Unexpected error in RunPath.isFinished()");
    		System.err.println(e);
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setBothDrive(0, 0);
    	if(animation != null)
    		animation.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("RUNPATH INTERRUPTED");
    	end();
    }
}
