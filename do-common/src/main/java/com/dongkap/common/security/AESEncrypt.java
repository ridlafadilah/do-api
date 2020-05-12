package com.dongkap.common.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESEncrypt {

	private static final String HASH_CIPHER = "AES/CBC/PKCS7Padding";
	private static final String CHARSET_TYPE = "UTF-8";
	private static final String KEY_ALGORITHM = "AES";
	private static int IV_SIZE = 128;
	private static Cipher cipher;


	private static AESEncrypt INSTANCE;

	private AESEncrypt() {
	}

	public static AESEncrypt getInstance() {
		if (INSTANCE == null) INSTANCE = new AESEncrypt();
		return INSTANCE;
	}

	public static String encrypt(String secretKey, String message) throws Exception {
		byte[] iv = new byte[IV_SIZE/8];
		new SecureRandom().nextBytes(iv);
				
		byte[] encrypted = new byte[128];
		
		try {
	        Security.addProvider(new BouncyCastleProvider());
	        cipher = Cipher.getInstance(HASH_CIPHER, "BC");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey.getBytes(CHARSET_TYPE), KEY_ALGORITHM), new IvParameterSpec(iv));
			encrypted = cipher.doFinal(message.getBytes(CHARSET_TYPE));
			return toHex(iv) + Base64.getEncoder().encodeToString(encrypted);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			throw new NoSuchAlgorithmException(e);
		} catch (Exception e) {
			throw new NoSuchAlgorithmException(e);
		}
	}

	public static String decrypt(String secretKey, String encryptMessage) throws Exception {
		try {
			byte[] iv = toByte(encryptMessage.substring(0, 32));
			byte[] encrypted = Base64.getDecoder().decode(encryptMessage.substring(32));
			
	        Security.addProvider(new BouncyCastleProvider());
	        cipher = Cipher.getInstance(HASH_CIPHER, "BC");
	        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey.getBytes(CHARSET_TYPE), KEY_ALGORITHM), new IvParameterSpec(iv));  
	        byte[] decoded = cipher.doFinal(encrypted);  
	        return new String(decoded, CHARSET_TYPE);			
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			throw new NoSuchAlgorithmException(e);
		} catch (StringIndexOutOfBoundsException e) {
			throw new NoSuchAlgorithmException(e);
		} catch (Exception e) {
			throw new NoSuchAlgorithmException(e);
		}
	}
	
	public static byte[] toByte(String hex) {
		int len = hex.length()/2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hex.substring(2*i, 2*i+2), 16).byteValue();
		return result;
	}
	
	private static String toHex(byte[] bytes) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }

}
