package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.WishListDTO;
import com.bag.store.util.DBUtil;

public class WishListDAO {
private final String Q_INSERT = "insert wishlist (user_id) values (?)";
private final String Q_UPDATE = "update wishlist set user_id = ? where id = ?";
private final String Q_FIND_BY_ID = "select * from wishlist	where id = ?";
private final String Q_DELETE_BY_ID = "delete from wishlist where id = ?";
private final String Q_FINDALL = "select * from wishlist";
private DBUtil dbUtil;
public WishListDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}
public int insert(WishListDTO wishListDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;

try {
	 connection = dbUtil.getConnection();

	pstmt = connection.prepareStatement(Q_INSERT);
	pstmt.setInt(1, wishListDTO.getUser_id());

	return pstmt.executeUpdate();
}
catch(Exception e) {
	e.printStackTrace();
	throw e;
}
finally {
	dbUtil.close(connection,pstmt);
}
}

public int update(WishListDTO wishListDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_UPDATE);
		pstmt.setInt(1, wishListDTO.getUser_id());
		pstmt.setInt(2, wishListDTO.getId());

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

public WishListDTO find_by_id(int id) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	WishListDTO wishListDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_BY_ID);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			wishListDTO = new WishListDTO();
			wishListDTO.setId(rs.getInt("id"));
			wishListDTO.setUser_id(rs.getInt("user_id"));
		}
		return wishListDTO;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}
}

public List<WishListDTO> findAll() throws Exception{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	WishListDTO wishListDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FINDALL);
		rs = pstmt.executeQuery();
		List<WishListDTO> wishListDTOList = new ArrayList<>();
		while(rs.next()) {
			wishListDTO = new WishListDTO();
			wishListDTO.setId(rs.getInt("id"));
			wishListDTO.setUser_id(rs.getInt("user_id"));
			wishListDTOList.add(wishListDTO);
		}
		return wishListDTOList;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}

}





}
