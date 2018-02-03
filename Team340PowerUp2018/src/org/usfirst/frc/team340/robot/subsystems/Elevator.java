package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private static WPI_TalonSRX talonA;
	private static WPI_TalonSRX talonB;
	private static WPI_TalonSRX talonC;
	
	
	public Elevator() {
		talonA = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_B_ID);
		talonC = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_C_ID);
		
		talonA.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		talonA.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0, 10);
		talonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		talonA.setSensorPhase(true);
		talonA.configForwardSoftLimitThreshold(RobotMap.ELEVATOR_MAX_TICS, 0);
		talonA.configForwardSoftLimitEnable(true, 0);
		talonA.configOpenloopRamp(RobotMap.ELEVATOR_RAMP_TIME_S, 0);
		talonA.setInverted(false);
		talonA.config_kP(0, RobotMap.ELEVATOR_KP, 10);
		
		
		talonB.set(ControlMode.Follower, RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB.configOpenloopRamp(0,0);
		
		talonC.set(ControlMode.Follower, RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonC.configOpenloopRamp(0,0);
		
	}
	
	public void initDefaultCommand() {}
    
    public void setPosition(int position) {
    	talonA.set(ControlMode.Position,position);
    }
    
    public int getPosition() {
    	return talonA.getSelectedSensorPosition(0);
    }
    
    public boolean isAtBottom() {
    	return talonA.getSensorCollection().isRevLimitSwitchClosed();
    }
    
    public void setTiltForward() {
    	
    }
    
    public void setTiltBackward() {
    	
    }
    
    public void goUp(double speed) {
    	//talonA.set(Math.abs(speed));
    }
    
    public void goDown(double speed) {
    	//talonA.set(-Math.abs(speed));
    }
    
    public void stop() {
    	talonA.stopMotor();
    }
}

