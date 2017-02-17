package balloons.hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class PizzaCutter {
	
	private static int currentSlice = 1;

	private static boolean printPicesCount = true;

	public static void main(String[] args) throws FileNotFoundException {
		PizzaInfo examplePizza = loadPizza("data/example.in");
		//cutPizza(examplePizza, 0);
		PizzaInfo smallPizza = loadPizza("data/small.in");
		//cutPizza(smallPizza, 0);
		PizzaInfo mediumPizza = loadPizza("data/medium.in");
		countSlices(mediumPizza.data, 200, 250, 25);
		//cutPizza(mediumPizza, 0);
		PizzaInfo bigPizza = loadPizza("data/big.in");
		countSlices(bigPizza.data, 1000, 1000, 20);
		//cutPizza(bigPizza, 0);
	}

	public static void countSlices(char[][] pizza, int r, int c, int size) throws FileNotFoundException {
		int countT = 0;
		int countM = 0;
		for (int i = 0; i < (r / size); i++) {
			for (int j = 0; j < (c / size); j++) {
				if (count(pizza, i * size, j * size, size) == 'T') {
					countT++;
				} else {
					countM++;
				}
			}
		}
		System.out.println("T:" + countT + " M: " + countM);
	}

	public static void cutPizza(PizzaInfo pizzaInfo, int subPizzaSize) {
		// int[][] cutPizza = new int[pizza.length][pizza[0].length];
		if (subPizzaSize == 0) {
			cutPizza(pizzaInfo.data, 0, 0, pizzaInfo.r, pizzaInfo.c, pizzaInfo.l, pizzaInfo.h);
		} else {
			for (int i = 0; i < pizzaInfo.r; i = i + subPizzaSize) {
				for(int j = 0; j < pizzaInfo.c/subPizzaSize; j = j +  + subPizzaSize) {
					cutPizza(pizzaInfo.data, i, j, i + subPizzaSize, j+ subPizzaSize, pizzaInfo.l, pizzaInfo.h);
				}
			}
		}
	}

	public static void cutPizza(char[][] pizza, int startR, int startC, int endR, int endC, int l, int h) {
		int[][] pizzaSlices = new int[endR-startR][endC-startC];
		for(int i = startR; i < endR; i++) {
			for (int j = startC; j < endC; j++) {
				pizzaSlices[i][j] = 0;
			}
		}
		
	}
	
	public static Map<Integer, List<Integer>> getFactors(int l, int h) {
		Map<Integer, List<Integer>> factors = new HashMap<Integer, List<Integer>>();
		for(int n = l*2; n <= h; n++) {
			factors.put(n, computeFactorsForNumber(n));
		}
		return factors;
	}
	
	public static List<Integer> computeFactorsForNumber(int n) {
		List<Integer> factorsForNumber = new ArrayList<Integer>();
		factorsForNumber.add(1);
		factorsForNumber.add(n);
		for(int i = 2; i < n/2; i ++) {
			if(n%i == 0) factorsForNumber.add(i);
		}
		return factorsForNumber;
	}

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

	private static char count(char[][] pizza, int startI, int startJ, int size) {
		int countT = 0;
		for (int i = startI; i < startI + size; i++) {
			for (int j = startJ; j < startJ + size; j++) {
				if (pizza[i][j] == 'T')
					countT++;
			}
		}
		if (printPicesCount)
			System.out.println("[" + startI + ", " + startJ + ", " + (startI + size - 1) + ", " + (startJ + size - 1)
					+ "] T:" + countT + " M:" + ((size * size) - countT));
		return (countT > ((size * size) / 2)) ? 'T' : 'M';
	}
}

class PizzaInfo {

	public char[][] data;
	public int r;
	public int c;
	public int l;
	public int h;

	public PizzaInfo(char[][] data, int r, int c, int l, int h) {
		this.r = r;
		this.c = c;
		this.l = l;
		this.h = h;
		this.data = data;
	}
}
