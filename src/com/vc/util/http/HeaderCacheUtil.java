/**
 * 
 */
package com.vc.util.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author ammen
 * 
 */
public class HeaderCacheUtil {

	private static final int MAX_AGE = 10 * 24 * 3600;

	public static void setMaxCacheAge(HttpServletResponse response) {
		setMaxCacheAge(response, MAX_AGE);

	}

	public static void setMaxCacheAge(HttpServletResponse response, int age) {
		response.setHeader("Cache-Control", "max-age=" + age);

	}

	public static DateFormat htmlExpiresDateFormat() {
		DateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return httpDateFormat;
	}

	public static void setNoCacheHeaders(HttpServletResponse resp) {
		resp.addHeader("Pragma", "No-cache");
		resp.addHeader("Cache-Control", "No-cache");
		resp.addDateHeader("Expires", new Long(0));
	}

}
