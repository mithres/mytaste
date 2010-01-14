package com.vc.dao.vod;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.vc.entity.PlayList;
import com.vc.util.hibernate.HibernateUtil;

public class PlayListDao {

	private static final Logger log = Red5LoggerFactory.getLogger(PlayListDao.class, "VideoConference");

	private static PlayListDao instance = null;

	private PlayListDao() {

	}

	public static PlayListDao getInstance() {
		if (instance == null) {
			instance = new PlayListDao();
		}
		return instance;
	}

	public PlayList savePlayList(PlayList playList) {

		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(playList);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return playList;
		} catch (HibernateException e) {
			log.info("savePlayList", e);
		} catch (Exception e) {
			log.info("savePlayList", e);
		}
		return null;
	}
}
