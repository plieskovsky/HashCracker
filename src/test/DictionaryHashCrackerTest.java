package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import cracker.DictionaryHashCracker;

@RunWith(Parameterized.class)
public class DictionaryHashCrackerTest {
	
	private static final DictionaryHashCracker cracker = DictionaryHashCracker.getInstance();
	
	private String hash;
	private File dictionary;
	private String expected;
	private int id;
	
	public DictionaryHashCrackerTest(int id, String hash, File dictionary, String expected) {
		this.id = id;
		this.hash= hash;
		this.dictionary = dictionary;
		this.expected = expected;
	}
	
	@Test
	public void test() throws FileNotFoundException, IOException {
		Optional<String> result = cracker.crackHash(hash, dictionary);
		assertEquals("Test with id: " + id, expected, result.get());
	}
	
	@Parameterized.Parameters
	   public static Collection<Object[]> primeNumbers() {
	      return Arrays.asList(new Object[][] {
	    	  //SHA 256
	         {1, "8D4E931EA8F6969639C27EDF0631C86A45C5961E64897F7207563271B8BDB92E", new File("passwords.txt"), "vjht008"},
	         {2, "6FC866BB1A3F423A060EB63B887E6499B6B8D915FE919A383FE59609773C3A9C", new File("dictionary-46MB.txt"), "IA6A9"}
	         //OTHERS...
	      });
	   }
}