package com.vc.presentation.action.common;

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
import com.vc.service.vod.PlayListSearchCondition;
import com.vc.util.photo.PhotoType;
import com.vc.util.photo.PicUtil;

public class RecommendAction extends BaseAction {

	private static final long serialVersionUID = -5887392015045305954L;

	@Autowired
	private IPlayListService playListService = null;

	@Override
	public String process() {

		PlayListSearchCondition condition = new PlayListSearchCondition();
		condition.setOrderBy("Today");
		IPageList<PlayList> playLists = playListService.findPlayListByCondition(
				new Hints(getStartRow(), getPageCount()), condition);
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("videos");
		int i = 0;
		for (PlayList playList : playLists.getRecords()) {
			Element video = root.addElement("video");
			video.addAttribute("id", playList.getId());
			video.addAttribute("index", String.valueOf(i));
			video.addAttribute("des", playList.getDescription());
			String photoUrl = PicUtil.loadPhotoUrl(playList.getPlayListIndex(), PhotoType.FilmScreenShot)+playList.getPlayListIndex()+".jpg";
			video.addAttribute("photo", photoUrl);
			i++;
		}
		try {
			log.info(doc.asXML());
			this.write(doc.asXML());
		} catch (Exception e) {
			log.error(e.toString());
		}
		return Action.NONE;
	}

}
