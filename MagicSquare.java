
public class MagicSquare {
	private int[][] sqr;

	public MagicSquare(int side) { // The goal of the lab is to build a class called MagicSquare which has a 2D
									// square array of
		// integers as it’s private variable which will analyze if the square array is
		// “magic”.
		sqr = new int[side][side];
		// magic number is equal to n(n*n + 1)/2.

		// populate numbers into the square
		int[] arr = random1D();

		// assign the numbers from the 1D array to the private 2D array
		fillSquare(arr);
	}

	private int[] create1D() {// make a 1D array that will have all the values in order first
		int[] arr = new int[sqr.length* sqr.length];
		for (int i = 0; i < arr.length; i++)
			arr[i] = i + 1; // 1 through n^2
		return arr;
	}
	
	private int[] random1D() {
		int[] arr = create1D();	// loop that sets the values from 1-n^2

		// shuffle
		for (int i = 0; i < arr.length; i++) { // randomize the values in the 1D array
			int ranLoc = (int) (Math.random() * arr.length);
			int temp = arr[ranLoc];
			arr[ranLoc] = arr[i];
			arr[i] = temp;
		}
		return arr;
	}

	public void randomize() {// - randomize() fill the square with random integers from 1 to length^2 (so 1-9 for above)
		int[] rand = random1D();
		fillSquare(rand);
	}

	public boolean fillSquare(int[] arr) { // - fillSquare(int[] arr) which will fill the 2D array with a 1D array
											// provided starting at the top left and going right.
		if (arr.length != sqr.length * sqr.length) // Make this a boolean that will return false if the 1D array doesn’t
													// match the spots in the 2D array.
			return false;
		int count = 0;
		for (int i = 0; i < sqr.length; i++) {
			for (int j = 0; j < sqr[i].length; j++) {
				sqr[i][j] = arr[count];
				count++;
			}
		}
		return true;
	}

	public boolean checkIfMagic() {	// - checkIfMagic() checks whether or not the square is magic. You should use
	// private methods to help break up the algorithms into parts. 
		
		//rows
		//columns
		//diagonals
		//isUnique in row / column / diagonal
		
		//is all numbers unique
		return true;
	}
	private boolean rowIsMagic() {
		
		return true;
	}

	//(check for rows, columns, and diagonals are the same, and if every number is unique)
	// Make sure all the numbers are unique. This would be easiest with a 1D array.

	public String toString() { 	// The toString method for this class should show the magic square in a pleasing
		// way and differentiate the way it’s presented if it’s magic or not magic. Be
		// creative.
		String temp = "";
		for (int i = 0; i < sqr.length; i++) {
			for (int j = 0; j < sqr[i].length; j++) {
				temp += "|" + sqr[i][j] + "|";
			}
			temp += "\n";
		}
		return temp;
	}

	public int[][] getSqr() {
		return sqr;
	}

	public void setSqr(int[][] sqr) {
		this.sqr = sqr;
	}

	// A square grid is “magic” if the all the rows, columns and diagonals add up to
	// the same number and
	// every number appears only once. For example
	// 6 1 8
	// 7 5 3
	// 2 9 4
	// Other than the standard methods for a class, the MagicSquare class also needs
	// to have the following methods.

	// - equals(MagicSquare other) A magicSquare is the same if all the squares are
	// the same if rotated
	// e.g. the above square would be the same as
	// 2 7 6
	// 9 5 1
	// 4 3 8
	// To do this you should build a method that rotates a square 90 degrees and
	// checks if the squares
	// now become the same. Do this 3 times, if any one of them equal then it must
	// be the “same”
	// You should also build a Driver class that tests this. Build a loop in there
	// to create a magic square by randomly generating squares until a magic one is
	// created. A dimension should bE inputted from the user.
}
