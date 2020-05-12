package com.dongkap.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

	private DateUtil() {}
	
	public static final String DEFAULT_FORMAT_DATE = "dd/MM/yyyy";
	public static final SimpleDateFormat DATE = new SimpleDateFormat(DEFAULT_FORMAT_DATE);
	
	public static final String DEFAULT_FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
	public static final SimpleDateFormat DATE_TIME = new SimpleDateFormat(DEFAULT_FORMAT_DATE_TIME);
	
	public static String formatDate(Date date, SimpleDateFormat sdf) {
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	public static String formatDate(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(dateFormat).format(date);
	}

	public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(aMask);
		Date date;
		df.setLenient(false);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	public static Timestamp convertStringToTimestamp(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(aMask);
		Date date;
		df.setLenient(false);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (new Timestamp(date.getTime()));
	}

	public static Date getBeginOfTheDay(Locale locale) {
		return getBeginOfTheDay(locale, new Date());
	}

	public static Date getBeginOfTheDay(Locale locale, Date date) {
		Calendar cal = Calendar.getInstance(locale);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getEndOfTheDay(Locale locale) {
		return getEndOfTheDay(locale, new Date());
	}

	public static Date getEndOfTheDay(Locale locale, Date date) {
		Calendar cal = Calendar.getInstance(locale);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date getBeginOfTheMonth(Locale locale, Date date) {
		Calendar cal = Calendar.getInstance(locale);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getEndOfTheMonth(Locale locale, Date date) {
		Calendar cal = Calendar.getInstance(locale);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static String getMonthName(Locale locale, int month) {
		SimpleDateFormat monthParse = new SimpleDateFormat("MM", locale);
		SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM", locale);
		try {
			return monthDisplay.format(monthParse.parse("" + month));
		} catch (ParseException e) {
			return "";
		}
	}

	public static String getMonthName(String locale, String month) {
		if(locale == null) locale = "en-US";
    	Locale i18n = Locale.forLanguageTag(locale);
    	return getMonthName(i18n, month);
	}

	public static String getMonthName(Locale locale, String month) {
		month = month.replaceAll(ParameterStatic.MONTH_PARAMETER, "");
		SimpleDateFormat monthParse = new SimpleDateFormat("MM", locale);
		SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM", locale);
		try {
			return monthDisplay.format(monthParse.parse("" + month));
		} catch (ParseException e) {
			return "";
		}
	}

	public static String getMonthNumber(String locale, Date date) {
		if(locale == null) locale = "en-US";
    	Locale i18n = Locale.forLanguageTag(locale);
    	return getMonthNumber(i18n, date);
	}

	public static String getMonthNumber(Locale locale, Date date) {
		SimpleDateFormat monthDisplay = new SimpleDateFormat("MM", locale);
		return monthDisplay.format(date);
	}

	public static int getYear(Locale locale, Date date) {
		Calendar c = Calendar.getInstance(locale);
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int getYear(String locale, Date date) {
		if(locale == null) locale = "en-US";
    	Locale i18n = Locale.forLanguageTag(locale);
    	return getYear(i18n, date);
	}

}
