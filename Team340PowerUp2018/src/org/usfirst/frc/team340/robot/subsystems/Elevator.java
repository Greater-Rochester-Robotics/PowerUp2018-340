package org.usfirst.frc.team340.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
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

