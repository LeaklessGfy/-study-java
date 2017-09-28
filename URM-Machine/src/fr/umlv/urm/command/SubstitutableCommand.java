package fr.umlv.urm.command;

import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;

import java.util.List;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public interface SubstitutableCommand extends EURMCommand {
	/**
	 * Substitute the command with EURM command
	 * 
	 * @param registerManager
	 * @param anchorManager
	 * @return list of eurm commands
	 */
	List<EURMCommand> substitute(RegisterManager registerManager, AnchorManager anchorManager);
}
