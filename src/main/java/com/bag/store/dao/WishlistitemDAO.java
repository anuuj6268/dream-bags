package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.WishListItemDTO;
import com.bag.store.util.DBUtil;

public class WishlistitemDAO {

	private final String Q_INSERT = "insert into wishlist_item (wishlist_id,bag_id) value (?,?)";
	private final String Q_UPDATE = "update wishlist_item set wishlist_id = ?,bag_id = ? where id = ?";
	private final String Q_DELETE = "delete from wishlist_item where id = ?";
	private final String Q_FIND_BY_ID = "select * from wishlist_item where id = ?";
	private final String Q_FINDALL = "select * from wishlist_item";
	private DBUtil dbUtil;
	public WishlistitemDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public int insert(WishListItemDTO wishListItemDTO) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;

	try {
		 connection = dbUtil.getConnection();

		pstmt = connection.prepareStatement(Q_INSERT);
		pstmt.setInt(1, wishListItemDTO.getWishListId());
		pstmt.setInt(2, wishListItemDTO.getBagId());
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

	public int update(WishListItemDTO wishListItemDTO) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_UPDATE);
			pstmt.setInt(1, wishListItemDTO.getWishListId());
			pstmt.setInt(2, wishListItemDTO.getBagId());
			pstmt.setInt(3, wishListItemDTO.getId());

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
			pstmt = connection.prepareStatement(Q_DELETE);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		}catch (Exception e) {
		e.printStackTrace();
		throw e;
		}
	}

	public WishListItemDTO find_by_id(int id) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WishListItemDTO wishListItemDTO = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				wishListItemDTO = new WishListItemDTO();
				wishListItemDTO.setId(rs.getInt("id"));
				wishListItemDTO.setWishListId(rs.getInt("wishlist_id"));
				wishListItemDTO.setBagId(rs.getInt("bag_id"));
			}

			return wishListItemDTO;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			dbUtil.close(connection,pstmt,rs);
		}
	}

	public List<WishListItemDTO> findAll() throws Exception{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WishListItemDTO wishListItemDTO = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FINDALL);
			rs = pstmt.executeQuery();
			List<WishListItemDTO> wishListItemDTOList = new ArrayList<>();
			while(rs.next()) {
				wishListItemDTO = new WishListItemDTO();
				wishListItemDTO.setId(rs.getInt("id"));
				wishListItemDTO.setWishListId(rs.getInt("wishlist_id"));
				wishListItemDTO.setBagId(rs.getInt("bag_id"));
				wishListItemDTOList.add(wishListItemDTO);
			}
			return wishListItemDTOList;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			dbUtil.close(connection,pstmt,rs);
		}

	}



}
