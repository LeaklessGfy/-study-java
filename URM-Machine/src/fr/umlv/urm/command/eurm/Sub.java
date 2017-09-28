package fr.umlv.urm.command.eurm;

import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.SubstitutableCommand;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.utilities.Commands;
import fr.umlv.urm.visitor.EURMVisitor;

import java.util.ArrayList;
import java.util.List;

public final class Sub implements SubstitutableCommand{
    public static final String NAME = "SUB";
    private final int primaryRegisterIndex;
    private final int secondaryRegisterIndex;

    public Sub(int primaryRegisterIndex, int secondaryRegisterIndex) {
        this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
        this.secondaryRegisterIndex = Commands.isPositiveInteger(secondaryRegisterIndex);
    }

    @Override
    public int maximumRegisterIndex() {
        return Math.max(primaryRegisterIndex, secondaryRegisterIndex);
    }

    @Override
    public List<EURMCommand> substitute(RegisterManager registerManager, AnchorManager anchorManager) {
        int tmpRegisterIndex = registerManager.getFixedRegister();

        String anchorStart = anchorManager.getRandomAnchor();
        String anchorEnd = anchorManager.getRandomAnchor();

        List<EURMCommand> l = new ArrayList<>();
        l.add(new Zero(tmpRegisterIndex));
        l.add(new Label(anchorStart));
        l.add(new EqTest(secondaryRegisterIndex, tmpRegisterIndex, anchorEnd));
        l.add(new Dec(primaryRegisterIndex));
        l.add(new Inc(tmpRegisterIndex));
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
