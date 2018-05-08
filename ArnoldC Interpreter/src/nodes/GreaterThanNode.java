package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class GreaterThanNode extends Node {

	ArrayList<Node> list;

	public GreaterThanNode() {
		super("GreaterThanNode");
		list = new ArrayList<Node>();
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
