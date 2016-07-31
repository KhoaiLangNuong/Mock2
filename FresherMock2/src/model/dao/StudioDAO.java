package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ProgramException;
import common.SystemException;
import model.bean.Studio;

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



}