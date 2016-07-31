package common; 

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: 19-07-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 19-07-2016       	NguyetNT6          Create
 */
public class StringProcess {
	
	/**
	 * Ham tra ve gioi tinh: 1=Nam, 0=Nu
	 * @param val
	 * @return String
	 */
	public static String gioiTinh(String val) {
		if("0".equals(val)){
			return "Nữ¯";
		}
		return "Nam";
	}
	
	/**
	 * Ham in ra mot xau, null in ra xau rong
	 * @param s
	 * @return String
	 */
	public static String getVaildString(String s) {
		if(s==null) return "";
		return s;
	}
	
	/**
	 * Ham kiem tra xau rong hay khong
	 * @param s
	 * @return boolean
	 */
	public static boolean notVaild(String s){
		if(s==null || s.length()==0) return true;
		return false;
	}
	
	/**
	 * Ham kiem tra xem xau co bao gom chi chu so hay khong
	 * @param s
	 * @return boolean
	 */
	public static boolean notVaildNumber(String s){
		if(notVaild(s)) return true;
		String regex = "[0-9]+"; 
		if(s.matches(regex)) return false;
		return true;
	}
	
	public static String toUTF8(String isoString){
		String utf8String = null;
		try {
			if (isoString != null){
				byte[] stringByteISO = isoString.getBytes("ISO-8859-1");
				utf8String = new String(stringByteISO, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			utf8String = isoString;
		}
		return utf8String;
	}
	public static String formatDate(String source, String formatIn, String formatOut){
		SimpleDateFormat sdfIn= new SimpleDateFormat(formatIn);
		SimpleDateFormat sdfOut= new SimpleDateFormat(formatOut);
		try {
			Date date= sdfIn.parse(source);
			return sdfOut.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	public static void main(String[] args) {
	}
}

