import java.util.Random;
import java.util.Scanner;

//Donald Newandee
//9.30.2021

public class CrapsLab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		For the lab you need to simulate 2 version of the craps game. The user can specify to play a "game" at a time 
//		until they run out of money or "walk away". They can also run a simulation to see what they will end up with 
//		if they continuously bet a certain amount after running the game a certain number of times. 
//		(Hint: for very large numbers they will always lose money, this means you should NEVER play this)
//
//		Pass Line[edit]
//		The fundamental bet in craps is the pass line bet, which is a bet for the shooter to win. 
//		This bet must be at least the table minimum and at most the table maximum.
//
//
//		If the come-out roll is 7 or 11, the bet wins.
//		If the come-out roll is 2, 3 or 12, the bet loses (known as "crapping out").
//
//
//		If the roll is any other value, it establishes a point.
//		If, with a point established, that point is rolled again before a 7, the bet wins.
//		If, with a point established, a 7 is rolled before the point is rolled again ("seven out"), the bet loses.
//		The pass line bet pays even money.
//
//		You will also need to create an option for the user to run a simulation the trial count specified by the user 
//		and keep track how many times it wins and loses. You will communicate the percentage won and lost to the user.

		Scanner in = new Scanner(System.in);
		Random dieRoll = new Random();
		double bank;
		double bet;
		int die1;
		int die2;
		int point;
		int roll;
		boolean play = true;
		boolean sim;
		double simRunTime;
		int loseCount;
		int winCount;
		// system start
		System.out.println("How much do you have?: ");
		bank = in.nextDouble();
		System.out.println("Bank: " + bank);
		while (play && bank > 0) {
			do { // get a bet
				System.out.println("How much do you bet?: ");
				bet = in.nextDouble();
				if (bet > bank || bet <= 0) {
					System.out.println(
							"That is an invalid Bet. Use a number less than or equal to your bank but more than 0.");
				}
			} while (bet > bank || bet <= 0); // prevent bet out of range of bank
			System.out.println("Do you want to play or simulate? True to simulate; false to play ");
			sim = in.nextBoolean();
			if (sim) { // the simulation aspect
				simRunTime = 0; // reset the sim run time in case of replay
				System.out.println("How many times do you want to run the simulation?: ");
				simRunTime = in.nextDouble();
				loseCount = 0; // reset variables just in case
				winCount = 0;
				double tempBank = bank;
				for (int i = 0; i < simRunTime; i++) {
					tempBank -= bet;
					die1 = dieRoll.nextInt(6) + 1;
					die2 = (int) (Math.random() * 6) + 1;
					point = die1 + die2; // establish the first die roll
					roll = point;
					if (point == 7 || point == 11) { // win
						tempBank += bet * 2;
						winCount++;
					} else if (point == 2 || point == 3 || point == 12) {// lose
						loseCount++;
					} else { // point is established
						do { // perform the actual die rolls for the game
							die1 = dieRoll.nextInt(6) + 1;
							die2 = (int) (Math.random() * 6) + 1;
							roll = die1 + die2;
						} while (roll != point && roll != 7);

						if (roll == 7) {
							loseCount++;
							; // bet was already taken
						} else { // pointC!= point
							tempBank += bet * 2;
							winCount++;
						}
					}
				}
				System.out.println("You won " + ((double) winCount / simRunTime) * 100 + "% of the time.");
				System.out.println("You lost " + ((double) loseCount / simRunTime) * 100 + "% of the time.");
				System.out.println(
						"the simulation ran " + simRunTime + " times and you ended with " + tempBank + " Left.");
			} else { // end of simulation code ; the user plays the game
				bank -= bet; // take the money already; once a valid bet is established
				die1 = dieRoll.nextInt(6) + 1;
				die2 = (int) (Math.random() * 6) + 1;
				point = die1 + die2; // establish the first die roll
				roll = point;
				System.out.println("the point is: " + point);
				if (point == 7 || point == 11) { // win
					bank += bet * 2; // double bet
					System.out.println("You Win!");
					System.out.println("Bank: " + bank + "\n");
				} else if (point == 2 || point == 3 || point == 12) {// lose
					System.out.println("You lose!");
					System.out.println("Bank: " + bank + "\n");
				} else { // point is established
					do { // perform the actual die rolls for the game
						die1 = dieRoll.nextInt(6) + 1;
						die2 = (int) (Math.random() * 6) + 1;
						roll = die1 + die2;
						System.out.println("the next roll was: " + (roll));
					} while (roll != point && roll != 7); // ----------------add more conditions?

					if (roll == 7) {
						System.out.println("You lose!"); // bet was already taken
					} else { // pointC!= point
						System.out.println("You Win!");
						bank += bet * 2;
					}
					System.out.println("Bank: " + bank + "\n");
				}
			}
			System.out.println("Do you want to keep playing? true or false: ");
			play = in.nextBoolean();
			if (bank <= 0) { // user must walk away when they have 0 money left
				System.out.println("You can not play anymore: you do not have any more money.");
				play = false;
			}
		}
		System.out.println("Good bye.");
	}

}
