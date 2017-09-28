package fr.umlv.urm.visitor;

import fr.umlv.urm.command.*;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;

import java.util.*;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class CompilerVisitor implements EURMVisitor {
    private final List<URMCommand> commands;
    private int lineNumber;
    private final int length;
    private final RegisterManager registerManager;
    private final AnchorManager anchorManager;

    /**
     * CompilerVisitor constructor
     * 
     * @param length
     * @param registerManager
     * @param anchorManager
     */
    public CompilerVisitor(int length, RegisterManager registerManager, AnchorManager anchorManager) {
        this.commands = new ArrayList<>();
        this.lineNumber = 0;
        this.length = length;
        this.registerManager = Objects.requireNonNull(registerManager);
        this.anchorManager = Objects.requireNonNull(anchorManager);
    }

    @Override
    public void visit(Anchor cmd) {

    }

    @Override
    public void visit(SubstitutableCommand cmd) {

    }

    @Override
    public void visit(CompilableCommand cmd) {
        if (cmd == null) {
            throw new IllegalArgumentException("CompilerVisitor - Can't visit null command");
        }

        List<URMCommand> l = cmd.getInstructions(lineNumber, length, registerManager, anchorManager);

        if (l == null) {
            throw new IllegalArgumentException("Null list of instructions is not allowed in : " + cmd);
        }

        l.forEach(c -> {
            if (c == null) {
                throw new IllegalArgumentException("Null instruction is not allowed in : " + cmd);
            }

            commands.add(c);
        });

        lineNumber += cmd.getNumberOfInstructions();
    }

    @Override
    public void visit(EURMCommand cmd) {

    }

    /**
     * Get compiled commands
     * 
     * @return list of compiled commands
     */
    public List<URMCommand> getCommands() {
        return commands;
    }
}
