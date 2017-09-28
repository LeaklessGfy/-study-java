package fr.umlv.urm.command;

import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;

import java.util.List;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public interface CompilableCommand extends EURMCommand {
	/**
	 * Get URM instructions 
	 * 
	 * @param lineNumber
	 * @param length
	 * @param registerManager
	 * @param anchorManager
	 * @return list of urm command
	 */
	List<URMCommand> getInstructions(int lineNumber, int length, RegisterManager registerManager, AnchorManager anchorManager);
	
	/**
	 * Get number of instructions
	 * 
	 * @return number of urm command
	 */
	int getNumberOfInstructions();
}
