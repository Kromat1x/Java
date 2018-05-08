package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class BodyNode extends Node {

	ArrayList<Node> list;
	int type;

	// type 1 is IfBodyNode
	// type 2 is ElseBodyNode
	// type 3 is BodyNode ( for while )
	// this should have a list TODO
	public BodyNode(int type, String name) {
		super(name);
		this.type = type;
		list = new ArrayList<Node>();
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
