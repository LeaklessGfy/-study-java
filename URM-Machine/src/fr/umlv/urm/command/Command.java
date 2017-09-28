package fr.umlv.urm.command;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public interface Command {
	/**
	 * Get the maximum register index of the command
	 * 
	 * @return the maximum register used by the command
	 */
	int maximumRegisterIndex();
}
