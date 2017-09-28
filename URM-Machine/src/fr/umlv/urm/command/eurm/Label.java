package fr.umlv.urm.command.eurm;

import java.util.Objects;

import fr.umlv.urm.command.Anchor;
import fr.umlv.urm.visitor.EURMVisitor;

public final class Label implements Anchor {
	public final static String NAME = "LABEL";
	private final String anchor;
	
	public Label(String anchor) {
		this.anchor = Objects.requireNonNull(anchor);
	}
	
	@Override
	public String getAnchorName() {
		return anchor;
	}
	
	@Override
	public int maximumRegisterIndex() {
		return 0;
	}

	@Override
	public String toString() {
		return NAME + " " + anchor;
	}

	@Override
	public void accept(EURMVisitor v) {
		v.visit(this);
	}
}
