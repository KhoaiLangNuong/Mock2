package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ProgramException;
import common.SystemException;
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

	public boolean signUp(ArrayList<Studio> listStudio) throws SystemException, ProgramException {

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

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("Error!");
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new ProgramException("Error!");
		} finally {
			closeConnection();
		}
	}

	public boolean checkKeyExist(String sysfiKey) {
		try {
			openConnection();
			String sql = "SELECT SYSFI_KEY,SYSFI_DATA FROM AUTSYSFI WHERE SYSFI_KEY = ?";
			PreparedStatement pstmt = getPreparedStatement(sql);
			pstmt.setString(1, sysfiKey);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		return false;
	}
}