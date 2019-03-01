import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import cracker.BruteForceHashCracker;
import cracker.DictionaryHashCracker;
import hasher.HasherFactory.HasherType;

public class Main {

	// Check test package for better understanding
	public static void main(String[] args) throws InterruptedException, ExecutionException, FileNotFoundException, IOException {
		long start = System.currentTimeMillis();
		
		//hash to crack
		String hash = "6B1933174DDB85FE2A3B6E72952377ED5226CE7BC10EBC09E47913E2FE2621A2C1CBE3855197D3C964DF65E441687864569B7B1C1530AA44FDBDCEDCD25C265E";
		
		//chars that will be used to cracking
		List<Character> defaultChars = Arrays.asList(
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
		
		//type of hash to crack
		HasherType hasherType = HasherType.SHA512;
		
		//max length of hashed value. For each length one thread will be executed, so for length 20 20 threads will run simultaneously
		int maxLength = 20;
		
		//hashed value expected start
		String expectedStart = "zZ";
		//hashed value expected end
		String expectedEnd = "z";
		
		//with hasherType
		Optional<String> crackedString = BruteForceHashCracker.getInstance().crackHash(hash, hasherType, defaultChars, maxLength, expectedStart, expectedEnd);
		
		//without hasherType
		//Optional<String> crackedString = HashCracker.getInstance().crackHash(hash, defaultChars, maxLength, expectedStart, expectedEnd);

		//dictionary
		//hash = "8D4E931EA8F6969639C27EDF0631C86A45C5961E64897F7207563271B8BDB92E";
		//Optional<String> crackedString = DictionaryHashCracker.getInstance().crackHash(hash, new File("passwords.txt"));
		
		
		if (crackedString.isPresent()) {
			System.out.println("Hash cracked. Hashed value is: " + crackedString.get());
		} else {
			System.out.println("Hash cracking unsuccessfull");
		}
		
		System.out.println("Running time: " + (System.currentTimeMillis() - start) / (1000 * 60) + " minutes");
	}
}