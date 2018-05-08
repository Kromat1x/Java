package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class NodeFactory {

	/**
	 * Reverse a list
	 * <p>
	 * The method takes a list, iterates through it and builds its reverse
	 * 
	 * @param initialList
	 *            the list we want to reverse
	 * @return the reversed list resulted
	 */
	private static ArrayList<String> reverseList(ArrayList<String> initialList) {

		ArrayList<String> result = new ArrayList<String>();
		for (int i = initialList.size() - 1; i >= 0; i--) {
			result.add(initialList.get(i));
		}

		return result;
	}

	/**
	 * Make a sublist
	 * <p>
	 * Makes a sublist from a given list from indexes x and y in the initial
	 * list
	 * 
	 * @param list
	 *            the initial list we make the sublist from
	 * @param x
	 *            the start of the sublist
	 * @param y
	 *            the end of the sublist
	 * @return the sublist
	 */
	private static ArrayList<String> makeSubList(ArrayList<String> list, int x, int y) {

		ArrayList<String> result = new ArrayList<String>();
		for (int i = x; i < y; i++) {
			result.add(list.get(i));
		}
		return result;
	}

	/**
	 * The method that creates a body node
	 * <p>
	 * This function is used to build nodes like IfBodyNode, ElseBodyNode and
	 * BodyNode, basically it takes the information in a block of if / else /
	 * while code and builds all then nodes inside it, connecting them to the
	 * "parent" node which is the body node created
	 * 
	 * @param list
	 *            the list of string that represent actual lines of code
	 * @param type
	 *            the type of body node we are creating, if, else or while
	 * @param p
	 *            the program we are working in, so we have access to variables
	 * @return returns the reference to the new body node, ready to be added in
	 *         the program
	 */
	private static Node createBodyNode(ArrayList<String> list, int type, Program p) {

		String name;
		String auxHelp;
		ArrayList<String> auxText = new ArrayList<String>();
		if (type == 1) {
			name = "IfBodyNode";
		} else if (type == 2) {
			name = "ElseBodyNode";
		} else {
			name = "BodyNode";
		}
		// we decide which type of body we have
		BodyNode node = new BodyNode(type, name);

		for (int i = 0; i < list.size(); i++) {
			auxHelp = list.get(i);
			if (auxHelp.startsWith("TALK TO THE HAND")) {
				// print node
				auxText.add(auxHelp);
				PrintNode printNode = (PrintNode) NodeFactory.createNode(auxText, p);
				node.list.add(printNode);
				auxText.clear();

			} else if (auxHelp.startsWith("GET TO THE CHOPPER")) {
				// assignment node
				auxHelp = list.get(i);

				while (!auxHelp.equals("ENOUGH TALK")) {
					auxText.add(auxHelp);
					i++;
					if (i < list.size()) {
						auxHelp = list.get(i);
					} else {
						break;
					}
				}
				i--;
				AssignmentNode assign = (AssignmentNode) NodeFactory.createNode(auxText, p);
				node.list.add(assign);
				auxText.clear();

			} else if (auxHelp.startsWith("BECAUSE I'M GOING TO SAY PLEASE")) {
				int endIfCount = 0;
				int ifCount = 1;

				while (endIfCount != ifCount) {

					auxText.add(auxHelp);
					i++;
					if (i < list.size()) {
						auxHelp = list.get(i);
					} else {
						break;
					}
					if (auxHelp.startsWith("BECAUSE I'M GOING TO SAY PLEASE"))
						ifCount++;

					if (auxHelp.startsWith("YOU HAVE NO RESPECT FOR LOGIC"))
						endIfCount++;

				}

				i--;
				IfNode ifnode = (IfNode) NodeFactory.createNode(auxText, p);
				node.list.add(ifnode);
				auxText.clear();

			} else if (auxHelp.startsWith("STICK AROUND")) {

				int endWhileCount = 0;
				int whileCount = 1;

				while (endWhileCount != whileCount) {

					auxText.add(auxHelp);
					i++;
					auxHelp = list.get(i);

					if (auxHelp.startsWith("STICK AROUND"))
						whileCount++;

					if (auxHelp.startsWith("CHILL"))
						endWhileCount++;
				}
				i--;
				WhileNode whilenode = (WhileNode) NodeFactory.createNode(auxText, p);
				node.list.add(whilenode);
				auxText.clear();
			}
		}

		return node;
	}

	/**
	 * The method to create a single node
	 * <p>
	 * This method gets a list of instructions and uses them to create nodes
	 * that link directly to the main node, it returns the actual node for each
	 * type of instruction given, it reads the required lines, for example for
	 * HEY CHRISTMAS TREE it reads the HEY CHRISTMAS TREE line and the one next
	 * to it that will be YOU SET US UP
	 * 
	 * @param text
	 *            - list of instructions
	 * @param p
	 *            - program we are working on
	 * @return = the new node created
	 */
	public static Node createNode(ArrayList<String> text, Program p) {
		// it's probably better that this function
		// will return the newly created node
		// so i can add it where ever i need it
		// for example i can add a PrintNode in the MainNode's list
		// but i can also have a PrintNode if an IfNode
		String auxStr;
		int value;

		if (text.get(0).startsWith("HEY CHRISTMAS TREE")) {

			String str;

			// get the first line for the variable name
			auxStr = text.get(0);
			// saving the name of the newly declared int
			str = auxStr.substring(19);
			// get the second line for the actual value
			auxStr = text.get(1);

			// first we search for the tokens
			if (auxStr.substring(14).equals("@NO PROBLEMO"))

				value = 1;

			else if (auxStr.substring(14).equals("@I LIED"))

				value = 0;

			else
				// if it's not a token it's definitely a value so we
				// make it an int to store and use with ease
				value = Integer.parseInt(auxStr.substring(14));

			// we create the parts of the DeclareNode,
			// assemble it and add it to the main list
			LvalNode lvalNode = new LvalNode(str);
			ConstantNode constNode = new ConstantNode(value);
			DeclareNode declareNode = new DeclareNode(lvalNode, constNode);
			p.addNewVar(value, str);
			return declareNode;

		} else if (text.get(0).startsWith("TALK TO THE HAND")) {

			PrintNode printNode = new PrintNode();
			auxStr = text.get(0);
			auxStr = auxStr.substring(17);
			// we make a substring to get the value we want to print
			if (auxStr.charAt(0) == '\"') {
				// means it is a string
				printNode.setType(1);
				printNode.setStr(auxStr.substring(1, auxStr.length() - 1));

			} else if (p.checkVar(auxStr)) {
				// means it is a variable
				printNode.setType(3);
				printNode.setVar(auxStr.substring(0, auxStr.length()));

			} else {
				// means it is a constant
				printNode.setType(2);
				value = Integer.parseInt(auxStr);
				printNode.setCons(value);

			}

			return printNode;

		} else if (text.get(0).startsWith("GET TO THE CHOPPER")) {
			// assignment node
			auxStr = text.get(0);
			auxStr = auxStr.substring(19);
			// create the AssignmentNode and add the LvalNode
			// with the given name
			AssignmentNode assignment = new AssignmentNode(auxStr);
			// we don't need the GET TO THE CHOPPER line
			text.remove(0);
			// we reverse the block of instructions because it is easier
			// to work with them that way, easier to build the node
			// basically we go through a tree from bottom top
			// and we build it recursively
			text = NodeFactory.reverseList(text);
			Node auxNode = createNode(text, p);
			assignment.list.add(auxNode);
			return assignment;
			// call recursively the createNode function

		} else if (text.get(0).startsWith("HERE IS MY INVITATION")) {
			// in the use of the reversed instruction block
			// if we encounter HERE IS MY INVITATION means that we have
			// to stop the assignment block, so in this else if
			// we no longer call the createNode because it is the
			// end of the block
			auxStr = text.get(0);
			auxStr = auxStr.substring(22);

			if (p.checkVar(auxStr)) {
				return new RvalNode(auxStr);
				// search for tokens
			} else if (auxStr.equals("@NO PROBLEMO")) {

				value = 1;
				return new ConstantNode(value);

			} else if (auxStr.equals("@I LIED")) {

				value = 0;
				return new ConstantNode(value);

			} else {

				value = Integer.parseInt(auxStr);
				return new ConstantNode(value);
			}

			/**
			 * For the arithmetic operations and the logic operations like "and"
			 * and "or", we have the same approach, we check if we have the
			 * operation with a variable or a constant, we create it and then we
			 * remove the line that starts the arithmetic or logic block like
			 * YOU'RE FIRED for the multiplication node and we call the
			 * createNode function again because we might have more operations
			 * in that block
			 */
		} else if (text.get(0).startsWith("YOU'RE FIRED")) {

			auxStr = text.get(0);
			auxStr = auxStr.substring(13);
			MultiplicationNode mul = new MultiplicationNode();

			if (p.checkVar(auxStr)) {
				// we make the RvalNode if the string we got
				// represents a variable
				mul.list.add(new RvalNode(auxStr));

			} else {
				// if we have a constant number
				value = Integer.parseInt(auxStr);
				mul.list.add(new ConstantNode(value));

			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			mul.list.add(auxNode);
			return mul;

		} else if (text.get(0).startsWith("GET UP")) {

			auxStr = text.get(0);
			auxStr = auxStr.substring(7);

			SumNode sum = new SumNode();

			if (p.checkVar(auxStr)) {

				sum.list.add(new RvalNode(auxStr));

			} else {

				value = Integer.parseInt(auxStr);
				sum.list.add(new ConstantNode(value));

			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			sum.list.add(auxNode);

			return sum;

		} else if (text.get(0).startsWith("CONSIDER THAT A DIVORCE")) {

			auxStr = text.get(0);
			auxStr = auxStr.substring(24);

			OrNode or = new OrNode();

			if (p.checkVar(auxStr)) {

				or.list.add(new RvalNode(auxStr));

			} else {

				value = Integer.parseInt(auxStr);
				or.list.add(new ConstantNode(value));

			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			or.list.add(auxNode);

			return or;

		} else if (text.get(0).startsWith("KNOCK KNOCK")) {

			auxStr = text.get(0);
			auxStr = auxStr.substring(12);

			AndNode and = new AndNode();

			if (p.checkVar(auxStr)) {

				and.list.add(new RvalNode(auxStr));

			} else {

				value = Integer.parseInt(auxStr);
				and.list.add(new ConstantNode(value));

			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			and.list.add(auxNode);

			return and;

		} else if (text.get(0).startsWith("BECAUSE I'M GOING TO SAY PLEASE")) {

			auxStr = text.get(0);
			auxStr = auxStr.substring(32);
			ArrayList<String> auxList = new ArrayList<String>();
			ArrayList<String> auxList2 = new ArrayList<String>();
			IfNode ifnode = new IfNode(auxStr);
			text.remove(0);
			// it doesn't need reversing if we have
			// a while or another if in this place
			// if (!text.get(text.size() - 1).equals("ENOUGH TALK") &&
			// !text.get(text.size() - 1).equals("CHILL OUT")
			// if this is true than we reverse it
			// else we just eliminate the last element and keep going

			if (text.contains("BULLSHIT")) {
				// we check for BULLSHIT so we know which type of if we have
				auxList = makeSubList(text, 0, text.indexOf("BULLSHIT"));
				auxList2 = makeSubList(text, text.indexOf("BULLSHIT") + 1, text.size());
			} else {

				auxList2 = text;

			}

			// check if we need to reverse or not, if we have
			// as the next instruction in the block, an if or and while
			// or an assignment

			if (!auxList.isEmpty()) {
				if (!auxList.get(auxList.size() - 1).equals("ENOUGH TALK")
						&& !auxList.get(auxList.size() - 1).equals("CHILL")
						&& !auxList.get(auxList.size() - 1).equals("YOU HAVE NO RESPECT FOR LOGIC")) {

					auxList = NodeFactory.reverseList(auxList);

				} else {

					auxList.remove(auxList.size() - 1);

				}
			}

			if (!auxList2.get(auxList2.size() - 1).equals("ENOUGH TALK")
					&& !auxList2.get(auxList2.size() - 1).equals("CHILL")
					&& !auxList2.get(auxList2.size() - 1).equals("YOU HAVE NO RESPECT FOR LOGIC")) {

				auxList2 = NodeFactory.reverseList(auxList2);

			} else {

				auxList2.remove(auxList2.size() - 1);

			}

			if (auxList.isEmpty()) {
				Node auxNode1 = createBodyNode(auxList2, 1, p);
				ifnode.list.add(auxNode1);
				ifnode.type = 1;

			} else {

				Node auxNode1 = createBodyNode(auxList, 1, p);
				ifnode.list.add(auxNode1);
				Node auxNode2 = createBodyNode(auxList2, 2, p);
				ifnode.list.add(auxNode2);

				ifnode.type = 2;
			}

			return ifnode;
			// call recursively the createNode function
		} else if (text.get(0).startsWith("STICK AROUND")) {

			auxStr = text.get(0);
			auxStr = auxStr.substring(13);
			;
			WhileNode whilenode = new WhileNode(auxStr);
			text.remove(0);
			// if we have a if in a while, we don't need to reverse the block
			// because the factory searches for the start which is
			// get to the chopper so we wont reverse it but we will
			// remove the last element ENOUGH TALK
			// CHILL in if is a check done so we can have a while
			// inside another while
			if (!text.get(text.size() - 1).equals("ENOUGH TALK") 
					&& !text.get(text.size() - 1).equals("CHILL")
					&& !text.get(text.size() - 1).equals("YOU HAVE NO RESPECT FOR LOGIC")) {

				text = NodeFactory.reverseList(text);

			} else {

				text.remove(text.size() - 1);

			}

			Node auxNode = createBodyNode(text, 3, p);
			whilenode.list.add(auxNode);

			return whilenode;

		} else if (text.get(0).startsWith("GET DOWN")) {
			auxStr = text.get(0);
			auxStr = auxStr.substring(9);

			DifferenceNode dif = new DifferenceNode();

			if (p.checkVar(auxStr)) {

				dif.list.add(new RvalNode(auxStr));

			} else {

				value = Integer.parseInt(auxStr);
				dif.list.add(new ConstantNode(value));

			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			dif.list.add(auxNode);

			return dif;
		} else if (text.get(0).startsWith("HE HAD TO SPLIT")) {
			auxStr = text.get(0);
			auxStr = auxStr.substring(16);

			DivisionNode div = new DivisionNode();

			if (p.checkVar(auxStr)) {

				div.list.add(new RvalNode(auxStr));

			} else {

				value = Integer.parseInt(auxStr);
				div.list.add(new ConstantNode(value));

			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			div.list.add(auxNode);

			return div;
		} else if (text.get(0).startsWith("I LET HIM GO")) {

			auxStr = text.get(0);
			auxStr = auxStr.substring(13);

			ModuloNode mod = new ModuloNode();

			if (p.checkVar(auxStr)) {

				mod.list.add(new RvalNode(auxStr));

			} else {

				value = Integer.parseInt(auxStr);
				mod.list.add(new ConstantNode(value));

			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			mod.list.add(auxNode);

			return mod;

		} else if (text.get(0).startsWith("YOU ARE NOT YOU YOU ARE ME")) {

			auxStr = text.get(0);
			auxStr = auxStr.substring(27);

			EqualToNode eq = new EqualToNode();

			if (p.checkVar(auxStr)) {

				eq.list.add(new RvalNode(auxStr));

			} else if (auxStr.equals("@NO PROBLEMO")) {

				value = 1;
				eq.list.add(new ConstantNode(value));

			} else if (auxStr.equals("@I LIED")) {

				value = 0;
				eq.list.add(new ConstantNode(value));

			} else {

				value = Integer.parseInt(auxStr);
				eq.list.add(new ConstantNode(value));
			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			eq.list.add(auxNode);

			return eq;

		} else if (text.get(0).startsWith("LET OFF SOME STEAM BENNET")) {
			auxStr = text.get(0);
			auxStr = auxStr.substring(26);

			GreaterThanNode great = new GreaterThanNode();

			if (p.checkVar(auxStr)) {

				great.list.add(new RvalNode(auxStr));

			} else {

				value = Integer.parseInt(auxStr);
				great.list.add(new ConstantNode(value));

			}

			text.remove(0);
			Node auxNode = createNode(text, p);
			great.list.add(auxNode);

			return great;
		}
		return null;

	}
}
