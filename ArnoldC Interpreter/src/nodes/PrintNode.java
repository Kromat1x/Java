package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class PrintNode extends Node {

	public StringNode str;
	public ConstantNode cons;
	public VariableNode var;
	public int type; // 1 is for String - 2 is for Constant - 3 is for Variable

	public PrintNode() {
		super("PrintNode");
		str = new StringNode();
		cons = new ConstantNode();
		var = new VariableNode();
	}

	public StringNode getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str.text = str;
	}

	public ConstantNode getCons() {
		return cons;
	}

	public void setCons(int cons) {
		this.cons.value = cons;

	}

	public VariableNode getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var.varName = var;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
