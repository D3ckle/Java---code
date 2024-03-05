//Donald A Newandee
// Sept. 16 2021
//this program will take the uer's inputed values and output whether or not you owe money to the
//government or the government owes you money.
//program is re-runnable and rerunning does not affect the outcome the second time

import java.util.Scanner;

public class TaxCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		// double age = input.nextDouble(); // reference for getting input

		// adjusted - first bracket
		// taxing 10% on first 9700, sub 9700 form adjusted,
		// tax .12 on next 39475 then
		// next, equal amount for these ranges

		boolean runProg = true;

		while (runProg) { // start of loop
			double rate1 = .1;// tax rates
			double rate2 = .12;
			double rate3 = .22;
			double rate4 = .35;

			double adjustedInc;
			int marriedDeduct = 22400;
			int singleDeduct = 12200;
			int dependencyRate = 6000;
			double tax = 0;
			int bracket1;
			int bracket2;
			int bracket3;

			// calc taxes on adjusted income

			System.out.println("What is your gross income?");// how much you make
			double grossInc = input.nextDouble();
			// double grossInc = 75000;
			// System.out.println("Gross income: " + grossInc);

			System.out.println("Are you married? Yes = true, No = false: ");
			boolean married = input.nextBoolean();
			// boolean married = false;
			// System.out.println("Married: " + married);

			System.out.println("How many dependencies do you have?");// how many dependencies (kids)
			int dependencies = input.nextInt();
			// int dependencies = 1;
			// System.out.println("dependencies: " + dependencies);

			System.out.println("how much was withheld from you?");// how much withheld
			double withheld = input.nextDouble();
			// double withheld = 3815;
			// System.out.println("withheld: " + withheld);

			// adujustedInc = grossInc - dependencies

			// if (adjustedInc < bracket1)
			// tax = adjustedInc*rate1
			// else if adjustedInc < bracket1 && adjustedInc

			if (married) { // married
				adjustedInc = (grossInc - marriedDeduct) - (dependencies * dependencyRate);
				bracket1 = 19450;
				bracket2 = 78950;
				bracket3 = 168400;
			} else { // single
				adjustedInc = (grossInc - singleDeduct) - (dependencies * dependencyRate);
				bracket1 = 9700;
				bracket2 = 39475;
				bracket3 = 84200;
			}

			if (adjustedInc <= bracket1) { // first = 0
				if (adjustedInc <= 0) { // in negatives or 0, it is just zero in taxes
					tax = 0;
				} else {
					tax = adjustedInc * rate1;
				}
			} else if (adjustedInc > bracket1 && adjustedInc <= bracket2) { // second
				tax += bracket1 * rate1;
				tax += (adjustedInc - bracket1) * rate2;

				// tax = adjustedInc * rate2; // the wrong way
			} else if (adjustedInc > bracket2 && adjustedInc <= bracket3) {// 3rd
				tax += bracket1 * rate1; // amount below bracket 1
				tax += (bracket2 - bracket1) * rate2;// amount between bracket 1 and 2
				tax += (adjustedInc - bracket2) * rate3;// amount between 2 and 3 bracket because it is below bracket 3

				// tax = adjustedInc * rate3; // the wrong way

			} else {// 4th
				tax += bracket1 * rate1; // below first bracket,
				tax += (bracket2 - bracket1) * rate2; // difference between first and second bracket
				tax += (bracket3 - bracket2) * rate3; // difference between second and 3rd bracket,
				tax += (adjustedInc - bracket3) * rate4; // amount over 3rd bracket

				// tax = adjustedInc * rate4; // the wrong way

			}

			tax = (Math.round(tax * 100.0)) / 100.0; // round up to the nearest cent

			if (tax > withheld) { // owe gov't
				System.out.println("You owe the government: " + (tax - withheld));
			} else if (tax < withheld) { // gov't owes you
				System.out.println("The government owes you: " + (withheld - tax));
			} else { // exactly amount owed is paid
				System.out.println("Nothing needs to be done, as what is withheld is exactly what you owe.");
			}

			System.out.println("Do you want to run the calculator again? Yes = true, No = false: ");// rerun
			runProg = input.nextBoolean();
			if (!runProg) {
				break;
			}
		}
	}

}
