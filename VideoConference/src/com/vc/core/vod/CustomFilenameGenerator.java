package com.vc.core.vod;

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
import org.red5.server.api.IScope;
import org.red5.server.api.stream.IStreamFilenameGenerator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.constants.Constants;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;

public class CustomFilenameGenerator implements IStreamFilenameGenerator {

	private static final Logger log = Red5LoggerFactory.getLogger(CustomFilenameGenerator.class, "VideoConference");

	// Path that will store recorded videos.
	private String recordPath = "recordedStreams/";
	// Path that contains VOD streams.
	private String playbackPath = "videoStreams/";

	@Autowired
	private IPlayListService playListService = null;

	@Override
	public String generateFilename(IScope scope, String name, GenerationType type) {
		return generateFilename(scope, name, null, type);
	}

	@Override
	public String generateFilename(IScope scope, String name, String extension, GenerationType type) {

		String filename = null;

		if (scope.getName().endsWith(Constants.VOD_SCOPE_NAME)) {

			if (name.endsWith(".flv")) {
				name = name.substring(0, name.indexOf(".flv"));
			}
			PlayList playList = playListService.findPlayListById(name);
			name = playList.getFileName();
		}

		if (type == GenerationType.RECORD) {
			filename = recordPath + name;
		} else {
			filename = playbackPath + name;
		}

		if (extension != null) {
			filename += extension;
		}
		return filename;
	}

	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}

	public void setPlaybackPath(String playbackPath) {
		this.playbackPath = playbackPath;
	}

	@Override
	public boolean resolvesToAbsolutePath() {
		return true;
	}

	public static void main(String[] a) {
		Configuration conf = new Configuration();

		// conf.set("hadoop.job.ugi", "root,123"); // 设置存储服务器的用户名，密码

		String uri = "hdfs://172.0.2.193:54310/";

		// try {
		// String str = "Hello world";
		//
		// FileSystem fs = FileSystem.get(URI.create(uri), conf);
		//			
		// fs.deleteOnExit(new Path("/helloworld"));
		//			
		// // java.io.OutputStream out = fs.create(new Path("/helloworld"));
		// //
		// // out.write(str.getBytes());
		// //
		// // out.flush();
		// //
		// // out.close();
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }

		InputStream in = null;
		OutputStream out = null;

		// FSDataInputStream in = null;
		try {

			File file = new File("/home/ammen/Test/videoStreams/terminator.flv");
			in = new FileInputStream(file);

			FileSystem fs = FileSystem.get(URI.create(uri), conf);
			out = fs.create(new Path("/vod/videoStreams/terminator.flv"));

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
				} catch (IOException e) {}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {}
			}

		}
	}
}
