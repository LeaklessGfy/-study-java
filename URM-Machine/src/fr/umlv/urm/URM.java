package fr.umlv.urm;

import java.math.BigInteger;
import java.util.*;

import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.program.URMProgram;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class URM {
	private final Mode mode;
	private int commandPointer;
	private int stage;
	private RegisterContainer registerContainer;
	
	/**
	 * URM constructor
	 * 
	 * @param mode
	 */
	public URM(Mode mode) {
		this.mode = mode;
	}

	/**
	 * Check if loop is detect
	 * 
	 * @param formers
	 * @param stateView
	 * @throws URMException
	 */
	private void loopDetect(List<StateView> formers, StateView stateView) throws URMException {
		Iterator<StateView> it = formers.iterator();

		while (it.hasNext()) {
			StateView former = it.next();

			if (former.equals(stateView)) {
				throw new URMException("LOOP DETECT");
			}
		}
	}
	
	/**
	 * Run program
	 * 
	 * @param program
	 * @param registers
	 * @return value of the first register
	 * @throws URMException
	 */
	public BigInteger run(URMProgram program, Map<Integer, BigInteger> registers) throws URMException {
		List<StateView> l = new ArrayList<>();
		this.registerContainer = new RegisterContainer(registers, mode);
		this.commandPointer = 0;
		this.stage = 0;

		while (program.isValidIndex(commandPointer)) {
			URMCommand cmd = program.getCommand(commandPointer);
			commandPointer = cmd.run(this.registerContainer, commandPointer);
			stage++;

			if (mode == Mode.LOOPDETECT) {
				StateView stateView = getStateView();

				if (stage > 1) {
					loopDetect(l, stateView);
				}

				l.add(stateView);
			}
		}

		if (commandPointer < 0) {
			throw new URMException("Invalid negative value");
		}

		return this.registerContainer.get(1);
	}
	
	/**
	 * Run with trace program
	 * 
	 * @param program
	 * @param registers
	 * @return list of all StateView
	 * @throws URMException
	 */
	public List<StateView> runTrace(URMProgram program, Map<Integer, BigInteger> registers) throws URMException {
		List<StateView> l = new ArrayList<>();
		this.registerContainer = new RegisterContainer(registers, mode);
		this.commandPointer = 0;
		this.stage = 0;
		
		while (program.isValidIndex(commandPointer)) {
			URMCommand cmd = program.getCommand(commandPointer);
			commandPointer = cmd.run(this.registerContainer, commandPointer);
			stage++;
			
			StateView stateView = getStateView();

			if (mode == Mode.LOOPDETECT && stage > 1) {
				loopDetect(l, stateView);
			}

			l.add(stateView);
		}

		if (commandPointer < 0) {
			throw new URMException("Invalid negative value");
		}
		
		return l;
	}
	
	/**
	 * Get a StateView
	 * 
	 * @return StateView
	 */
	public StateView getStateView() {
		return new StateView(mode, commandPointer, stage, registerContainer);
	}
}
