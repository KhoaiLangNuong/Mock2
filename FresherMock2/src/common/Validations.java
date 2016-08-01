package common;

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
}
