package fr.umlv.urm.command.eurm;

import java.util.Objects;

import fr.umlv.urm.command.EURMCommand;
import fr.umlv.urm.visitor.EURMVisitor;

public final class Comment implements EURMCommand {
	public static final String NAME = "COMMENT";
	private final String comment;
	
	public Comment(String comment) {
		this.comment = Objects.requireNonNull(comment);
	}
	
	@Override
	public int maximumRegisterIndex() {
		return 0;
	}
	
	@Override
	public String toString() {
		return NAME + " " + comment;
	}

	@Override
	public void accept(EURMVisitor v) {
		v.visit(this);
	}
}
