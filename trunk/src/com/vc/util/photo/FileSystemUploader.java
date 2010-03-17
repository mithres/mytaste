package com.vc.util.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

/**
 * Writes content to file.
 * 
 */
public class FileSystemUploader implements IFileUploader {
	
	private static Logger log = Red5LoggerFactory.getLogger(FileSystemUploader.class, "VideoConference");

	
	@Override
	public boolean preparePath(String uri, String authString) throws IOException {
		String filePath = uri.substring(0, uri.lastIndexOf("/"));
		File file = new File(filePath);
		if (!file.exists()) {
			return file.mkdirs();
		}

		return true;

	}

	@Override
	public void upload(InputStream is, int contentLength, String uri, String authString) throws IOException {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(uri);
			IOUtils.copyLarge(is, os);
		} finally {
			if (os != null) {
				IOUtils.closeQuietly(os);
			}
		}

	}

	@Override
	public boolean exists(String uri, String authString) {
		return new File(uri).exists();
	}

	@Override
	public byte[] download(String uri, String authString) throws IOException {
		File file = new File(uri);
		InputStream inputStream = new FileInputStream(file);
		byte[] bytes = IOUtils.toByteArray(inputStream);
		return bytes;
	}

}