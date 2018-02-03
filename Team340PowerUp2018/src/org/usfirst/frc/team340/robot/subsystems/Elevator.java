package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private static WPI_TalonSRX talonA;
	private static WPI_TalonSRX talonB;
	private static WPI_TalonSRX talonC;
	private static DoubleSolenoid tilt;
	private static DoubleSolenoid brake;
	
	public Elevator() {
		tilt = new DoubleSolenoid(RobotMap.ELEVATOR_TILT_CHANNEL_A, RobotMap.ELEVATOR_TILT_CHANNEL_B);
		brake = new DoubleSolenoid(RobotMap.ELEVATOR_BRAKE_CHANNEL_A, RobotMap.ELEVATOR_BRAKE_CHANNEL_B);
		talonA = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_B_ID);
		talonC = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_C_ID);
		
		talonA.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		talonA.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0, 10);
		talonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		talonA.configForwardSoftLimitThreshold(RobotMap.ELEVATOR_MAX_TICS, 0);
		talonA.configForwardSoftLimitEnable(true, 0);
		talonA.configOpenloopRamp(RobotMap.ELEVATOR_RAMP_TIME_S, 0);
		talonA.configAllowableClosedloopError(0, RobotMap.ELEVATOR_TOLERANCE_TICS, 0);
		talonA.config_kP(0, RobotMap.ELEVATOR_KP, 10);
		talonA.configNominalOutputForward(RobotMap.ELEVATOR_MIN_SPEED_UP_VBUS, 0);
		talonA.configPeakOutputForward(RobotMap.ELEVATOR_MAX_SPEED_UP_VBUS, 0);
		talonA.configNominalOutputReverse(RobotMap.ELEVATOR_MIN_SPEED_DOWN_VBUS, 0);
		talonA.configPeakOutputReverse(RobotMap.ELEVATOR_MAX_SPEED_DOWN_VBUS, 0);
		
		//Tune here
		talonA.setSensorPhase(true);
		talonA.setInverted(false);
		
		talonB.set(ControlMode.Follower, RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB.configOpenloopRamp(0,0);
		
		talonC.set(ControlMode.Follower, RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonC.configOpenloopRamp(0,0);
		
	}
	
	public void initDefaultCommand() {}
    
    public void setPosition(int position) {
    	talonA.set(ControlMode.Position,position);
    	setBrakeDisengaged();
    }
    
    public int getPosition() {
    	return talonA.getSelectedSensorPosition(0);
    }
    
    public boolean isAtPosition(int position) {
    	return getPosition() == position;
    }
    
    public boolean isAtToleratedPosition(int position) {
    	return getPosition() <= position + RobotMap.ELEVATOR_TOLERANCE_TICS && getPosition() >= position - RobotMap.ELEVATOR_TOLERANCE_TICS;
    }
    
    public boolean isAtBottom() {
    	return talonA.getSensorCollection().isRevLimitSwitchClosed();
    }
    
    public void setTiltForward() {
    	tilt.set(Value.kForward);    	
    }
    
    public void setTiltBackward() {
    	tilt.set(Value.kReverse);
    }
    
    public void setBrakeEngaged() {
    	brake.set(Value.kForward);
    }
    
    public void setBrakeDisengaged() {
    	brake.set(Value.kReverse);
    }
    
    public void goUp(double speed) {
    	talonA.set(ControlMode.PercentOutput, Math.abs(speed));
    	setBrakeDisengaged();
    }
    
    public void goDown(double speed) {
    	talonA.set(ControlMode.PercentOutput, -Math.abs(speed));
    	setBrakeDisengaged();
    }
    
    public void stop() {
    	talonA.stopMotor();
    	setBrakeEngaged();
    }
}

