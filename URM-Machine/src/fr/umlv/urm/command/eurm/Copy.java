package fr.umlv.urm.command.eurm;

import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.visitor.EURMVisitor;
import fr.umlv.urm.utilities.Commands;

import java.util.ArrayList;
import java.util.List;

public final class Copy implements CompilableCommand {
    public static final String NAME = "COPY";
    private final int primaryRegisterIndex;
    private final int secondaryRegisterIndex;

    public Copy(int primaryRegisterIndex, int secondaryRegisterIndex) {
        this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
        this.secondaryRegisterIndex = Commands.isPositiveInteger(secondaryRegisterIndex);
    }

    @Override
    public int maximumRegisterIndex() {
        return Math.max(primaryRegisterIndex, secondaryRegisterIndex);
    }

    @Override
    public List<URMCommand> getInstructions(int lineNumber, int length, RegisterManager registerManager, AnchorManager anchorManager) {
        List<URMCommand> l = new ArrayList<>();
        l.add(new fr.umlv.urm.command.urm.Copy(primaryRegisterIndex, secondaryRegisterIndex));
        return l;
    }

    @Override
    public int getNumberOfInstructions() {
        return 1;
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
