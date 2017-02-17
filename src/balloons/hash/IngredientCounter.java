package balloons.hash;

import java.io.FileNotFoundException;

public class IngredientCounter {
	private static boolean printPicesCount = true;


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
