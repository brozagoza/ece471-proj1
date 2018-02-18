
public class CryptoFunctions implements CryptoConstants {

	// Calculates the Index of Coincidence
	// Should be between 1.50 and 2.00 for plaintext english of over 50 chars
	// The larger the message, the closer to 1.73.
	//
	// If random letters, it is likely around 1.00 falling between 0.75 and 1.25
	public static double indexOfCoincidence(String s) {
		double iC = 0;
		int[] letC = letterCount(s);

		for (int i = 0; i < ALPHABET_COUNT; i++)
			iC += (double) (letC[i] * (letC[i] - 1));

		iC *= (double) 1 / (s.length() * (s.length() - 1));

		return iC;
	}

	// Given String, returns count of each letter as array
	public static int[] letterCount(String s) {
		int[] letterCount = new int[ALPHABET_COUNT];

		for (int i = 0; i < s.length(); i++)
			letterCount[s.charAt(i) - ASCII_OFFSET]++;

		return letterCount;
	} // end letterCount

	// Returns double array of frequency of letters as percents (ex: 5.34 = 5.34%)
	public static double[] letterFrequency(String s) {
		int[] letterCount = letterCount(s);
		double[] letFreq = new double[ALPHABET_COUNT];

		for (int i = 0; i < ALPHABET_COUNT; i++)
			letFreq[i] = (double) (letterCount[i] * 100) / s.length();

		return letFreq;
	}

	// Decrypt Function given a key
	public static String decrypt(String s, String key) {
		StringBuilder strb = new StringBuilder();
		int j = 0;

		for (int i = 0; i < s.length(); i++) {
			int charVal = (s.charAt(i) - key.charAt(j));

			if (charVal < 0)
				charVal = ALPHABET_COUNT + charVal;
			
			charVal += ASCII_OFFSET;
			strb.append((char)(charVal));
			j = (j+1)%key.length();
		}

		return strb.toString();
	}

}
