package fr.umlv.urm.command.eurm.factory;

import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.eurm.*;
import fr.umlv.urm.utilities.Commands;

public abstract class EURMFactory {
    @FunctionalInterface
    private interface OneStringBuilder { EURMCommand build(String anchor); };

    @FunctionalInterface
    private interface OneRegisterBuilder { EURMCommand build(int primaryRegisterIndex); };

    @FunctionalInterface
    private interface TwoRegisterBuilder { EURMCommand build(int primaryRegisterIndex, int secondaryRegisterIndex); }

    @FunctionalInterface
    private interface TwoRegisterAnchorBuilder { EURMCommand build(int primaryRegisterIndex, int secondaryRegisterIndex, String anchor); }

    private static EURMCommand oneString(String[] tokens, OneStringBuilder builder) {
        Commands.hasNumberOfArguments(tokens, 2);

        return builder.build(tokens[1]);
    }

    private static EURMCommand oneRegister(String[] tokens, OneRegisterBuilder builder) {
        Commands.hasNumberOfArguments(tokens, 2);
        int primaryRegisterIndex = Commands.isInteger(tokens[1]);

        return builder.build(primaryRegisterIndex);
    }

    private static EURMCommand twoRegister(String[] tokens, TwoRegisterBuilder builder) {
        Commands.hasNumberOfArguments(tokens, 3);
        int primaryRegisterIndex = Commands.isInteger(tokens[1]);
        int secondaryRegisterIndex = Commands.isInteger(tokens[2]);

        return builder.build(primaryRegisterIndex, secondaryRegisterIndex);
    }

    private static EURMCommand twoRegisterAnchor(String[] tokens, TwoRegisterAnchorBuilder builder) {
        Commands.hasNumberOfArguments(tokens, 4);
        int primaryRegisterIndex = Commands.isInteger(tokens[1]);
        int secondaryRegisterIndex = Commands.isInteger(tokens[2]);

        return builder.build(primaryRegisterIndex, secondaryRegisterIndex, tokens[3]);
    }

    public static EURMCommand buildAdd(String[] tokens) {
        return twoRegister(tokens, Add::new);
    }

    public static EURMCommand buildComment(String[] tokens) {
        Commands.hasNumberOfArguments(tokens, 2);
        return new Comment(Commands.join(tokens, 1, tokens.length));
    }

    public static EURMCommand buildCopy(String[] tokens) {
        return twoRegister(tokens, Copy::new);
    }

    public static EURMCommand buildDec(String[] tokens) {
        return oneRegister(tokens, Dec::new);
    }

    public static EURMCommand buildEqTest(String[] tokens) {
        return twoRegisterAnchor(tokens, EqTest::new);
    }

    public static EURMCommand buildGeqTest(String[] tokens) {
        return twoRegisterAnchor(tokens, GeqTest::new);
    }

    public static EURMCommand buildGoto(String[] tokens) {
        return oneString(tokens, Goto::new);
    }

    public static EURMCommand buildInc(String[] tokens) {
        return oneRegister(tokens, Inc::new);
    }

    public static EURMCommand buildLabel(String[] tokens) {
        return oneString(tokens, Label::new);
    }

    public static EURMCommand buildMult(String[] tokens) {
        return twoRegister(tokens, Mult::new);
    }

    public static EURMCommand buildQuit(String[] tokens) {
        Commands.hasNumberOfArguments(tokens, 1);

        return new Quit();
    }

    public static EURMCommand buildSub(String[] tokens) {
        return twoRegister(tokens, Sub::new);
    }

    public static EURMCommand buildZero(String[] tokens) {
        return oneRegister(tokens, Zero::new);
    }

    public static EURMCommand buildZeroTest(String[] tokens) {
        Commands.hasNumberOfArguments(tokens, 3);
        int primaryRegisterIndex = Commands.isInteger(tokens[1]);

        return new ZeroTest(primaryRegisterIndex, tokens[2]);
    }
}
