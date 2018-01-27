package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private static CANTalon talonA;
	private static CANTalon talonB;
	private static DigitalInput bottomSwitch;
	private static Encoder encoder;
	
	public Elevator() {
		talonA = new CANTalon(RobotMap.ELEVATOR_TALONSRX_A_ID);
		talonB = new CANTalon(RobotMap.ELEVATOR_TALONSRX_B_ID);
		bottomSwitch = new DigitalInput(RobotMap.ELEVATOR_BUMP_SWITCH_PORT);
		encoder = new Encoder(RobotMap.ELEVATOR_ENCODER_PORTA, RobotMap.ELEVATOR_ENCODER_PORTB);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPosition(int position) {
    	
    }
    
    public int getPosition() {
    	return -1;
    }
    
    public boolean isAtBottom() {
    	return false;
    }
    
    public void setTiltForward() {
    	
    }
    
    public void setTiltBackward() {
    	
    }
    
    public void goUp(double speed) {
    	
    }
    
    public void goDown(double speed) {
    	
    }
    
    public void stop() {
    	
    }
}

