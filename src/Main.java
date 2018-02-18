import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
	final static int EXIT_PROGRAM = 7;
	final static String pstr = "THERE ARETW OWAYS OFCON STRUC TINGA SOFTW AREDE SIGNO NEWAY"
			+ "ISTOM AKEIT SOSIM PLETH ATTHE REARE OBVIO USLYN ODEFI CIENC"
			+ "IESAN DTHEO THERW AYIST OMAKE ITSOC OMPLI CATED THATT HEREA"
			+ "RENOO BVIOU SDEFI CIENC IESTH EFIRS TMETH ODISF ARMOR EDIFF" + "ICULT";
	final static String pstr2 = "MDULAZMUEDQFGDZUZSVGZU"
			+ "ADWTMXUXFMFQITAIMEAZQARFTQYAEFQXQOFDURKUZSBXMKQDEUZOAXXQSQRAAFNMXXXMEFE"
			+ "QMEAZMXAZSIUFTDQPETUDFEQZUADNDMZPAZPMIWUZEITATMEEFMDFUZSQJBQDUQZOQGZ" + "XUWQMXAFARNMOWGBCGMDFQDNMOWE";
	final static String pstr3 = "VVQGY TVVVK ALURW FHQAC MMVLE HUCAT WFHHI PLXHV UWSCI GINCM"
			+ "UHNHQ RMSUI MHWZO DXTNA EKVVQ GYTVV QPHXI NWCAB ASYYM TKSZR"
			+ "CXWRP RFWYH XYGFI PSBWK QAMZY BXJQQ ABJEM TCHQS NAEKV VQGYT"
			+ "VVPCA QPBSL URQUC VMVPQ UTMML VHWDH NFIKJ CPXMY EIOCD TXBJW" + "KQGAN";
	final static String pstr4 = "LFWKI MJCLP SISWK HJOGL KMVGU RAGKM KMXMA MJCVX WUYLG GIISW"
			+ "ALXAE YCXMF KMKBQ BDCLA EFLFW KIMJC GUZUG SKECZ GBWYM OACFV"
			+ "MQKYF WXTWM LAIDO YQBWF GKSDI ULQGV SYHJA VEFWB LAEFL FWKIM"
			+ "JCFHS NNGGN WPWDA VMQFA AXWFZ CXBVE LKWML AVGKY EDEMJ XHUXD" + "AVYXL";
	final static String ppstr = "RSTCS JLSLR SLFEL GWLFI ISIKR MGL";

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

		CipherCracker cracker = new CipherCracker();
		cracker.run(toUpperNoWhiteString(pstr4));

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

}
