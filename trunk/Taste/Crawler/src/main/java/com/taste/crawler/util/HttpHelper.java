package com.taste.crawler.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * @author ammen
 * 
 */
public class HttpHelper {

	public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 2.0.50727)";

	private MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

	private static HttpHelper helper = null;

	private HttpHelper() {

	}

	public static HttpHelper getInstance() {
		if (helper == null) {
			helper = new HttpHelper();
		}
		return helper;
	}

	public final HttpClient createHttpClient() {
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setBooleanParameter(HttpClientParams.USE_EXPECT_CONTINUE, false);
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		return httpClient;
	}

}
