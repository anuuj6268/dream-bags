package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.CartDTO;
import com.bag.store.util.DBUtil;

public class CartDAO {
	private final String Q_INSERT = "insert into cart (user_id) values(?)";
	private final String Q_UPDATE = "update cart set user_id = ? where id = ?";
	private final String Q_DELETE = "delete from cart where id = ?";
	private final String Q_FIND_BY_ID = "select * from cart where id = ?";
	private final String Q_FINDALL = "select * from cart";
	private DBUtil dbUtil;

	public CartDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public int insert(CartDTO cartDTO) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_INSERT);
			pstmt.setInt(1,cartDTO.getUser_id());
			 return pstmt.executeUpdate();

		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection,pstmt);
		 }
	}

	public int update(CartDTO cartDTO) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_INSERT);
			pstmt.setInt(1, cartDTO.getUser_id());
			pstmt.setInt(2, cartDTO.getId());
			 return pstmt.executeUpdate();

		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection,pstmt);
		 }
	}
	public int delete(int id) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_DELETE);
			 pstmt.setInt(1, id);
			 return pstmt.executeUpdate();
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			dbUtil.close(connection,pstmt);
		 }
	}

	public CartDTO findById(int id) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			 pstmt.setInt(1,id);
			 rs = pstmt.executeQuery();
			 CartDTO cartDTO = null;
			 while(rs.next()) {
				 cartDTO = new CartDTO();
				 cartDTO.setId(rs.getInt("id"));
				 cartDTO.setUser_id(rs.getInt("user_id"));
				}
			 return cartDTO;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection,pstmt,rs);
		 }
	}

	public List<CartDTO> findAll() throws Exception{
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 CartDTO cartDTO = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FINDALL);
			 rs = pstmt.executeQuery();
			 List<CartDTO> cartDTOList = new ArrayList<>();
			 while(rs.next()) {
			 cartDTO = new CartDTO();
			 cartDTO.setId(rs.getInt("id"));
			 cartDTO.setUser_id(rs.getInt("user_id"));
			 cartDTOList.add(cartDTO);
			 }
			 return cartDTOList;

		 }catch(Exception e) {

		 e.printStackTrace();
		 throw e;
	}finally {
		 dbUtil.close(connection, pstmt, rs);
	}
	}


}
