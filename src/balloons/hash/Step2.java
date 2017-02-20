package balloons.hash;

import java.util.ArrayList;

/**
 * 
 * After slicing the pizza, a second step is used to try to make the slices
 * bigger in order to include the maximum number of cells in the slices.
 * 
 * @author aida
 *
 */

public class Step2 {

	static int[][] direction = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static int maxExtending;
	static ArrayList<Integer> bestDirsToExtend;

	public static void run(PizzaInfo pizza, SlicedPizza slicedPizza) {
		// extend each slice as much as you can
		for (Slice slice : slicedPizza.slices.values()) {
			maxExtending = -1;
			extendSlice(pizza, slicedPizza.slicedPizza, slice, new ArrayList<Integer>());
			for (int i : bestDirsToExtend) {
				extendSlice(pizza, slicedPizza.slicedPizza, slice, direction[i][0], direction[i][1], false);
			}
		}
	}

	static void extendSlice(PizzaInfo pizza, int[][] slicedPizza, Slice slice, ArrayList<Integer> dirIndexes) {
		Slice tempSlice = new Slice(slice);
		int extending = 0;
		for (int index : dirIndexes) {
			int oneDirExt = extendSlice(pizza, slicedPizza, tempSlice, direction[index][0], direction[index][1], true);
			if (direction[index][0] == 1)
				tempSlice.r1 += (oneDirExt / (tempSlice.c1 - tempSlice.c0));
			else if (direction[index][0] == -1)
				tempSlice.r0 -= (oneDirExt / (tempSlice.c1 - tempSlice.c0));
			if (direction[index][1] == 1)
				tempSlice.c1 += (oneDirExt / (tempSlice.r1 - tempSlice.r0));
			else if (direction[index][1] == -1)
				tempSlice.c0 -= (oneDirExt / (tempSlice.r1 - tempSlice.r0));
			extending += oneDirExt;
		}

		if (extending > maxExtending) {
			maxExtending = extending;
			bestDirsToExtend = new ArrayList<>(dirIndexes);
		}

		// if we can extend until now continue for adding other directions
		if (dirIndexes.isEmpty() || (!dirIndexes.isEmpty() && extending > 0)) {
			int newDirInd;
			if (dirIndexes.isEmpty())
				newDirInd = 0;
			else
				newDirInd = dirIndexes.get(dirIndexes.size() - 1) + 1;

			for (int i = newDirInd; i < direction.length; i++) {
				ArrayList<Integer> passing = new ArrayList<>(dirIndexes);
				passing.add(i);
				extendSlice(pizza, slicedPizza, slice, passing);
			}
		}
	}

	static int extendSlice(PizzaInfo pizza, int[][] slicedPizza, Slice slice, int dR, int dC, boolean justCount) {
		int count = 0;
		if (dC == 0) {
			if (dR == 1) {
				for (int i = slice.r1;; i++) {
					boolean canExtend = true;
					// make sure we are in PizzaCutter.pizza
					if (i >= pizza.rows)
						break;

					// if we add another row and it become very large skip this
					// extending
					if (slice.getSize() + (i - slice.r1 + 1) * (slice.c1 - slice.c0) > pizza.h)
						break;

					// make sure all of this row can be extended
					for (int j = slice.c0; j < slice.c1; j++) {
						if (slicedPizza[i][j] != 0) {
							canExtend = false;
							break;
						}
					}

					// if this row can be extended, extend it
					// else break because we cannot have hole
					if (canExtend) {
						for (int j = slice.c0; j < slice.c1; j++) {
							if (!justCount)
								slicedPizza[i][j] = slice.num;
							count++;
						}
						if (!justCount)
							slice.r1++;
					} else
						break;
				}
			} else if (dR == -1) {
				for (int i = slice.r0 - 1;; i--) {
					boolean canExtend = true;
					// make sure we are in PizzaCutter.pizza
					if (i < 0)
						break;

					// if we add another row and it become very large skip this
					// extending
					if (slice.getSize() + (slice.r0 - i) * (slice.c1 - slice.c0) > pizza.h)
						break;

					// make sure all of this row can be extended
					for (int j = slice.c0; j < slice.c1; j++) {
						if (slicedPizza[i][j] != 0) {
							canExtend = false;
							break;
						}
					}

					// if this row can be extended, extend it
					// else break because we cannot have hole
					if (canExtend) {
						for (int j = slice.c0; j < slice.c1; j++) {
							if (!justCount)
								slicedPizza[i][j] = slice.num;
							count++;
						}
						if (!justCount)
							slice.r0--;
					} else
						break;
				}
			}
		}

		if (dR == 0) {
			if (dC == 1) {
				for (int j = slice.c1;; j++) {
					boolean canExtend = true;
					// make sure we are in PizzaCutter.pizza
					if (j >= pizza.columns)
						break;

					// if we add another column and it become very large skip
					// this extending
					if (slice.getSize() + (j - slice.c1 + 1) * (slice.r1 - slice.r0) > pizza.h)
						break;

					// make sure all of this row can be extended
					for (int i = slice.r0; i < slice.r1; i++) {
						if (slicedPizza[i][j] != 0) {
							canExtend = false;
							break;
						}
					}

					// if this row can be extended, extend it
					// else break because we cannot have hole
					if (canExtend) {
						for (int i = slice.r0; i < slice.r1; i++) {
							if (!justCount)
								slicedPizza[i][j] = slice.num;
							count++;
						}
						if (!justCount)
							slice.c1++;
					} else
						break;
				}
			} else if (dC == -1) {
				for (int j = slice.c0 - 1;; j--) {
					boolean canExtend = true;
					// make sure we are in PizzaCutter.pizza
					if (j < 0)
						break;

					// if we add another column and it become very large skip
					// this extending
					if (slice.getSize() + (slice.c0 - j) * (slice.r1 - slice.r0) > pizza.h)
						break;

					// make sure all of this row can be extended
					for (int i = slice.r0; i < slice.r1; i++) {
						if (slicedPizza[i][j] != 0) {
							canExtend = false;
							break;
						}
					}

					// if this row can be extended, extend it
					// else break because we cannot have hole
					if (canExtend) {
						for (int i = slice.r0; i < slice.r1; i++) {
							if (!justCount)
								slicedPizza[i][j] = slice.num;
							count++;
						}
						if (!justCount)
							slice.c0--;
					} else
						break;
				}
			}
		}
		return count;
	}

}
