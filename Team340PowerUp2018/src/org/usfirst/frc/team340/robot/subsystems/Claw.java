package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {
	private static Solenoid closeSolenoid;
	private static Solenoid openSolenoid;
	private static WPI_TalonSRX leftWheels;
	private static WPI_TalonSRX rightWheels;
	private static DigitalInput cubeSensor;
	
	public Claw() {
		closeSolenoid = new Solenoid(RobotMap.CLAW_SOLENOID_CLOSE_CHANNEL);
		openSolenoid = new Solenoid(RobotMap.CLAW_SOLENOID_OPEN_CHANNEL);
		rightWheels = new WPI_TalonSRX(RobotMap.CLAW_WHEEL_RIGHT_CHANNEL);
		leftWheels = new WPI_TalonSRX(RobotMap.CLAW_WHEEL_LEFT_CHANNEL);
		leftWheels.setInverted(true);
		cubeSensor = new DigitalInput(RobotMap.CLAW_CUBE_SENSOR_CHANNEL);
	}
	
    public void initDefaultCommand() {}
    
    public void open() {
    	closeSolenoid.set(false);
    	openSolenoid.set(true);
    }
    
    public void close() {
    	closeSolenoid.set(true);
    	openSolenoid.set(false);
    }
    
    public void neutral() {
    	openSolenoid.set(false);
    	closeSolenoid.set(false);
    }
    
    public void spinWheelsIn(double speed) {
    	leftWheels.set(-Math.abs(speed));
    	rightWheels.set(-Math.abs(speed));
    }
    
    public void spinWheelsOut(double speed) {
    	leftWheels.set(Math.abs(speed));
    	rightWheels.set(Math.abs(speed));
    }
    
    public void stopWheels() {
    	leftWheels.set(0);
    	rightWheels.set(0);
    }
    
    public boolean isCubePresent() {
    	return cubeSensor.get();
    }
}
