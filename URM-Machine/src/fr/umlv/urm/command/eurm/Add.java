package fr.umlv.urm.command.eurm;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.utilities.Commands;
import fr.umlv.urm.command.urm.Jump;
import fr.umlv.urm.command.urm.Successor;
import fr.umlv.urm.command.urm.Zero;
import fr.umlv.urm.visitor.EURMVisitor;

public final class Add implements CompilableCommand {
	public static final String NAME = "ADD";
	private final int primaryRegisterIndex;
	private final int secondaryRegisterIndex;
	
	public Add(int primaryRegisterIndex, int secondaryRegisterIndex) {
		this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
		this.secondaryRegisterIndex = Commands.isPositiveInteger(secondaryRegisterIndex);
	}
	
	@Override
	public int maximumRegisterIndex() {
		return Math.max(primaryRegisterIndex, secondaryRegisterIndex);
	}
	
	@Override
	public List<URMCommand> getInstructions(int lineNumber, int length, RegisterManager registerManager, AnchorManager anchorManager) {
		int tmpRegisterIndex = registerManager.getTemporaryRegister();
		
		List<URMCommand> l = new ArrayList<>();
		l.add(new Zero(tmpRegisterIndex));
		l.add(new Jump(secondaryRegisterIndex, tmpRegisterIndex, lineNumber + getNumberOfInstructions()));
		l.add(new Successor(primaryRegisterIndex));
		l.add(new Successor(tmpRegisterIndex));
		l.add(new Jump(primaryRegisterIndex, primaryRegisterIndex, lineNumber + 1));
		
		return l;
	}

	@Override
	public int getNumberOfInstructions() {
		return 5;
	}

	@Override
	public String toString() {
		return NAME + " " + primaryRegisterIndex + " " + secondaryRegisterIndex;
	}

	@Override
	public void accept(EURMVisitor v) {
		v.visit(this);
	}
}
