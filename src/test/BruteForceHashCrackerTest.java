package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import cracker.BruteForceHashCracker;
import hasher.HasherFactory.HasherType;

@RunWith(Parameterized.class)
public class BruteForceHashCrackerTest {

	private final List<Character> defaultChars = Arrays.asList(
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	private  final int maxLength = 10;
	
	private final BruteForceHashCracker cracker = BruteForceHashCracker.getInstance();
	
	private int id;
	private String hash;
	private HasherType hasherType;
	private String expectedStart;
	private String expectedEnd;
	private String expectedResult;
	
	public BruteForceHashCrackerTest(int id, String hash, HasherType hasherType, String expectedStart, String expectedEnd, String expectedResult) {
		this.id = id;
		this.hash = hash;
		this.hasherType= hasherType;
		this.expectedStart = expectedStart;
		this.expectedEnd = expectedEnd;
		this.expectedResult = expectedResult;
	}
	
	@Test
	public void test() throws InterruptedException, ExecutionException {
		Optional<String> result = cracker.crackHash(hash, hasherType, defaultChars, maxLength, expectedStart, expectedEnd);
		assertEquals("Test with id: " + id, expectedResult, result.get());
	}
	
	@Parameterized.Parameters
	   public static Collection<Object[]> primeNumbers() {
	      return Arrays.asList(new Object[][] {
	    	  //SHA256
	         {1, "9EAE51B2FCB3F8EAAA7B60C65EBB57302DD59D4C61ABAEF732DC2E4959B3DDB9", HasherType.SHA256, null, null, "Z3f"},
	         {2, "6EC9B5207B1BE5B709D04F7D7E474FD3356B35FE38EEAE59EF414EAE3BE0286D", HasherType.SHA256, "P", "a", "Petka"},
	         {3, "5C62E091B8C0565F1BAFAD0DAD5934276143AE2CCEF7A5381E8ADA5B1A8D26D2", HasherType.SHA256, "P", "a", "P"},
	         {4, "CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB", HasherType.SHA256, "P", "a", "a"},
	         {5, "43B9231919848B668531AA92CF9DAFAD6DBBE0B12AE97DDE9B13068C289D9033", HasherType.SHA256, "P", "a", "Pa"},
	         {6, "5C62E091B8C0565F1BAFAD0DAD5934276143AE2CCEF7A5381E8ADA5B1A8D26D2", HasherType.SHA256, "P", null, "P"},
	         {7, "DE993602F6E152E9C5E8A1508C9805D5038528970AA42286118F1145DE9E419E", HasherType.SHA256, "P", null, "Par"},
	         {8, "CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB", HasherType.SHA256, null, "a", "a"},
	         {9, "3094DB2C5130915C7F9030E520D546349564AF31C7D948B3EC62D23517E0AFC5", HasherType.SHA256, null, "a", "noha"}
	         //OTHERS ..
	      });
	   }
}