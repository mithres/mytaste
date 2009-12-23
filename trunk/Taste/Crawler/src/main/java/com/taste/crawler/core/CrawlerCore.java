package com.taste.crawler.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CrawlerCore {

	private static Logger log = LoggerFactory.getLogger(CrawlerCore.class);

	protected static final String DEFAULT_CHARSET = "UTF-8";

	public void createIndex(String url) {
		log.info("Indexing... url " + url);
	}

}
