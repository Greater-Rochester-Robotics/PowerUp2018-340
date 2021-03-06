package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;
import org.usfirst.frc.team340.robot.commands.elevator.ElevatorStickControl2;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * <h1><i>Elevator</i></h1>
 * Raises and lowers - or elevates - the cube acquisition device (commonly referred to as the {@link Claw})
 */
public class Elevator extends Subsystem {
	private static Solenoid brake;
	private static DoubleSolenoid tilt;
	public static WPI_TalonSRX talonA; //Contains bottom switch and encoder. Is hw 1.7
	private static WPI_TalonSRX talonB;
	private static WPI_TalonSRX talonC;
	
	private static final double BOTTOM_LOWER_SLOW = 400;
	private static final double BOTTOM_UPPER_SLOW = 800;
	private static final double TOP_LOWER_SLOW = 2725;
	private static final double TOP_UPPER_SLOW = 2942;
	
	/**
	 * Raises and lowers the claw to get the cube into the switch and scale
	 */
	public Elevator() {
		brake = new Solenoid(RobotMap.ELEVATOR_BRAKE_CHANNEL);
		tilt = new DoubleSolenoid(RobotMap.ELEVATOR_TILT_CHANNEL_A, RobotMap.ELEVATOR_TILT_CHANNEL_B);
		
		talonA = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_B_ID);
		talonC = new WPI_TalonSRX(RobotMap.ELEVATOR_TALONSRX_C_ID);
		
		talonA.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 50);
		talonA.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0, 50);
		talonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 50);
		
		// broke (further testing shows maybe NOT broke
		talonA.configForwardSoftLimitThreshold((int) (RobotMap.ELEVATOR_MAX_TICS), 50);
		talonA.configForwardSoftLimitEnable(true, 50);
		
		talonA.configOpenloopRamp(RobotMap.ELEVATOR_RAMP_TIME_S, 50);
		talonA.configAllowableClosedloopError(0, RobotMap.ELEVATOR_TOLERANCE_TICS, 50);
		talonA.config_kP(0, RobotMap.ELEVATOR_KP, 50);
		talonA.configNominalOutputForward(RobotMap.ELEVATOR_MIN_SPEED_UP_VBUS, 50);
//		talonA.configPeakOutputForward(RobotMap.ELEVATOR_MAX_SPEED_UP_VBUS, 0);
//		talonA.configNominalOutputReverse(-RobotMap.ELEVATOR_MIN_SPEED_DOWN_VBUS, 0);
//		talonA.configPeakOutputReverse(-RobotMap.ELEVATOR_MAX_SPEED_DOWN_VBUS, 0);
		//TODO: tune here
		talonA.setSensorPhase(false);
		talonA.setInverted(false);
		
		talonB.set(ControlMode.Follower, RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB.configOpenloopRamp(0, 50);
		
		talonC.set(ControlMode.Follower, RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonC.configOpenloopRamp(0, 50);
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorStickControl2());
	}
    
	/**
	 * Set the elevator to some unit-counted position
	 * @param position the unit count to position the elevator
	 */
    public void setPosition(int position) {
    	setBrakeDisengaged();
    	talonA.set(ControlMode.Position, position);
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
     * Sets the sensor position of the elevator, hopefully without physically moving it
     * @param position
     */
    public void setEncoderPosition(int position) {
    	talonA.setSelectedSensorPosition(position, 0, 0); // from ctre docs: int sensorPos, int pidIdx, int timeoutMs
    }
    
    /**
     * @return <code>true</code> if the bottom DIO is pressed
     */
    public boolean isAtBottom() {
    	return talonA.getSensorCollection().isRevLimitSwitchClosed();
    }
    
    /**
     * @return the brake value
     */
    public boolean getBrake() {
    	return brake.get();
    }
    
    /**
     * Tilt the elevator into the vertical/forward position
     */
    public void setTiltForward() {
    	tilt.set(Value.kReverse);
    }
    
    /**
     * Tilt the elevator into the angled/backward position
     */
    public void setTiltBackward() {
    	tilt.set(Value.kForward);
    }
    
    /**
     * Engage the elevator brake
     */
    public void setBrakeEngaged() {
    	brake.set(false);
    }
    
    /**
     * Disengage the elevator brake
     */
    public void setBrakeDisengaged() {
    	brake.set(true);
    }
    public void setSpeedScaled(double speed){
    	if (speed < -0.05) {
			speed *= 1.0;
			if(isAtBottom()) {
				speed = 0;
			}else if(getPosition() < BOTTOM_UPPER_SLOW) {
				speed *= 0.3;
			} else if(getPosition() < BOTTOM_LOWER_SLOW) {
				speed *= 0.1;
			}
			
			goDown(speed);
		} else if (speed > 0.05) {
			speed *= 1.00;
			if(getPosition() > TOP_UPPER_SLOW) {
				speed = 0.05;
			} else if(getPosition() > TOP_LOWER_SLOW) {
				speed *= 0.4;
			}
			
			goUp(speed);
		} else {
			stop();
		}
    }
    
    public void setSpeedUnscaled(double speed) {
    	// Tayno and James told me to do this  (-Justin)
    	if (speed < 0 ){
    		goDown(speed);
    	} else if (speed > 0) {
    		goUp(speed);
    	} else {
    		stop();
    	}
    	
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
    
    //testing
    
    public double getSpeed() {
    	return talonA.getBusVoltage()/12;
    }
    
    public String getCurrent() {
    	return talonA.getOutputCurrent() + " / " + talonB.getOutputCurrent() + " / " + talonC.getOutputCurrent();
    }
}
