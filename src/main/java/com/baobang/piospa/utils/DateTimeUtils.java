package com.baobang.piospa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
  * @author BaoBang
  * @Created Aug 16, 2018
  * 
  */
public class DateTimeUtils {

	
	public static Date getDate(String date, String time) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy",new Locale("vi", "VN"));
		try {
			cal.setTime(sdf.parse(date));
			String items[] = time.split(":");
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(items[0]));

			cal.set(Calendar.MINUTE, Integer.parseInt(items[1]));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cal.getTime();
	}
	
	
	public static Date addMinute(Date date,  int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		
		return cal.getTime();
	}
}
