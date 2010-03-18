package com.vc.presentation.action.user;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.vc.core.action.BaseAction;
import com.vc.entity.PointCard;
import com.vc.entity.UserInfo;
import com.vc.presentation.exception.DepositException;
import com.vc.presentation.exception.PointCardException;
import com.vc.service.system.IPurchaseService;
import com.vc.service.user.IUserService;
import com.vc.util.security.ItemChecker;
import com.vc.vo.PurchaseVO;

public class DepositServiceAction extends BaseAction {

	private static final long serialVersionUID = 1470350362804180491L;

	@Autowired
	private IPurchaseService purchaseService = null;
	@Autowired
	private IUserService userService = null;

	private String cardPassword = null;

	private String account = null;

	private PointCard pointCard = null;
	private UserInfo userAccount = null;

	public String index() {
		return Action.SUCCESS;
	}

	private final void preValidate() {

		if (ItemChecker.checkNull(account)) {
			this.addActionError(this.getText("vc.accountdeposits.account.empty"));
		}
		if (!ItemChecker.checkUserName(account)) {
			this.addActionError(this.getText("vc.accountdeposits.account.error"));
		}

		if (ItemChecker.checkNull(cardPassword)) {
			this.addActionError(this.getText("vc.accountdeposits.card.password.empty"));
		}
		if (ItemChecker.checkNumber(cardPassword)) {
			this.addActionError(this.getText("vc.accountdeposits.card.password.error"));
		}
	}

	public String summary() {
		try {

			preValidate();
			if (this.getActionErrors().size() > 0) {
				return Action.INPUT;
			}
			UserInfo user = userService.findUserByName(account);
			if (user == null) {
				this.addActionError(getText("vc.user.notfound", new String[] { account }));
			} else if (!user.isEnabled()) {
				this.addActionError(getText("vc.user.disabled", new String[] { account }));
			}

			pointCard = purchaseService.findCardInfoByPassword(cardPassword);
			if (pointCard.getUsed()) {
				this.addActionError(getText("vc.accountdeposits.cardused"));
				return Action.INPUT;
			} else if (pointCard.getExpireTime().before(new Timestamp(System.currentTimeMillis()))) {
				this.addActionError(getText("vc.accountdeposits.card.expired"));
				return Action.INPUT;
			}

			userAccount = userService.findUserByName(account);
		} catch (PointCardException e) {
			this.addActionError(getText(e.getMessageKey()));
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	@Override
	public String process() {

		preValidate();
		if (this.getActionErrors().size() > 0) {
			return Action.INPUT;
		}

		PurchaseVO vo = new PurchaseVO();
		vo.setAccount(account);
		vo.setCardPassword(cardPassword);
		vo.setRemoteIp(request.getRemoteAddr());
		try {
			purchaseService.purchase(vo);
		} catch (DepositException e) {
			this.addActionError(e.getMessage());
			return Action.INPUT;
		} catch (PointCardException e) {
			this.addActionError(e.getMessage());
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public PointCard getPointCard() {
		return pointCard;
	}

	public UserInfo getUserAccount() {
		return userAccount;
	}

}
