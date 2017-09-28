package fr.umlv.urm.compiler;

import java.util.Iterator;
import java.util.List;

import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.program.EURMProgram;
import fr.umlv.urm.program.URMProgram;
import fr.umlv.urm.visitor.AnchorVisitor;
import fr.umlv.urm.visitor.CompilerVisitor;
import fr.umlv.urm.visitor.SubstitutableVisitor;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class Compiler {
	/**
	 * Compile a specific EURM program to URM program
	 * 
	 * @param program
	 * @return URMProgram
	 * @throws URMException
	 */
    public static URMProgram compile(EURMProgram program) throws URMException {
		int maximumRegisterIndex = program.maximumRegisterIndex();
		RegisterManager registerManager = new RegisterManager(maximumRegisterIndex);
		AnchorManager anchorManager = new AnchorManager();

		Iterator<EURMCommand> it = program.iterator();
		SubstitutableVisitor sVisitor = substitutableVisit(it, registerManager, anchorManager);
    	List<EURMCommand> substituteCommands = sVisitor.getCommands();

    	it = substituteCommands.iterator();
		AnchorVisitor aVisitor = anchorVisit(it, anchorManager);
    	int length = aVisitor.getLength();
    	
    	it = substituteCommands.iterator();
    	CompilerVisitor cVisitor = compileVisit(it, length, registerManager, anchorManager);
    	List<URMCommand> commands = cVisitor.getCommands();
    	
    	return new URMProgram(commands);
    }
    
    /**
     * Do the substitution of EURM commands
     * 
     * @param it
     * @param registerManager
     * @param anchorManager
     * @return substitutable visitor
     * @throws URMException
     */
    private static SubstitutableVisitor substitutableVisit(Iterator<EURMCommand> it, RegisterManager registerManager, AnchorManager anchorManager) throws URMException {
    	SubstitutableVisitor visitor = new SubstitutableVisitor(registerManager, anchorManager);
    	
    	while (it.hasNext()) {
    		EURMCommand cmd = it.next();
    		cmd.accept(visitor);
    	}
    	
    	return visitor;
    }
    
    /**
     * Do the anchor visit
     * 
     * @param it
     * @param anchorManager
     * @return anchor visitor
     * @throws URMException
     */
    private static AnchorVisitor anchorVisit(Iterator<EURMCommand> it, AnchorManager anchorManager) throws URMException {
    	AnchorVisitor visitor = new AnchorVisitor(anchorManager);
    	
    	while (it.hasNext()) {
    		EURMCommand command = it.next();
    		command.accept(visitor);
    	}
    	
    	return visitor;
    }
    
    /**
     * Do the compile visit
     * 
     * @param it
     * @param length
     * @param registerManager
     * @param anchorManager
     * @return compiler visitor
     * @throws URMException
     */
    private static CompilerVisitor compileVisit(Iterator<EURMCommand> it, int length, RegisterManager registerManager, AnchorManager anchorManager) throws URMException {
    	CompilerVisitor visitor = new CompilerVisitor(length, registerManager, anchorManager);

    	while (it.hasNext()) {
    		EURMCommand command = it.next();
    		command.accept(visitor);
		}

		return visitor;
    }
}
