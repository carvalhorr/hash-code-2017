package balloons.hash.competition;

import java.util.ArrayList;
import java.util.HashMap;

public class Knapsack {
	
	// return list of videos should be in this cache
	// list of videos and their values that are suitable for this cache
	// array of sizes of all videos in our world
    public static ArrayList<Integer> knapsack(int cacheSize, HashMap<Integer, Integer> videos, int videos_sizes[]) {
        int N = videos_sizes.length; // Get the total number of items. Could be wt.length or val.length. Doesn't matter
        int[][] V = new int[N + 1][cacheSize + 1]; //Create a matrix. Items are in rows and weight at in columns +1 on each side
        //What if the knapsack's capacity is 0 - Set all columns at row 0 to be 0
        for (int col = 0; col <= cacheSize; col++) {
            V[0][col] = 0;
        }
        //What if there are no items at home.  Fill the first row with 0
        for (int row = 0; row <= N; row++) {
            V[row][0] = 0;
        }
        for ( int item = 1; item <= N; item++) {
//            //Let's fill the values row by row
//            for ( int weight=1; weight <= cacheSize; weight++ ) { 
//                //Is the current items weight less than or equal to running weight
//                if ( videos.get(item-1) <= weight ) {
//                    //Given a weight, check if the value of the current item + value of the item that we could afford with the remaining weight
//                    //is greater than the value without the current item itself
//                    V[item][weight] = Math.max(val[item-1]+V[item-1][weight-wt[item-1]], V[item-1][weight]);
//                }
//                else {
//                    //If the current item's weight is more than the running weight, just carry forward the value without the current item
//                    V[item][weight]=V[item-1][weight];
//                }
//            }
        }
//        //Printing the matrix
//        for (int[] rows : V) {
//            for (int col : rows) {
//                System.out.format("%5d", col);
//            }
//            System.out.println();
//        }
//        return V[N][cacheSize];
    	return new ArrayList<>();
    }
}