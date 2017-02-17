package balloons.hash;

import java.io.IOException;
import java.io.PrintWriter;

public class OutputWriter {

	public static void writeOutput(String fileName, SlicedPizza pizza) {
		try{
		    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		    writer.println(pizza.numberOfSlices);
		    for (int i = 1; i <= pizza.numberOfSlices; i++) {
		    	Slice s = pizza.slices.get(i);
		    	writer.println(s.r1 + " " + s.c1 + " " + s.r2 + " " + s.c2);
		    }
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
}
