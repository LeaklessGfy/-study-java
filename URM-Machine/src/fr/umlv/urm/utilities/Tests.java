package fr.umlv.urm.utilities;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import fr.umlv.urm.URM;
import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.program.URMProgram;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class Tests {
	/**
	 * Run test
	 * 
	 * @param urm
	 * @param program
	 * @param registersMap
	 * @throws URMException
	 */
	private static void run(URM urm, URMProgram program, Map<Integer, BigInteger> registersMap) throws URMException {
		System.out.println("init with registers=" + registersMap);
		BigInteger output = urm.run(program, registersMap);
		System.out.println("output=" + output);
		System.out.println("URM state view=" + urm.getStateView());
		System.out.println("\n");
	}
	
	/**
	 * Test program with only one initialized register 1
	 * 
	 * @param urm
	 * @param program
	 * @throws URMException
	 */
	public static void testWithInitRegister1(URM urm, URMProgram program) throws URMException {
		Map<Integer, BigInteger> registersMap = null;
		
		for (int i = 0; i < 5; i++) {
			registersMap = new HashMap<>();
			registersMap.put(1, BigInteger.valueOf(i));
			run(urm, program, registersMap);
		}
	}
	
	/**
	 * Test program with two initialized register
	 * 
	 * @param urm
	 * @param program
	 * @throws URMException
	 */
	public static void testWithInitRegister1Register2(URM urm, URMProgram program) throws URMException {
		Map<Integer, BigInteger> registersMap = null;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				registersMap = new HashMap<>();
				registersMap.put(1, BigInteger.valueOf(i));
				registersMap.put(2, BigInteger.valueOf(j));
				run(urm, program, registersMap);
			}
		}
	}

	/**
	 * Test program with one initialized register with BIG integer value
	 * 
	 * @param urm
	 * @param program
	 * @throws URMException
	 */
	public static void testWithBigRegisters(URM urm, URMProgram program) throws URMException {
		Map<Integer, BigInteger> registersMap = new HashMap<>();
		BigInteger v = new BigInteger("55555555555555555555555");
		registersMap.put(1, v);
		run(urm, program, registersMap);
	}

	/**
	 * Test program with one initialized register with error value
	 *
	 * @param urm
	 * @param program
	 * @throws URMException
	 */
	public static void testWithError(URM urm, URMProgram program) throws URMException {
		Map<Integer, BigInteger> registersMap = new HashMap<>();
		BigInteger v = new BigInteger("-55");
		registersMap.put(1, v);
		run(urm, program, registersMap);
	}

	/**
	 * Test program with random initialized register
	 *
	 * @param urm
	 * @param program
	 * @throws URMException
	 */
	public static void testWithRandom(URM urm, URMProgram program) throws URMException {
		Map<Integer, BigInteger> registersMap = new HashMap<>();

		for (int i = 0; i < 5; i++) {
			Random rand = new Random();
			int r = rand.nextInt(6) + 1;
			int  n = rand.nextInt(20);

			registersMap.put(r, BigInteger.valueOf(n));
		}

		run(urm, program, registersMap);
	}
}
