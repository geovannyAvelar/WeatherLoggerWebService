package br.com.avelar.wlws.utils;

import java.util.Date;
import java.util.Calendar;

public class DateUtils {
	
	private static int SUM = 0;
	private static int SUBTRACT = 1;
	
	/**
	 * 
	 * @param date A java.util.Date object
	 * @param type A constant of java.util.Calendar 
	 * 				class (Calendar.MINUTE, Calendar.HOUR_OF_DAY, etc...)
	 * @param quantity The period of time that will be decreased or incresead on date
	 * @param operation An integer, if equals 0, sum the date, if different of 0, subtract 
	 * @return A java.util.Date object
	 */
	private static Date manipulateDate(Date date, int type, Integer quantity, int operation) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		if(operation == 0) {
			c.set(type, c.get(type) + quantity);
		} else {
			c.set(type, c.get(type) - quantity);
		}
		
		return c.getTime();
	}
	
	/**
	 * 
	 * @param date A java.util.Date object
	 * @param type A constant of java.util.Calendar 
	 * 				class (Calendar.MINUTE, Calendar.HOUR_OF_DAY, etc...)
	 * @param quantity The period of time that will be incresead on date
	 * @return A java.util.Date object
	 */
	public static Date sumDate(Date date, int type, Integer quantity) {
		return manipulateDate(date, type, quantity, SUM);
	}
	
	/**
	 * 
	 * @param date A java.util.Date object
	 * @param type A constant of java.util.Calendar 
	 * 				class (Calendar.MINUTE, Calendar.HOUR_OF_DAY, etc...)
	 * @param quantity The period of time that will be decreased on date
	 * @return A java.util.Date object
	 */
	public static Date subtractDate(Date date, int type, Integer quantity) {
		return manipulateDate(date, type, quantity, SUBTRACT);
	}
	
}
