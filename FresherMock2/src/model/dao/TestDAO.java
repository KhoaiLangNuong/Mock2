package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.bean.Studio;

/**
 * ListUpdateStudioDAO.java
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
public class TestDAO extends ConnectDAO {

	public ArrayList<Studio> getListStudio() {
		try {
			openConnection();
			String sql = "select * from AUTSYSFI";
			PreparedStatement pst = getPreparedStatement(sql);
			ArrayList<Studio> listStudio = new ArrayList<Studio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Studio studio = new Studio();
				studio.setSysfiKey(rs.getString(1));
				studio.setSysfiData(rs.getString(2));
				studio.setAction("");
				listStudio.add(studio);
			}
			return listStudio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection();
		}
	}

	public boolean updateData(ArrayList<Studio> listStudioUpdate) {
		try {
			openConnection();
			String sqlUpdate = "update AUTSYSFI set sysfi_data= ? where sysfi_key = ?";
			int countUpdate = 0;
			PreparedStatement pstmtUpdate = getPreparedStatement(sqlUpdate);
			for (int i = 0; i < listStudioUpdate.size(); i++) {
				if ("C".equals(listStudioUpdate.get(i).getAction())) {
					countUpdate ++;
					pstmtUpdate.setString(1, listStudioUpdate.get(i).getSysfiData());
					pstmtUpdate.setString(2, listStudioUpdate.get(i).getSysfiKey());
					pstmtUpdate.addBatch();
				}

			}
			int [] result = pstmtUpdate.executeBatch();
			//dem so cau lenh that bai
			int countRowUpdate = 0; 
			for(int i=0; i<result.length; i++){
				if(result[i] == 0){
					countRowUpdate ++;
				}
			}
			if(countRowUpdate == countUpdate){ //so cau lenh dc cap nhat
				return false;
			}else{
				return true;
			}
			
				
		} catch (Exception e) {
			return false;
		} finally {
			closeConnection();
		}
	}

	public boolean deleteData(ArrayList<Studio> listStudioDelete) {
		try {
			openConnection();
			String sqlDelete = "delete from AUTSYSFI where sysfi_key= ?";
			PreparedStatement pstmtDelete = getPreparedStatement(sqlDelete);
			int countRowDelete = 0;
			for (int i = 0; i < listStudioDelete.size(); i++) {
				if ("D".equals(listStudioDelete.get(i).getAction())) {
					countRowDelete ++;
					pstmtDelete.setString(1, listStudioDelete.get(i).getSysfiKey());
					pstmtDelete.addBatch();
				}
			}
			int [] result = pstmtDelete.executeBatch();
			//dem so cau lenh that bai
			int countRowUpdate = 0; 
			for(int i=0; i<result.length; i++){
				if(result[i] == 0){
					countRowUpdate ++;
				}
			}
			if(countRowUpdate == countRowDelete){ //so cau lenh dc cap nhat
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection();
		}

	}

	public ArrayList<Studio> search(String sysfiKey) {
		
		ArrayList<Studio> listStudio = new ArrayList<Studio>();
		String sql = "SELECT SYSFI_KEY,SYSFI_DATA FROM AUTSYSFI WHERE SYSFI_KEY = ? ";

		try {
			openConnection();
			PreparedStatement pst = getPreparedStatement(sql);
			pst.setString(1, sysfiKey);
			ResultSet rs = pst.executeQuery();
			Studio studio;
			if (rs.next()) {
				studio = new Studio();
				studio.setSysfiKey(rs.getString(1));
				studio.setSysfiData(rs.getString(2));
				listStudio.add(studio);

			}
			return listStudio;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnection();
		}
	}
public void  deleteAllData() {
	try {
		openConnection();
		String sql = "DELETE AUTSYSFI ";
		
		PreparedStatement pstmtDelete = getPreparedStatement(sql);
		pstmtDelete.execute();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeConnection();
	}

		
	}
}
