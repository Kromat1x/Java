package nodes;

import java.util.ArrayList;
/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class DivisionNode extends Node{

	ArrayList<Node> list;
	
	public DivisionNode() {
		super("DivisionNode");
		list = new ArrayList<Node>();
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
