package org.usfirst.frc.team340.robot.subsystems;

import org.usfirst.frc.team340.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * <h1><i>Claw</i></h1>
 * It's a claw. It picks up cubes. Power Cubes. They're secretly milk cartons, but they're cubes<br>
 * <br>
 * <i>Do not use {@link #getRightSRX()}, {@link #getLeftSRX()}, or {@link #getClawStatus()}. Please.</i>
 */
public class Claw extends Subsystem {
	private static DigitalInput cubeSensor1;
	private static DigitalInput cubeSensor2;
	private static Solenoid closeSolenoid;
	private static Solenoid openSolenoid;
	private static Solenoid LEDRed;
	private static Solenoid LEDGreen;
	private static WPI_TalonSRX leftWheels;
	private static WPI_TalonSRX rightWheels;
	
	/**
	 * It picks up <s>milk cartons</s> cubes
	 */
	public Claw() {
		cubeSensor1 = new DigitalInput(RobotMap.CLAW_CUBE_SENSOR_CHANNEL_A);
		cubeSensor2 = new DigitalInput(RobotMap.CLAW_CUBE_SENSOR_CHANNEL_B);
		
		closeSolenoid = new Solenoid(RobotMap.CLAW_SOLENOID_CLOSE_CHANNEL);
		openSolenoid = new Solenoid(RobotMap.CLAW_SOLENOID_OPEN_CHANNEL);
		
		LEDRed = new Solenoid(RobotMap.LED_RED_CHANNEL);
		LEDGreen = new Solenoid(RobotMap.LED_GREEN_CHANNEL);
		
		leftWheels = new WPI_TalonSRX(RobotMap.CLAW_WHEEL_LEFT_CHANNEL);
		leftWheels.setInverted(true); //This will probably not be inverted later :P
		rightWheels = new WPI_TalonSRX(RobotMap.CLAW_WHEEL_RIGHT_CHANNEL);
		rightWheels.setInverted(true);
	}
	
	/**
	 * We don't use this because we don't need the claw doing anything by default
	 */
    public void initDefaultCommand() {}
    
    /**
     * TEMPORARY SOLUTION! WILL CHANGE!
     * @return
     */
    public WPI_TalonSRX getRightSRX() {
    	return rightWheels;
    }
    
    /**
     * TEMPORARY SOLUTION! WILL CHANGE!
     * @return
     */
    public WPI_TalonSRX getLeftSRX() {
    	return leftWheels;
    }
    
    /**
     * JANKY TEMPORARY SOLUTION! WILL DEFINITELY CHANGE!!
     */
    public String getClawStatus() {
    	if(closeSolenoid.get() && !openSolenoid.get()) {
    		return "Closed";
    	} else if(!closeSolenoid.get() && openSolenoid.get()) {
    		return "Opened";
    	} else {
    		return "Neutralized";
    	}
    }
    
    /**
     * Open the claw
     */
    public void open() {
    	closeSolenoid.set(true); //false on prac
    	openSolenoid.set(false); //true on prac
    }
    
    /**
     * Close the claw
     */
    public void close() {
    	closeSolenoid.set(false); //true on prac
    	openSolenoid.set(true); //false on prac
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
    	leftWheels.set(-Math.abs(speed)); // unchecked
    	rightWheels.set(Math.abs(speed)); // checked
    }
    
    /**
     * Claw wheels spin out of the robot
     * @param speed percent of max speed [0 ~ 1]
     */
    public void spinWheelsOut(double speed) {
    	leftWheels.set(Math.abs(speed)); // unchecked
    	rightWheels.set(-Math.abs(speed)); // checked
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
//    	System.out.println("SENSOR 1: " + cubeSensor1.get() + " SENSOR 2: " + cubeSensor2.get());
//    	System.out.println("Boolean condition: " + (!cubeSensor1.get() || !cubeSensor2.get()));
    	return !cubeSensor1.get() || !cubeSensor2.get();
    }
    public int getNumCubeSensors() {
    	int num = 0;
    	if(cubeSensor1.get()) {
    		num++;
    	}
    	if(cubeSensor2.get()) {
    		num++;
    	}
    	return num;
    }
    
    public void setRedLEDs(boolean on) {
    	LEDRed.set(on);
    }
    
    public void setGreenLEDs(boolean on) {
    	LEDGreen.set(on);
    }
}
