package com.vc.red5;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.util.configuration.ServerConfiguration;

public class HadoopFile extends File {

	private static final long serialVersionUID = -4494231407960396552L;

	private final Logger log = Red5LoggerFactory.getLogger(HadoopFile.class, "VideoConference");

	private FileSystem fs = null;

	private Path inputFile = null;

	private Configuration conf = null;

	private String fsUri = null;

	public HadoopFile(String pathname) {
		super(pathname);
		try {
			conf = new Configuration();
			fsUri = ServerConfiguration.getFsUri();
			fs = FileSystem.get(URI.create(fsUri), conf);
			inputFile = new Path(pathname);
		} catch (IOException e) {
			log.error("Get input stream from flv file on hdfs error.", e);
		}
	}

	public InputStream getInputStream() {
		try {
			return fs.open(inputFile);
		} catch (IOException e) {
			log.error("Get input stream from flv file on hdfs error.", e);
			return null;
		}
	}

	@Override
	public boolean exists() {
		try {
			return fs.exists(inputFile);
		} catch (IOException e) {
			log.error("Get input stream from flv file on hdfs error.", e);
			return false;
		}
	}

	@Override
	public long length() {
		try {
			return fs.getFileStatus(inputFile).getLen();
		} catch (IOException e) {
			log.error("Get input stream from flv file on hdfs error.", e);
			return -1;
		}
	}

	public Configuration getConf() {
		return conf;
	}

	public void setConf(Configuration conf) {
		this.conf = conf;
	}

	public String getFsUri() {
		return fsUri;
	}

	public void setFsUri(String fsUri) {
		this.fsUri = fsUri;
	}

}