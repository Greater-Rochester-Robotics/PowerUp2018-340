package org.usfirst.frc.team340.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {
	private static Solenoid solenoid;
	private static Talon wheels;
	
	public Claw() {
		solenoid = new Solenoid(1);
		wheels = new Talon(2);
	}
	
    public void initDefaultCommand() {}
    
    public void open() {
    	solenoid.set(false);
    }
    
    public void close() {
    	solenoid.set(true);
    }
    
    public void neutral() {
    	
    }
    
    public void spinWheelsIn(double speed) {
    	wheels.set(-Math.abs(speed));
    }
    
    public void spinWheelsOut(double speed) {
    	wheels.set(Math.abs(speed));
    }
    
    public void stopWheels() {
    	wheels.set(0);
    }
    
    public boolean isCubePresent() {
    	return false;
    }
}
