package balloons.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factors {
	public static Map<Integer, List<Integer>> getFactors(int l, int h) {
		Map<Integer, List<Integer>> factors = new HashMap<Integer, List<Integer>>();
		for(int n = l*2; n <= h; n++) {
			factors.put(n, getFactorsForNumber(n));
		}
		return factors;
	}
	
	public static List<Integer> getFactorsForNumber(int n) {
		List<Integer> factorsForNumber = new ArrayList<Integer>();
		factorsForNumber.add(1);
		factorsForNumber.add(n);
		for(int i = 2; i <= (n+1)/2; i ++) {
			if(n%i == 0) factorsForNumber.add(i);
		}
		return factorsForNumber;
	}


}
