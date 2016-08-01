package common;

import java.io.UnsupportedEncodingException;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: 29-07-2016
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * -----------------------------------------------------------------------
 * 29-07-2016 NguyetNT6 Create
 */
public class StringProcess {
	
	/**
	 * format UTF-8
	 * @param isoString
	 * @return
	 */
	public static String toUTF8(String isoString) {
		String utf8String = null;
		try {
			if (isoString != null) {
				byte[] stringByteISO = isoString.getBytes("ISO-8859-1");
				utf8String = new String(stringByteISO, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			utf8String = isoString;
		}
		return utf8String;
	}

}
