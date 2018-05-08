import java.util.LinkedList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */

public class Character {
	private String name;
	private Point p = new Point();
	public int[][] matrix;
	private LinkedList<Point> list = new LinkedList<Point>();
	
	
	public Character(String name, Point p){
		this.setName(name);
		this.setP(p);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public Point getP() {
		return p;
	}



	public void setP(Point p) {
		this.p = p;
	}


	/**
	 * Turning the char map into an integer map
	 * <p>
	 * The method simply goes through all the char map and converts 
	 * elements like this: the walls get converted from X to -1, the free spaces from space to 0
	 * and Romeo/Juliet are represented by 1 in their own matrix
	 *
	 * @param cityMap - the char map read from file using {@link CityMap#buildMap(String) buildMap} method
	 */
	public void makeIntMap(CityMap cityMap) {
		int x = cityMap.getX();
		int y = cityMap.getY();
		this.matrix = new int[x][y];
		this.matrix[this.p.x][this.p.y] = 1;
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if(cityMap.matrix[i][j] == 'X'){
					this.matrix[i][j] = -1;
				}else if(i != this.p.getX() || j != this.p.getY()){
					this.matrix[i][j] = 0;
				}
			}
		}
	}
	
	/**
	 * Fills the integer map with numbers of steps
	 * <p>
	 * The method stars from the coordinates of the main character,
	 * Romeo or Juliet, putting in a list that is used as a queue,
	 * then it checks all the neighbors of that point
	 * if the neighbors are free areas and not walls, they are also
	 * added to the queue and their value is modified to the value of the
	 * first point, incremented by 1. For example, if the main character has
	 * the value of 1, the first set of neighbors will have the value of 2.
	 * And going on like this, the neighbors of the neighbors will have 3.
	 * All this implemented in a while that works when the queue is not empty.
	 * In the end we get a matrix full of values that represents the number of steps
	 * needed for the character to get there
	 * @param cityMap - the char map read from file using {@link CityMap#buildMap(String) buildMap} method 
	 */
	public void setup(CityMap cityMap) {
		int x = cityMap.getX();
		int y = cityMap.getY();
		Point p_aux = new Point();
		list.addLast(this.p);
		while(!list.isEmpty()){
			p_aux = list.removeFirst();
			int px = p_aux.getX();
			int py = p_aux.getY();
			for(int i = px-1; i <= px+1; i++){
				for(int j = py-1; j <= py+1; j++){
					if((i != px || j != py)
							&& (i >= 0 && j >= 0 && j < y && i < x)
							&& (this.matrix[i][j] == 0)){
						this.matrix[i][j] = this.matrix[px][py] + 1;
						Point point_aux_2 = new Point(i,j);
						list.addLast(point_aux_2);
					}
				}
			}
		}
	}	
}
