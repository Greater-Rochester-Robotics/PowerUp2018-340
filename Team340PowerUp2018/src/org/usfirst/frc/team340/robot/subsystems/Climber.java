package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	private static WPI_TalonSRX winchA;
	private static WPI_TalonSRX winchB;
	private static Solenoid deploy;
	
	public Climber() {
		winchA = new WPI_TalonSRX(RobotMap.CLIMBER_TALONSRX_A_ID);
		winchB = new WPI_TalonSRX(RobotMap.CLIMBER_TALONSRX_B_ID);
		deploy = new Solenoid(RobotMap.CLIMBER_DEPLOY_CHANNEL);
		
		winchB.set(ControlMode.Follower, RobotMap.CLIMBER_TALONSRX_A_ID);
	}
	
    public void initDefaultCommand() {}
    
    public void deploy() {
    	deploy.set(true); //TODO: check direction
    }
    
    public void retract() {
    	deploy.set(false);
    }
    
    public void setWinch(double speed) {
    	winchA.set(speed);
    }
    
    public void stopWinch() {
    	winchA.set(0);
    }
}
