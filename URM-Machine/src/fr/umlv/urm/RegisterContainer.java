package fr.umlv.urm;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.utilities.Registers;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class RegisterContainer implements Cloneable {
	private final Map<Integer, BigInteger> registers;
	private final Mode mode;

	/**
	 * RegisterContainer constructor
	 * 
	 * @param registers
	 * @param mode
	 * @throws URMException
	 */
	public RegisterContainer(Map<Integer, BigInteger> registers, Mode mode) throws URMException {
		this.registers = Registers.validRegisters(Registers.clone(registers));
		this.mode = mode;
	}
	
	/**
	 * Get the content of the register at specified index
	 * 
	 * @param index the index of the register to get
	 * @return value of the register
	 * @throws URMException
	 */
	public BigInteger get(int index) throws URMException {
		BigInteger value = registers.get(index);
		
		if (value != null) {
			return value;
		}

		if (mode == Mode.STRICT) {
			throw new URMException("Register to index " + index + " is not initialized");
		}

		BigInteger defaultValue = BigInteger.ZERO;
		registers.put(index, defaultValue);
		
		return defaultValue;
	}
	
	/**
	 * Put content in the register at specified index
	 * 
	 * @param index the index of the register where to put the new value
	 * @param value the value to put in the register
	 */
	public void put(int index, BigInteger value) {
		registers.put(index, value);
	}
	
	/**
	 * Get all the registers
	 * 
	 * @return all registers of the container
	 */
	public Map<Integer, BigInteger> getAll() {
		return registers;
	}

	@Override
	public RegisterContainer clone() {
		try {
			return new RegisterContainer(registers, mode);
		} catch (URMException e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RegisterContainer that = (RegisterContainer) o;

		if (that.registers.size() != this.registers.size()) {
			return false;
		}

		if (that.hashCode() != this.hashCode()) {
			return false;
		}

		Set<Entry<Integer, BigInteger>> set = registers.entrySet();
		Iterator<Entry<Integer, BigInteger>> it = set.iterator();

		while (it.hasNext()) {
			Entry<Integer, BigInteger> e = it.next();

			if (!that.registers.containsKey(e.getKey())) {
				return false;
			}

			if (!that.registers.containsValue(e.getValue())) {
				return false;
			}
		}

		return registers.equals(that.registers) && mode == that.mode;
	}

	@Override
	public int hashCode() {
		int result = registers != null ? registers.hashCode() : 0;
		result = 31 * result + (mode != null ? mode.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Register{");
		
		Set<Entry<Integer, BigInteger>> s = registers.entrySet();
		Iterator<Entry<Integer, BigInteger>> it = s.iterator();
		
		while (it.hasNext()) {
			Entry<Integer, BigInteger> e = it.next();
			sb.append("R").append(e.getKey()).append("=").append(e.getValue());
			if (it.hasNext()) sb.append(", ");
		}
		
		sb.append("}");
		
		return sb.toString();
	}
}
