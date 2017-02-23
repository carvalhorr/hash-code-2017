package balloons.hash.competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Knapsack {

	// return list of videos should be in this cache
	// list of videos and their values that are suitable for this cache
	// array of sizes of all videos in our world
	public static ArrayList<Integer> knapsack(int cacheSize, Map<Integer, Integer> videos, int videos_sizes[]) {
		int N = videos.size(); // Get the total number of items. Could be wt.length or val.length. Doesn't matter
		int[][] V = new int[N+1][cacheSize+1]; //Create a matrix. Items are in rows and weight at in columns +1 on each side
		
		//What if the knapsack's capacity is 0 - Set all columns at row 0 to be 0
		for (int col = 0; col < cacheSize; col++) {
			V[0][col] = 0;
		}
		//What if there are no items at home.  Fill the first row with 0
		for (int row = 0; row < N; row++) {
			V[row][0] = 0;
		}

//		int[][] video_value = new int [videos.size()][2];
//		int i = 0;
//		for (Map.Entry<Integer, Integer> video : videos.entrySet()) {
//			video_value[i++][0] = video.getKey()
//		}
		
		int prev_value = 0;
		int prev_key = 0;
		Iterator it = videos.entrySet().iterator();
		
		for ( int item = 0; item < videos.size(); item++) {
			//Let's fill the values row by row
			for ( int size = 0; size <= cacheSize; size++ ) { 
                if ( item == 0 || size == 0 ) 
                    V[item][size] = 0;
                
				//Is the current items weight less than or equal to running weight
                else if ( videos_sizes[prev_key] <= size ) {
					//Given a weight, check if the value of the current item + value of the item that we could afford with the remaining weight
					//is greater than the value without the current item itself
					V[item][size] = Math.max(prev_value+V[item-1][size - videos_sizes[prev_key]], V[item-1][size]);
				}
                
				else {
					//If the current item's weight is more than the running weight, just carry forward the value without the current item
					V[item][size]=V[item-1][size];
				}
			}
			Map.Entry video = (Entry) it.next();
			prev_key = (int) video.getKey();
			prev_value = (int) video.getValue();
			item++;
		}
		
        //Printing the matrix
        for (int[] rows : V) {
            for (int col : rows) {
                System.out.format("%5d", col);
            }
            System.out.println();
        }

		return new ArrayList<>();
	}
}