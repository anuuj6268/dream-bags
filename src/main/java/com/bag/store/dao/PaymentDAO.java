//package com.book.store.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//import java.time.LocalDateTime;
//import java.sql.Timestamp;
//import com.book.store.dto.PaymentDTO;
//import com.book.store.dto.PaymentDTO;
//import com.book.store.util.DBUtil;
//
//public class PaymentDAO {
//
//	private final String Q_INSERT = "insert into payment (payment_id,date_and_time,amount) values(?,?,?)";
//	private final String Q_DELETE = "delete from payment where id = ?";
//	private final String Q_FINDALL= "select * from payment";
//	private final String Q_FIND_BY_ID = "select * from payment where id = ?";
//	private final String Q_UPDATE = "update payment set payment_id = ?,date_and_time = ?,amount = ? where id = ?";
//	private DBUtil dbUtil;
//
//	public PaymentDAO(DBUtil dbUtil) {
//		this.dbUtil = dbUtil;
//	}
//
//	public int insert(PaymentDTO paymentDTO) throws Exception {
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//
//	try {
//		 connection = dbUtil.getConnection();
//
//		pstmt = connection.prepareStatement(Q_INSERT);
//		pstmt.setInt(1, paymentDTO.getPaymentTypeId());
//		pstmt.setInt(2, paymentDTO.getDateAndTime());
//		pstmt.setString(3, paymentDTO.getHouseNumber());
//		pstmt.setString(4, paymentDTO.getLandmark());
//		pstmt.setInt(5, paymentDTO.getPincodeId());
//
//
//		return pstmt.executeUpdate();
//	}
//	catch(Exception e) {
//		e.printStackTrace();
////		throw e;
////	}
////	finally {
////		dbUtil.close(connection,pstmt);
////	}
////	}
////	public int update(PaymentDTO paymentDTO) throws Exception {
////		Connection connection = null;
////		PreparedStatement pstmt = null;
////		try {
////			connection = dbUtil.getConnection();
////			pstmt = connection.prepareStatement(Q_UPDATE_BY_ID);
////			pstmt.setString(1, paymentDTO.getHouseNumber());
////			pstmt.setString(2,paymentDTO.getLandmark());
////			pstmt.setInt(3, paymentDTO.getPincodeId());
////			pstmt.setInt(4, paymentDTO.getId());
////			return pstmt.executeUpdate();
////		}catch (Exception e) {
////		e.printStackTrace();
////		throw e;
////		}finally {
////			dbUtil.close(connection,pstmt);
////		}
////	}
////	public int deleteById(int id) throws Exception {
////		Connection connection = null;
////		PreparedStatement pstmt = null;
////		try {
////			connection = dbUtil.getConnection();
////			pstmt = connection.prepareStatement(Q_DELETE_BY_ID);
////			pstmt.setInt(1, id);
////			return pstmt.executeUpdate();
////		}catch (Exception e) {
////		e.printStackTrace();
////		throw e;
////		}
////	}
////
////	public PaymentDTO find_by_id(int id) throws Exception {
////		Connection connection = null;
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////		PaymentDTO paymentDTO = null;
////		try {
////			connection = dbUtil.getConnection();
////			pstmt = connection.prepareStatement(Q_FIND_BY_ID);
////			pstmt.setInt(1, id);
////			rs = pstmt.executeQuery();
////			if(rs.next()) {
////				paymentDTO = new PaymentDTO();
////				paymentDTO.setId(rs.getInt("id"));
////				paymentDTO.setUserId(rs.getInt("user_id"));
////				paymentDTO.setHouseNumber(rs.getString("house_number"));
////				paymentDTO.setLandmark(rs.getString("landmark"));
////				paymentDTO.setPincodeId(rs.getInt("pincode_id"));
////			}
////			return paymentDTO;
////		}catch(Exception e) {
////			e.printStackTrace();
////			throw e;
////		}finally {
////			dbUtil.close(connection,pstmt,rs);
////		}
////	}
////
////	public List<PaymentDTO> findAll() throws Exception{
////		Connection connection = null;
////		PreparedStatement pstmt = null;
////		ResultSet rs = null;
////		PaymentDTO paymentDTO = null;
////		try {
////			connection = dbUtil.getConnection();
////			pstmt = connection.prepareStatement(Q_FIND_ALL);
////			rs = pstmt.executeQuery();
////			List<PaymentDTO> paymentDTOList = new ArrayList<>();
////			while(rs.next()) {
////				paymentDTO = new PaymentDTO();
////				paymentDTO.setId(rs.getInt("id"));
////				paymentDTO.setUserId(rs.getInt("user_id"));
////				paymentDTO.setHouseNumber(rs.getString("house_number"));
////				paymentDTO.setLandmark(rs.getString("landmark"));
////				paymentDTO.setPincodeId(rs.getInt("pincode_id"));
////				paymentDTOList.add(paymentDTO);
////			}
////			return paymentDTOList;
////		}catch(Exception e) {
////			e.printStackTrace();
////			throw e;
////		}finally {
////			dbUtil.close(connection,pstmt,rs);
////		}
////
////	}
////
////
////
////
////
////
////}
//package com;
//
//
