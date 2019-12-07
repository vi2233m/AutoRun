package com.zot.autorun.moudules.apitest.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dateformat {

//	public static void main(String[] args) throws ParseException {
//		// TODO Auto-generated method stub
////		System.out.println(Dateformat.getDateAndOne());
////		System.out.println(Dateformat.getDateAndThree());
////		System.out.println(Dateformat.getTimestamp());
////		System.out.println(Dateformat.getDate());
//	}
	
	
	
	/**
	 * 返回当前时间 + 1天 ； 格式：2018-06-28
	 * @return
	 */
	public  static String getDateAndOne() {
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		//Date beginDate = new Date();
		Calendar date = Calendar.getInstance();		
		//date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);

		String dateAndone = dft.format(date.getTime());		
		return dateAndone;		
	
	}

	/**
	 * 返回当前时间 + 3天 ； 格式：2018-06-28
	 * @return
	 */
	public  static String getDateAndThree() {
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		//Date beginDate = new Date();
		Calendar date = Calendar.getInstance();		
		//date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + 3);

		String dateAndthree = dft.format(date.getTime());		
		return dateAndthree;			
	}
	
	
	public  static String getTimestamp() {
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();		
		date.setTime(beginDate);
		//date.set(Calendar.DATE, date.get(Calendar.DATE) + 3);

		String timestamp = dft.format(date.getTime());		
		return timestamp;			
	}
	
}
