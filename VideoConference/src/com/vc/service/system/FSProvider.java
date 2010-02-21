package com.vc.service.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

import com.vc.util.configuration.ServerConfiguration;

@Service("fs")
public class FSProvider implements IFSProvider {

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

}
