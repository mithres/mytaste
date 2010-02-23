package com.vc.core.vod;

import org.red5.server.api.IScope;
import org.red5.server.api.stream.IStreamFilenameGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.constants.Constants;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;

public class CustomFilenameGenerator implements IStreamFilenameGenerator {

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
			filename = Constants.RECORDED_STREAM_PATH + name;
		} else {
			filename = Constants.VIDEO_STREAM_PATH + name;
		}

		if (extension != null) {
			filename += extension;
		}

		return filename;
	}

	@Override
	public boolean resolvesToAbsolutePath() {
		return true;
	}

}
