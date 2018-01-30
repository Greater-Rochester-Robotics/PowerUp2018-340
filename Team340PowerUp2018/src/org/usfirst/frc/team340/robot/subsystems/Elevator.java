package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private static TalonSRX talonA;
	private static TalonSRX talonB;
	private static DigitalInput bottomSwitch;
	private static Encoder encoder;
	
	public Elevator() {
		talonA = new TalonSRX(RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB = new TalonSRX(RobotMap.ELEVATOR_TALONSRX_B_ID);
		bottomSwitch = new DigitalInput(RobotMap.ELEVATOR_BUMP_SWITCH_PORT);
		encoder = new Encoder(RobotMap.ELEVATOR_ENCODER_PORTA, RobotMap.ELEVATOR_ENCODER_PORTB);
		
		talonB.setControlMode(TalonControlMode.Follower);
		talonB.set(RobotMap.ELEVATOR_TALONSRX_A_ID);
		
		talonA.set(ControlMode.PercentOutput, 0.5);
	}
	
	public void initDefaultCommand() {}
    
    public void setPosition(int position) {
    	//A lot of PID here
    }
    
    public int getPosition() {
    	return encoder.get();
    }
    
    public boolean isAtBottom() {
    	return bottomSwitch.get();
    }
    
    public void setTiltForward() {
    	
    }
    
    public void setTiltBackward() {
    	
    }
    
    public void goUp(double speed) {
    	talonA.set(Math.abs(speed));
    }
    
    public void goDown(double speed) {
    	talonA.set(-Math.abs(speed));
    }
    
    public void stop() {
    	talonA.set(0);
    }
}

