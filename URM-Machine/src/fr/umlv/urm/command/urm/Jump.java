package fr.umlv.urm.command.urm;

import fr.umlv.urm.RegisterContainer;
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.utilities.Commands;

import java.math.BigInteger;

public final class Jump implements URMCommand {
	public static final String NAME = "JUMP";
	private final int primaryRegisterIndex;
	private final int secondaryRegisterIndex;
	private final int commandPointer;
	
	public Jump(int primaryRegisterIndex, int secondaryRegisterIndex, int commandPointer) {
		this.primaryRegisterIndex = Commands.isPositiveInteger(primaryRegisterIndex);
		this.secondaryRegisterIndex = Commands.isPositiveInteger(secondaryRegisterIndex);
		this.commandPointer = commandPointer;
	}
	
	@Override
	public int maximumRegisterIndex() {
		return Math.max(primaryRegisterIndex, secondaryRegisterIndex);
	}

	@Override
	public int run(RegisterContainer registerContainer, int commandPointer) throws URMException {
		BigInteger primaryRegister = registerContainer.get(primaryRegisterIndex);
		BigInteger secondaryRegister = registerContainer.get(secondaryRegisterIndex);
		
		if (primaryRegister.compareTo(secondaryRegister) == 0) {
			return this.commandPointer;
		}
		
		return commandPointer + 1;
	}

	@Override
	public String toString() {
		return NAME + " " + primaryRegisterIndex + " " + secondaryRegisterIndex + " " + commandPointer;
	}
}
