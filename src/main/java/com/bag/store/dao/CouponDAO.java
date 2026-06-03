package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.CouponDTO;
import com.bag.store.util.DBUtil;

public class CouponDAO {
private final String Q_INSERT = "insert into coupon_code (code,off) values(?,?)";
private final String Q_DELETE_BY_ID = "delete from coupon_code where id = ?";
private final String Q_FIND_BY_ID = "select * from coupon_code where id = ?";
private final String Q_FINDALL = "select * from coupon_code";
private final String Q_UPDATE = "update coupon_code set code = ?,off = ? where id = ?";
private DBUtil dbUtil;

public CouponDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}
public int insert(CouponDTO couponDTO) throws Exception {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_INSERT);
		pstmt.setString(1,couponDTO.getCode());
		pstmt.setInt(2, couponDTO.getOff());

		 return pstmt.executeUpdate();

	 }catch(Exception e) {
		 e.printStackTrace();
		 throw e;
	 }finally {
		 dbUtil.close(connection,pstmt);
	 }
}

public int update(CouponDTO couponDTO) throws Exception {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_INSERT);
		pstmt.setString(1,couponDTO.getCode());
		pstmt.setInt(2, couponDTO.getOff());
		pstmt.setInt(3, couponDTO.getId());
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
		 pstmt = connection.prepareStatement(Q_DELETE_BY_ID);
		 pstmt.setInt(1, id);
		 return pstmt.executeUpdate();
	 }catch(Exception e) {
		 e.printStackTrace();
		 throw e;
	 }finally {
		dbUtil.close(connection,pstmt);
	 }
}

public CouponDTO findById(int id) throws Exception {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 try {
		 connection = dbUtil.getConnection();
		 pstmt = connection.prepareStatement(Q_FIND_BY_ID);
		 pstmt.setInt(1,id);
		 rs = pstmt.executeQuery();
		 CouponDTO couponDTO = null;
		 while(rs.next()) {
			 couponDTO = new CouponDTO();
			 couponDTO.setId(rs.getInt("id"));
			 couponDTO.setCode(rs.getString("code"));
			 couponDTO.setOff(rs.getInt("off"));
			}
		 return couponDTO;
	 }catch(Exception e) {
		 e.printStackTrace();
		 throw e;
	 }finally {
		 dbUtil.close(connection,pstmt,rs);
	 }
}

public List<CouponDTO> findAll() throws Exception{
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 CouponDTO couponDTO = null;
	 try {
		 connection = dbUtil.getConnection();
		 pstmt = connection.prepareStatement(Q_FINDALL);
		 rs = pstmt.executeQuery();
		 List<CouponDTO> couponDTOList = new ArrayList<>();
		 while(rs.next()) {
		 couponDTO = new CouponDTO();
		 couponDTO.setId(rs.getInt("id"));
		 couponDTO.setCode(rs.getString("code"));
		 couponDTO.setOff(rs.getInt("off"));
		 couponDTOList.add(couponDTO);
		 }
		 return couponDTOList;

	 }catch(Exception e) {

	 e.printStackTrace();
	 throw e;
}finally {
	 dbUtil.close(connection, pstmt, rs);
}
}

}
