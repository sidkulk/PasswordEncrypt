package crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESAlgorithm {
	private static final String ALGO = "AES";
	private byte[] keyValue;
	private static String secretKey = "thracbL&qaml12Xc";
	public AESAlgorithm() {
		keyValue = secretKey.getBytes();
	}
	
	public String encrypt(String data) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = cipher.doFinal(data.getBytes());
		return new String(Base64.encodeBase64(encVal));
	}
	
	public String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.decodeBase64(encryptedData);
		byte[] decValue = cipher.doFinal(decodedValue);
		return new String(decValue);
	}

	private Key generateKey() {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
}
