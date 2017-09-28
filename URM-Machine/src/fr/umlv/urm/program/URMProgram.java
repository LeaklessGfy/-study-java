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
import fr.umlv.urm.command.URMCommand;
import fr.umlv.urm.command.urm.Copy;
import fr.umlv.urm.command.urm.Jump;
import fr.umlv.urm.command.urm.Successor;
import fr.umlv.urm.command.urm.Zero;
import fr.umlv.urm.command.urm.factory.URMFactory;
import fr.umlv.urm.exception.URMException;
import fr.umlv.urm.parser.CommandParser;
import fr.umlv.urm.utilities.Programs;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class URMProgram extends ProgramAbstract<URMCommand> {
	/**
	 * URMProgram constructor
	 * 
	 * @param commands
	 */
	public URMProgram(List<URMCommand> commands) {
		super(commands);
	}

	/**
	 * Get available commands for URM program
	 * 
	 * @return map of available commands for URM program
	 */
	private static Map<String, CommandBuilder<URMCommand>> getAvailableCommands() {
		Map<String, CommandBuilder<URMCommand>> available = new HashMap<>();
		available.put(Copy.NAME, URMFactory::buildCopy);
		available.put(Jump.NAME, URMFactory::buildJump);
		available.put(Successor.NAME, URMFactory::buildSuccessor);
		available.put(Zero.NAME, URMFactory::buildZero);

		return available;
	}
	
	/**
	 * Load an URM program based on list of strings
	 * 
	 * @param commands
	 * @return URMProgram
	 * @throws URMException
	 */
	public static URMProgram load(List<String> commands) throws URMException {
		if (commands.size() < 1) {
			throw new URMException("List of commands is empty");
		}
		
		Iterator<String> it = commands.iterator();
		List<URMCommand> compiledCommands = new ArrayList<>();
		
		Map<String, CommandBuilder<URMCommand>> available = getAvailableCommands();
		CommandParser<URMCommand> cb = new CommandParser<>(available);
		
		while (it.hasNext()) {
			URMCommand command = cb.parse(it.next());
			compiledCommands.add(command);
		}
		
		return new URMProgram(compiledCommands);
	}
	
	/**
	 * Load an URM program based on filename
	 * 
	 * @param filename
	 * @return URMProgram
	 * @throws FileNotFoundException
	 * @throws URMException
	 */
	public static URMProgram load(String filename) throws FileNotFoundException, URMException {
		List<String> list;

		try {
			list = Programs.parseFile(filename, "urm");
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
