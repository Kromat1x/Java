package nodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class TreeCreator {
	/**
	 * The method that creates a tree
	 * <p>
	 * This method reads directly from file and scans it for potential lines
	 * that start certain blocks of code. For example BECAUSE I'M GOING TO SAY
	 * PLEASE means an if node but it won't search for nodes like the sum node
	 * or any arithmetic or logic operation because we cannot find blocks that
	 * start with them they will be eventually used in the createNode function
	 * which creates a sub tree for a main node
	 * 
	 * @param p
	 *            the program we are building in
	 * @param file_name
	 *            the input file name
	 */
	public static void createTree(Program p, String file_name) {
		// TODO
		File file = new File(file_name);
		String line;
		ArrayList<String> text = new ArrayList<String>();
		try {

			Scanner scan = new Scanner(file);
			if (!scan.nextLine().replaceAll("\\s+", " ").equals("IT'S SHOWTIME")) {
				// Checking just to be sure
				// Possibly to have text that has instructions before
				// the IT'S SHOWTIME tag and they should be ignored
				System.out.println("Houston, we have a problem !");
			}

			while (scan.hasNextLine()) {
				line = scan.nextLine().replaceAll("\\s+", " ");
				if (!line.isEmpty()) {

					if (line.charAt(0) == ' ')

						line = line.substring(1);

					if (line.charAt(line.length() - 1) == ' ')

						line = line.substring(0, line.length() - 1);

					if (line.startsWith("HEY CHRISTMAS TREE")) {
						// if we have a declaration
						// add the HEY CHRISTMAS TREE line
						text.add(line);
						// trim excess whitespace
						line = scan.nextLine().replaceAll("\\s+", " ");
						if (line.charAt(0) == ' ')
							// a space at the beginning might mess it up
							line = line.substring(1);

						// add the YOU SET US UP line
						text.add(line);
						// create the actual node
						DeclareNode declareNode = (DeclareNode) NodeFactory.createNode(text, p);
						p.main.add(declareNode);

						text.clear();
						// make sure you clear text after every call so it will
						// be clear for the next block of instructions

					} else if (line.startsWith("TALK TO THE HAND")) {
						// if we have a print instruction
						text.add(line);
						PrintNode printNode = (PrintNode) NodeFactory.createNode(text, p);
						p.main.add(printNode);
						// clear the list for the next block
						text.clear();

					} else if (line.startsWith("GET TO THE CHOPPER")) {
						// if we have an assignment
						while (!line.equals("ENOUGH TALK")) {

							text.add(line);
							line = scan.nextLine().replaceAll("\\s+", " ");
							if (line.charAt(0) == ' ')

								line = line.substring(1);

							if (line.charAt(line.length() - 1) == ' ')

								line = line.substring(0, line.length() - 1);

						}
						AssignmentNode assign = (AssignmentNode) NodeFactory.createNode(text, p);
						p.main.add(assign);
						text.clear();

					} else if (line.startsWith("BECAUSE I'M GOING TO SAY PLEASE")) {

						int endIfCount = 0;
						int ifCount = 1;

						while (endIfCount != ifCount) {

							text.add(line);
							line = scan.nextLine().replaceAll("\\s+", " ");
							if (!line.isEmpty()) {

								if (line.charAt(0) == ' ')

									line = line.substring(1);

								if (line.charAt(line.length() - 1) == ' ')

									line = line.substring(0, line.length() - 1);
							}

							if (line.startsWith("BECAUSE I'M GOING TO SAY PLEASE"))
								ifCount++;

							if (line.startsWith("YOU HAVE NO RESPECT FOR LOGIC"))
								endIfCount++;
						}

						IfNode ifnode = (IfNode) NodeFactory.createNode(text, p);
						p.main.add(ifnode);
						text.clear();

					} else if (line.startsWith("STICK AROUND")) {

						int endWhileCount = 0;
						int whileCount = 1;

						while (endWhileCount != whileCount) {

							text.add(line);
							line = scan.nextLine().replaceAll("\\s+", " ");
							if (!line.isEmpty()) {

								if (line.charAt(0) == ' ')

									line = line.substring(1);

								if (line.length() > 0)

									if (line.charAt(line.length() - 1) == ' ')

										line = line.substring(0, line.length() - 1);
							}
							if (line.startsWith("STICK AROUND"))
								whileCount++;

							if (line.startsWith("CHILL"))
								endWhileCount++;
						}

						WhileNode whilenode = (WhileNode) NodeFactory.createNode(text, p);
						p.main.add(whilenode);
						text.clear();
					}
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {

			System.out.println("File not found !");

		}

	}
}
