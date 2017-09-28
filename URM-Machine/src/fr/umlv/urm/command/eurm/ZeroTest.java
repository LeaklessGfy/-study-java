package fr.umlv.urm.command.eurm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.command.urm.Jump;
import fr.umlv.urm.command.urm.Zero;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.visitor.EURMVisitor;
import fr.umlv.urm.utilities.Commands;

public final class ZeroTest implements CompilableCommand {
	public static final String NAME = "ZERO?";
	private final int primaryRegisterIndex;
	private final String anchor;
	
	public ZeroTest(int primaryRegisterIndex, String anchor) {
		this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
		this.anchor = Objects.requireNonNull(anchor);
	}
	
	@Override
	public int maximumRegisterIndex() {
		return primaryRegisterIndex;
	}
	
	@Override
	public List<URMCommand> getInstructions(int lineNumber, int length, RegisterManager registerManager, AnchorManager anchorManager) {
		int line = anchorManager.getLineNumber(anchor);
		int tmpRegisterIndex = registerManager.getTemporaryRegister();
		
		List<URMCommand> l = new ArrayList<>();
		l.add(new Zero(tmpRegisterIndex));
		l.add(new Jump(primaryRegisterIndex, tmpRegisterIndex, line));

		return l;
	}

	@Override
	public int getNumberOfInstructions() {
		return 2;
	}

	@Override
	public String toString() {
		return NAME + " " + primaryRegisterIndex + " " + anchor;
	}

	@Override
	public void accept(EURMVisitor v) {
		v.visit(this);
	}
}
