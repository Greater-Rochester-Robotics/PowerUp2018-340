package org.usfirst.frc.team340.robot.commands.elevator;

import org.usfirst.frc.team340.robot.Robot;
import org.usfirst.frc.team340.robot.OI.Axis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorStickControl2 extends Command {
	
	
    public ElevatorStickControl2() {
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * this execute needs to match the following use cases:
     * 1) if we're going up and we're trying to stop
     *    - set speed to up at small speed
     *    - engage brake
     *    - after some time stop motor
     * 2) if we're going down and we're trying to stop
     *    - stop motors
     *    - after some time engage brake  
     */
    protected void execute() {
		double speed = -Robot.oi.getCoDriverAxis(Axis.LEFT_Y);
		
    	Robot.elevator.setSpeedScaled(speed);
		
    	//Accounted for
//		if(speed == 0) {
//			Robot.elevator.setBrakeEngaged();
//		} else {
//			Robot.elevator.setBrakeDisengaged();
//		}
		
		SmartDashboard.putNumber("Elevator speed", speed);
		SmartDashboard.putNumber("encoder", Robot.elevator.getPosition());
		
//		System.out.println("\n\nTalon: " + Robot.elevator.talonA.get());
//		System.out.println("Joystick: " + Robot.oi.getCoDriverAxis(Axis.LEFT_Y));
//		System.out.println("Encoder: " + Robot.elevator.getPosition() + " Switch: " + Robot.elevator.isAtBottom());
//		System.out.println("speed: " + speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
