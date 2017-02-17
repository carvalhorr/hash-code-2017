package balloons.hash;

public class SlicedPizzaCounter {
	
	public static int countSlicesNotIncluded(int[][] slices) {
		int count = 0;
		for (int i = 0; i < slices.length; i ++) {
			for (int j = 0; j < slices[0].length; j ++) {
				if (slices[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}
}
