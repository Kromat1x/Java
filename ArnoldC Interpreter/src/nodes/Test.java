package nodes;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class Test {
	public static void main(String[] args) {
		// get the input file name from args[]
		// because it is specified as an argument when
		// the program is used
		String inputFileName = args[0];
		// create a new program
		Program prog = new Program();
		// create the tree from the filename we got from input
		TreeCreator.createTree(prog, inputFileName);
		// use the interpreting visitor to interpret
		// the tree and make the output
		Visitor in = new InterpreterVisitor();
		in.visit(prog.main);
		// use the printing visitor to print
		// the tree
		Visitor v = new PrintingVisitor();
		v.visit(prog.main);

		// make the names for the output files with the extensions needed
		String outputFileName = inputFileName.substring(6, inputFileName.length() - 3);
		final String output1 = outputFileName + ".ast";
		final String output2 = outputFileName + ".out";

		try {
			// print the .ast representation of the tree into file
			PrintWriter printer = new PrintWriter(output1);
			printer.print(((PrintingVisitor) v).output);
			printer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			// print the output of the program represented by the tree into file
			PrintWriter printer2 = new PrintWriter(output2);
			printer2.print(((InterpreterVisitor) in).output);
			printer2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
