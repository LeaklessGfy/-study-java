package fr.umlv.urm.visitor;

import fr.umlv.urm.command.Anchor;
import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.SubstitutableCommand;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public interface EURMVisitor {
	/**
	 * Visit an anchor command
	 * 
	 * @param cmd
	 */
    void visit(Anchor cmd);
    
    /**
     * Visit a substitutable command
     * 
     * @param cmd
     */
    void visit(SubstitutableCommand cmd);
    
    /**
     * Visit a compilable command
     * 
     * @param cmd
     */
    void visit(CompilableCommand cmd);
    
    /**
     * Visit a eurm command
     * 
     * @param cmd
     */
    void visit(EURMCommand cmd);
}
