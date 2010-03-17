package com.vc.util.server;

import java.sql.Timestamp;

public class TimeUtil {
	
	public static final Timestamp getCurrentTime(){
		return new Timestamp(System.currentTimeMillis());
	}
	
}
