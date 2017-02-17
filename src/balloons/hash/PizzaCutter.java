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

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(Factors.getFactors(6, 14));
		PizzaInfo examplePizza = PizzaInfoFileLoader.loadPizza("data/example.in");
		//cutPizza(examplePizza, 0);
		PizzaInfo smallPizza = PizzaInfoFileLoader.loadPizza("data/small.in");
		//cutPizza(smallPizza, 0);
		PizzaInfo mediumPizza = PizzaInfoFileLoader.loadPizza("data/medium.in");
		IngredientCounter.countSlices(mediumPizza.data, 200, 250, 25);
		//cutPizza(mediumPizza, 0);
		PizzaInfo bigPizza = PizzaInfoFileLoader.loadPizza("data/big.in");
		IngredientCounter.countSlices(bigPizza.data, 1000, 1000, 20);
		//cutPizza(bigPizza, 0);
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
		Map<Integer, List<Integer>> factors = Factors.getFactors(l, h);
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
