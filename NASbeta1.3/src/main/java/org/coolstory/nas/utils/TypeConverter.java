package org.coolstory.nas.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class TypeConverter {

	private TypeConverter() {
	}
	
	public static String getCurrentDateInString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
		Date currentDate = new Date();
		return sdf.format(currentDate);
	}

	/**
	 * To get "Month Date, Year" value in String format from UtilDate
	 * 
	 * @param
	 * @return String
	 */
	public static String getFormattedCurrentDate() {
		DateFormat sdf = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
		String formattedDate = sdf.format(new java.util.Date());
		return formattedDate;
	}
	
	public static String formatDateInString(Date date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static Date parseInDate(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(date);
			return sdf.parse(date);
	}
	
	public static Date parseFormattedDateDate(String formattedDate, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(formattedDate);
	}
}