package fr.umlv.urm.program;

import java.util.Iterator;
import java.util.List;

import fr.umlv.urm.command.Command;
import fr.umlv.urm.exception.URMException;

/**
 * 
 * @author vrasquie
 * @version 1
 * @param <T>
 */
public abstract class ProgramAbstract<T extends Command> implements Program<T>, Iterable<T> {
	private final List<T> commands;
	
	/**
	 * ProgramAbstract constructor
	 * 
	 * @param commands
	 */
	public ProgramAbstract(List<T> commands) {
		this.commands = commands;
	}
	
	/**
	 * Check if index of line is a valid one
	 * 
	 * @param index
	 * @return boolean
	 */
	public boolean isValidIndex(int index) {
		return (index > -1 && index < getLength());
	}
	
	@Override
	public T getCommand(int index) throws IllegalArgumentException {
		if (!isValidIndex(index)) throw new IllegalArgumentException();
		return commands.get(index);
	}
	
	@Override
	public int getLength() {
		return commands.size();
	}
	
	@Override
	public int maximumRegisterIndex() throws URMException {
		Iterator<T> it = commands.iterator();
		int maximum = 0;

		while (it.hasNext()) {
			Command command = it.next();
			maximum = Math.max(maximum, command.maximumRegisterIndex());
		}
		
		return maximum;
	}

	@Override
	public Iterator<T> iterator() {
		return commands.iterator();
	}
	
	@Override
	public String toString() {
		Iterator<T> it = iterator();
		StringBuilder sb = new StringBuilder();
		
		while (it.hasNext()) {
			sb.append(it.next().toString()).append('\n');
		}
		
		return sb.toString();
	}
}
