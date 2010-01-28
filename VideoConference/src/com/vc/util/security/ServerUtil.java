package com.vc.util.security;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.security.Authentication;

import com.vc.core.constants.Constants;

public class ServerUtil {

	public static String getCurrentDate() {

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Calendar rightNow = Calendar.getInstance();
		return formatter.format(rightNow.getTime());

	}

	public static String createEncryptedAuthInfo(Authentication authentication) {
		
		AesCrypt aes = new AesCrypt();
		try {
			aes.setKey(aes.hexToByte(MD5.do_checksum(Constants.USER_CREDENTIAL_AES_KEY)));
		} catch (NoSuchAlgorithmException e) {
			
		}
		String authInfo = authentication.getName() + ":" + authentication.getCredentials().toString();
		authInfo = aes.encrypt(authInfo);
		return authInfo;
	}
	
	public static String[] getUserAuthInfo(String authInfo){
		
		AesCrypt aes = new AesCrypt();
		try {
			aes.setKey(aes.hexToByte(MD5.do_checksum(Constants.USER_CREDENTIAL_AES_KEY)));
		} catch (NoSuchAlgorithmException e) {
			
		}

		String decryptedStr = aes.decrypt(authInfo);
		String[] result = decryptedStr.split(":");
		
		return result;
	}

}
