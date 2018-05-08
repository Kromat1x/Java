package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class SumNode extends Node {

	ArrayList<Node> list;

	public SumNode() {
		super("SumNode");
		list = new ArrayList<Node>();
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
