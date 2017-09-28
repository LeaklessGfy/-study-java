package fr.umlv.urm.command.eurm;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.SubstitutableCommand;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.visitor.EURMVisitor;
import fr.umlv.urm.utilities.Commands;

public final class Mult implements SubstitutableCommand {
	public static final String NAME = "MULT";
	private final int primaryRegisterIndex;
	private final int secondaryRegisterIndex;
	
	public Mult(int primaryRegisterIndex, int secondaryRegisterIndex) {
		this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
		this.secondaryRegisterIndex = Commands.isPositiveInteger(secondaryRegisterIndex);
	}
	
	@Override
	public int maximumRegisterIndex() {
        return Math.max(primaryRegisterIndex, secondaryRegisterIndex);
	}
	
	@Override
	public List<EURMCommand> substitute(RegisterManager registerManager, AnchorManager anchorManager) {
	    int tmpRegisterIndex1 = registerManager.getFixedRegister();
	    int tmpRegisterIndex2 = registerManager.getFixedRegister();
	    int tmpRegisterIndex3 = registerManager.getFixedRegister();

	    String anchorStart = anchorManager.getRandomAnchor();
	    String anchorA = anchorManager.getRandomAnchor();
	    String anchorB = anchorManager.getRandomAnchor();
	    String anchorEnd = anchorManager.getRandomAnchor();

		List<EURMCommand> l = new ArrayList<>();
		l.add(new Copy(tmpRegisterIndex1, primaryRegisterIndex));
		l.add(new Zero(primaryRegisterIndex));
		l.add(new Zero(tmpRegisterIndex2));

		l.add(new Label(anchorStart));
		l.add(new EqTest(secondaryRegisterIndex, tmpRegisterIndex2, anchorEnd));
		l.add(new Zero(tmpRegisterIndex3));

		l.add(new Label(anchorA));
		l.add(new EqTest(tmpRegisterIndex1, tmpRegisterIndex3, anchorB));
		l.add(new Inc(primaryRegisterIndex));
		l.add(new Inc(tmpRegisterIndex3));
		l.add(new Goto(anchorA));

		l.add(new Label(anchorB));
		l.add(new Inc(tmpRegisterIndex2));
		l.add(new Goto(anchorStart));

		l.add(new Label(anchorEnd));

		return l;
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
