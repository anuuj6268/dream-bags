package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.CartItemDTO;
import com.bag.store.util.DBUtil;

public class CartItemDAO {
private final String Q_INSERT = "insert into cart_item (cart_id,bag_id,quantity) values (?,?,?)";
private final String Q_DELETE = "delete from cart_item where id = ?";
private final String Q_FIND_BY_ID = "select * from cart_item where id = ?";
private final String Q_FINDALL = "select * from cart_item";
private DBUtil dbUtil;

public CartItemDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}

public int insert(CartItemDTO cartItemDTO) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_INSERT);
			pstmt.setInt(1,cartItemDTO.getCart_id());
			pstmt.setInt(2, cartItemDTO.getBag_id());
			pstmt.setInt(3, cartItemDTO.getQuantity());
			 return pstmt.executeUpdate();

		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection,pstmt);
		 }
	}

	public int update(CartItemDTO cartItemDTO) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_INSERT);
			pstmt.setInt(1, cartItemDTO.getCart_id());
			pstmt.setInt(2, cartItemDTO.getBag_id());
			pstmt.setInt(3, cartItemDTO.getId());
			pstmt.setInt(4, cartItemDTO.getQuantity());
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

	public CartItemDTO findById(int id) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			 pstmt.setInt(1,id);
			 rs = pstmt.executeQuery();
			 CartItemDTO cartItemDTO = null;
			 while(rs.next()) {
				 cartItemDTO = new CartItemDTO();
				 cartItemDTO.setId(rs.getInt("id"));
				 cartItemDTO.setCart_id(rs.getInt("cart_id"));
				 cartItemDTO.setBag_id(rs.getInt("bag_id"));
				 cartItemDTO.setQuantity(rs.getInt("quantity"));
			 }
			 return cartItemDTO;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection,pstmt,rs);
		 }
	}

	public List<CartItemDTO> findAll() throws Exception{
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 CartItemDTO cartItemDTO = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FINDALL);
			 rs = pstmt.executeQuery();
			 List<CartItemDTO> cartItemDTOList = new ArrayList<>();
			 while(rs.next()) {
			 cartItemDTO = new CartItemDTO();
			 cartItemDTO.setId(rs.getInt("id"));
			 cartItemDTO.setCart_id(rs.getInt("cart_id"));
			 cartItemDTO.setBag_id(rs.getInt("bag_id"));
			 cartItemDTO.setQuantity(rs.getInt("quantity"));
			 cartItemDTOList.add(cartItemDTO);
			 }
			 return cartItemDTOList;

		 }catch(Exception e) {

		 e.printStackTrace();
		 throw e;
	}finally {
		 dbUtil.close(connection, pstmt, rs);
	}
	}

}
