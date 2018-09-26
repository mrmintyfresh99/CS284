package Maze;

/**
 * Class that builds a Pair of integers.
 * @author Mathew Seedhom
 *I pledge my honor That I have abided by the Stevens Honor System.
 */
public class PairInt {

	private int x;
	private int y;
	
	/**
	 * Constructor of the PairInt.
	 * @param x
	 * @param y
	 */
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x value of a PairInt.
	 * @return x value of PairInt
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Returns the y value of a PairInt.
	 * @return y value of PairInt
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Changes the x value of a PairInt.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Changes the y value of a PairInt.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Checks if a PairInt is equivalent to an object.
	 * @return if p is equivalent to a PairInt
	 */
	public boolean equals(Object p) {
		if (p instanceof PairInt) {
			PairInt temp = (PairInt) p;
			return (this.x == temp.getX() && this.y == temp.getY());
		} else {
			return false;
		}
	}
	
	/**
	 * Returns a String representation of the PairInt.
	 * @return string of PairInt
	 */
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	/**
	 * Copies a PairInt.
	 * @return new PairInts
	 */
	public PairInt copy() {
		return new PairInt(this.x, this.y);
	}
}
