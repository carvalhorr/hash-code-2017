package balloons.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Given l (number of each ingredient that must be included in the slice) and h
 * (maximum number of cells in a slice), compute all possible sizes of slices by
 * finding the factors of the numbers from minimum slice size to maximum slice
 * size.
 * 
 * Since a slice is a rectangle, the factors gives the sizes of the sides.
 * 
 * Example: in a pizza where the minimumm number of each ingredient is 2, the
 * minimum slice size is 4. And supposing a slice can have up to 6 cells, this
 * algorithm will compute all the factors for numbers 4, 5 and 6 and the slices
 * can be of sizes 1 x 4, 2 x 2, 4 x 1, 1 x 5, 5 x 1, 1 x 6, 2 x 3, 3 x 2 and 6 x 1
 * 
 * @author carvalhorr
 *
 */
public class Factors {

	public static Map<Integer, List<Integer>> getFactors(int l, int h) {
		Map<Integer, List<Integer>> factors = new HashMap<Integer, List<Integer>>();
		for (int n = l * 2; n <= h; n++) {
			factors.put(n, getFactorsForNumber(n));
		}
		return factors;
	}

	public static List<Integer> getFactorsForNumber(int n) {
		List<Integer> factorsForNumber = new ArrayList<Integer>();
		factorsForNumber.add(1);
		for (int i = 2; i <= (n + 1) / 2; i++) {
			if (n % i == 0)
				factorsForNumber.add(i);
		}
		factorsForNumber.add(n);
		return factorsForNumber;
	}

}
