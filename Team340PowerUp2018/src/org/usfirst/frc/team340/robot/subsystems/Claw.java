package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * <h1><i>Claw</i></h1>
 * It's a claw. It picks up cubes. Power Cubes. They're secretly milk cartons, but they're cubes
 */
public class Claw extends Subsystem {
	private static DigitalInput cubeSensor;
	private static Solenoid closeSolenoid;
	private static Solenoid openSolenoid;
	private static WPI_TalonSRX leftWheels;
	private static WPI_TalonSRX rightWheels;
	
	/**
	 * It picks up <s>milk cartons</s> cubes
	 */
	public Claw() {
		cubeSensor = new DigitalInput(RobotMap.CLAW_CUBE_SENSOR_CHANNEL);
		
		closeSolenoid = new Solenoid(RobotMap.CLAW_SOLENOID_CLOSE_CHANNEL);
		openSolenoid = new Solenoid(RobotMap.CLAW_SOLENOID_OPEN_CHANNEL);
		
		leftWheels = new WPI_TalonSRX(RobotMap.CLAW_WHEEL_LEFT_CHANNEL);
		leftWheels.setInverted(true);
		rightWheels = new WPI_TalonSRX(RobotMap.CLAW_WHEEL_RIGHT_CHANNEL);
	}
	
	/**
	 * We don't use this because we don't need the claw doing anything by default
	 */
    public void initDefaultCommand() {}
    
    /**
     * Open the claw
     */
    public void open() {
    	closeSolenoid.set(false);
    	openSolenoid.set(true);
    }
    
    /**
     * Close the claw
     */
    public void close() {
    	closeSolenoid.set(true);
    	openSolenoid.set(false);
    }
    
    /**
     * Loosens the claw. No force applied either way
     */
    public void neutral() {
    	openSolenoid.set(false);
    	closeSolenoid.set(false);
    }
    
    /**
     * Claw wheels spin into the robot
     * @param speed percent of max speed [0 ~ 1]
     */
    public void spinWheelsIn(double speed) {
    	leftWheels.set(-Math.abs(speed));
    	rightWheels.set(-Math.abs(speed));
    }
    
    /**
     * Claw wheels spin out of the robot
     * @param speed percent of max speed [0 ~ 1]
     */
    public void spinWheelsOut(double speed) {
    	leftWheels.set(Math.abs(speed));
    	rightWheels.set(Math.abs(speed));
    }
    
    /**
     * Stop wheels from spinning
     */
    public void stopWheels() {
    	leftWheels.set(0);
    	rightWheels.set(0);
    }
    
    /**
     * @return <code>true</code> if a cube is currently in the claw
     */
    public boolean isCubePresent() {
    	return cubeSensor.get();
    }
}
