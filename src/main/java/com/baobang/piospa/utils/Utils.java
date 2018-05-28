package com.baobang.piospa.utils;

import java.util.Calendar;

/**
 * @author BaoBang
 * @Created May 27, 2018
 * 
 */
public class Utils {

	public static String genarateCode() {
		Calendar calendar = Calendar.getInstance();
		return "" 
				+ calendar.get(Calendar.YEAR)
				+ String.format("%02d", (calendar.get(Calendar.MONTH) + 1))
				+ String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))
				+ String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))
				+ String.format("%02d", calendar.get(Calendar.MINUTE))
				+ String.format("%02d", calendar.get(Calendar.SECOND))
				+ calendar.get(Calendar.MILLISECOND);
	}
}
