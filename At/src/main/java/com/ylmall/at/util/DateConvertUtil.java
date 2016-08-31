/**  
* @Title: DateConvertUtil.java 
* @Package com.wcsportal.web.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author SEA   
* @date 2013-5-30 上午10:07:03 
* @version V1.0  
*/ 

package com.ylmall.at.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/** 
 * @author SEA 
 *  
 */
public final class DateConvertUtil {
	
	/**
	 * 格式化日期。
	* @param date 需格式化的日期
	* @param pattern 格式化日期的格式
	* @return 日期
	* @throws ParseException
	 */
	public static Date getFormatDate(String date, String pattern) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(date);
	}
	
	/**
	 * 格式化日期
	 */
	   public static Date getFormatterDate(Date date, String pattern) throws ParseException {
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	        String strFormat = dateFormat.format(date); 
	        return dateFormat.parse(strFormat);
	    }
	
	/**
	 * 日期格式化
	* @param date
	* @param pattern
	* @return
	* @throws ParseException
	 */
	public static String getFormatDate(Date date,String pattern)throws ParseException{
	    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
	}
	public static String formateString(Date date,String formate) throws ParseException{
        SimpleDateFormat fml=new SimpleDateFormat(formate);
        return fml.format(date);
    }

}
