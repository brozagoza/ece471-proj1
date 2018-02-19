import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SubstitutionCipher implements CryptoConstants{
	static PrintWriter writer;
	static double[] engFreqArr = new double[ALPHABET_COUNT];
	static double[] subFreqArr;
	static int[] map = new int[ALPHABET_COUNT];
	static HashMap<Character,Character> hm = new HashMap<Character,Character>();
	
	public static void run(String s) {
		try {
			writer = new PrintWriter("output-subcipher.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.println(runSubstitutionCipherTest(s));
		writer.close();
	}
	
	
	
	public static String runSubstitutionCipherTest(String s){
		engFreqArr = Arrays.copyOf(freqEnglish, freqEnglish.length);
		subFreqArr = CryptoFunctions.letterFrequency(s);
		
		
		for(int i = 0; i < ALPHABET_COUNT; i++)
		{
			int tempMaxEng = findMax(engFreqArr);
			int tempMaxSub = findMax(subFreqArr);
			char tempChEng = (char) (tempMaxEng + ASCII_OFFSET);
			char tempChSub = (char) (tempMaxSub + ASCII_OFFSET);			
			
			hm.put(tempChSub, tempChEng); 
			map[tempMaxSub] = tempMaxEng;
			
			engFreqArr[tempMaxEng] = 0;
			subFreqArr[tempMaxSub] = 0;
		}
		engFreqArr = Arrays.copyOf(freqEnglish, freqEnglish.length);
		subFreqArr = CryptoFunctions.letterFrequency(s);
		
		userInput(s);
		
		
		
		return printCurrentMapSample(s);
	}
	
	public static void userInput(String s)
	{
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		System.out.println("The Substitution Cipher maps a letter to any other letter in the English \n"
				+ "language. Here is the frequency of each letter for the original English alphabet \n"
				+ "and the cipher alphabet.");
		
		System.out.println();
		printIntro();
		
		System.out.println("\nThe map that is created from this is: ");
		
		printMap();
		
		System.out.println("\nAnd using this mapping to decrypt the cipher provides us this excerpt from the text:");
		
		printSample(s);
		
		System.out.println("Please enter pairs of letters separated by a space (ex:A Z) to switch their mapping or enter 'quit' to exit and print to 'output.txt': ");
		
		while(scanner.hasNextLine())
		{
			char ch1 = ' ';
			char ch2 = ' ';
			
			input = scanner.nextLine();
			if(input.equals("quit") || input.equals("Quit"))
				break;
			else if(input.length() != 3)
			{
				System.out.println("Please enter an acceptable change:");
			}
			else
			{
				ch1 = input.charAt(0);
				ch2 = input.charAt(2);
				if(ch1 < 'A' || ch1 > 'Z' || ch2 < 'A' || ch2 > 'Z' )
					System.out.println("Please enter an acceptable change:");
				else 
				{
					updateMap(ch1, ch2);
					System.out.println("\nThe new map is:");
					printMap();
					System.out.println("\nAnd here is the new sample: ");
					printSample(s);
					
					System.out.println("Please enter another pair or enter 'quit' to exit  and print to 'output-subcipher.txt':");
				}
			}
			
		}
		
		scanner.close();
		
	}
	
	public static void printMap()
	{
		for(int i = 0; i < ALPHABET_COUNT; i++)
		{
			System.out.print((char) (i + ASCII_OFFSET) + " ");
		}
		
		System.out.println();
		
		for(int i = 0; i < ALPHABET_COUNT; i++)
		{
			System.out.print(hm.get((char) (i + ASCII_OFFSET)) + " ");
		}
		
		System.out.println();
	}
	
	public static void printSample(String s)
	{
		System.out.println();
		for(int i = 0; i < 100; i++)
		{
			System.out.print(hm.get(s.charAt(i)));
		}
		System.out.println("\n");
	}
	
	public static void updateMap(char ch1, char ch2)
	{
		char ch4 = hm.get(ch1);
		char ch3 = hm.get(ch2);

		for(int i = 0; i < ALPHABET_COUNT; i++)
		{
			char temp = (char) (i + ASCII_OFFSET);
			if(hm.get(temp) == ch2)
			{
				ch3 = temp;
				break;
			}
		}
		
		hm.put(ch1, ch2);
		hm.put(ch3, ch4);
	}
	
	public static String printCurrentMapSample(String s)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < s.length(); i++)
		{
			char temp =  s.charAt(i);
			
			sb.append(hm.get(temp));
			
			
		}
		
		return sb.toString();
	}
	
	public static int findMax(double[] temp)
	{
		int max = 0;
		
		for(int i = 0; i < temp.length; i++)
		{
			if(temp[i] > temp[max])
				max = i;
		}
		
		return max;
	}
	
	public static void printIntro()
	{
		for(int i = 0; i < ALPHABET_COUNT; i++)
		{
			System.out.print("" + (char) (i + ASCII_OFFSET) + "     ");
		}
		
		System.out.println();
		
		for(int i = 0; i < ALPHABET_COUNT; i++)
		{
			if(engFreqArr[i] < 10)
				System.out.print("0");
				
			System.out.printf("%.2f ", engFreqArr[i]);
		}
		
		System.out.println();
		System.out.println();
		
		for(int i = 0; i < ALPHABET_COUNT; i++)
		{
			System.out.print((char) (i + ASCII_OFFSET) + "     ");
		}
		
		System.out.println();
		
		for(int i = 0; i < ALPHABET_COUNT; i++)
		{
			if(engFreqArr[i] < 10)
				System.out.print("0");
			
			System.out.printf("%1.2f ", subFreqArr[i]);
		}
		
		System.out.println();
		
	}
}