// gives file library to read and take in files
import java.io.File;
//there is not a built in ability if the file can not be found; this will check for that
import java.io.FileNotFoundException;
//outputs reults into a new file; normally we do this in the console but this time in this 
//case it outputs in a newly created file
import java.io.PrintWriter;
//library for Scanner type varaibles in JAVA
import java.util.Scanner;

public class Methods_Strings_Files {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// our standard way ot get input from the user through the console
		Scanner input = new Scanner(System.in); 
		//our way to read a file; must be in the file location, OUTSIDE the src folder
		//use the file name here
		Scanner file = new Scanner(new File("data.txt"));

		// result of where the program will end up; new file made at the name specified, with
		// the data type specified
		PrintWriter output = new PrintWriter(new File ("output.txt"));
		System.out.println();
		input.close();
		input = new Scanner(new File("data.txt"));
		
		
		while(input.hasNext()) {
			String word = input.next();
			word = blabla(word);
			System.out.print(word +' ');
			output.print(word+' ');
		}
		System.out.println();
		
		output.close();
		
		
		
		
		
		
		
		
		// System.out.println("Enter a string that will be converted into Ubbi Dubbi:
		// ");
		// String str = in.nextLine();

	}

	public static String blabla(String word) {
		String temp = "";
		for (int i = 0; i < word.length(); i++) {
			if (i % 3 == 0) { // spot 1
				temp += "b";
			} else if (i % 3 == 1) { // spot 2
				temp += "l";
			} else {// spot3
				temp += "a";
			}
		}
		return temp;
	}

	public static String reverse(String word) {
		String temp1 = "";
		for (int i = 0; i < word.length(); i++) {
			temp1 += word.charAt(word.length() - i - 1);
		}
		return temp1;
	}

	public static boolean Ib4e(String word) {// except after c
		word = word.toLowerCase();

		int iIndex = word.indexOf('i');
		if (iIndex == -1)
			return true;
		if (iIndex < word.length() - 1 && // way so you don't go out of bounds; prevents next condition from happening
											// when out of bounds
				word.charAt(iIndex + 1) == 'e') {
			if (iIndex > 0 && // short cut out in out of bounds again
					word.charAt(iIndex - 1) == 'c')
				return true;
			else
				return false;
		}
		if (iIndex > 1 && word.charAt(iIndex - 1) == 'e') {
			if (word.charAt(iIndex - 2) == 'c')
				return true;
		}

		return false;
	}
}
