package org.usfirst.frc.team340.robot.commands.pathing;

import java.util.function.Function;

import org.usfirst.frc.team340.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class RunPath extends Command {
	

	private final double arcDivisor = 45;

	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
	private double length = -1;
	
	private Path path;
	
	private Function<Double, Double> speed;
	
    private RunPath(Path path, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.path = path;
    	this.leftSpeed = speed;
    	this.rightSpeed = speed;
    	this.speed = x -> speed;
    }
    
    private RunPath(Path path, Function<Double, Double> speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.path = path;
    	this.speed = speed;
    	this.leftSpeed = speed.apply(0.0);
    	this.rightSpeed = speed.apply(0.0);
    }
    
	public double dydx(double s) {
		return path.getPathAtDistance(s).getDerivative().apply(s);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	Robot.drive.resetIMU();
    	Robot.drive.resetBothEncoders();
    }
    
    private double getDistance() {
    	return Robot.drive.getRightDistance();
    }
    
    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double nextSlope = dydx(getDistance());
    	
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	
    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
    	System.out.println("Encoder: " + getDistance() + " dydx: " + dydx(getDistance()));
    	return angle;
    }
    
    public double speed() {
    	return speed.apply(getDistance());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = -deltaAngle(Robot.drive.getYaw());
    	
    	leftSpeed = speed();
    	rightSpeed = speed();
    	
    	System.out.println("error: " + error);
    	if(Math.abs(getDistance()) > 3) {
    		double speed = leftSpeed;
        	Robot.drive.setBothDrive(
        			leftSpeed-((error)/(arcDivisor/Math.abs(speed))), 
        			rightSpeed+(((error)/(arcDivisor/Math.abs(speed)))));
    	} else {
        	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(getDistance()) > (length);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setBothDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
