package com.vc.util.configuration;

import java.util.ResourceBundle;

import com.vc.core.constants.Constants;
import com.vc.util.photo.PhotoType;

public class ServerConfiguration {

	static {

		try {
			ResourceBundle rb = ResourceBundle.getBundle("server-config");

			// load photo configuration
			Constants.PHOTO_PATH = rb.getString("photo_path");
			Constants.PHOTO_URL = rb.getString("photo_url");

		} catch (Exception e) {
			e.printStackTrace();
		}

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
			return Constants.PHOTO_PATH + "Film/";
		} else if (photoType.equals(PhotoType.UserPhoto)) {
			return Constants.PHOTO_PATH + "User/";
		}
		return null;
	}

}
