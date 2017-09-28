package fr.umlv.urm.command.eurm;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.command.urm.Jump;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.visitor.EURMVisitor;

public final class Quit implements CompilableCommand {
	public static final String NAME = "QUIT";

	@Override
	public int maximumRegisterIndex() {
		return 0;
	}

	@Override
	public List<URMCommand> getInstructions(int lineNumber, int length, RegisterManager registerManager, AnchorManager anchorManager) {
		int tmpRegisterIndex = registerManager.getTemporaryRegister();

		List<URMCommand> l = new ArrayList<>();
		l.add(new fr.umlv.urm.command.urm.Zero(tmpRegisterIndex));
		l.add(new Jump(tmpRegisterIndex, tmpRegisterIndex, length + 1));

		return l;
	}

	@Override
	public int getNumberOfInstructions() {
		return 2;
	}
	
	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void accept(EURMVisitor v) {
		v.visit(this);
	}
}
