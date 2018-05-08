import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Permutari {
	public static void main(String[] args) {
		MyScanner s = new MyScanner("permutari.in");
		int wordCount = s.nextInt();
		ArrayList<String> wordList = new ArrayList<String>();
		int impossibru = 0;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Graph g = new Graph();
		for (int i = 0; i < alphabet.length; i++) {
			g.addNode(new Node(alphabet[i]));
		}
		for (int i = 0; i < wordCount; i++) {
			wordList.add(s.nextLine());
		}

		for (int i = 0; i < wordCount - 1; i++) {
			int j = i + 1;
			char[] aux_word1 = wordList.get(i).toCharArray();
			char[] aux_word2 = wordList.get(j).toCharArray();
			int min = Math.min(aux_word1.length, aux_word2.length);
			for (int k = 0; k < min; k++) {
				if (aux_word1[k] != aux_word2[k]) {
					g.addChar(aux_word1[k], aux_word2[k]);
					break;
				}
			}
		}
		
		ArrayList<Node> noIncoming = g.noIncomingEdges();
		ArrayList<Node> resultList = new ArrayList<Node>();

		while (!noIncoming.isEmpty()) {
			Node aux = noIncoming.get(0);
			noIncoming.remove(0);
			resultList.add(aux);
			int size = aux.neighbors.size();
			for (int j = 0; j < size; j++) {
				Character c = aux.neighbors.get(j);
				aux.neighbors.remove(c);
				size--;
				j--;
				int incoming = 0;
				for (int i = 0; i < g.nodes.size(); i++) {
					Node help = g.nodes.get(i);
					if (help.getNeighbors().contains(c)) {

						incoming = 1;
					}
				}
				if (incoming == 0) {
					int found = 0;
					for (int i = 0; i < g.nodes.size(); i++) {
						Node help = g.nodes.get(i);
						if (help.getValue() == c) {
							found = 1;
							noIncoming.add(help);
						}
					}
					if (found == 0) {
						noIncoming.add(new Node(c));
					}

				}
			}
		}

		for (int i = 0; i < g.nodes.size(); i++) {
			if (!g.nodes.get(i).neighbors.isEmpty()) {
				impossibru = 1;
				break;
			}
		}

		if (impossibru == 1) {
			System.out.println("Impossible");
		} else {
			System.out.println("Order : ");
			for (int i = 0; i < resultList.size(); i++) {
				System.out.print(resultList.get(i).value + " ");
			}
			System.out.println();
		}

		try {
			PrintWriter printer = new PrintWriter("permutari.out");
			if (impossibru == 1) {
				printer.println("Impossible");
			} else {
				for (int i = 0; i < resultList.size(); i++) {
					printer.print(resultList.get(i).value);
				}
			}
			printer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Problem encountered while writing in file !");
			e.printStackTrace();
		}

	}
}
