package com.vc.presentation.action.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.core.constants.Constants;
import com.vc.core.dao.Hints;
import com.vc.entity.Category;
import com.vc.entity.PlayList;
import com.vc.service.system.ISystemService;
import com.vc.service.vod.IPlayListService;

public class IndexAction extends BaseAction {

	private static final long serialVersionUID = -8814106229563837658L;

	@Autowired
	private ISystemService systemService = null;
	@Autowired
	private IPlayListService playListService = null;

	private List<Category> categories = null;
	private List<PlayList> mostViewedPlayLists = null;

	@Override
	public String process() {
		categories = systemService.findAllCategories();
		mostViewedPlayLists = playListService.findPlayListByViewCount(new Hints(Constants.DEFAULT_START, 5));
		return Action.SUCCESS;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<PlayList> getMostViewedPlayLists() {
		return mostViewedPlayLists;
	}

}