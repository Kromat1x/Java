import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class CityMap {
	private int x;
	private int y;
	Point R = new Point();
	Point J = new Point();
	char[][] matrix;
			
	/**
	 * The constructor simply builds the map using the
	 * {@link #buildMap(String) buildMap} method
	 * @param file_name represents the actual name of the file that will be read
	 */
	public CityMap(String file_name){
		buildMap(file_name);
	}
	
	
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}


	/**
	 * Builds up the map read from file
	 * <p>
	 * The method reads the file, gets the dimension of the matrix and
	 * initialize it. After that, the file is read line by line using the scanner method called nextLine();
	 * which is made into a char array which represents a line from our matrix.
	 * While reading the file, it also searches for the positions of Romeo and Juliet
	 * and sets them properly 
	 * @param file_name - represents the actual name of the file that will be read
	 */
	private void buildMap(String file_name){
		File file = new File(file_name);
		try{
			Scanner scan = new Scanner(file);
			this.setX(scan.nextInt());
			this.setY(scan.nextInt());
			this.matrix = new char[this.x][this.y];
			scan.nextLine();
			for(int i = 0; i < this.x; i++){
				this.matrix[i] = scan.nextLine().toCharArray();
				for(int j = 0; j < this.y; j++){
					if(this.matrix[i][j] == 'R'){
						this.R.setX(i);
						this.R.setY(j);
					}else if(this.matrix[i][j] == 'J'){
						this.J.setX(i);
						this.J.setY(j);
					}else if(this.matrix[i][j] == ' '){
						this.matrix[i][j] = '0';
					}
				}
			}
			scan.close();
		}catch(FileNotFoundException ex){
			System.out.println("File not Found!");
		}
	}
}
