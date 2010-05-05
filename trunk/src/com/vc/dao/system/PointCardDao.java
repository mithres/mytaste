package com.vc.dao.system;

import org.springframework.stereotype.Repository;

import com.vc.core.dao.GenericDAO;
import com.vc.core.dao.Hints;
import com.vc.entity.PointCard;

@Repository
public class PointCardDao extends GenericDAO<PointCard, String> {

	private static final String FIND_CARD_BY_PASSWORD = " from PointCard where cardPassword = ? and cardId = ? ";

	public PointCard findPointCardByIdAndPassword(String cardId, String password) {
		return (PointCard) findUnique(FIND_CARD_BY_PASSWORD, new Hints(0), password, cardId);
	}

}
