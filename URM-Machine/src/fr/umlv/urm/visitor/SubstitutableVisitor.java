package fr.umlv.urm.visitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import fr.umlv.urm.command.Anchor;
import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.command.SubstitutableCommand;
import fr.umlv.urm.command.CompilableCommand;
import fr.umlv.urm.manager.AnchorManager;
import fr.umlv.urm.manager.RegisterManager;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class SubstitutableVisitor implements EURMVisitor {
	private final List<EURMCommand> commands;
	private final RegisterManager registerManager;
	private final AnchorManager anchorManager;
	
	/**
	 * SubstitutableVisitor constructor
	 * 
	 * @param registerManager
	 * @param anchorManager
	 */
	public SubstitutableVisitor(RegisterManager registerManager, AnchorManager anchorManager) {
		this.commands = new LinkedList<>();
		this.registerManager = Objects.requireNonNull(registerManager);
		this.anchorManager = Objects.requireNonNull(anchorManager);
	}

	@Override
	public void visit(Anchor cmd) {
		if (cmd == null) {
			throw new IllegalArgumentException("SubstitutableVisitor - Can't visit null command");
		}

		commands.add(cmd);
	}

	@Override
	public void visit(SubstitutableCommand cmd) {
		if (cmd == null) {
			throw new IllegalArgumentException("SubstitutableVisitor - Can't visit null command");
		}

		List<EURMCommand> l = cmd.substitute(registerManager, anchorManager);

		if (l == null) {
			throw new IllegalArgumentException("Null list of instructions is not allowed in : " + cmd);
		}

		l.forEach(c -> c.accept(this));
	}

	@Override
	public void visit(CompilableCommand cmd) {
		if (cmd == null) {
			throw new IllegalArgumentException("SubstitutableVisitor -Can't visit null command");
		}

		commands.add(cmd);
	}

	@Override
	public void visit(EURMCommand cmd) {
		if (cmd == null) {
			throw new IllegalArgumentException("SubstitutableVisitor - Can't visit null command");
		}

		commands.add(cmd);
	}
	
	/**
	 * Get compilable commands
	 * 
	 * @return compilable commands
	 */
	public List<EURMCommand> getCommands() {
		return commands;
	}
}
