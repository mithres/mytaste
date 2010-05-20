package com.comodo.p2pav.fes.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.comodo.p2pav.fes.server.FESServer;

public class ServletContextLoaderListener implements ServletContextListener  {

	private static final Log logger = LogFactory.getLog(ServletContextLoaderListener.class);
	
	private FESServer server = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		try {
			server = new FESServer();
			new Thread(server).start();
		} catch (IOException e) {
			logger.error("Start fes server error.",e);
		}
	}

}
