package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.bean.Studio;

/**
 * StudioDAO.java
 *
 * Version 1.0
 *
 * Date: 29/07/2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 29/07/2016       	NguyetNT6       Create
 */
public class StudioDAO extends ConnectDAO {
	
	/**
	 * sign up list data
	 * @param listStudio
	 * @return true/false
	 */
	public boolean signUp(ArrayList<Studio> listStudio) {

		String sql1 = "INSERT INTO AUTSYSFI(SYSFI_KEY,SYSFI_DATA) VALUES(?,?)";
		PreparedStatement pst1;
		try {
			openConnection();
			pst1 = getConnect().prepareStatement(sql1);
			for (int i = 0; i < listStudio.size(); i++) {
				pst1.setString(1, listStudio.get(i).getSysfiKey());
				pst1.setString(2, listStudio.get(i).getSysfiData());
				pst1.addBatch();
			}
			int[] a = pst1.executeBatch();
			return (a != null ? true : false);

		} catch (Exception e) {
			return false;
		} finally{
			closeConnection();
		}
	}

	
	/**
	 * check sysfiKey exist
	 * @param sysfiKey
	 * @return true/false
	 */
	public boolean checkKeyExist(String sysfiKey) {
		try {
			openConnection();
			String sql = "SELECT SYSFI_KEY,SYSFI_DATA FROM AUTSYSFI WHERE SYSFI_KEY = ?";
			PreparedStatement pstmt = getPreparedStatement(sql);
			pstmt.setString(1, sysfiKey);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			
		}
		finally {
			closeConnection();
		}
		return false;
	}
}