package hasher;

import org.apache.commons.codec.digest.DigestUtils;

public class MD2Hasher implements Hasher {

	private static final MD2Hasher INSTANCE = new MD2Hasher();
	
	private MD2Hasher() {};
	
	public static MD2Hasher getInstance() {
		return INSTANCE;
	}
	
	@Override
	public String getHash(String input) {
		return DigestUtils.md2Hex(input);
	}
}