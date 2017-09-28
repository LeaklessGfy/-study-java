package fr.umlv.urm.parser;

import java.util.HashMap;
import java.util.Map;

import fr.umlv.urm.command.Command;
import fr.umlv.urm.command.CommandBuilder;
import fr.umlv.urm.exception.URMException;

/**
 * 
 * @author vrasquie
 * @version 1
 * @param <T>
 */
public final class CommandParser<T extends Command> {
	private final Map<String, CommandBuilder<T>> availableCommands;
	
	/**
	 * CommandParser constructor
	 * 
	 * @param availableCommands
	 */
	public CommandParser(Map<String, CommandBuilder<T>> availableCommands) {
		this.availableCommands = new HashMap<>();
		this.availableCommands.putAll(availableCommands);
	}
	
	/**
	 * Parse a specific full command
	 * 
	 * @param command
	 * @return Command
	 * @throws URMException
	 */
	public T parse(String command) throws URMException {
		String[] tokens = command.split("\\s+");
		
		if (tokens.length < 1) {
			throw new IllegalArgumentException("Command is empty");
		}
		
		CommandBuilder<T> builder = availableCommands.get(tokens[0].toUpperCase());
		if (builder == null) throw new URMException("No command available for this token " + tokens[0]);
		
		return builder.build(tokens);
	}
}
