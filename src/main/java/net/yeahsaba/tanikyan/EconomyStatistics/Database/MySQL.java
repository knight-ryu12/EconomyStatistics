package net.yeahsaba.tanikyan.EconomyStatistics.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

	public static Connection con;
	public static void connect(String url, String database, String user, String password){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + url + "/" + database, user, password);
			System.out.println("[ES-MySQL] MySQL Connected!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[ES-MySQL] MySQL Connection Failed.");
		}

	}

	public static void close(){
		if(con != null){
			try {
				con.close();
				System.out.println("[ES-MySQL] MySQL Disconnected!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void Update(String qry){
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(qry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet Query(String qry){
		ResultSet rs = null;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(qry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
