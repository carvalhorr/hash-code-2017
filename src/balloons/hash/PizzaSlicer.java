package balloons.hash;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaSlicer {

	private static int currentSlice = 1;

	public static void main(String[] args) throws FileNotFoundException {
		
		// Slice the example pizza
		PizzaInfo examplePizza = PizzaInfoFileLoader.loadPizza("data/example.in");
		SlicedPizza examplePizzaSliced = slicePizza(examplePizza, 0);
		Step2.run(examplePizza, examplePizzaSliced);
		OutputWriter.writeOutput("data/example.out", examplePizzaSliced);
		System.out.println(
				"Number of cells not included in a slice for the example pizza :" + SlicedPizzaCounter.countSlicesNotIncluded(examplePizzaSliced.slicedPizza));

		// Slice the small pizza
		PizzaInfo smallPizza = PizzaInfoFileLoader.loadPizza("data/small.in");
		SlicedPizza smallPizzaSliced = slicePizza(smallPizza, 0);
		Step2.run(smallPizza, smallPizzaSliced);
		OutputWriter.writeOutput("data/small.out", smallPizzaSliced);
		System.out.println(
				"Number of cells not included in a slice for the small pizza :" + SlicedPizzaCounter.countSlicesNotIncluded(smallPizzaSliced.slicedPizza));

		// Slice the medium pizza
		PizzaInfo mediumPizza = PizzaInfoFileLoader.loadPizza("data/medium.in");
		SlicedPizza mediumPizzaSliced = slicePizza(mediumPizza, 0);
		Step2.run(mediumPizza, mediumPizzaSliced);
		OutputWriter.writeOutput("data/medium.out", mediumPizzaSliced);
		System.out.println(
				"Number of cells not included in a slice for the medium pizza :" + SlicedPizzaCounter.countSlicesNotIncluded(mediumPizzaSliced.slicedPizza));

		// Slice the big pizza
		PizzaInfo bigPizza = PizzaInfoFileLoader.loadPizza("data/big.in");
		SlicedPizza bigPizzaSliced = slicePizza(bigPizza, 0);
		Step2.run(bigPizza, bigPizzaSliced);
		OutputWriter.writeOutput("data/big.out", bigPizzaSliced);
		System.out.println(
				"Number of cells not included in a slice for the big pizza :" + SlicedPizzaCounter.countSlicesNotIncluded(bigPizzaSliced.slicedPizza));
	}

	/**
	 * 
	 * Cut an entire pizza.
	 * 
	 * At each cell of the pizza, all possible slices sizes are attempted
	 * starting from the smaller ones to the biggest. The first match is cut.
	 * 
	 * @param pizzaInfo
	 * @param subPizzaSize
	 * @return
	 */
	public static SlicedPizza slicePizza(PizzaInfo pizzaInfo, int subPizzaSize) {
		
		// Start first slice number to 1
		currentSlice = 1;
		
		// Create a new matrix to hold the slice numbers
		int[][] pizzaSolution = createNewEmptyPizzaSolution(pizzaInfo.rows, pizzaInfo.columns);
		
		// Compute all factors to use as slices sizes
		Map<Integer, List<Integer>> factors = Factors.getFactors(pizzaInfo.l, pizzaInfo.h);
		
		// Map to store the slices
		Map<Integer, Slice> slices = new HashMap<Integer, Slice>();
		
		// Iterate over all the matrix
		for (int currentRow = 0; currentRow < pizzaInfo.rows; currentRow++) {
			for (int currentColumn = 0; currentColumn < pizzaInfo.columns; currentColumn++) {
				
				// if cell is not cut
				if (pizzaSolution[currentRow][currentColumn] == 0) {
					
					// tries all possible slice sizes
					for (int n : factors.keySet()) {
						for (int sliceWidth : factors.get(n)) {
							int sliceHeight = n / sliceWidth;
							
							if (isSliceInsidePizza(pizzaInfo.rows, pizzaInfo.columns, currentRow, currentColumn, sliceWidth, sliceHeight)) {
								if (canCutSlice(pizzaSolution, pizzaInfo.data, pizzaInfo.l, currentRow, currentColumn, currentRow + sliceWidth, currentColumn + sliceHeight)) {
									// cut the slice
									cutSliceOfPizza(pizzaSolution, currentRow, currentColumn, currentRow + sliceWidth, currentColumn + sliceHeight, currentSlice);
									
									// store the slice just cut
									slices.put(currentSlice, new Slice(currentSlice, currentRow, currentColumn, currentRow + sliceWidth, currentColumn + sliceHeight));
									
									// compute next slice number
									currentSlice++;
								}
							}
						}
					}

				}
			}
		}
		return new SlicedPizza(pizzaSolution, slices, currentSlice - 1);
	}

	/**
	 * 
	 * Cut a slice of pizza by putting the slice number on each slice cell.
	 * 
	 * @param slices
	 * @param startR
	 * @param startC
	 * @param endR
	 * @param endC
	 * @param sliceNumber
	 */
	public static void cutSliceOfPizza(int[][] slices, int startR, int startC, int endR, int endC, int sliceNumber) {
		for (int i = startR; i < endR; i++) {
			for (int j = startC; j < endC; j++) {
				slices[i][j] = sliceNumber;
			}
		}
	}

	/**
	 * Check if the bounds of the slice is inside the pizza area.
	 * 
	 * @param endR
	 * @param endC
	 * @param i
	 * @param j
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static boolean isSliceInsidePizza(int endR, int endC, int i, int j, int f1, int f2) {
		return (i + f1 <= endR && j + f2 <= endC);
	}

	/**
	 * Check if a slice can be cut on the position [startR, startC, endR, endC]
	 * 
	 * A slice can be cut if it has the minimum number of each ingredients (l)
	 * and non of its cells is included in other slice.
	 * 
	 * @param slices
	 * @param pizza
	 * @param l
	 * @param startR
	 * @param startC
	 * @param endR
	 * @param endC
	 * @return
	 */
	public static boolean canCutSlice(int[][] slices, char[][] pizza, int l, int startR, int startC, int endR,
			int endC) {
		// Number of tomatoes in the slice
		int countT = 0;

		for (int i = startR; i < endR; i++) {
			for (int j = startC; j < endC; j++) {
				// If a cell is part of other slice then a new slice can't be
				// cut
				if (slices[i][j] > 0) {
					return false;
				}
				if (pizza[i][j] == 'T')
					countT++;
			}
		}
		// Number of mushrooms in the slice
		int countM = ((endR - startR) * (endC - startC)) - countT;

		// Return true if both the number of tomatoes and mushrooms is bigger
		// the the minimum number of ingredients
		return countT >= l && countM >= l;
	}

	/**
	 * Create an empty matrix to store the slice number in each cell.
	 * 
	 * Each cell is initialized with 0, meaning the cell is not included in any
	 * slice
	 * 
	 * @param r
	 *            number of rows
	 * @param c
	 *            number of columns
	 * @return
	 */
	public static int[][] createNewEmptyPizzaSolution(int r, int c) {

		// create matrix
		int[][] pizzaSlices = new int[r][c];

		// initialize it
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				pizzaSlices[i][j] = 0;
			}
		}

		return pizzaSlices;
	}

}

/**
 * 
 * A slice of the pizza
 * 
 * @author carvalhorr
 *
 */
class Slice {

	// Starting row of the slice
	public int r0;

	// Starting column of the slice
	public int c0;

	// End row of the slice
	public int r1;

	// End column of the slice
	public int c1;

	// Slice number
	public int num;

	public Slice(int num, int r1, int c1, int r2, int c2) {
		this.r0 = r1;
		this.c0 = c1;
		this.r1 = r2;
		this.c1 = c2;
		this.num = num;
	}

	public Slice(Slice slice) {
		this.r0 = slice.r0;
		this.c0 = slice.c0;
		this.r1 = slice.r1;
		this.c1 = slice.c1;
	}

	public int getSize() {
		return (r1 - r0) * (c1 - c0);
	}
}