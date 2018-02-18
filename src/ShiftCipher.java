
public class ShiftCipher implements CryptoConstants {

	// Runs the Shift Test and returns the shifted string that is most likely the
	// answer
	/*
	 * =============================================================================
	 * runShiftTest
	 * 
	 * Given a string, it calculates how likely each shifted version of the string is
	 * the English plain-text message by taking the difference of letter Frequencies
	 * of the 12 most popular letters against the letter Frequencies of standard 
	 * English messages.
	 * =============================================================================
	 */
	public static String runShiftTest(String s) {
		char[] testChars = { 'E', 'T', 'A', 'O', 'I', 'N', 'S', 'H', 'R', 'D', 'L', 'U' };
		String[] shiftedStrs = ShiftCipherStrings(s);
		double minDifference = Double.MAX_VALUE;
		int minIndex = 0;

		for (int i = 0; i < shiftedStrs.length; i++) {
			// Calculate difference value
			double tmpDiff = 0.0;
			double[] freqA = CryptoFunctions.letterFrequency(shiftedStrs[i]);
			
			for (int j = 0; j < testChars.length; j++) {
				int indexOfChar = testChars[j] - 'A';
				tmpDiff += (Math.abs(freqEnglish[indexOfChar] - freqA[indexOfChar]));
			}

			if (tmpDiff < minDifference) {
				minDifference = tmpDiff;
				minIndex = i;
			}

		} // end for

		return shiftedStrs[minIndex];
	}

	/*
	 * =============================================================================
	 * ShiftCipherStrings
	 * 
	 * Given a string, returns an array of every shifted version of the given
	 * string.
	 * =============================================================================
	 */
	private static String[] ShiftCipherStrings(String s) {
		String[] shiftedStrings = new String[ALPHABET_COUNT];

		for (int key = 0; key < ALPHABET_COUNT; key++) {
			StringBuilder strb = new StringBuilder();

			for (int i = 0; i < s.length(); i++)
				strb.append((char) ((((s.charAt(i) - ASCII_OFFSET) + key) % ALPHABET_COUNT) + ASCII_OFFSET));

			shiftedStrings[key] = strb.toString();
		} // end for loop

		return shiftedStrings;
	}
}
