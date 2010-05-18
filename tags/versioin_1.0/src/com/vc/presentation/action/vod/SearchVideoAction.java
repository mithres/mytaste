package com.vc.presentation.action.vod;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.Channels;
import com.vc.entity.PlayList;
import com.vc.service.system.ISystemService;
import com.vc.service.vod.IPlayListService;

public class SearchVideoAction extends BaseAction {

	private static final long serialVersionUID = 3728720954438353340L;

	@Autowired
	private ISystemService systemService = null;
	@Autowired
	private IPlayListService playListService = null;

	private IPageList<PlayList> playLists = null;
	
	private Channels channel = null;
	
	private String cid = null;

	private String text = null;

	@Override
	public String process() {
		playLists = playListService.searchPlayListInChannel(new Hints(getStartRow(), getPageCount()), cid, text);
		channel = systemService.findChannelById(cid);
		return Action.SUCCESS;
	}

	public IPageList<PlayList> getPlayLists() {
		return playLists;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Channels getChannel() {
		return channel;
	}

}
