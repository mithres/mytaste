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
	public PointCard findCardInfoByIdAndPassword(String cardId, String password) throws PointCardException {
		PointCard card = pointCardDao.findPointCardByIdAndPassword(cardId, password);
		if (card == null) {
			throw new PointCardException("Point Card with NO {" + cardId + "} and password {" + password + "} not found.",
					"vc.accountdeposits.card.notfound");
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

		PointCard card = findCardInfoByIdAndPassword(vo.getCardId(), vo.getCardPassword());

		if (card.getExpireTime().before(currentTime)) {
			throw new DepositException("Card {" + card.getCardId() + "} expired.", "vc.accountdeposits.card.expired");
		} else if (card.getUsed()) {
			throw new DepositException("Card {" + card.getCardId() + "} has been used.", "vc.accountdeposits.cardused");
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
