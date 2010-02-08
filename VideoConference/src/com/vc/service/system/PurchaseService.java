package com.vc.service.system;

import java.io.File;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.dao.system.PointCardDao;
import com.vc.dao.system.PurchasesHistoryDao;
import com.vc.dao.user.UserInfoDao;
import com.vc.entity.PointCard;
import com.vc.entity.PurchasesHistory;
import com.vc.entity.UserInfo;

public class PurchaseService implements IPurchaseService {

	@Autowired
	private PointCardDao pointCardDao = null;
	@Autowired
	private PurchasesHistoryDao purchasesHistoryDao = null;
	@Autowired
	private UserInfoDao userInfoDao = null;

	@Override
	public PointCard findCardInfoByPassword(String password) {
		return this.pointCardDao.findPointCardByPassword(password);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean importPointCardInfo(File file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserInfo purchase(String userName, String password) {

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		PointCard card = pointCardDao.findPointCardByPassword(password);
		if (card == null || card.getExpireTime().before(currentTime) || card.getUsed()) {
			// Throw exception
		}
		card.setUsed(Boolean.TRUE);
		pointCardDao.update(card);

		UserInfo user = userInfoDao.findById(userName);

		PurchasesHistory piHistory = new PurchasesHistory();
		piHistory.setPurchaseDate(currentTime);
		piHistory.setUserInfo(user);
		purchasesHistoryDao.create(piHistory);

		user.getPurchasesHistory().add(piHistory);
		user.setAccountBalance(user.getAccountBalance() + card.getDenomination());
		userInfoDao.update(user);

		return user;
	}

}
