package com.vc.presentation.action.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.Category;
import com.vc.service.system.ISystemService;

public class IndexAction extends BaseAction {

	private static final long serialVersionUID = -8814106229563837658L;
	
	@Autowired
	private ISystemService systemService = null;
	
	private List<Category> categoies = null;
	
	@Override
	public String process() {
		categoies = systemService.findAllCategories();
		return Action.SUCCESS;
	}

	public List<Category> getCategoies() {
		return categoies;
	}

}
