package balloons.hash;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaCutter {
	
	private static int currentSlice = 1;

	public static void main(String[] args) throws FileNotFoundException {
		PizzaInfo examplePizza = PizzaInfoFileLoader.loadPizza("data/example.in");
		SlicedPizza examplePizzaSliced = cutPizza(examplePizza, 0);
		OutputWriter.writeOutput("data/example.out", examplePizzaSliced);
		System.out.println("example not cut slices :" + SlicedPizzaCounter.countSlicesNotIncluded(examplePizzaSliced.slicedPizza));

		currentSlice = 1;
		PizzaInfo smallPizza = PizzaInfoFileLoader.loadPizza("data/small.in");
		SlicedPizza smallPizzaSliced = cutPizza(smallPizza, 0);
		OutputWriter.writeOutput("data/small.out", smallPizzaSliced);
		System.out.println("small not cut slices :" + SlicedPizzaCounter.countSlicesNotIncluded(smallPizzaSliced.slicedPizza));
		
		currentSlice = 1;
		PizzaInfo mediumPizza = PizzaInfoFileLoader.loadPizza("data/medium.in");
		//IngredientCounter.countSlices(mediumPizza.data, 200, 250, 25);
		SlicedPizza mediumPizzaSliced = cutPizza(mediumPizza, 0);
		OutputWriter.writeOutput("data/medium.out", mediumPizzaSliced);
		System.out.println("medium not cut slices :" + SlicedPizzaCounter.countSlicesNotIncluded(mediumPizzaSliced.slicedPizza));
		
		currentSlice = 1;
		PizzaInfo bigPizza = PizzaInfoFileLoader.loadPizza("data/big.in");
		//IngredientCounter.countSlices(bigPizza.data, 1000, 1000, 20);
		SlicedPizza bigPizzaSliced = cutPizza(bigPizza, 0);
		OutputWriter.writeOutput("data/big.out", bigPizzaSliced);
		System.out.println("big not cut slices :" + SlicedPizzaCounter.countSlicesNotIncluded(bigPizzaSliced.slicedPizza));
	}


	public static SlicedPizza cutPizza(PizzaInfo pizzaInfo, int subPizzaSize) {
		return cutSubPizza(pizzaInfo.data, 0, 0, pizzaInfo.r, pizzaInfo.c, pizzaInfo.l, pizzaInfo.h);
		// int[][] cutPizza = new int[pizza.length][pizza[0].length];
		/*if (subPizzaSize == 0) {
			return cutSubPizza(pizzaInfo.data, 0, 0, pizzaInfo.r, pizzaInfo.c, pizzaInfo.l, pizzaInfo.h);
		} else {
			for (int i = 0; i < pizzaInfo.r; i = i + subPizzaSize) {
				for(int j = 0; j < pizzaInfo.c/subPizzaSize; j = j +  + subPizzaSize) {
					cutSubPizza(pizzaInfo.data, i, j, i + subPizzaSize, j+ subPizzaSize, pizzaInfo.l, pizzaInfo.h);
				}
			}
		}*/
	}

	public static SlicedPizza cutSubPizza(char[][] pizza, int startR, int startC, int endR, int endC, int l, int h) {
		int[][] pizzaSolution = createNewEmptySubPizzaSolution(startR, startC, endR, endC);
		Map<Integer, List<Integer>> factors = Factors.getFactors(l, h);
		Map<Integer, Slice> slices = new HashMap<Integer, Slice>();
		for (int i = startR; i< endR; i++) {
			for (int j = startC; j < endC; j++) {
				if (pizzaSolution[i][j] == 0) {
					for(int n: factors.keySet()) {
						for(int f1: factors.get(n)) {
							int f2 = n/f1;
							if (isInsideArea(endR, endC, i, j, f1, f2)) {
								if (canCut(pizzaSolution, pizza, l, i, j, i+f1, j+f2)) {
									cutPizza(pizzaSolution, i, j, i+f1, j+f2, currentSlice);
									slices.put(currentSlice, new Slice(i, j, i+f1-1, j+f2-1));
									currentSlice ++;
								}
							}
			 			}
					}
					
				}
			}
		}
		return new SlicedPizza(pizzaSolution, slices, currentSlice-1);
	}
	
	public static void cutPizza(int[][] slices, int startR, int startC, int endR, int endC, int sliceNumber) {
		for (int i = startR; i< endR; i++) {
			for (int j = startC; j < endC; j++) {
				slices[i][j] = sliceNumber;
			}
		}
	}
	
	public static boolean isInsideArea(int endR, int endC, int i, int j, int f1, int f2) {
		return (i+f1 <= endR && j+f2 <= endC);
	}
	
	public static boolean canCut(int[][] slices, char[][] pizza, int l, int startR, int startC, int endR, int endC) {
		int countT = 0;
		for (int i = startR; i < endR; i++) {
			for (int j = startC; j < endC; j++) {
				if (slices[i][j] > 0) {
					return false;
				}
				if (pizza[i][j] == 'T')
					countT++;
			}
		}
		int countM = ((endR - startR) * (endC - startC)) - countT;
		return countT >= l && countM >= l;
	}
	
	
	public static int[][] createNewEmptySubPizzaSolution(int startR, int startC, int endR, int endC) {
		int[][] pizzaSlices = new int[endR-startR][endC-startC];
		for(int i = startR; i < endR; i++) {
			for (int j = startC; j < endC; j++) {
				pizzaSlices[i][j] = 0;
			}
		}
		return pizzaSlices;
	}

	public static void slice(int sliceNumber, char[][] pizza, int l, int startR, int startC, int endR, int endC) {
		
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

class SlicedPizza {
	public int numberOfSlices;
	public int[][] slicedPizza;
	Map<Integer, Slice> slices;
	
	public SlicedPizza(int[][] slicedPizza, Map<Integer, Slice> slices, int numberOfSlices) {
		this.slicedPizza = slicedPizza;
		this.numberOfSlices = numberOfSlices;
		this.slices = slices;
	}
}

class Slice {
	public int r1, c1, r2, c2;
	public Slice(int r1, int c1, int r2, int c2) {
		this.r1 = r1;
		this.c1 = c1;
		this.r2 = r2;
		this.c2 = c2;
	}
}
