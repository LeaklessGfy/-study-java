package fr.umlv.urm.command.urm;

import fr.umlv.urm.RegisterContainer;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.utilities.Commands;

import java.math.BigInteger;

public final class Copy implements URMCommand {
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
	public int run(RegisterContainer registerContainer, int commandPointer) throws URMException {
		BigInteger secondaryRegister = registerContainer.get(secondaryRegisterIndex);
		registerContainer.put(primaryRegisterIndex, secondaryRegister);

		return commandPointer + 1;
	}

	@Override
	public String toString() {
		return NAME + " " + primaryRegisterIndex + " " + secondaryRegisterIndex;
	}
}
