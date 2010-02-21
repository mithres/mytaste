package com.vc.service.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.vc.util.configuration.ServerConfiguration;

@Service("fs")
public class FSProvider implements IFSProvider {

	protected final Logger log = Red5LoggerFactory.getLogger(FSProvider.class, "VideoConference");

	@Override
	public boolean createFile(String persistenceName, File file) {

		InputStream in = null;
		OutputStream out = null;

		try {

			in = new FileInputStream(file);
			out = new FileOutputStream(new File(ServerConfiguration.getFsUri() + "videoStreams/" + persistenceName));

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}

		}

		return false;
	}

	@Override
	public InputStream readFile(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.error("Get input stream from file on fs error.", e);
			return null;
		}
	}

}
