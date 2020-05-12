package com.dongkap.common.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignatureEncrypt {
	
	private static SignatureEncrypt INSTANCE;
     
    private SignatureEncrypt() {}
     
    public static SignatureEncrypt getInstance() {
        if(INSTANCE == null) INSTANCE = new SignatureEncrypt();
        return INSTANCE;
    }
    
    public String hash(String secret, String message) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	    SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	    sha256_HMAC.init(secret_key);
	    String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes()));
	    return hash;
    }

}
