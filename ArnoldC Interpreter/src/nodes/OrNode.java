package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class OrNode extends Node {

	ArrayList<Node> list;

	public OrNode() {

		super("OrNode");
		list = new ArrayList<Node>();

	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
