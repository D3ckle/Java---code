
public class Monopoly {
	private static String[] board = { " go ", "brwn", "chnc", "brwn", "tax ", " rr ", "lblu", "chnc", "lblu", "lblu",
			"jail", "prpl", "util", "prpl", "prpl", " rr ", "orng", "chnc", "orng", "orng", "park", "red ", "chnc",
			"red ", "red ", " rr ", "yelw", "yelw", "util", "yelw", "goto", "gren", "gren", "chnc", "gren", " rr ",
			"chnc", "blue", "tax ", "blue" }; // brown, go, jail, etc.
	private int position; // (0-39) position of only one player; no other players
	private int[] tally; // list of 10 properties types (each color property in monopoly);
	// keep track of how many times each type of property was landed on

	public Monopoly() {
		position = 0;
		tally = new int[10]; // all zero
	}

	public String move() {// move() – rolls 2 dice and moves the piece to a new spot.
		// Make sure to properly wrap around the
		// board and perform the appropriate action and count update.
		// Returns a String on what happened

		// role 2 dice and add them together
		int d1 = (int) (Math.random() * 6 + 1);
		int d2 = (int) (Math.random() * 6 + 1);

		position += d1 + d2;// position += roll
		if (position > 39)
			position -= 40;// check for position BIGGER (NOT EQUAL) 39th position, then subtract 40
							// (passing go)

		// now checking for things that could move the player
		if (position == 30)// check if landed on go to jail; if lnaded on gotojail (pos 30), and move
							// position to 10
			position = 10;
		chance();
		// update tallies
		if (board[position] == "brwn")// 0 = Brown
			tally[0]++;
		if (board[position] == " rr ")// 1 = Railroad
			tally[1]++;
		if (board[position] == "lblu")// 2 = LightBlue
			tally[2]++;
		if (board[position] == "util")// 3 = Utilities
			tally[3]++;
		if (board[position] == "prpl")// 4 = Purple
			tally[4]++;
		if (board[position] == "orng")// 5 = Orange
			tally[5]++;
		if (board[position] == "red ")// 6 = Red
			tally[6]++;
		if (board[position] == "yelw")// 7 = Yellow
			tally[7]++;
		if (board[position] == "gren")// 8 = Green
			tally[8]++;
		if (board[position] == "blue")// 9 = Blue
			tally[9]++;

		return "dice1: " + d1 + " | dice2: " + d2 + " | position: " + position;
	}

	public String move(int i) {
		// move(int) - moves the piece to a particular spot and does the proper actions.
		// Returns a string of the new location and the array index. So “LightBlue;9”
		position = i;
		if (position > 39)
			position -= 40;// check for position BIGGER (NOT EQUAL) 39th position, then subtract 40
							// (passing go)
		// now checking for things that could move the player
		if (position == 30)// check if landed on go to jail; if landed on gotojail (pos 30), and move
							// position to 10
			position = 10;
		chance(); // envoke chance cards: could change position
		// update tallies + return string output
		// this cout output jail, parking, etc
		if (board[position] == "brwn") {// 0 = Brown
			tally[0]++;
			return "Brown;" + position;
		} else if (board[position] == " rr ") {// 1 = Railroad
			tally[1]++;
			return "Railroad;" + position;
		} else if (board[position] == "lblu") {// 2 = LightBlue
			tally[2]++;
			return "LightBlue;" + position;
		} else if (board[position] == "util") {// 3 = Utilities
			tally[3]++;
			return "Utilities;" + position;
		} else if (board[position] == "prpl") {// 4 = Purple
			tally[4]++;
			return "Purple;" + position;
		} else if (board[position] == "orng") {// 5 = Orange
			tally[5]++;
			return "Orange;" + position;
		} else if (board[position] == "red ") {// 6 = Red
			tally[6]++;
			return "Red;" + position;
		} else if (board[position] == "yelw") {// 7 = Yellow
			tally[7]++;
			return "Yellow;" + position;
		} else if (board[position] == "gren") {// 8 = Green
			tally[8]++;
			return "Green;" + position;
		} else if (board[position] == "gren") {
			tally[9]++;
			return "Blue;" + position;// 9 = Blue
		} else if (board[position] == "jail") { // no tally
			return "Jail;" + position;
		} else if (board[position] == " go ") { // no tally
			return "Go;" + position;
		} else if (board[position] == "park") { // no tally
			return "FreeParking;" + position;
		} else // no tally
			return "Chance;" + position;
	}

