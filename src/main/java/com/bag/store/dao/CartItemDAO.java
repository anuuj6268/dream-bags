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
private final String Q_FIND_BY_CART_ID_AND_BAG_ID = "select * from cart_item where cart_id = ? and bag_id = ?";
private final String Q_FINDALL = "select * from cart_item";
private final String Q_FIND_BY_CART_ID = "select * from cart_item where cart_id = ?";
private final String Q_UPDATE_QUANTITY =  "update cart_item set quantity = ? where id = ? ";
private final String Q_UPDATE = "update cart_item set cart_id = ?,bag_id = ? , quantity = ? where id = ?";
private final String Q_GET_QUANTITY = "select quantity from cart_item where id = ?";
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


public int getQuantity(int cartItemId) throws Exception {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		conn = dbUtil.getConnection();
		pstmt = conn.prepareStatement(Q_GET_QUANTITY);
		pstmt.setInt(1, cartItemId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("quantity");
		}
		return 0;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}
	finally {
		dbUtil.close(conn,pstmt,rs);
	}
}





public int updateQuantity(int cartItemId,int quantity) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_UPDATE_QUANTITY);
		pstmt.setInt(1, quantity);
		pstmt.setInt(2, cartItemId);
		return pstmt.executeUpdate();
		}catch(Exception e){
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
			pstmt = connection.prepareStatement(Q_UPDATE);
			pstmt.setInt(1, cartItemDTO.getCart_id());
			pstmt.setInt(2, cartItemDTO.getBag_id());
			pstmt.setInt(3, cartItemDTO.getQuantity());
			pstmt.setInt(4, cartItemDTO.getId());
		
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

	public CartItemDTO findByCartIdAndBagId(int cartId,int bagId) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FIND_BY_CART_ID_AND_BAG_ID);
			 pstmt.setInt(1,cartId);
			 pstmt.setInt(2, bagId);
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

	

	
	
	
	public List<CartItemDTO> findCartItemAll(int cartId) throws Exception{
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 CartItemDTO cartItemDTO = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FIND_BY_CART_ID);
			 pstmt.setInt(1, cartId);
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
