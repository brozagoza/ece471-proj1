import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class CrackCipher implements CryptoConstants {

	// MAIN
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Include text file as argument");
			System.exit(0);
		}
		String str = null;

		try {
			File input = new File(args[0]);
			Scanner scanner = new Scanner(input);
			str = scanner.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		run(str);
	} // end main

	// removes whitespace and makes upercase
	public static String toUpperNoWhiteString(String s) {
		StringBuilder strb = new StringBuilder();

		for (char c : s.toCharArray())
			if (!Character.isWhitespace(c) && c >= 'A' && c <= 'Z')
				strb.append(c);
			else if (!Character.isWhitespace(c) && c >= 'a' && c <= 'z')
				strb.append((char) (c - 'a' + 'A'));

		return strb.toString();
	}
	
	public static void run(String s) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Super Cracker"
				+ "\nIndex of Coincidence for Random: "+IC_RANDOM
				+ "\nIndex of Coincidence for English language: "+IC_ENGLISH
				+ "\nIndex of Coincidence for Passed in String: "+CryptoFunctions.indexOfCoincidence(s)
				+ "\nSpecify which cipher youd like to run on the passed in string."
				+ "\n1.) Shift Cipher"
				+ "\n2.) Substitution Cipher"
				+ "\n3.) Vigenere Cipher"
				+ "\n5.) Return"
				+ "\n4.) Exit");
		
		System.out.print("Your choice: ");
		int input = keyboard.nextInt();
		System.out.println();
		
		switch (input) {
		case 1:
			ShiftCipher.run(s);
			break;
		case 2:
			
			break;
		case 3:
			VigenereCipher.run(s);
			break;
		case 5:
			System.exit(0);
			break;
		}
	}

}
