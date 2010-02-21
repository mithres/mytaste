package com.vc.service.system;

import java.io.File;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vc.dao.system.PointCardDao;
import com.vc.dao.system.PurchasesHistoryDao;
import com.vc.dao.user.UserInfoDao;
import com.vc.entity.PointCard;
import com.vc.entity.PurchasesHistory;
import com.vc.entity.UserInfo;
import com.vc.presentation.exception.DepositException;
import com.vc.presentation.exception.PointCardException;
import com.vc.vo.PurchaseVO;

@Service
public class PurchaseService implements IPurchaseService {

	@Autowired
	private PointCardDao pointCardDao = null;
	@Autowired
	private PurchasesHistoryDao purchasesHistoryDao = null;
	@Autowired
	private UserInfoDao userInfoDao = null;

	@Override
	public PointCard findCardInfoByPassword(String password) throws PointCardException {
		PointCard card = pointCardDao.findPointCardByPassword(password);
		if (card == null) {
			throw new PointCardException("No card found.");
		}
		return card;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean importPointCardInfo(File file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserInfo purchase(PurchaseVO vo) throws DepositException, PointCardException {

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		PointCard card = findCardInfoByPassword(vo.getCardPassword());

		if (card.getExpireTime().before(currentTime)) {
			throw new DepositException("Card expired.");
		} else if (card.getUsed()) {
			throw new DepositException("Card has been used.");
		}

		card.setUsed(Boolean.TRUE);
		pointCardDao.update(card);

		UserInfo user = userInfoDao.findById(vo.getAccount());

		PurchasesHistory piHistory = new PurchasesHistory();
		piHistory.setPurchaseDate(currentTime);
		piHistory.setUserInfo(user);
		piHistory.setRemoteIp(vo.getRemoteIp());
		purchasesHistoryDao.create(piHistory);

		user.getPurchasesHistory().add(piHistory);
		user.setAccountBalance(user.getAccountBalance() + card.getDenomination());
		userInfoDao.update(user);

		return user;
	}
}
