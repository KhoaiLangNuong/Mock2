package common;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.bean.Studio;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: 29-07-2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 29-07-2016       	NguyetNT6          Create
 */
public class Validations {
	
    /**
     * validate space
     * @param str
     * @return true/false
     */
	public static boolean validateSpace(String str) {
		if (str == null  ) {
			return false;
		} else {
			return "".equals(str.trim());
		}
	}
    
	
    /**
     * validate nhập quá ký tự
     * @param str
     * @param length
     * @return true/false
     */
	public static boolean validLength(String str, int length) {
		if (str.length() > length)
			return false;
		return true;
	}

    /**
     * validate null
     * @param str
     * @return true/false
     */
	public static boolean validateNull(String str) {
		if (str == null || str.length() == 0)
			return true;
		return false;
	}
	
	/**
	 * validate contain special
	 * @param s
	 * @return true/false
	 */
		public static boolean containsSpecialCharacter(String s) {
			Pattern regex = Pattern.compile("[!%*$&+:;=?@#|]");
			Matcher matcher = regex.matcher(s);
			if (matcher.find()) {
				return true;
			}
			return false;
		}
		
		/**
		 * validate key
		 * @param key1
		 * @param listStudio
		 * @param index
		 * @return
		 */
		public static boolean validateKey(String key1,
				ArrayList<Studio> listStudio, int index) {
			int i;
			for(i=0; i<listStudio.size(); i++){
				if (i!=index && key1.equals(listStudio.get(i).getSysfiKey()))
					return true;
			}
			return false;
		}

}
