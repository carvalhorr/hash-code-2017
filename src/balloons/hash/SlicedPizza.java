package balloons.hash;

import java.util.Map;

/**
 * 
 * Result of cutting the pizza. 
 * 
 * @author carvalhorr
 *
 */
public class SlicedPizza {
	
	// Number of slices
	public int numberOfSlices;
	
	// Matrix holding the slice number in each position of the pizza.
	public int[][] slicedPizza;
	
	// List of slices
	Map<Integer, Slice> slices;
	
	public SlicedPizza(int[][] slicedPizza, Map<Integer, Slice> slices, int numberOfSlices) {
		this.slicedPizza = slicedPizza;
		this.numberOfSlices = numberOfSlices;
		this.slices = slices;
	}
}

