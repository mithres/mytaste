package com.vc.util.security;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesCrypt {
	/*
	 * The key used for the encryption
	 */
	private byte[] key = hexToByte("0f 0f 0f 0f 0f 0f 0f 0f 0f 0f 0f 0f 0f 0f 0f 0f");

	public String encrypt(String str) {
		try {
			/*
			 * If the string is too short, add 0x00's
			 */
			int buffl = str.length();
			while (buffl % 16 != 0) {
				buffl++;
			}
			byte[] buff = new byte[buffl];
			for (int i = 0; i < str.length(); i++) {
				buff[i] = str.getBytes()[i];
			}
			for (int i = str.length() + 1; i < buff.length; i++) {
				buff[i] = (byte) 0x00;
			}

			/*
			 * encrypt
			 */
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
			byte[] encrypted = cipher.doFinal(buff);
			return asHex(encrypted);
		} catch (Exception err) {
			return err.toString();
		}
	}

	public String decrypt(String str) {
		try {
			byte[] buff = hexToByte(str);

			/*
			 * decode
			 */
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
			byte[] decrypted = cipher.doFinal(buff);

			// Trim the 0-bytes at the end
			int toDel;
			for (toDel = decrypted.length - 1; toDel > 0; toDel--) {
				if (decrypted[toDel] != (byte) 0x00) {
					toDel++;
					break;
				}
			}
			byte[] decryptedWOnull = new byte[toDel];
			for (int i = 0; i < toDel; i++) {
				decryptedWOnull[i] = decrypted[i];
			}
			return new String(decryptedWOnull);
		} catch (Exception err) {
			return err.toString();
		}
	}

	public String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	public byte[] hexToByte(String hex) {
		hex = hex.replace(" ", "");
		byte[] bts = new byte[hex.length() / 2];
		for (int i = 0; i < bts.length; i++) {
			bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bts;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

}
