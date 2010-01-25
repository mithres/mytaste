package com.vc.core.vod;

import java.security.NoSuchAlgorithmException;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.IScope;
import org.red5.server.api.stream.IStreamFilenameGenerator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.bo.vod.VODClient;
import com.vc.core.adapter.ApplicationAdapterHelper;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.IVODClientManager;
import com.vc.util.security.AesCrypt;
import com.vc.util.security.MD5;

public class CustomFilenameGenerator implements IStreamFilenameGenerator {

	private static final Logger log = Red5LoggerFactory.getLogger(CustomFilenameGenerator.class, "VideoConference");

	// Path that will store recorded videos.
	private String recordPath = "recordedStreams/";

	// Path that contains VOD streams.
	private String playbackPath = "videoStreams/";

	@Autowired
	private IVODClientManager vodClientManager = null;

	@Autowired
	private IPlayListService playListService = null;

	@Override
	public String generateFilename(IScope scope, String name, GenerationType type) {
		return generateFilename(scope, name, null, type);
	}

	@Override
	public String generateFilename(IScope scope, String name, String extension, GenerationType type) {

		String filename = null;
	
		VODClient client = vodClientManager.getClientByID(ApplicationAdapterHelper.getCurrentConnection().getClient().getId());

		if (client != null) {
			//TODO: Sometimes this method couldn't decrypt the encrypted message from client. 
			AesCrypt ac = new AesCrypt();
			try {
				String key = MD5.do_checksum(client.getClientKey());
				ac.setKey(ac.hexToByte(key));
				if (name.endsWith(".flv")) {
					name = name.substring(0, name.indexOf(".flv"));
				}
				log.debug("Use :" + key + " to decrypt :"+name);
				name = ac.decrypt(name);
				log.debug("Decrypted file name is:" + name);
				PlayList playList = playListService.findPlayListById(name);
				name = playList.getFileName();
			} catch (NoSuchAlgorithmException e) {
				log.error("Decrypted film name error", e);
			}
		} else {
			//Reject client
			return "";
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

}
