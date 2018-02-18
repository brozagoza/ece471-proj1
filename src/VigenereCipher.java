
public class VigenereCipher implements CryptoConstants {

	public void keywordLength(String s) {
		double[] firstTen = new double[10];

		for (int i = 0; i < 10; i++) {
			StringBuilder[] cosets = new StringBuilder[i + 1];

			for (int j = 0; j < s.length(); j++) {
				if (cosets[j % (i + 1)] == null)
					cosets[j % (i + 1)] = new StringBuilder();

				cosets[j % (i + 1)].append(s.charAt(j));
			}

			double cosetICAvg = 0;
			for (int j = 0; j < cosets.length; j++) {
				cosetICAvg += CryptoFunctions.indexOfCoincidence(cosets[j].toString());
			}

			cosetICAvg /= (i + 1);

			firstTen[i] = cosetICAvg;
			System.out.println(cosetICAvg);
		}

	}
}
