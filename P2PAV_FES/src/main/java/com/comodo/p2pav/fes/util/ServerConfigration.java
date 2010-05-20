package com.comodo.p2pav.fes.util;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.PropertyConfigurator;

public class ServerConfigration {

	private static ResourceBundle rb = null;

	static {

		try {
			Properties logProperties = new Properties();
			logProperties.load(ServerConfigration.class.getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(logProperties);
		} catch (IOException e) {
			e.printStackTrace();
		}

		rb = ResourceBundle.getBundle("server");
	}

	public static int getServerPort() {
		return Integer.valueOf(rb.getString("tcpPort"));
	}
	
	public static int getServerUdpPort() {
		return Integer.valueOf(rb.getString("udpPort"));
	}

}
