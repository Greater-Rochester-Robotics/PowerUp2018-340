package org.usfirst.frc.team340.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class CommandConsumer {
	
	private Command command;
	private boolean consumed = false;
	
	/**
	 * A command consumer will "consumes" a command, by running it once and 
	 * preventing it from being ran again
	 * @param command The command to consume
	 */
	public CommandConsumer(Command command) {
		this.command = command;
	}
	
	/**
	 * Consume the command by starting it
	 */
	public boolean consume() {
		if(!consumed) {
			command.start();
			consumed = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check if the command has been consumed
	 */
	public boolean isConsumed() {
		return consumed;
	}
	
	/**
	 * Finish the command, cancelling it and preventing it from ever being consumed
	 */
	public void finish() {
		command.cancel();
		consumed = true;
	}
}
