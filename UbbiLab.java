//Donald Newandee
//UbbiLab
//10.19.2021

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UbbiLab {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

//		Develop a translator which would read in a file with English words and create a file with
//		the words translated into the language of Ubbi Dubbi. The language has a simple rule of adding
//		the phrase “ub before each syllable in a word. That would be very difficult to code so we will
//		simplify this to be an ub before every vowel unless the vowel has a vowel before it (see
//		example of “Reader” below). The algorithm itself needs to happen in a method, which will take
//		in a reader and a writer object, and update the output file with the translation.
//		Examples of translations (more can be gotten from Wikipedia)
//		Puppy >> pubuppy
//		Computer >> cubompubutuber
//		Reader >> Rubeaduber
//		GHS >> GHS
//		I >> UBI
//
//		Make sure to handle special cases of words and ensure the program doesn’t crash! Test your
//		lab with empty files and files with a single word in them.
//		Use “helper” methods to make the lab easier.

		// our standard way to get input from the user through the console
		Scanner console = new Scanner(System.in);
		// our way to read a file; must be in the file location, OUTSIDE the src folder
		// use the file name here

		System.out.println("Enter a file name to translate into Ubbi Dubbi: ");
		String str = console.next();
		// String str = "data.txt";
		Scanner file = new Scanner(new File(str));

		System.out.println("Enter the output file name you want: ");
		String str2 = console.next();
		// String str2 = "out.txt";
		// result of where the program will end up; new file made at the name specified,
		// with
		// the data type specified
		PrintWriter output = new PrintWriter(new File(str2));
		System.out.println();
		// de-links the scanner so i could use the variable name again if i wanted, with
		// different types if i wanted
		console.close();

		translateFile(file, output);
		// de-links the output file so when the program ends, it isn't open and causing
		// issues for other projects
		output.close();

		// String startStr = "computer"; // input string
		// System.out.println(ubbidubbi(startStr));

//		Scanner console = new Scanner(System.in);
//		System.out.print(isAlpha(console));
		// output.close();
	}

	public static boolean translateFile(Scanner input, PrintWriter output) {
		while (input.hasNext()) {
			String word = input.next();
			word = ubbidubbi(word);
			System.out.print(word + ' ');
			output.print(word + ' ');
		}
		System.out.println();

		return true;
	}

	public static boolean isAlpha(Scanner input) { // checks if input is alphabetical
		String word = "";
		String prevWord = "A";// ' first' possible letter in the alphabet, uppercase first, then lowercase
		boolean alpha = true;
		do {
			word = input.next();
			if (!word.equals("done") && word.compareTo(prevWord) < 0) {
				alpha = false;
				prevWord = word;
			}
		} while (!word.equals("done"));
		return alpha;
	}

	public static String ubbidubbi(String startStr) {
		String newStr = "";
		// scans through the entire length of the
		for (int i = 0; i < startStr.length(); i++) {

			if (isVowel(startStr.charAt(i))) { // found a vowel
				if (i > 0 && isVowel(startStr.charAt(i - 1))) { // double vowel
					newStr += startStr.charAt(i); // only add the vowel. nothing else
				} else// vowel, but no vowel before it
				if (Character.isUpperCase(startStr.charAt(i)))
					newStr += "Ub" + Character.toLowerCase(startStr.charAt(i));
				else
					newStr += "ub" + startStr.charAt(i);
			} else { // Consonant letter
				newStr += startStr.charAt(i);
				// System.out.println(newStr); // testing purposes
			}
		}
		return newStr;
	}

	public static boolean isVowel(char chr) {
		if (chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u' || chr == 'A' || chr == 'E' || chr == 'I'
				|| chr == 'O' || chr == 'U')
			return true;
		else
			return false;
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

	public static String reverse(String word) {
		String temp1 = "";
		for (int i = 0; i < word.length(); i++) {
			temp1 += word.charAt(word.length() - i - 1);
		}
		return temp1;
	}

}