	private void chance() { // potentially change the position
//		If you land on Chance (see the appendix) generate a number between 1 and 32 and do the
//		action that affect position.
		if (!(position == 2 || position == 7 || position == 17 || position == 2 || position == 22 || position == 33
				|| position == 36)) // the chance positions
			return;
		int num = (int) (Math.random() * 32 + 1); // generate a random number between 1 - 32
		switch (num) {
		case 1:// 1. Advance to Boardwalk
			position = 39;
			break;
		case 2, 17:// 2 & 17. Advance to Go (Collect $200)
			position = 0;
			break;
		case 3:// 3. Advance to Illinois Avenue. If you pass Go, collect $200
			position = 24;
			break;
		case 4:// 4. Advance to St. Charles Place. If you pass Go, collect $200
			position = 11;
			break;
		case 5, 6: // 5 & 6. Advance to the nearest Railroad. If unowned, you may buy it from the
					// Bank. If owned, pay wonder twice the rental to which they are otherwise
					// entitled
			if (position > 5 && position <= 15)
				position = 15;
			else if (position > 15 && position <= 25)
				position = 25;
			else if (position > 25 && position <= 35)
				position = 35;
			else
				position = 5;
			break;
		case 7: // 7. Advance token to nearest Utility. If unowned, you may buy it from the
				// Bank. If owned, throw dice and pay owner a total ten times amount thrown.

			if (position > 12 && position <= 28)
				position = 28;
			else
				position = 12;
			break;
		case 10:// 10. Go Back 3 Spaces
			if (position < 3)
				position = 39 + (position - 3);
			else
				position -= 3;
			break;
		case 11, 22: // 11. Go to Jail. Go directly to Jail, do not pass Go, do not collect $200
			position = 10;
			break;

		case 14:// 14. Take a trip to Reading Railroad. If you pass Go, collect $200
			position = 5;
			break;

		default:
			break;
		}

		// only 11 actually change position, since we are not tracking money
		// closest railroad going forward, can not go backwards
		// nearest utility, just do the same with railroad but with util spots

	}

