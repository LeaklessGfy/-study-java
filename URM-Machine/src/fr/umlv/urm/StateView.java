package fr.umlv.urm;

import java.math.BigInteger;
import java.util.Map;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class StateView {
	private final int commandPointer;
	private final Mode mode;
	private final int stage;
	private final RegisterContainer registerContainer;
	
	/**
	 * StateView constructor
	 * 
	 * @param mode
	 * @param commandPointer
	 * @param stage
	 * @param registerContainer
	 */
	public StateView(Mode mode, int commandPointer, int stage, RegisterContainer registerContainer) {
		this.mode = mode;
		this.commandPointer = commandPointer;
		this.stage = stage;
		this.registerContainer = registerContainer.clone();
	}
	
	/**
	 * Get command pointer
	 * 
	 * @return commandPointer
	 */
	public int getCommandPointer() {
		return commandPointer;
	}
	
	/**
	 * Get mode
	 * 
	 * @return mode
	 */
	public Mode getMode() {
		return mode;
	}
	
	/**
	 * Get stage
	 * 
	 * @return stage
	 */
	public int getStage() {
		return stage;
	}
	
	/**
	 * Get all registers
	 * 
	 * @return all registers
	 */
	public Map<Integer, BigInteger> getRegisters() {
		return registerContainer.getAll();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StateView stateView = (StateView) o;

		if (stateView.hashCode() != this.hashCode()) {
			return false;
		}

		return commandPointer == stateView.commandPointer && mode == stateView.mode && (registerContainer != null ? registerContainer.equals(stateView.registerContainer) : stateView.registerContainer == null);
	}

	@Override
	public int hashCode() {
		int result = commandPointer;
		result = 31 * result + (mode != null ? mode.hashCode() : 0);
		result = 31 * result + (registerContainer != null ? registerContainer.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return  "StateView{registerContainer=" +
				registerContainer +
				", command pointer=" + commandPointer +
				", stage=" + stage +
				", mode=" + mode + "}";
	}
}
