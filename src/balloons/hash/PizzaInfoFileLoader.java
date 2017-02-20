package balloons.hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads the pizza info from a file and load into a new instance of PizzaInfo
 * 
 * @author carvalhorr
 *
 */
public class PizzaInfoFileLoader {
	
	public static PizzaInfo loadPizza(String fileName) throws FileNotFoundException {
		
		Scanner scan = new Scanner(new File(fileName));
		
		// Reads the parameters
		int r = scan.nextInt();
		int c = scan.nextInt();
		int l = scan.nextInt();
		int h = scan.nextInt();
		
		String line = "";
		scan.nextLine();

		// create instance of the matrix to hold each ingredient
		char[][] p = new char[r][c];
		
		// Read the cells into the matrix
		for (int i = 0; i < r; i++) {
			line = scan.nextLine();
			for (int j = 0; j < c; j++) {
				p[i][j] = line.charAt(j);
			}
		}
		
		return new PizzaInfo(p, r, c, l, h);
	}
}