	public String toString() {
		// Other than the standard getters, setters and constructors you need to create
		// a toString method
		// that prints the board the way it is supposed to look and then make the
		// current location of the
		// piece be in ALL CAPS.
		String temp = board[position]; // memorize the original String of the location
		board[position] = board[position].toUpperCase(); // make only current location uppercase
		// drawing the board

		String output = "========================================================================\n";

		// top row
		// bottom row
		switch (position) {
		case 20:
			output += "||X                                                                   ||\n";
			break;
		case 21:
			output += "||             X                                                      ||\n";
			break;
		case 22:
			output += "||                  X                                                 ||\n";
			break;
		case 23:
			output += "||                       X                                            ||\n";
			break;
		case 24:
			output += "||                            X                                       ||\n";
			break;
		case 25:
			output += "||                                 X                                  ||\n";
			break;
		case 26:
			output += "||                                      X                             ||\n";
			break;
		case 27:
			output += "||                                           X                        ||\n";
			break;
		case 28:
			output += "||                                                X                   ||\n";
			break;
		case 29:
			output += "||                                                     X              ||\n";
			break;
		case 30:
			output += "||                                                                   X||\n";
			break;
		default:
			output += "||                                                                    ||\n";
			break;
		}
		output += "||\t";

		for (int i = 20; i != 31; i++) {
			output += board[i] + "|";
		}
		output += "       ||\n";
		// rows in middle
		if (position == 19)
			output += "||X\t";
		else
			output += "||\t";
		output += board[19] + "|                                              |" + board[31];
		if (position == 31)
			output += "     X||\n";
		else
			output += "      ||\n";
		if (position == 18)
			output += "||X\t";
		else
			output += "||\t";
		output += board[18] + "|                                              |" + board[32];
		if (position == 32)
			output += "     X||\n";
		else
			output += "      ||\n";
		if (position == 17)
			output += "||X\t";
		else
			output += "||\t";
		output += board[17] + "|                                              |" + board[33];
		if (position == 33)
			output += "     X||\n";
		else
			output += "      ||\n";
		if (position == 16)
			output += "||X\t";
		else
			output += "||\t";
		output += board[16] + "|                                              |" + board[34];
		if (position == 34)
			output += "     X||\n";
		else
			output += "      ||\n";
		if (position == 15)
			output += "||X\t";
		else
			output += "||\t";
		output += board[15] + "|                                              |" + board[35];
		if (position == 35)
			output += "     X||\n";
		else
			output += "      ||\n";
		if (position == 14)
			output += "||X\t";
		else
			output += "||\t";
		output += board[14] + "|                                              |" + board[36];
		if (position == 36)
			output += "     X||\n";
		else
			output += "      ||\n";
		if (position == 13)
			output += "||X\t";
		else
			output += "||\t";
		output += board[13] + "|                                              |" + board[37];
		if (position == 37)
			output += "     X||\n";
		else
			output += "      ||\n";
		if (position == 12)
			output += "||X\t";
		else
			output += "||\t";
		output += board[12] + "|                                              |" + board[38];
		if (position == 38)
			output += "     X||\n";
		else
			output += "      ||\n";
		if (position == 11)
			output += "||X\t";
		else
			output += "||\t";
		output += board[11] + "|                                              |" + board[39];
		if (position == 39)
			output += "     X||\n";
		else
			output += "      ||\n";

		// output += "|| |"+ list[0] + "|\t\t\t|"+list[0]+"| ||\n";

		// bottom row
		output += "||\t";
		for (int i = 10; i != -1; i--) {
			output += board[i] + "|";
		}
		output += "       ||\n";
		switch (position) {
		case 10:
			output += "||x                                                                   ||\n";
			break;
		case 9:
			output += "||             X                                                      ||\n";
			break;
		case 8:
			output += "||                  X                                                 ||\n";
			break;
		case 7:
			output += "||                       X                                            ||\n";
			break;
		case 6:
			output += "||                            X                                       ||\n";
			break;
		case 5:
			output += "||                                 X                                  ||\n";
			break;
		case 4:
			output += "||                                      X                             ||\n";
			break;
		case 3:
			output += "||                                           X                        ||\n";
			break;
		case 2:
			output += "||                                                X                   ||\n";
			break;
		case 1:
			output += "||                                                     X              ||\n";
			break;
		case 0:
			output += "||                                                                   X||\n";
			break;
		default:
			output += "||                                                                    ||\n";
			break;
		}
		output += "========================================================================";

		board[position] = temp; // reset the board to original
		return output;
	}

	public void reset() {// reset() – resets the counts of property landings
		for (int i = 0; i < 10; i++) {
			tally[i] = 0;
		}
	}

	public double[] getStats(int simCount) { // simcount = the number of times to run simulation
		// getStats() – returns the array of current stats.
		double[] count = new double[10]; // parallel array that tells stats of each property in percent
		for (int i = 0; i < simCount; i++) {
			move();
		}
		for (int i = 0; i < 10; i++) { // gives percent occurrence of each property type
			count[i] = ((double) tally[i]) / simCount * 100.0;
			count[i] = (Math.round(((double) tally[i]) / simCount * 10000.0))/100.0;
		}
		return count;
	}

	public String printStats(double[] arr) {
		String output = "{Brown: " + arr[0] + "}\n";
		output += "{RailRoad: " + arr[1] + "}\n";
		output += "{LightBlue: " + arr[2] + "}\n";
		output += "{Utilities: " + arr[3] + "}\n";
		output += "{Purple: " + arr[4] + "}\n";
		output += "{Orange: " + arr[5] + "}\n";
		output += "{Red: " + arr[6] + "}\n";
		output += "{Yellow: " + arr[7] + "}\n";
		output += "{Green: " + arr[8] + "}\n";
		output += "{Blue: " + arr[9] + "}\n";
		return output;
	}

	public static String[] getBoard() {
		return board;
	}

	public static void setBoard(String[] board) {
		Monopoly.board = board;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int[] getTally() {
		return tally;
	}

	public void setTally(int[] tally) {
		this.tally = tally;
	}
}
