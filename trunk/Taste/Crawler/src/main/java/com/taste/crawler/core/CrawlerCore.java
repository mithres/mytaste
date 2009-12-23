package com.taste.crawler.core;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CrawlerCore {

	private static Logger log = LoggerFactory.getLogger(CrawlerCore.class);

	public abstract void crawle(ArrayList<String> urls);

	public void createIndex(String url) {
		log.info("Indexing... url " + url);
	}

}
