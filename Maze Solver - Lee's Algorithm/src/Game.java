
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
/**
 *
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */

public class Game {
	private CityMap cityMap;
	private Character juliet;
	private Character romeo;

	/**
	 * Game class constructor
	 * <p>
	 * The constructor sets up the variables using the file
	 * @param file_name - represents the name of the actual file that will be read
	 */
	public Game(String file_name){
		this.cityMap = new CityMap(file_name);
		this.juliet = new Character("Juliet", cityMap.J);
		this.romeo = new Character("Romeo", cityMap.R);
	}

	/**
	 * Initialize a new game
	 * <p>
	 * The method calls the {@link Character#makeIntMap(CityMap) makeIntMap} and
	 * {@link Character#setup(CityMap) setup} from the Character class, for both
	 * main characters. After this is done, we have the matrixes all set.
	 * Both Romeo and Juliet now have the integer matrix full of values that represents
	 * the number of steps needed to get in that exact location.
	 */
	private void initGame(){
		juliet.makeIntMap(cityMap);
		romeo.makeIntMap(cityMap);
		juliet.setup(cityMap);
		romeo.setup(cityMap);
	}

	/**
	 * Finds the position accessible for both characters with a minimum of steps
	 * <p>
	 * The method overlaps the 2 integer matrixes of both characters using 2 for loops
	 * if the value found in a location is the same for both Romeo and Juliet, and also
	 * it's not a wall (-1) or unaccessible (0), it is added to a list.
	 * After the matrixes are covered entirely, the list is sorted
	 * to find the minimum of steps needed for them to meet
	 * @return - the minimum steps needed for them to meet in a location
	 */
	private int findMe(){
		int x = cityMap.getX();
		int y = cityMap.getY();
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if(romeo.matrix[i][j] == juliet.matrix[i][j]
						&& romeo.matrix[i][j] != -1
						&& romeo.matrix[i][j] != 0){
					list.add(romeo.matrix[i][j]);
				}
			}
		}
		int min = list.peek();
		int aux;
		int size = list.size();
		for (int i = 0; i < size; i++){
			aux = list.remove();
			if(min > aux){
				min = aux;
			}
		}
		return min;
	}

	/**
	 * Searches for that minimum amount of steps and makes the output file
	 * <p>
	 * The method covers the matrixes one last time, this time checking only if the
	 * values match the minimum and they are also equal for both Romeo and Juliet,
	 * then it prints using the following format : Number_of_steps Coord_x Coord_y.
	 * You may observe that i and j, the coordinates are incremented by one, that is
	 * because the output is written as if the matrix is numbered from 1 and not from 0
	 * like the matrixes in Java
	 * @param min - the minimum steps needed for them to meet in a location
	 */
	private void print(int min){
		try {
			int x = cityMap.getX();
			int y = cityMap.getY();
			PrintWriter printer = new PrintWriter("maze.out");
			for(int i = 0; i < x; i++){
				for(int j = 0; j < y; j++){
					if(romeo.matrix[i][j] == juliet.matrix[i][j] && romeo.matrix[i][j] == min){
						printer.print(romeo.matrix[i][j] + " " + (i+1) + " " + (j+1));
						printer.println();
					}
				}
			}
			printer.close();
		} catch (IOException e) {
			System.out.println("ERROR");
		}
	}

	/**
	 * Using all the auxiliary methods it makes the output
	 * <p>
	 * First is the constructor of the Game class
	 * <br>
	 * Then the setup of the matrixes
	 * <br>
	 * {@link #initGame() initGame}
	 * <br>
	 * Then finding the minimum of steps
	 * <br>
	 * {@link #findMe() findMe}
	 * <br>
	 * Then finding the coordinates of the minimum steps and printing in file
	 * <br>
	 * {@link #print(int) print}
	 * <br>
	 * @param file_name - the name of the input game file
	 */
	public static void Play(String file_name){
		Game game = new Game(file_name);
		game.initGame();
		int min = game.findMe();
		game.print(min);
	}


	public static void main(String[] args){
		Play("maze.in");
	}
}
