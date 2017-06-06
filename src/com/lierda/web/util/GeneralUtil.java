package com.lierda.web.util;

import java.util.Calendar;
/**
 * 常用工具类
 * @author qianjiawei
 *
 */
public class GeneralUtil {
	
	//获得当天0点时间 
	public static int getTimesmorning(){ 
	Calendar cal = Calendar.getInstance(); 
	cal.set(Calendar.HOUR_OF_DAY, 0); 
	cal.set(Calendar.SECOND, 0); 
	cal.set(Calendar.MINUTE, 0); 
	cal.set(Calendar.MILLISECOND, 0); 
	return (int) (cal.getTimeInMillis()/1000); 
	} 
	
	//获得当天24点时间 
	public static int getTimesnight(){ 
	Calendar cal = Calendar.getInstance(); 
	cal.set(Calendar.HOUR_OF_DAY, 24); 
	cal.set(Calendar.SECOND, 0); 
	cal.set(Calendar.MINUTE, 0); 
	cal.set(Calendar.MILLISECOND, 0); 
	return (int) (cal.getTimeInMillis()/1000); 
	} 
}
