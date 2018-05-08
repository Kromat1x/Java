package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class PrintingVisitor implements Visitor {

	String tabs = "";
	ArrayList<String> savedTabs = new ArrayList<String>();
	public String output = "";

	/**
	 * the PrintingVisitor implements the Visitor, therefore all the visit
	 * methods in it, for every type of node. The visitor is used recursively,
	 * for example in the main it is used a list to iterate through the children
	 * and each one of them is visited. Also, for each node visited at least
	 * it's name is printed, so unlike the interpreter visitor, this one has
	 * output for every single node The indentation is done by saving a string
	 * tabs which is gets bigger every time we go down a level in the tree but
	 * it's also saved in the list of savedTabs because we might go back to the
	 * previous level because of the recursive use of the method and we need to
	 * use the same indentation for all the nodes on a certain level. So between
	 * visiting the children we get the tabs from the savedTabs, because the
	 * tabs string was modified in the call of the previous child
	 */
	@Override
	public void visit(MainNode n) {
		int index;
		output = output + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		for (int i = 0; i < ((MainNode) n).list.size(); i++) {
			Node aux = n.list.get(i);
			tabs = savedTabs.get(index);
			aux.accept(this);
		}

	}

	@Override
	public void visit(DeclareNode n) {
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		(n.n).accept(this);
		(n.c).accept(this);
	}

	@Override
	public void visit(StringNode n) {
		output = output + tabs + n.name + " <" + n.text + ">" + "\n";
	}

	@Override
	public void visit(ConstantNode n) {
		output = output + tabs + n.name + " <" + n.value + ">" + "\n";
	}

	@Override
	public void visit(LvalNode n) {
		output = output + tabs + n.name + " <" + n.varName + ">" + "\n";
	}

	@Override
	public void visit(VariableNode n) {
		output = output + tabs + n.name + " <" + n.varName + ">" + "\n";
	}

	@Override
	public void visit(PrintNode n) {
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";

		if (((PrintNode) n).getType() == 1)

			(n.str).accept(this);

		else if (((PrintNode) n).getType() == 2)

			(n.cons).accept(this);

		else if (((PrintNode) n).getType() == 3)

			(n.var).accept(this);

	}

	@Override
	public void visit(RvalNode n) {
		output = output + tabs + n.name + " <" + n.varName + ">" + "\n";
	}

	@Override
	public void visit(AssignmentNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(0)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(1)).accept(this);
	}

	@Override
	public void visit(MultiplicationNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);
	}

	@Override
	public void visit(SumNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);

	}

	@Override
	public void visit(OrNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);

	}

	@Override
	public void visit(ConditionNode n) {
		output = output + tabs + n.name + " <" + n.varName + ">" + "\n";
	}

	@Override
	public void visit(IfNode n) {

		int index = 0;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(0)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(1)).accept(this);

		if (((IfNode) n).type == 2) {
			tabs = savedTabs.get(index);
			(n.list.get(2)).accept(this);
		}

	}

	@Override
	public void visit(WhileNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(0)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(1)).accept(this);

	}

	@Override
	public void visit(BodyNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		for (int i = 0; i < n.list.size(); i++) {
			Node aux = n.list.get(i);
			tabs = savedTabs.get(index);
			aux.accept(this);
		}
	}

	@Override
	public void visit(DifferenceNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);
	}

	@Override
	public void visit(ModuloNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);
	}

	@Override
	public void visit(GreaterThanNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);
	}

	@Override
	public void visit(EqualToNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);
	}

	@Override
	public void visit(DivisionNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);
	}

	@Override
	public void visit(AndNode n) {

		int index;
		output = output + tabs + n.name + "\n";
		tabs = tabs + "\t";
		savedTabs.add(tabs);
		index = savedTabs.size() - 1;
		(n.list.get(1)).accept(this);
		tabs = savedTabs.get(index);
		(n.list.get(0)).accept(this);

	}

}
