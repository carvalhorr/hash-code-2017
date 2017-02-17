package balloons.hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PizzaInfoFileLoader {
	public static PizzaInfo loadPizza(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileName));
		int r = scan.nextInt();
		int c = scan.nextInt();
		int l = scan.nextInt();
		int h = scan.nextInt();
		System.out.println(r + " x " + c);
		String line = "";
		scan.nextLine();
		char[][] p = new char[r][c];
		for (int i = 0; i < r; i++) {
			line = scan.nextLine();
			for (int j = 0; j < c; j++) {
				p[i][j] = line.charAt(j);
			}
		}
		return new PizzaInfo(p, r, c, l, h);
	}
}
