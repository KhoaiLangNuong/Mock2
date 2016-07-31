package common;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class Validations {
	
/**
 * validate chứa ký tự đặc biệt
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
     * validate ký tự khác chữ
     * @param s
     * @return true/false
     */
	public static boolean checkEnterAllCharacter(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c < 67 || c > 122) || (c > 90) && (c < 97)) {
				return false;
			}
		}
		return true;
	}
    
	/**
	 * validate ký tự khác số
	 * @param s 
	 * @return true/false
	 */
	public static boolean checkEnterAllNumber(String s) {
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();

	}
    /**
     * validate số âm
     * @param so
     * @return true/false
     */
	public static boolean validateSoAm(int so) {
		if (so < 0)
			return true;
		return false;
	}
	
    /**
     * validate space
     * @param str
     * @return true/false
     */
	public static boolean validateSpace(String str) {
		if (str == null ) {
			return false;
		} else {
			return "".equals(str.trim());
		}
	}
    
	/**
	 * validate ngày 
	 * @param strDate1
	 * @param strDate2
	 * @return true/false
	 */
	public static boolean validateDate(String strDate1, String strDate2) {
		if (strDate1 == null || strDate2 == null) {
			return true;
		}
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		formatDate.setLenient(false);
		try {
			Date date1 = formatDate.parse(strDate1);
			Date date2 = formatDate.parse(strDate2);
			if (date1.after(date2)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return true;
		}
	}
    
	/**
	 * validate ngày
	 * @param strDate
	 * @return true/false
	 */ 
	public static boolean validateDate(String strDate) {
		if (strDate == null) {
			return true;
		}
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		formatDate.setLenient(false);
		try {
			formatDate.parse(strDate);
		} catch (ParseException e) {
			return true;
		}
		return false;
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
	 * validate so
	 * @param str
	 * @return true/false
	 */
	public static boolean validateNumber(String str) {
		try {
			Double.parseDouble(str);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}

	}
    
	/**
	 * Kiểm tra ngay có lớn hơn ngày hiện tại không?
	 * 
	 * @param strNgayKiemTra
	 * @return true: Nếu <= ngày hiện tại false : Nếu > ngày hiện tại
	 */
	public static boolean ktLonHonNgayHienTai(String strNgayKiemTra) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date ngayKiemTra = formatDate.parse(strNgayKiemTra);
			Calendar calendarKiemTra = Calendar.getInstance();
			calendarKiemTra.setTime(ngayKiemTra); 
			Date ngayHienTai = new Date();
			Calendar calendarHienTai = Calendar.getInstance();
			calendarHienTai.setTime(ngayHienTai);
			return calendarKiemTra.before(calendarHienTai);
		} catch (ParseException e) {
			return false;
		}

	}
    
	/**
	 * validate ngày lớn hơn ngày hiện tại
	 * @param day
	 * @return true/false
	 */
	public static boolean dateLonHonNgayHienTai(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date currentDay = new Date();
			Date dayOfBirth = sdf.parse(day);
			return dayOfBirth.after(currentDay) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
	/**
	 * validate ngày lớn hơn ngày cầm
	 * @param ngayCam
	 * @param ngayTraLai
	 * @return true/false
	 */
	public static boolean dateLonHonNgayThoiGianCam(String ngayCam, String ngayTraLai) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {

			Date dayTraLai = sdf.parse(ngayTraLai);
			Date dayCam = sdf.parse(ngayCam);

			return dayTraLai.after(dayCam) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static String formatMoney(double money){
		  //return String.format("%,.0f", money);
		  String tmp=NumberFormat.getCurrencyInstance(new Locale("vi")).format(money);
		  return tmp.substring(2,tmp.length()-2);
		 }
	
	public static String formatPrice(int number){
		NumberFormat formatter = new DecimalFormat("###,###");
        String resp = formatter.format(number);
        resp = resp.replaceAll(",", ".");
		
		return resp;
	}
	public static void main(String[] args) {

	}

	public static String formatFromStringToInt(String giaRaoBanString) {
		if(giaRaoBanString == null || giaRaoBanString.trim().length() == 0){
			return "0";
		}else{
			giaRaoBanString = giaRaoBanString.replace(".", "");
			return giaRaoBanString;
		}
	}
}
