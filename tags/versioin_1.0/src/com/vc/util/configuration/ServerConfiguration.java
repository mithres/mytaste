package com.vc.util.configuration;

import java.io.File;
import java.util.ResourceBundle;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.core.constants.Constants;
import com.vc.util.photo.PhotoType;

public class ServerConfiguration {

	private static final Logger log = Red5LoggerFactory.getLogger(ServerConfiguration.class, "VideoConference");

	static {

		try {
			ResourceBundle rb = ResourceBundle.getBundle("server-config");

			// load photo configuration
			Constants.PHOTO_PATH = rb.getString("photo_path");
			Constants.PHOTO_PATH_AUTH = rb.getString("photo_path_auth");
			Constants.PHOTO_URL = rb.getString("photo_url");
			Constants.ALLOWED_HTML_DOMAINS = rb.getString("allowedHTMLDomains");
			Constants.ALLOWED_SWF_DOMAINS = rb.getString("allowedSWFDomains");
			Constants.FS_URI = rb.getString("fs_uri");

			Constants.RTMP_IP = rb.getString("rtmp_ip");
			Constants.RTMP_PORT = Integer.valueOf(rb.getString("rtmp_port")).intValue();
			Constants.IS_RTMP_SERVER = Boolean.valueOf(rb.getString("localhost_is_rtmpserver"));

		} catch (Exception e) {
			log.error("Load server configuration error.", e);
		}

	}

	public static final boolean isCurrentHostRTMPServer() {
		return Constants.IS_RTMP_SERVER;
	}

	public static final String getRTMPIp() {
		return Constants.RTMP_IP;
	}

	public static final int getRTMPPort() {
		return Constants.RTMP_PORT;
	}

	public static final String getFsUri() {
		return Constants.FS_URI;
	}

	public static String getAllowedHTMLDomains() {
		return Constants.ALLOWED_HTML_DOMAINS;
	}

	public static String getAllowedSWFDomains() {
		return Constants.ALLOWED_SWF_DOMAINS;
	}

	public static String getPhotoUrl(PhotoType photoType) {

		if (photoType.equals(PhotoType.UserPhoto)) {
			return Constants.PHOTO_URL + "User/";
		} else if (photoType.equals(PhotoType.FilmScreenShot)) {
			return Constants.PHOTO_URL + "Film/";
		}
		return null;
	}

	public static final String getPhotoPath(PhotoType photoType) {

		if (photoType.equals(PhotoType.FilmScreenShot)) {
			return Constants.PHOTO_PATH + "Film"+File.separator;
		} else if (photoType.equals(PhotoType.UserPhoto)) {
			return Constants.PHOTO_PATH + "User"+File.separator;
		}
		return null;
	}

}
