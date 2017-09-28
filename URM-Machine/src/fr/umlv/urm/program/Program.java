package fr.umlv.urm.program;

import fr.umlv.urm.command.Command;
import fr.umlv.urm.exception.URMException;

/**
 * 
 * @author vrasquie
 * @version 1
 * @param <T>
 */
public interface Program<T extends Command> {
	/**
	 * Return the command at specified line
	 * 
	 * @param lineNumber
	 * @return Command
	 * @throws IllegalArgumentException
	 */
	T getCommand(int lineNumber) throws IllegalArgumentException;
	
	/**
	 * Return the maximum register index used in program
	 * 
	 * @return maximum register index
	 * @throws URMException
	 */
	int maximumRegisterIndex() throws URMException;
	
	/**
	 * Return the length of the program
	 * 
	 * @return length of the program
	 */
	int getLength();
}
