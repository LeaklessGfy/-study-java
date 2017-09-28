package fr.umlv.urm.command.eurm;

import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.SubstitutableCommand;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.visitor.EURMVisitor;
import fr.umlv.urm.utilities.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class GeqTest implements SubstitutableCommand {
    public static final String NAME = "GEQ?";
    private final int primaryRegisterIndex;
    private final int secondaryRegisterIndex;
    private final String anchor;

    public GeqTest(int primaryRegisterIndex, int secondaryRegisterIndex, String anchor) {
        this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
        this.secondaryRegisterIndex = Commands.isPositiveInteger(secondaryRegisterIndex);
        this.anchor = Objects.requireNonNull(anchor);
    }

    @Override
    public int maximumRegisterIndex() {
        return Math.max(primaryRegisterIndex, secondaryRegisterIndex);
    }

    @Override
    public List<EURMCommand> substitute(RegisterManager registerManager, AnchorManager anchorManager) {
        int tmpRegisterIndex1 = registerManager.getFixedRegister();
        int tmpRegisterIndex2 = registerManager.getFixedRegister();
        String anchorStart = anchorManager.getRandomAnchor();
        String anchorEnd = anchorManager.getRandomAnchor();

        List<EURMCommand> l = new ArrayList<>();
        l.add(new Zero(tmpRegisterIndex1));
        l.add(new Zero(tmpRegisterIndex2));
        l.add(new Label(anchorStart));
        l.add(new EqTest(secondaryRegisterIndex, tmpRegisterIndex2, anchor));
        l.add(new EqTest(primaryRegisterIndex, tmpRegisterIndex1, anchorEnd));
        l.add(new Inc(tmpRegisterIndex1));
        l.add(new Inc(tmpRegisterIndex2));
        l.add(new Goto(anchorStart));
        l.add(new Label(anchorEnd));

        return l;
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
