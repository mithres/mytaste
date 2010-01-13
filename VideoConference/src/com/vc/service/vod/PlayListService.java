package com.vc.service.vod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IScope;
import org.red5.server.api.Red5;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class PlayListService implements IPlayListService {

	private static Logger log = Red5LoggerFactory.getLogger(PlayListService.class, "VideoConference");

	@Override
	public Map<String, Map<String, Object>> getPlayList() {

		IScope scope = Red5.getConnectionLocal().getScope();
		Map<String, Map<String, Object>> filesMap = new HashMap<String, Map<String, Object>>();
		try {
			log.debug("getting the FLV files");
			addToMap(filesMap, scope.getResources("streams/*.flv"));

			addToMap(filesMap, scope.getResources("streams/*.f4v"));

			addToMap(filesMap, scope.getResources("streams/*.mp3"));

			addToMap(filesMap, scope.getResources("streams/*.mp4"));

			addToMap(filesMap, scope.getResources("streams/*.m4a"));

			addToMap(filesMap, scope.getResources("streams/*.3g2"));

			addToMap(filesMap, scope.getResources("streams/*.3gp"));

		} catch (IOException e) {
			log.error("", e);
		}
		return filesMap;
	}

	private String formatDate(Date date) {
		SimpleDateFormat formatter;
		String pattern = "dd/MM/yy H:mm:ss";
		Locale locale = new Locale("en", "US");
		formatter = new SimpleDateFormat(pattern, locale);
		return formatter.format(date);
	}

	private void addToMap(Map<String, Map<String, Object>> filesMap, Resource[] files) throws IOException {
		if (files != null) {
			for (Resource flv : files) {
				File file = flv.getFile();
				Date lastModifiedDate = new Date(file.lastModified());
				String lastModified = formatDate(lastModifiedDate);
				String flvName = flv.getFile().getName();
				String flvBytes = Long.toString(file.length());
				if (log.isDebugEnabled()) {
					log.debug("flvName: {}", flvName);
					log.debug("lastModified date: {}", lastModified);
					log.debug("flvBytes: {}", flvBytes);
					log.debug("-------");
				}
				Map<String, Object> fileInfo = new HashMap<String, Object>();
				fileInfo.put("name", flvName);
				fileInfo.put("lastModified", lastModified);
				fileInfo.put("size", flvBytes);
				filesMap.put(flvName, fileInfo);
			}
		}
	}

}
