package fr.umlv.urm.command.eurm;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.command.urm.Successor;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.utilities.Commands;
import fr.umlv.urm.visitor.EURMVisitor;

public final class Inc implements CompilableCommand {
	public static final String NAME = "INC";
	private final int primaryRegisterIndex;
	
	public Inc(int primaryRegisterIndex) {
		this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
	}
	
	@Override
	public int maximumRegisterIndex() {
		return primaryRegisterIndex;
	}
	
	@Override
	public List<URMCommand> getInstructions(int lineNumber, int length, RegisterManager registerManager, AnchorManager anchorManager) {
		List<URMCommand> l = new ArrayList<>();
		l.add(new Successor(primaryRegisterIndex));
		return l;
	}
	
	@Override
	public int getNumberOfInstructions() {
		return 1;
	}
	
	@Override
	public String toString() {
		return NAME + " " + primaryRegisterIndex;
	}

	@Override
	public void accept(EURMVisitor v) {
		v.visit(this);
	}
}
