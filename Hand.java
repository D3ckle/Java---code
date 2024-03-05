
public class Hand {
//  Donald Newandee
//  nov 10 2021
//  BragsLab
//	You will need to develop 3 classes, the card class, the Hand class and the Driver class.
//	The card class will have the instance variables of card rank (integer) and suit (character). On top
//	of the standard getters, setters, toString, default and all parameter constructors, you will also
//	need to have:
//	The Hand class will have 3 card objects as its instance variables. The class needs to have a
//	default constructor and one with 3 parameters, getters, setters and toString. Chain the setters.
//	And also have the following method.

	private Card c1, c2, c3;

	public Hand() { // default
		super();
		// TODO Auto-generated constructor stub
	}

	public Hand(Card c1, Card c2, Card c3) { // 3 parameters
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		order();
	}

	private void order() {// organizes the cards c1 being smallest, c3 the biggest

		if (this.c1.getRank() > this.c2.getRank()) {
			Card temp = this.c1;
			c1 = c2;
			c2 = temp;
		}
		if (this.c2.getRank() > this.c3.getRank()) {
			Card temp = this.c2;
			c2 = c3;
			c3 = temp;
		}
		if (this.c1.getRank() > this.c2.getRank()) {
			Card temp = this.c1;
			c1 = c2;
			c2 = temp;
		}
	}

	public Card getC1() {
		return c1;
	}

	public void setC1(Card c1) {
		this.c1 = c1;
	}

	public Card getC2() {
		return c2;
	}

	public void setC2(Card c2) {
		this.c2 = c2;
	}

	public Card getC3() {
		return c3;
	}

	public void setC3(Card c3) {
		this.c3 = c3;
	}

	public String toString() { // display hand (3 cards) onto console
		String output = "";
		output = c1.toString() + "; \n" + c2.toString() + "; \n" + c3.toString() + "; \n";
		return output;
	}

	public void deal() { // - deal. This will generate 3 random cards. You need to ensure that all the
							// cards are
//	different within a Hand but 2 Hand objects can have duplicated cards between them.
//	You should use a private helper method for this one.
		do {
			c1 = createCard();
			c2 = createCard();
			c3 = createCard();
		} while (cardsEqual(c1, c2, c3));
		order();
	}

	private Card createCard() { // randomly creates a card
		int rank = (int) (Math.random() * 13) + 1;
		int suit = (int) (Math.random() * 4) + 1;
		return new Card(rank, suit);
	}

	private boolean cardsEqual(Card n1, Card n2, Card n3) { // compares 3 cards together to make sure theyre different
		if ((n1.getSuit() == n2.getSuit() && n1.getRank() == n2.getRank())
				|| (n3.getSuit() == n2.getSuit() && n3.getRank() == n2.getRank())
				|| (n1.getSuit() == n3.getSuit() && n1.getRank() == n3.getRank()))
			/// -----------------red flag
			return true;
		return false;
	}

	public void deal(Card c1, Card c2, Card c3) { // deal (card one, card two, card three) stacked. So the cards will be
													// provided and set to
//		the hand. Create new cards for this.
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;

	}

	public String value() { // value – refer to Wikipedia for the different RANKS of combos
		// this should return a String (e.g. Pair;K or Straight Flush;J) use a semicolon
		// for a delimeter.

		// Straight flush Three suited cards in sequence
		if ((c1.getSuit() == c2.getSuit() && c1.getSuit() == c3.getSuit()) && // same suit
				(c1.getRank() + 1 == c2.getRank() && c2.getRank() + 1 == c3.getRank())) // consecutive rank
			return "straightflush;" + c3.getChar();
		// Prial or Three of a kind Three cards of same rank
		else if (c1.getRank() == c2.getRank() && c2.getRank() == c3.getRank())
			return "threeofkind;" + c3.getChar();
		// Straight Three cards in sequence
		else if (c1.getRank() + 1 == c2.getRank() && c2.getRank() + 1 == c3.getRank())
			return "straight;" + c3.getChar();
		// Flush Three suited cards
		else if (c1.getSuit() == c2.getSuit() && c1.getSuit() == c3.getSuit()) // same suit
			return "flush;" + c3.getChar();
		// Pair Two cards of same rank
		else if (c1.getRank() == c2.getRank() || c2.getRank() == c3.getRank())
			return "pair;" + c3.getChar();
		// High card
		else
			return "highcard;" + c3.getChar();
	}

//	- compareTo. Compares to another Hand and see who has a better hand. How far away
//	one hand is from the other (e.g. three of a kind compared to flush should return 2,
//	return 0 if they’re the same and return 10 if you won by a tie and -10 if you lost by a tie)
	public int compareTo(Hand h) { // all negative return values = loss / all positive values = win

		String str1 = this.value(); // this compared to that (aka h)
		String str2 = h.value();

		// get numerical value from string
		int int1 = numVal(str1);
		int int2 = numVal(str2);
		// compare values
		int diff = int1 - int2;
		if (diff != 0) // different hand ranks compared
			// three of a kind compared to flush should return 2, bc 2 difference
			return diff;
		else // ties / tie breakers
				// tie breakers
			//If the colors of the cards are ever the same then that will beat ties
		if (this.allOneColor() && !h.allOneColor()) // hand1 is all one color while hand2 is not
			return 10;
		else if (h.allOneColor() && !this.allOneColor())// hand2 is all one color while hand1 is not
			return -10;
		else { // either both all one color or both have miss match colored cards; compare highcard
			if (this.c3.getRank() > h.c3.getRank()) // win tie breaker
				return 10;
			else if (this.c3.getRank() < h.c3.getRank()) // lose tie breaker
				return -10;
		}
		return 0; // complete tie in the end
	}

	private boolean allOneColor() { /// tells me if a hand is all a solid color
		if ((this.c1.getColor() && this.c2.getColor() && this.c3.getColor()) || // all red
				(!this.c1.getColor() && !this.c2.getColor() && !this.c3.getColor())) // or all black
			return true;
		return false;
	}

	public int numVal(String str) { // value string into int value to compare; paramarter is a string w/ semicolon
									// delim
		int i = str.indexOf(';');
		String newStr = str.substring(0, i);
		switch (newStr) {
		case "straightflush":
			return 6;
		case "threeofkind":
			return 5;
		case "straight":
			return 4;
		case "flush":
			return 3;
		case "pair":
			return 2;
		default: // high card
			return 1;
		}
	}

	public void fold() {// Erases all the cards from memory. (just in the hand that this method is
						// refered to)
		this.c1 = null;
		this.c2 = null;
		this.c3 = null;
	}

	public boolean equal(Hand h) {// equal Compares two hand values to see if they’re the same.
		if (this.value().equals(h.value())) // comparing string to string
			return true; // same
		return false; // different
	}
}
