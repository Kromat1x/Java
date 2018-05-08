package nodes;

import java.util.ArrayList;
/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class DifferenceNode extends Node {

	ArrayList<Node> list;
	
	public DifferenceNode() {
		super("DifferenceNode");
		list = new ArrayList<Node>();
	}
	
	public void accept(Visitor v) {
        v.visit(this);          
	}

}
