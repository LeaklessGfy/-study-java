package fr.umlv.urm.command.urm.factory;

import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.command.urm.Copy;
import fr.umlv.urm.command.urm.Jump;
import fr.umlv.urm.command.urm.Successor;
import fr.umlv.urm.command.urm.Zero;
import fr.umlv.urm.utilities.Commands;

public abstract class URMFactory {
    @FunctionalInterface
    private interface OneRegisterBuilder { URMCommand build(int primaryRegisterIndex); };

    private static URMCommand oneRegister(String[] tokens, OneRegisterBuilder builder) {
        Commands.hasNumberOfArguments(tokens, 2);
        int primaryRegisterIndex = Commands.isInteger(tokens[1]);

        return builder.build(primaryRegisterIndex);
    }

    public static URMCommand buildCopy(String[] tokens) {
        Commands.hasNumberOfArguments(tokens, 3);
        int primaryRegisterIndex = Commands.isInteger(tokens[1]);
        int secondaryRegisterIndex = Commands.isInteger(tokens[2]);

        return new Copy(primaryRegisterIndex, secondaryRegisterIndex);
    }

    public static URMCommand buildJump(String[] tokens) {
        Commands.hasNumberOfArguments(tokens, 4);
        int primaryRegisterIndex = Commands.isInteger(tokens[1]);
        int secondaryRegisterIndex = Commands.isInteger(tokens[2]);
        int commandPointer = Commands.isInteger(tokens[3]);

        return new Jump(primaryRegisterIndex, secondaryRegisterIndex, commandPointer);
    }

    public static URMCommand buildSuccessor(String[] tokens) {
        return oneRegister(tokens, Successor::new);
    }

    public static URMCommand buildZero(String[] tokens) {
        return oneRegister(tokens, Zero::new);
    }
}
