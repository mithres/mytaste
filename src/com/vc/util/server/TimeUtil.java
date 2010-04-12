package com.vc.util.server;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	public static final Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static final Date[] getCurrentWeek(int weekIndex) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, weekIndex);

		int index = cal.get(Calendar.DAY_OF_WEEK);

		if (index == 1) {
			index = 8;
		}
		cal.add(Calendar.DATE, -(index - 2));
		cal.set(cal.HOUR_OF_DAY, 0);
		cal.set(cal.MINUTE, 0);
		cal.set(cal.SECOND, 0);
		Date start = cal.getTime();

		cal.add(Calendar.DATE, 6);
		cal.set(cal.HOUR_OF_DAY, 23);
		cal.set(cal.MINUTE, 59);
		cal.set(cal.SECOND, 59);
		Date end = cal.getTime();
		Date[] result = new Date[] { start, end };
		return result;
	}

	public static final int getDateIndex(Date date,String type) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		if(type.equals("Day")){
			return cal.get(Calendar.DAY_OF_YEAR);
		}else if(type.equals("Week")){
			return cal.get(Calendar.WEEK_OF_YEAR);
		}else if(type.equals("Month")){
			return cal.get(Calendar.MONTH);
		}
		return -1;
		
	}

}
