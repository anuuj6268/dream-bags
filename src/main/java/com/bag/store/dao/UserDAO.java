package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.UserDTO;
import com.bag.store.util.DBUtil;

public class UserDAO {
private final String Q_INSERT = "insert into user(name,mobile_number,email,password) value (?,?,?,?)";
private final String Q_FIND_BY_ID = "select * from user where id = ?";
private final String Q_FIND_ALL = "select * from user";
private final String Q_UPDATE_BY_ID = "update user set name = ?,mobile_number=?,email=? where id = ?";
private final String Q_DELETE_BY_ID = "delete from user where id = ?";
private final String Q_USER_LOGIN = "select * from user where email = ? and password = ?";
private DBUtil dbUtil;

public UserDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}

public int insert(UserDTO userDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;

try {
	 connection = dbUtil.getConnection();

	pstmt = connection.prepareStatement(Q_INSERT,Statement.RETURN_GENERATED_KEYS);
	pstmt.setString(1, userDTO.getName());
	pstmt.setString(2, userDTO.getMobile_number());
	pstmt.setString(3, userDTO.getEmail());
	pstmt.setString(4, userDTO.getPassword());

	int a = pstmt.executeUpdate();
	if(a>0) {
	ResultSet rs = pstmt.getGeneratedKeys();
	if (rs.next()) {
	    int userId = rs.getInt(1);
	    dbUtil.close(rs);
	    return userId;
	}
	}
return 0;
}
catch(Exception e) {
	e.printStackTrace();
	throw e;
}
finally {
	dbUtil.close(connection,pstmt);
}
}

public int update(UserDTO userDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_UPDATE_BY_ID);
		pstmt.setString(1, userDTO.getName());
		pstmt.setString(2, userDTO.getMobile_number());
		pstmt.setString(3, userDTO.getEmail());
		pstmt.setInt(4, userDTO.getId());

		return pstmt.executeUpdate();
	}catch (Exception e) {
	e.printStackTrace();
	throw e;
	}finally {
		dbUtil.close(connection,pstmt);
	}
}
public int deleteById(int id) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_DELETE_BY_ID);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
	}catch (Exception e) {
	e.printStackTrace();
	throw e;
	}
}

public UserDTO find_by_id(int id) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	UserDTO userDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_BY_ID);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			userDTO = new UserDTO();
			userDTO.setId(rs.getInt("id"));
			userDTO.setName(rs.getString("name"));
			userDTO.setMobile_number(rs.getString("mobile_number"));
			userDTO.setEmail(rs.getString("email"));
		}
		return userDTO;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}
}

public List<UserDTO> findAll() throws Exception{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	UserDTO userDTO;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_ALL);
		rs = pstmt.executeQuery();
		List<UserDTO> userDTOList = new ArrayList<>();
		while(rs.next()) {
			userDTO = new UserDTO();
			userDTO.setId(rs.getInt("id"));
			userDTO.setMobile_number(rs.getString("mobile_number"));
			userDTO.setEmail(rs.getString("email"));
			userDTOList.add(userDTO);
		}
		return userDTOList;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}

}

public UserDTO login(String email,String password) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	UserDTO userDTO = null;


	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_USER_LOGIN);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			userDTO = new UserDTO();
			userDTO.setId(rs.getInt("id"));
			userDTO.setName(rs.getString("name"));
			userDTO.setEmail(rs.getString("email"));
			userDTO.setMobile_number(rs.getString("mobile_number"));
		}
		return userDTO;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}
}


}
