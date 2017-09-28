package fr.umlv.urm.command.eurm;

import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.command.urm.Copy;
import fr.umlv.urm.command.urm.Jump;
import fr.umlv.urm.command.urm.Successor;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;
import fr.umlv.urm.visitor.EURMVisitor;
import fr.umlv.urm.utilities.Commands;

import java.util.ArrayList;
import java.util.List;

public final class Dec implements CompilableCommand {
    public static final String NAME = "DEC";
    private final int primaryRegisterIndex;

    public Dec(int primaryRegisterIndex) {
        this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
    }

    @Override
    public int maximumRegisterIndex() {
        return primaryRegisterIndex;
    }

    @Override
    public List<URMCommand> getInstructions(int lineNumber, int length, RegisterManager registerManager, AnchorManager anchorManager) {
        int tmpRegisterIndex = registerManager.getTemporaryRegister();
        int tmpRegisterIndex2 = tmpRegisterIndex + 1;

        List<URMCommand> l = new ArrayList<>();
        l.add(new fr.umlv.urm.command.urm.Zero(tmpRegisterIndex));
        l.add(new fr.umlv.urm.command.urm.Zero(tmpRegisterIndex2));
        l.add(new Jump(primaryRegisterIndex, tmpRegisterIndex, -1));
        l.add(new Jump(primaryRegisterIndex, tmpRegisterIndex, lineNumber + 8));
        l.add(new Successor(tmpRegisterIndex));
        l.add(new Jump(primaryRegisterIndex, tmpRegisterIndex, lineNumber + 8));
        l.add(new Successor(tmpRegisterIndex2));
        l.add(new Jump(tmpRegisterIndex, tmpRegisterIndex, lineNumber + 4));
        l.add(new Copy(primaryRegisterIndex, tmpRegisterIndex2));

        return l;
    }

    @Override
    public int getNumberOfInstructions() {
        return 9;
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
