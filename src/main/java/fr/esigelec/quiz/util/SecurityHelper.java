package fr.esigelec.quiz.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.log4j.Logger;

import fr.esigelec.quiz.controleur.InscrireAction;

public class SecurityHelper {

	private static final Logger logger = Logger.getLogger(InscrireAction.class);
	
	public static String MD5(String plaintext) {
		MessageDigest digest;
		String ciphertext = "";
        try{
            digest = MessageDigest.getInstance ("MD5");
            byte[] arrayreturn = digest.digest(plaintext.getBytes());
            ciphertext = String.format("%032X",new BigInteger (1, arrayreturn));
        }catch(Exception e){
        	logger.error(e.getMessage());
        }
		return ciphertext;
	}
}
