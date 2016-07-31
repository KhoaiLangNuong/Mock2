package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.bean.Studio;

public class ListUpdateStudioDAO extends ConnectDAO {

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
			PreparedStatement pstmtUpdate = getPreparedStatement(sqlUpdate);
			for (int i = 0; i < listStudioUpdate.size(); i++) {
				if ("C".equals(listStudioUpdate.get(i).getAction())) {
					pstmtUpdate.setString(1, listStudioUpdate.get(i).getSysfiData());
					pstmtUpdate.setString(2, listStudioUpdate.get(i).getSysfiKey());
					pstmtUpdate.addBatch();
				}

			}
			pstmtUpdate.executeBatch();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
			for (int i = 0; i < listStudioDelete.size(); i++) {
				if ("D".equals(listStudioDelete.get(i).getAction())) {
					pstmtDelete.setString(1, listStudioDelete.get(i).getSysfiKey());
					pstmtDelete.addBatch();
				}
			}
			pstmtDelete.executeBatch();
			return true;
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
}
