import java.util.ArrayList;
import java.util.Scanner;

public class April30 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int temp = input.nextInt();
		// Scanner hi = new Scanner(System.in);
		input.nextLine();
		String str = input.nextLine();
		String old = str.substring(0, str.indexOf(' '));
		int oldc = Integer.parseInt(old);

		String newS = str.strip().substring(old.length() + 1);
		int newc = Integer.parseInt(newS);

		ArrayList<String> great = new ArrayList<String>();
		ArrayList<String> greater = new ArrayList<String>();

//		for (int i = 0; i < oldc; i++) {
//			great.add(input.nextLine());
//		}
//
//		for (int i = 0; i < newc; i++) {
//			greater.add(input.nextLine());
//		}
		great.add("John Doe,1234567890,123 Anywhere Street");
		great.add("Jane Doe,9876543210,456 Somewhere Road");
		great.add("Billy Bob Joe,1472583690,789 Nowhere Avenue");
		greater.add("Jane Doe,9876543210,456 Somewhere Road");
		greater.add("Joe Bob Bill,9638520147,159 Over There Lane");
		greater.add("John Doe,1597538462,123 Anywhere Street");

//		greater.add("heather Doe,1597538462,123 Anywhere Street");

		for (int x = 0; x < temp; x++) {
			for (int i = 0; i < oldc; i++) {
				boolean swi = false;
				for (int j = 0; j < newc; j++) {
					if (great.get(i).substring(0, great.get(i).indexOf(","))
							.equals(greater.get(j).substring(0, greater.get(j).indexOf(","))))
						swi = true;
				}
				if (!swi)
					System.out.println(great.get(i).substring(0, great.get(i).indexOf(",")) + " DELETED");
			}

			for (int i = 0; i < newc; i++) {
				boolean swi1 = false;
				for (int j = 0; j < oldc; j++) {
					if (greater.get(i).substring(0, greater.get(i).indexOf(","))
							.equals(great.get(j).substring(0, great.get(j).indexOf(",")))) {
						swi1 = true;
					}
				}
				if (!swi1)
					System.out.println(greater.get(i).substring(0, greater.get(i).indexOf(",")) + " CREATED");
			}

			for (int i = 0; i < oldc; i++) {
				for (int j = 0; j < newc; j++) {
					if (great.get(i).substring(0, great.get(i).indexOf(","))
							.equals(greater.get(j).substring(0, greater.get(j).indexOf(",")))) {// same name
						String tempstr = great.get(i).substring(great.get(i).indexOf(",") + 1); // phone number
						String tempstr2 = greater.get(j).substring(greater.get(j).indexOf(",") + 1);
						int addressi = great.get(i).substring(0, great.get(i).indexOf(",")).length() + 12;
						int addressi2 = greater.get(j).substring(0, greater.get(j).indexOf(",")).length() + 12;
						if (!tempstr.substring(0, tempstr.indexOf(",")).equals(tempstr2.substring(0, tempstr2.indexOf(",")))
								&& !great.get(i).substring(addressi).equals(greater.get(j).substring(addressi2))) {
							System.out.println(
									greater.get(j).substring(0, greater.get(j).indexOf(",")) + " UPDATED BOTH");

						} else if (!tempstr.substring(0, tempstr.indexOf(",")).equals(tempstr2.substring(0, tempstr2.indexOf(","))))
							System.out.println(
									greater.get(j).substring(0, greater.get(j).indexOf(",")) + " UPDATED PHONE NUMBER");

						else if (!great.get(i).substring(addressi).equals(greater.get(j).substring(addressi2)))
							System.out.println(
									greater.get(j).substring(0, greater.get(j).indexOf(",")) + " UPDATED ADDRESS");

					}
				}

			}
		}
	}
}
