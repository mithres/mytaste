package com.vc.util.security;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerUtil {
	
	public static String getCurrentDate(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Calendar rightNow = Calendar.getInstance();
		return formatter.format(rightNow.getTime());
				
	}
	
}
