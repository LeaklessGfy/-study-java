package fr.umlv.urm.command;

import fr.umlv.urm.RegisterContainer;
import fr.umlv.urm.exception.URMException;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public interface URMCommand extends Command {
	/**
	 * Run the urm command
	 * 
	 * @param registerContainer
	 * @param commandPointer
	 * @return new pointer command
	 * @throws URMException
	 */
	int run(RegisterContainer registerContainer, int commandPointer) throws URMException;
}
