package nodes;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class MainNode extends Node {
	
	List<Node> list;
	
	public MainNode() {
		super("MainNode");
		this.list = new ArrayList<Node>();
	}
	
	public void add(Node n) {
		this.list.add(n);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
}
