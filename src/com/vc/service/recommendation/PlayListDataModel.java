package com.vc.service.recommendation;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;

public class PlayListDataModel extends MySQLJDBCDataModel {

	public final static String PERFERENCETABLE = "playlistrating";
	public final static String USERID_COLUMN = "userindex";
	public final static String ITEMID_COLUMN = "playlistindex";
	public final static String PERFERENCE_COLUMN = "preference";

	public PlayListDataModel(DataSource ds) throws TasteException {
		super(ds, PERFERENCETABLE, USERID_COLUMN, ITEMID_COLUMN, PERFERENCE_COLUMN);
	}
}
