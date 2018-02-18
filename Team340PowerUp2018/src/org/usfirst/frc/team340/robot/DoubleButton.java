package org.usfirst.frc.team340.robot;

import edu.wpi.first.wpilibj.buttons.Button;

public class DoubleButton extends Button {
	public Button m_button1,m_button2;
	
	/**
	 * Creates a button object that requires two simultaneous
	 *  presses to activate true
	 * @param button1 first button to compare
	 * @param button2 second button to compare
	 */
	public DoubleButton (Button button1 , Button button2){
		m_button1 = button1;
		m_button2 = button2;
	}
	
	/**
	 * Returns true when both buttons are pressed
	 */
	public boolean get(){
		return m_button1.get() && m_button2.get();
	}
}
