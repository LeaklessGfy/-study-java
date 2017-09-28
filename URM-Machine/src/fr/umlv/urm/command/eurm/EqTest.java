package fr.umlv.urm.command.eurm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.command.urm.Jump;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.visitor.EURMVisitor;
import fr.umlv.urm.utilities.Commands;

public final class EqTest implements CompilableCommand {
	public static final String NAME = "EQ?";
	private final int primaryRegisterIndex;
	private final int secondaryRegisterIndex;
	private final String anchor;
	
	public EqTest(int primaryRegisterIndex, int secondaryRegisterIndex, String anchor) {
		this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
		this.secondaryRegisterIndex = Commands.isPositiveInteger(secondaryRegisterIndex);
		this.anchor = Objects.requireNonNull(anchor);
	}
	
	@Override
	public int maximumRegisterIndex() {
		return Math.max(primaryRegisterIndex, secondaryRegisterIndex);
	}

	@Override
	public List<URMCommand> getInstructions(int lineNumber, int length, RegisterManager registerManager, AnchorManager anchorManager) {
		int line = anchorManager.getLineNumber(anchor);
		List<URMCommand> l = new ArrayList<>();
		l.add(new Jump(primaryRegisterIndex, secondaryRegisterIndex, line));
		return l;
	}

	@Override
	public int getNumberOfInstructions() {
		return 1;
	}

	@Override
	public String toString() {
		return NAME + " " + primaryRegisterIndex + " " + secondaryRegisterIndex + " " + anchor;
	}

	@Override
	public void accept(EURMVisitor v) {
		v.visit(this);
	}
}
