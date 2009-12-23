package com.taste.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taste.crawler.core.CrawlerCore;
import com.taste.crawler.util.HttpHelper;

public class Crawler extends CrawlerCore implements Runnable {

	private static Logger log = LoggerFactory.getLogger(Crawler.class);

	private ArrayList<String> urls = null;

	private Boolean stop = Boolean.FALSE;

	@Override
	public void run() {

		HttpClient client = HttpHelper.getInstance().createHttpClient();

		while (!stop) {

			ArrayList<String> links = new ArrayList<String>();
			for (String url : urls) {

				HttpMethod method = new GetMethod(url);
				method.setRequestHeader("User-Agent", HttpHelper.DEFAULT_USER_AGENT);
				try {
					client.executeMethod(method);
					links.addAll(findLinks(method.getResponseBodyAsStream()));
					createIndex(url);
				} catch (Exception e) {
					log.error("Can't open url :" + url);
				} finally {
					method.releaseConnection();
				}
			}
			urls = links;
		}

	}

	public Crawler(ArrayList<String> targetUrls) {
		urls = targetUrls;
	}

	private ArrayList<String> findLinks(InputStream is) {

		ArrayList<String> links = new ArrayList<String>();
		Parser parser = new Parser();

		try {
			parser.setInputHTML(readFromStream(is, DEFAULT_CHARSET));
			NodeFilter filter = new NodeClassFilter(LinkTag.class);
			NodeList nodes = parser.parse(filter);
			for (Node node : nodes.toNodeArray()) {
				LinkTag linkTag = (LinkTag) node;
				String link = linkTag.getLink().trim();
				if (link.length() > 0) {
					links.add(link);
				}
			}

		} catch (ParserException e) {
			log.error("Parse html error ");
		}

		return links;
	}

	private String readFromStream(InputStream is, String charSet) {

		StringBuffer sb = new StringBuffer();

		String line = null;
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(is, charSet));
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error("Read target website error!" + e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}

		return sb.toString();
	}

	public static void main(String[] a) {

		ArrayList<String> urls = new ArrayList<String>();
		urls.add("http://www.hao123.com");

		Crawler crawler = new Crawler(urls);
		Thread t = new Thread(crawler);
		t.start();

	}

}