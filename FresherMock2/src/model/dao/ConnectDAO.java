package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import common.ProgramException;
import common.SystemException;

/**
 * ConnectDAO.java
 *
 * Version 1.0
 *
 * Date: 19-07-2016
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * -----------------------------------------------------------------------
 * 19-07-2016 NguyetNT6 Create
 */

public class ConnectDAO {
	private Connection connect;
	private Statement statement;

	/**
	 * mở kết nối database
	 */
	public void openConnection() throws SystemException, ProgramException {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost;databaseName=FresherMock2";
			String user = "sa";
			String pass = "12345678";

			connect = DriverManager.getConnection(url, user, pass);
			statement = connect.createStatement();
			System.out.println("Kết nối thành công !");
		} catch (SQLException e) {
			throw new SystemException("Connection error");
		} catch (ClassNotFoundException e) {
			throw new ProgramException("Connection error");
		}
	}

	/**
	 * đóng kết nối database
	 */
	public void closeConnection() {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getStatement
	 * 
	 * @return Statement
	 */
	public Statement getStatement() {
		return this.statement;
	}

	/**
	 * get PreparedStatement
	 * 
	 * @param sql
	 * @return PreparedStatement
	 */
	public PreparedStatement getPreparedStatement(String sql) throws SystemException {
		try {
			return connect.prepareStatement(sql);
		} catch (SQLException e) {
			throw new SystemException("Connection error");
		}
	}

	/**
	 * getConnect
	 * 
	 * @return Connection
	 */
	public Connection getConnect() {
		return this.connect;
	}

	public static void main(String[] agrs) {
		ConnectDAO con= new ConnectDAO();
		try {
			con.openConnection();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProgramException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
