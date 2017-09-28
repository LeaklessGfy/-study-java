package fr.umlv.urm.utilities;

import fr.umlv.urm.exception.URMException;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class Registers {
	/**
	 * Check if registers are valid one (superior to 0)
	 * 
	 * @param registers
	 * @return registers
	 * @throws URMException
	 */
    public static Map<Integer, BigInteger> validRegisters(Map<Integer, BigInteger> registers) throws URMException {
        Set<Map.Entry<Integer, BigInteger>> set = registers.entrySet();
        Iterator<Map.Entry<Integer, BigInteger>> it = set.iterator();

        while (it.hasNext()) {
            Map.Entry<Integer, BigInteger> e = it.next();
            if (e.getKey() < 0) throw new URMException("Invalid negative register key");
            if (e.getValue().compareTo(BigInteger.ZERO) < 0) throw new URMException("Invalid negative register value");
        }

        return registers;
    }

    /**
     * Clone map of registers
     * 
     * @param registers
     * @return new registers
     */
    public static Map<Integer, BigInteger> clone(Map<Integer, BigInteger> registers) {
        Map<Integer, BigInteger> defensive = new HashMap<>();
        defensive.putAll(registers);

        return defensive;
    }
}
