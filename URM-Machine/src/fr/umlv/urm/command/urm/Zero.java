package fr.umlv.urm.command.urm;

import fr.umlv.urm.RegisterContainer;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.utilities.Commands;

import java.math.BigInteger;

public final class Zero implements URMCommand {
	public static final String NAME = "ZERO";
	private final int primaryRegisterIndex;

	public Zero(int primaryRegisterIndex) {
		this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
	}
	
	@Override
	public int maximumRegisterIndex() {
		return primaryRegisterIndex;
	}
	
	@Override
	public int run(RegisterContainer registerContainer, int commandPointer) {
		registerContainer.put(primaryRegisterIndex, BigInteger.ZERO);

		return commandPointer + 1;
	}
	
	@Override
	public String toString() {
		return NAME + " " + primaryRegisterIndex;
	}
}
