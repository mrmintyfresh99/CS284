import java.util.Arrays;

/**
 * Class that defines an object of the type BinaryNumber
 * @author Mathew Seedhom
 * I pledge my honor that I have abided by the Stevens Honor System.
 * I am using big-endian format for this assigment.
 * CS 284B
 */
public class BinaryNumber {
	private int data[];
	private boolean overflow = false;
	
	/**
	 * Constructor that creates a Binary Number of 0's with a specified length.
	 * @param length
	 * @throws Exception
	 */
	public BinaryNumber(int length) throws Exception{
		if (length < 0) {
			throw new Exception("Length must be a non-negative integer.");
		}
		int n = 0;
		data = new int[length];
		while (n < length - 1) {
			data[n] = 0;
			n++;
		}
	}
	
	/**
	 * Constructor that takes a string value and makes a Binary Number.
	 * @param str 
	 */
	public BinaryNumber(String str) throws Exception{
		int i = Integer.parseInt(str);
		while (i != 0){
			if (i % 10 > 1 || i < 0){
				throw new Exception("Binary Number cannot be negative, and must only have zeros and ones.");
			}
			i = i/10;
		}
		data = new int[str.length()];
		for (int z = 0; z < str.length(); z++) {
			data[z] = Character.getNumericValue(str.charAt(z)); 
		}
	}
	
	/**
	 * Method that returns the length of the BinaryNumber array.
	 * @return length of BinaryNumber
	 */
	public int getLength() {
		return data.length;
	}
	
	/**
	 * Method that returns the digit of the BinaryNumber at an index.
	 * @param index
	 * @return digit at the index
	 */
	public int getDigit(int index) throws Exception{
		try {
			int tempint = data[index];
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new Exception("Index must be greater than 0 and less than maximum index of the BinaryNumber.");
		}
		return data[index];
	}
	
	/**
	 * Method that converts the BinaryNumber to a Decimal Number.
	 * @return Decimal Number representation
	 */
	public int toDecimal() {
		int a = 0;
		for (int i = 0; i < data.length; i ++) {
			a = a * 2 + data[i];
		}
		return a;
	}
	
	/**
	 * Shifts the number by a specified amount of 0's to the right
	 * @param amount
	 */
	public void shiftR(int amount) throws Exception{
		if (amount < 0) {
			throw new Exception("Amount must be a non-negative integer.");
		}
		int array[] = new int[data.length + amount];
		for (int i = 0; i < amount; i ++) {
			array[i] = 0;
		}
		for (int i = amount; i < data.length + amount; i ++) {
			array[i] = data[i - amount];
		}
		data = array;
	}
	
	/**
	 * Method that adds 2 Binary Numbers of the same length, and indicates whether there was an overflow.
	 * @param aBinaryNumber
	 */
	public void add(BinaryNumber aBinaryNumber) throws Exception{
		if (aBinaryNumber.getLength() != data.length) {
			throw new Exception("Lengths of the two Binary Numbers need to match.");
		}
		int c = 0;
		int sum[] = new int[data.length];
		for (int i = data.length - 1; i < data.length && i > -1; i --) {
			int temp = c + data[i] + aBinaryNumber.getDigit(i);
			if (temp == 0) {
				sum[i] = 0;
				c = 0;
			}
			if (temp == 1) {
				sum[i] = 1;
				c = 0;
			}
			if (temp == 2) {
				sum[i] = 0;
				c = 1;
			}
			if (temp == 3) {
				sum[i] = 1;
				c = 1;
			}
		}
		data = sum;
		if (c == 1) {
			overflow = true;
		}
	}
	
	/**
	 * Sets overflow back to the default false.
	 */
	public void clearOverflow() {
		overflow = false;
	}
	
	/**
	 * Converts the BinaryNumber to a string, and indicates if there is overflow.
	 */
	public String toString() {
		if (overflow == true) {
			return "Overflow";
		}
		return Arrays.toString(data);
	}
	
	public static void main(String[] args) throws Exception {

	}
}
