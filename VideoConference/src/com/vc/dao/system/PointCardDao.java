package com.vc.dao.system;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PointCard;

public class PointCardDao extends GenericDAO<PointCard, String> {

	public PointCard findPointCardByPassword(String password) {
		return this.findUniqueByProperty("cardPassword", password, new Hints(0));
	}

}
