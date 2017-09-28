package fr.umlv.urm.visitor;

import java.util.Objects;

import fr.umlv.urm.command.Anchor;
import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.SubstitutableCommand;
import fr.umlv.urm.manager.AnchorManager;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class AnchorVisitor implements EURMVisitor {
	private int lineNumber;
	private final AnchorManager anchorManager;
	
	/**
	 * AnchorVisitor constructor
	 * 
	 * @param anchorManager
	 */
	public AnchorVisitor(AnchorManager anchorManager) {
		this.lineNumber = 0;
		this.anchorManager = Objects.requireNonNull(anchorManager);
	}

	@Override
	public void visit(Anchor cmd) {
		if (cmd == null) {
			throw new IllegalArgumentException("AnchorVisitor - Can't visit null command");
		}

		anchorManager.register(cmd.getAnchorName(), lineNumber);
	}

	@Override
	public void visit(SubstitutableCommand cmd) {

    }

	@Override
	public void visit(CompilableCommand cmd) {
		if (cmd == null) {
			throw new IllegalArgumentException("AnchorVisitor - Can't visit null command");
		}

	    lineNumber += cmd.getNumberOfInstructions();
	}

	@Override
	public void visit(EURMCommand cmd) {

    }

	/**
	 * Get length of program
	 * 
	 * @return length of program
	 */
	public int getLength() {
	    return lineNumber;
    }
}
