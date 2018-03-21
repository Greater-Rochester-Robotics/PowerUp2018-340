package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem {
	private static WPI_TalonSRX winchA;
	private static WPI_TalonSRX winchB;
	private static Solenoid deploy;
//	private static Solenoid forks;
	
	public Climber() {
		winchA = new WPI_TalonSRX(RobotMap.CLIMBER_TALONSRX_A_ID);
		winchB = new WPI_TalonSRX(RobotMap.CLIMBER_TALONSRX_B_ID);
		deploy = new Solenoid(RobotMap.CLIMBER_DEPLOY_CHANNEL);
//		forks = new Solenoid(RobotMap.FORKS_CHANNEL);
		
		winchB.set(ControlMode.Follower, RobotMap.CLIMBER_TALONSRX_A_ID);
		winchA.configNominalOutputReverse(-1, 0);
		winchB.configNominalOutputReverse(-1, 0);
		
		winchA.configPeakOutputReverse(-1, 0);
		winchB.configPeakOutputReverse(-1, 0);
	}
	
    public void initDefaultCommand() {}
    
    public void deploy() {
    	deploy.set(true); //TODO: check direction
    }
    
    public void retract() {
    	deploy.set(false);
    }
    
    public void deployForks() {
//    	forks.set(false);
    }
    public void lockForks() {
//    	forks.set(true);
    }
    
    public void setWinch(double speed) {
    	winchA.set(speed);
    	SmartDashboard.putNumber("climb speed", speed);
    }
    
    public void stopWinch() {
    	winchA.set(0);
    }
}
