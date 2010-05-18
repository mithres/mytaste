package com.vc.test.service;

import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vc.core.dao.Hints;
import com.vc.core.entity.IPageList;
import com.vc.entity.FilmType;
import com.vc.entity.PlayList;
import com.vc.entity.PlayListType;
import com.vc.service.vod.IPlayListService;
import com.vc.service.vod.PlayListSearchCondition;
import com.vc.test.core.BaseTest;

public class PlayListServiceTest extends BaseTest {

	@Autowired
	private IPlayListService playListService = null;
 
	@Test
	public void testPlayListManage() {

		PlayList list = new PlayList();
		list.setAddedTime(new Timestamp(System.currentTimeMillis()));
		list.setDescription("PlayList001");
		list.setFileName("fileName001");
		list.setFilmType(FilmType.Normal);
		list.setPlayListType(PlayListType.Game);

		playListService.savePlayList(list,new String[]{"tag1","tag2"});
		
		PlayListSearchCondition condition = new PlayListSearchCondition();
		condition.setOrderBy("AddedTime");
		IPageList<PlayList> plist = playListService.findPlayListByCondition(new Hints(0),condition);
		assertEquals(1, plist.getRecordTotal());

	}

}
