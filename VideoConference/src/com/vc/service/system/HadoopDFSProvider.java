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
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.vc.util.configuration.ServerConfiguration;

@Service("hdfs")
public class HadoopDFSProvider implements IFSProvider {

	protected final Logger log = Red5LoggerFactory.getLogger(HadoopDFSProvider.class, "VideoConference");

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

	@Override
	public InputStream readFile(File file) {

		Configuration conf = new Configuration();
		String uri = ServerConfiguration.getFsUri();
		Path inFile = new Path("/vod/videoStreams/" + file.getName());

		FileSystem fs = null;
		try {
			fs = FileSystem.get(URI.create(uri), conf);
			if (fs.exists(inFile)) {
				return fs.open(inFile);
			}
		} catch (IOException e) {
			log.error("Get input stream from flv file on hdfs error.", e);
			return null;
		}
		return null;
	}
}
