package balloons.hash;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * Writes the slices info to the output file.
 * 
 * @author carvalhorr
 *
 */
public class OutputWriter {

	public static void writeOutput(String fileName, SlicedPizza pizza) {
		
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    
		    // Writes the number of slices
		    writer.println(pizza.numberOfSlices);
		    
		    // Write all the slices
		    for (int i = 1; i <= pizza.numberOfSlices; i++) {
		    	Slice s = pizza.slices.get(i);
		    	writer.println(s.r0 + " " + s.c0 + " " + (s.r1 - 1) + " " + (s.c1 - 1));
		    }
		    
		    writer.close();
		
		} catch (IOException e) {
		
			System.out.println("Error writing file: " + fileName);
			e.printStackTrace();
		
		}
		
	}
}