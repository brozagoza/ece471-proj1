import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/*
 * This file prints an input string out to a file named output.txt
 */

public class CipherCracker {
	PrintWriter writer = null;
	
	public CipherCracker() {
		
	}
	
	public void run(String s) {
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.println(s);
		
		//keywordLength(s);
		System.out.println(CryptoFunctions.decrypt(s, "SYSTEM"));
		
	}
	
}
