package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class MultiplicationNode extends Node {

	ArrayList<Node> list;

	public MultiplicationNode() {

		super("MultiplicationNode");
		list = new ArrayList<Node>();

	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
