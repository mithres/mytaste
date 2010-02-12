package com.vc.service.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import com.vc.util.configuration.ServerConfiguration;

@Service
public class HadoopDFSProvider implements IFSProvider {

	@Override
	public boolean createFile(String persistenceName, File file) {

		Configuration conf = new Configuration();
		String uri = ServerConfiguration.getFsUri();

		InputStream in = null;
		OutputStream out = null;

		// FSDataInputStream in = null;
		try {

			in = new FileInputStream(file);

			FileSystem fs = FileSystem.get(URI.create(uri), conf);
			out = fs.create(new Path("/vod/videoStreams/" + persistenceName));

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
