import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class VigenereCipher implements CryptoConstants {
	static PrintWriter writer = null;

	public static void run(String s) {
		System.out.println("===== Began Running Vigenere Cipher =====");
		Scanner keyboard = new Scanner(System.in);
		int keywordLength = keywordLength(s);
		StringBuilder[] cosets = getCosets(s, keywordLength);
		StringBuilder key = new StringBuilder();
		String yn = "";

		while (!yn.equals("Y")) {
			System.out.println("Suggested keyword length is: " + keywordLength);
			System.out.println("\nInstructions:");
			System.out.println("---------------");
			System.out.println("Try to line up the numbers from the first "
					+ "and second column as close together as possible. \nEnter a positive number to shift the second "
					+ "column left. \nWhen you're done, type -1 "
					+ "to go onto the next coset. Once there are no more cosets to go through, you will have a\n"
					+ "keyword. \nHint: Try your hardest to match up the number in the second column"
					+ " with the 13.11 in the first column.\n");

			for (int i = 0; i < keywordLength; i++) {
				int input = 0;
				int shiftVal = 0;
				double[] tmp = CryptoFunctions.letterFrequency(cosets[i].toString());

				System.out.println("================== RUN " + (i + 1) + "/" + keywordLength + " ==================");
				do {
					shiftVal += input;

					System.out.print("\nCol1: ");
					for (int j = 0; j < ALPHABET_COUNT; j++)
						System.out.printf("%02.2f  ", freqEnglish[j]);

					System.out.print("\nCol2: ");
					for (int j = 0; j < ALPHABET_COUNT; j++)
						System.out.printf("%02.2f  ", tmp[(j + shiftVal) % ALPHABET_COUNT]);

					System.out.print("\nInput: ");
					input = keyboard.nextInt();
					System.out.println();
				} while (input != -1);

				System.out.println("Guessed letter: " + (char) ((shiftVal % ALPHABET_COUNT) + ASCII_OFFSET));
				key.append((char) ((shiftVal % ALPHABET_COUNT) + ASCII_OFFSET));
				System.out.println("");
			}
			
			System.out.println("Your guessed keyword is: " + key.toString());
			System.out.println("Does this sound corect? (Y/N): ");
			yn = keyboard.next();
		}
		
		try {
			writer = new PrintWriter("output-vigcipher.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.println(CryptoFunctions.decrypt(s, "TUCSON"));
		writer.close();
		
		System.out.println("Output of VigenereCipher has been printed to output-vigcihper.txt");
		
		keyboard.close();
	}

	public static int keywordLength(String s) {
		double[] firstTen = new double[10];

		for (int i = 0; i < 10; i++) {
			StringBuilder[] cosets = getCosets(s, i + 1);

			double cosetICAvg = 0;
			for (int j = 0; j < cosets.length; j++) {
				cosetICAvg += CryptoFunctions.indexOfCoincidence(cosets[j].toString());
			}

			cosetICAvg /= (i + 1);

			firstTen[i] = cosetICAvg;
		}

		double maxIndex = 0;
		int index = 0;
		for (int i = 0; i < 10; i++)
			if (firstTen[i] > maxIndex) {
				maxIndex = firstTen[i];
				index = i;
			}

		return index + 1;
	}

	public static StringBuilder[] getCosets(String s, int size) {
		StringBuilder[] cosets = new StringBuilder[size];

		for (int j = 0; j < s.length(); j++) {
			if (cosets[j % (size)] == null)
				cosets[j % (size)] = new StringBuilder();

			cosets[j % (size)].append(s.charAt(j));
		}

		return cosets;
	}

}
