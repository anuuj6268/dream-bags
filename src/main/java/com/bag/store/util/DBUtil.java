package com.bag.store.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
public final String URL = "jdbc:mysql://localhost:3306/bag_store";
public final String USERNAME = "root";
public final String PASSWORD = "Anuj#123";
public final String DRIVER = "com.mysql.cj.jdbc.Driver";


public Connection getConnection() throws ClassNotFoundException, SQLException {
	Class.forName(DRIVER);
	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	return connection;
}

public void close(Connection conn,PreparedStatement pstmt,ResultSet rs) throws SQLException {
	if(rs!=null) {
		rs.close();
	}
	if(pstmt!=null) {
		pstmt.close();
	}
	if(conn!=null) {
		conn.close();
	}
}
public void close(Connection conn,PreparedStatement pstmt) throws SQLException {
	close(conn,pstmt,null);
}

public void close(Connection conn,ResultSet rs) throws SQLException {
close(conn,null,rs);
}
public void close(Connection conn) throws SQLException {

close(conn,null,null);
}
public void close(PreparedStatement pstmt) throws SQLException {
	close(null,pstmt,null);
}
public void close(ResultSet rs) throws SQLException {
	close(null,null,rs);
}

public static void main(String[] args) {
	DBUtil dbutil = new DBUtil();
	try {
		dbutil.getConnection();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



}
