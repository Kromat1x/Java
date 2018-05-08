package nodes;
/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class LvalNode extends Node {

	String varName;
	
	public LvalNode(String varName) {
		super("LvalNode");
		this.varName = varName;
	}
	
	public LvalNode() {
		super("LvalNode");
	}
	
	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}
	
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
