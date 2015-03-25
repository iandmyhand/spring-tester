package study.hard.spring.core.commons.util.cipher;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * A test code to encrypt & decrypt
 * @author iandmyhand@gmail.com
 */
public class EncryptPropertiesTest {

	private StandardPBEStringEncryptor encryptor;
	private String encryptedUsername;
	private String encryptedPassword;

	@Before
	public void setUp() {
		encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("$TESTKEY$"); // encrypt & decrypt key
	}

	/**
	 * Encrypt
	 */
	@Test
	@Ignore
	public void testEncrypt() {
		// Insert plain values into below codes and run junit test.
		encryptedUsername = encryptor.encrypt("springtester"); //HkjhbmAJS7t9F/UO4bJK89LmNgwiX76p
		encryptedPassword = encryptor.encrypt("springtestpw"); //n1J7Y5cJIOAliMj+wbm/0uQiujDafqPs
		System.out.println("username : " + encryptor.decrypt(encryptedUsername) + " | " + encryptedUsername);
		System.out.println("password : " + encryptor.decrypt(encryptedPassword) + " | " + encryptedPassword);
	}

	/**
	 * Decrypt
	 */
	@Test
	@Ignore
	public void testDecrypt() {
		// Insert encrypted values into below codes and run junit test.
		String decryptedUsername = encryptor.decrypt("");
		String decryptedPassword = encryptor.decrypt("");
		System.out.println("decrypted username : " + decryptedUsername);
		System.out.println("decrypted password : " + decryptedPassword);
	}

}
