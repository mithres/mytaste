package com.vc.util.security;

public class ItemChecker {

	public static final boolean checkPrice(float price) {
		if (price < 0) {
			return false;
		}
		return true;
	}

	/**
	 * Check email username@domain
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean checkEmail(String value, int length) {
		return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && value.length() <= length;
	}

	/**
	 * Check Chinese chars
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean checkChineseName(String value, int length) {
		return value.matches("^[\u4e00-\u9fa5]+$") && value.length() <= length;
	}

	/**
	 * Check string contains HTML tag
	 * 
	 * @param value
	 * @return
	 */

	public static final boolean checkHtmlTag(String value) {
		return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
	}

	/**
	 * Check url format
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean checkURL(String value) {
		return value.matches("[a-zA-z]+://[^\\s]*");
	}

	/**
	 * Check userName (id) User Name 4~20
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean checkUserName(String value) {
		return value.matches("[a-zA-Z][a-zA-Z0-9_]{4,20}$");
	}

	public static final boolean checkPassword(String value) {
		return value.matches("[a-zA-Z0-9][a-zA-Z0-9_]{4,20}$");
	}
	
	public static final boolean checkNumber(String value) {
		return value.matches("[0-9]");
	}

	/**
	 * Check input length
	 * 
	 * @param length
	 * @param value
	 * @return
	 */
	public static final boolean checkLength(String value, int length) {
		return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;
	}

	/**
	 * Check whether value is empty
	 * 
	 * @param value
	 * @return
	 */
	public static final boolean checkNull(Object value) {
		if (value == null) {
			return value == null;
		} else if (value instanceof String) {
			return "".equals(((String) value).trim());
		}
		return true;
	}

}
