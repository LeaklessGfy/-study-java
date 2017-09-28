package fr.umlv.urm.test;

import fr.umlv.urm.Mode;
import fr.umlv.urm.URM;
import fr.umlv.urm.compiler.Compiler;
import fr.umlv.urm.program.EURMProgram;
import fr.umlv.urm.program.URMProgram;
import fr.umlv.urm.utilities.Programs;
import fr.umlv.urm.utilities.Tests;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public class Main {
	/**
	 * Start point of URM program
	 * 
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args ) throws Exception {
    	String filename = "";
    	Mode mode = Mode.STRICT;
    	String registry = "1";

    	for (int i = 0; i < args.length; i++) {
    		if (args[i].charAt(0) == '-') {
    			switch (args[i].charAt(1)) {
					case 'f':
						filename = args[i + 1];
						break;
					case 'm':
						switch (args[i + 1].toUpperCase()) {
							case "SAFE":
								mode = Mode.SAFE;
								break;
							case "STRICT":
								mode = Mode.STRICT;
								break;
							case "LOOPDETECT":
								mode = Mode.LOOPDETECT;
								break;
						}
						break;
					case 'r':
						registry = args[i + 1];
						break;
				}
			}
		}

		if (filename.isEmpty()) {
			System.out.println("USAGE: -f path/to/filename -m [SAFE/STRICT/LOOPDETECT] -r [1/2/3/4/5]");
			return;
		}

		if (Programs.checkExtension(filename, "urm")) {
			urm(filename, mode, registry);
			return;
		}

		if (Programs.checkExtension(filename, "eurm")) {
			eurm(filename, mode, registry);
			return;
		}

		throw new IllegalArgumentException(filename + " not a valid file");
    }
    
    /**
     * Start urm program based on filename and mode
     * 
     * @param filename
     * @param mode
     * @throws Exception
     */
    private static void urm(String filename, Mode mode, String registry) throws Exception {
    	URMProgram urmProgram = URMProgram.load(filename);
    	System.out.println("URM Program (" + urmProgram.getLength() + " instructions) : \n" + urmProgram);
    	
    	URM urm = new URM(mode);
    	exec(urm, urmProgram, registry);
	}

    /**
     * Start eurm program based on filename and mode
     * 
     * @param filename
     * @param mode
     * @throws Exception
     */
    private static void eurm(String filename, Mode mode, String registry) throws Exception {
		EURMProgram eurmProgram = EURMProgram.load(filename);
		System.out.println("EURM program (" + eurmProgram.getLength() + " instructions) : \n" + eurmProgram);
		
		URMProgram urmProgram = Compiler.compile(eurmProgram);
		System.out.println("URM program (" + urmProgram.getLength() + " instructions) : \n" + urmProgram);
		
		URM urm = new URM(mode);
		exec(urm, urmProgram, registry);
	}

	/**
	 * Exec a urm program with right register mode
	 *
	 * @param urm
	 * @param urmProgram
	 * @param registry
	 * @throws Exception
	 */
	private static void exec(URM urm, URMProgram urmProgram, String registry) throws Exception {
		switch (registry) {
			case "1":
				Tests.testWithInitRegister1(urm, urmProgram);
				break;
			case "2":
				Tests.testWithInitRegister1Register2(urm, urmProgram);
				break;
			case "3":
				Tests.testWithBigRegisters(urm, urmProgram);
                break;
			case "4":
				Tests.testWithError(urm, urmProgram);
                break;
			case "5":
				Tests.testWithRandom(urm, urmProgram);
		}
	}
}
