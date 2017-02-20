package balloons.hash;

/**
 * Info read from file.
 * 
 * @author carvalhorr
 *
 */
public class PizzaInfo {

	// Matrix of pieces os (T)omatoes or (M)ushrooms
	public char[][] data;
	
	// Number of rows
	public int rows;
	
	// Number of columns
	public int columns;
	
	// Minimum number of cells of each ingredient that each slice must have
	public int l;
	
	// Maximun number of cells that a slice of pizza can have
	public int h;

	public PizzaInfo(char[][] data, int r, int c, int l, int h) {
		this.rows = r;
		this.columns = c;
		this.l = l;
		this.h = h;
		this.data = data;
	}
}
