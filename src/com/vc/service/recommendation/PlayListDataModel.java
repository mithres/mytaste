package com.vc.service.recommendation;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;

import com.vc.core.constants.Constants;

public class PlayListDataModel extends MySQLJDBCDataModel {

	public PlayListDataModel(DataSource ds) throws TasteException {
		super(ds, Constants.PERFERENCETABLE, Constants.USERID_COLUMN, Constants.ITEMID_COLUMN,
				Constants.PERFERENCE_COLUMN);
	}
}
