package fr.umlv.urm.program;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.umlv.urm.command.CommandBuilder;
import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.eurm.*;
import fr.umlv.urm.command.eurm.factory.EURMFactory;
import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.parser.CommandParser;
import fr.umlv.urm.utilities.Programs;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class EURMProgram extends ProgramAbstract<EURMCommand> {
	/**
	 * EURMProgram constructor
	 * 
	 * @param commands
	 */
	public EURMProgram(List<EURMCommand> commands) {
		super(commands);
	}

	/**
	 * Get available commands for EURM program
	 * 
	 * @return map of available commands for EURM program
	 */
	private static Map<String, CommandBuilder<EURMCommand>> getAvailableCommands() {
		Map<String, CommandBuilder<EURMCommand>> available = new HashMap<>();
		available.put(Add.NAME, EURMFactory::buildAdd);
		available.put(Comment.NAME, EURMFactory::buildComment);
		available.put(Copy.NAME, EURMFactory::buildCopy);
		available.put(Dec.NAME, EURMFactory::buildDec);
		available.put(EqTest.NAME, EURMFactory::buildEqTest);
		available.put(GeqTest.NAME, EURMFactory::buildGeqTest);
		available.put(Goto.NAME, EURMFactory::buildGoto);
		available.put(Inc.NAME, EURMFactory::buildInc);
		available.put(Label.NAME, EURMFactory::buildLabel);
		available.put(Mult.NAME, EURMFactory::buildMult);
		available.put(Quit.NAME, EURMFactory::buildQuit);
		available.put(Sub.NAME, EURMFactory::buildSub);
		available.put(Zero.NAME, EURMFactory::buildZero);
		available.put(ZeroTest.NAME, EURMFactory::buildZeroTest);

		return available;
	}
	
	/**
	 * Load an EURM program based on list of strings
	 * 
	 * @param commands
	 * @return EURMProgram
	 * @throws URMException
	 */
	public static EURMProgram load(List<String> commands) throws URMException {
		if (commands.size() < 1) {
			throw new URMException("List of commands is empty");
		}

		Iterator<String> it = commands.iterator();
		List<EURMCommand> compiledCommands = new ArrayList<>();

		Map<String, CommandBuilder<EURMCommand>> available = getAvailableCommands();
		CommandParser<EURMCommand> cb = new CommandParser<>(available);
		
		while (it.hasNext()) {
			EURMCommand command = cb.parse(it.next());
			compiledCommands.add(command);
		}
		
		return new EURMProgram(compiledCommands);
	}
	
	/**
	 * Load an EURM program based on filename
	 * 
	 * @param filename
	 * @return EURMProgram
	 * @throws FileNotFoundException
	 * @throws URMException
	 */
	public static EURMProgram load(String filename) throws FileNotFoundException, URMException {
		List<String> list;
		
		try {
			list = Programs.parseFile(filename, "eurm");
		} catch (NoSuchFileException e) {
			throw new URMException("File not found");
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw new URMException("Unexpected error");
		}
		
		return load(list);
	}
}
