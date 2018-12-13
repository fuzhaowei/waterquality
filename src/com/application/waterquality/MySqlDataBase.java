package com.application.waterquality;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDataBase {

	private static final String URL = "jdbc:mysql://192.168.150.105:3306/project_20181204";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection connent() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	private void unConnent(Connection connection) throws SQLException {
		if (null != connection && connection.isClosed()) {
			connection.close();
		}
	}

	/**
	 * ���ݲ�ѯ
	 * 
	 * @param sql       ��ѯ���
	 * @param presenter ��ѯ����ص�
	 */
	public void search(String sql, SearchDataBasePresenter presenter) {
		Connection connection = null;
		Statement ment = null;
		ResultSet set = null;
		try {
			connection = connent();
			ment = connection.createStatement();
			set = ment.executeQuery(sql);
			presenter.setFruit(set);
		} catch (SQLException e) {
			presenter.error(e.toString());
		} finally {
			try {
				if (null != set && set.isClosed()) {
					set.close();
				}
				if (null != ment && ment.isClosed()) {
					ment.close();
				}
				unConnent(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * �������ݿ�:���
	 * 
	 * @param sql       ������ݿ����
	 * @param presenter ��ӽ����ѯ
	 */
	public void operate(String sql, OperateDataBasePresenter presenter) {
		Connection connection = null;
		PreparedStatement ment = null;
		try {
			connection = connent();
			ment = connection.prepareStatement(sql);
			presenter.add(ment);
		} catch (SQLException e) {
			e.printStackTrace();
			presenter.error(e.toString());
		} finally {
			try {
				if (null != ment && ment.isClosed()) {
					ment.close();
				}
				unConnent(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}