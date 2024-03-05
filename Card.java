
public class Card {
//  Donald Newandee
//  nov 10 2021
//  BragsLab
	// The Card class will have the instance variables of card rank (integer) and
	// suit (character).
	// On top
	// of the standard getters, setters, toString, default and all parameter
	// constructors, you will also
	// need to have:
	// - getChar() A method that returns the character of the card (e.g. if the rank
	// is 13 it will
	// return a ‘K’)
	// - getColor() A method that returns the color (red is true and black is false)
	private int rank, suit;

	public Card() {// default
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(int rank, int suit) {// all parameter constructor
		super();
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSuit() {
		return suit;
	}
	
	public char getSuitChar() {
		char temp;
		switch (suit) {
		case 1:
			temp = 'D';
			break;
		case 2:
			temp = 'H';
			break;
		case 3:
			temp = 'C';
			break;
		default:
			temp = 'S';
		}
		return temp;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public boolean getColor() { // determines the color of the card
		if (suit == 1 || suit == 2)
			return true; // red diamond / hearts
		return false; // black clubs and clover
	}

	public char getChar() { // converts the ranks into a char for display purposes
		switch (rank) {
		case 1:
			return 'A';
		case 10:
			return 'T';
		case 11:
			return 'J';
		case 12:
			return 'Q';
		case 13:
			return 'K';
		default:
			return (char) (this.rank + '0');
		}
	}

	public String toString() { // output a single card on console
		String output = "";
		output += this.getChar();
		output += " of " + this.getSuitChar();
		return output;
	}
}
