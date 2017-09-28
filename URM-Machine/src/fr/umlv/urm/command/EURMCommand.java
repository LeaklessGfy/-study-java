package fr.umlv.urm.command;

import fr.umlv.urm.visitor.EURMVisitor;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public interface EURMCommand extends Command {
	/**
	 * Accept visitor
	 * 
	 * @param v
	 */
	void accept(EURMVisitor v);
}
