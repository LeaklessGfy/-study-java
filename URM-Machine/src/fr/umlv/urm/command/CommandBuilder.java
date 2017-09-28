package fr.umlv.urm.command;

/**
 * 
 * @author vrasquie
 * @version 1
 * @param <T extends Command>
 */
@FunctionalInterface
public interface CommandBuilder<T extends Command> {
	/**
	 * Build the command based on string array
	 * 
	 * @param tokens array of string representing command
	 * @return the command
	 */
	T build(String[] tokens);
}
