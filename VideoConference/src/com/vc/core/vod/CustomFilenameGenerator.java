package com.vc.core.vod;

import org.red5.server.api.IScope;
import org.red5.server.api.stream.IStreamFilenameGenerator;

public class CustomFilenameGenerator implements IStreamFilenameGenerator {

	// Path that will store recorded videos.
	private String recordPath = "recordedStreams/";
	
	// Path that contains VOD streams.
	private String playbackPath = "videoStreams/";

	@Override
	public String generateFilename(IScope scope, String name, GenerationType type) {
		return generateFilename(scope, name, null, type);
	}

	@Override
	public String generateFilename(IScope scope, String name, String extension, GenerationType type) {

		String filename;
		if (type == GenerationType.RECORD)
			filename = recordPath + name;
		else
			filename = playbackPath + name;

		if (extension != null)
			// Add extension
			filename += extension;

		return filename;
	}

	@Override
	public boolean resolvesToAbsolutePath() {
		return true;
	}

	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}

	public void setPlaybackPath(String playbackPath) {
		this.playbackPath = playbackPath;
	}
}
