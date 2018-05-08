package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class EqualToNode extends Node {

	ArrayList<Node> list;

	public EqualToNode() {
		super("EqualToNode");
		list = new ArrayList<Node>();
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
