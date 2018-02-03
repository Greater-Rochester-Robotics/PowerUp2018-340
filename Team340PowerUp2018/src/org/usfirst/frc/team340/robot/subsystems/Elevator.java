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
 * <h1><i>Elevator</i></h1>
 * Raises and lowers - or elevates - the cube acquisition device (commonly referred to as the {@link Claw})
 */
public class Elevator extends Subsystem {
	private static DoubleSolenoid brake;
	private static DoubleSolenoid tilt;
	private static WPI_TalonSRX talonA;
	private static WPI_TalonSRX talonB;
	private static WPI_TalonSRX talonC;
	
	/**
	 * Raises and lowers the claw to get the cube into the switch and scale
	 */
	public Elevator() {
		brake = new DoubleSolenoid(RobotMap.ELEVATOR_BRAKE_CHANNEL_A, RobotMap.ELEVATOR_BRAKE_CHANNEL_B);
		tilt = new DoubleSolenoid(RobotMap.ELEVATOR_TILT_CHANNEL_A, RobotMap.ELEVATOR_TILT_CHANNEL_B);
		
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
		
		//TODO: tune here
		talonA.setSensorPhase(true);
		talonA.setInverted(false);
		
		talonB.set(ControlMode.Follower, RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB.configOpenloopRamp(0,0);
		
		talonC.set(ControlMode.Follower, RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonC.configOpenloopRamp(0,0);
		
	}
	
	/**
	 * No, because we don't want any elevator commands going at the moment we start
	 */
	public void initDefaultCommand() {}
    
	/**
	 * Set the elevator to some unit-counted position
	 * @param position the unit count to position the elevator
	 */
    public void setPosition(int position) {
    	talonA.set(ControlMode.Position,position);
    	setBrakeDisengaged();
    }
    
    /**
     * @return the encoder position
     */
    public int getPosition() {
    	return talonA.getSelectedSensorPosition(0);
    }
    
    /**
     * @param position unit position to check
     * @return <code>true</code> if the intended position is exactly equal to the encoder reading
     */
    public boolean isAtPosition(int position) {
    	return getPosition() == position;
    }
    
    /**
     * @param position unit position to check
     * @return <code>true</code> if the encoder reading is within the constant tolerance above/below the given position
     */
    public boolean isAtToleratedPosition(int position) {
    	return getPosition() <= position + RobotMap.ELEVATOR_TOLERANCE_TICS && getPosition() >= position - RobotMap.ELEVATOR_TOLERANCE_TICS;
    }
    
    /**
     * @return <code>true</code> if the bottom DIO is pressed
     */
    public boolean isAtBottom() {
    	return talonA.getSensorCollection().isRevLimitSwitchClosed();
    }
    
    /**
     * Tilt the elevator into the vertical/forward position
     */
    public void setTiltForward() {
    	tilt.set(Value.kForward);
    }
    
    /**
     * Tilt the elevator into the angled/backward position
     */
    public void setTiltBackward() {
    	tilt.set(Value.kReverse);
    }
    
    /**
     * Engage the elevator brake
     */
    public void setBrakeEngaged() {
    	brake.set(Value.kForward);
    }
    
    /**
     * Disengage the elevator brake
     */
    public void setBrakeDisengaged() {
    	brake.set(Value.kReverse);
    }
    
    /**
     * Raise the elevator at a constant speed
     * @param speed the speed to raise at as [0 ~ 1]
     */
    public void goUp(double speed) {
    	talonA.set(ControlMode.PercentOutput, Math.abs(speed));
    	setBrakeDisengaged();
    }
    
    /**
     * Lower the elevator at a constant speed
     * @param speed the speed to drop at as [0 ~ 1]
     */
    public void goDown(double speed) {
    	talonA.set(ControlMode.PercentOutput, -Math.abs(speed));
    	setBrakeDisengaged();
    }
    
    /**
     * Stop the elevator's movement and engage the brake
     */
    public void stop() {
    	talonA.stopMotor();
    	setBrakeEngaged();
    }
}
