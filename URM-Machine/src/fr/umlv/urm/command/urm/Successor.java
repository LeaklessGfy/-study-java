package fr.umlv.urm.command.urm;

import fr.umlv.urm.RegisterContainer;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.utilities.Commands;

import java.math.BigInteger;

public final class Successor implements URMCommand {
	public static final String NAME = "SUCCESSOR";
	private final int primaryRegisterIndex;

	public Successor(int primaryRegisterIndex) {
		this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
	}
	
	@Override
	public int maximumRegisterIndex() {
		return primaryRegisterIndex;
	}

	@Override
	public int run(RegisterContainer registerContainer, int commandPointer) throws URMException {
		BigInteger primaryRegister = registerContainer.get(primaryRegisterIndex);
		registerContainer.put(primaryRegisterIndex, primaryRegister.add(BigInteger.ONE));

		return commandPointer + 1;
	}
	
	@Override
	public String toString() {
		return NAME + " " + primaryRegisterIndex;
	}
}
