package com.taste.crawler;

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

public class Crawler extends CrawlerCore {

	private static Logger log = LoggerFactory.getLogger(Crawler.class);

	public void crawle(ArrayList<String> urls) {

		HttpClient client = HttpHelper.getInstance().createHttpClient();

		boolean stop = false;

		while (!stop) {

			ArrayList<String> links = new ArrayList<String>();
			for (String url : urls) {

				HttpMethod method = new GetMethod(url);
				method.setRequestHeader("User-Agent", HttpHelper.DEFAULT_USER_AGENT);
				try {
					client.executeMethod(method);
					String responseHtml = method.getResponseBodyAsString();
					links.addAll(findLinks(responseHtml));
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

	private ArrayList<String> findLinks(String html) {

		ArrayList<String> links = new ArrayList<String>();
		Parser parser = new Parser();

		try {
			parser.setInputHTML(html);
			NodeFilter filter = new NodeClassFilter(LinkTag.class);
			NodeList nodes = parser.parse(filter);
			for (Node node : nodes.toNodeArray()) {
				LinkTag linkTag = (LinkTag) node;
				String link = linkTag.getLink().trim();
				links.add(link);
			}

		} catch (ParserException e) {
			log.error("Parse html error ");
		}

		return links;
	}

	public static void main(String[] a) {
		Crawler crawler = new Crawler();
		ArrayList<String> urls = new ArrayList<String>();
		urls.add("http://www.126.com");
		crawler.crawle(urls);
	}

}