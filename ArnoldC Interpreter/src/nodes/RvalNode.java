package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class RvalNode extends Node {
	String varName;

	public RvalNode(String varName) {
		super("RvalNode");
		this.varName = varName;
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
