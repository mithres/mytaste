package com.vc.presentation.vod.action;

import java.io.IOException;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.PlayList;
import com.vc.service.vod.IPlayListService;

public class PlayListIndexAction extends BaseAction {

	private static final long serialVersionUID = 6373087375653567380L;

	@Autowired
	private IPlayListService playListService = null;
	
	private IPageList<PlayList> playList = null;
	
	@Override
	public String process() {
		playList = playListService.findPlayList(new Hints(getStartRow(),getPageCount()));
		return Action.SUCCESS;
	}
	
	public String getInfo(){
				
		this.getSession().setAttribute("name", "abc");
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("Info");
		root.addElement("name").setText("abc");
		try {
			write(doc.asXML());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Action.NONE;
	}
	
	public String getS(){
		log.info("xxxxxxxxxxxxxxxxxxxxxxxxxx"+this.getSession().getAttribute("name"));
		return Action.NONE;
	}
	
	public IPlayListService getPlayListService() {
		return playListService;
	}

	public IPageList<PlayList> getPlayList() {
		return playList;
	}

}