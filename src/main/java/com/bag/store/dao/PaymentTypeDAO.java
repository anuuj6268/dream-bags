package com.bag.store.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.PaymentTypeDTO;
import com.bag.store.util.DBUtil;

public class PaymentTypeDAO {
		private final String Q_INSERT = "insert into payment_type (payment_type_name) values (?)";
		private final String Q_UPDATE = "update payment_type set payment_type_name = ? where id = ?";
		private final String Q_DELETE_BY_ID = "delete from payment_type where id = ?";
		private final String Q_FIND_BY_ID = "select * from payment_type where id = ?";
		private final String Q_FINDALL = "select * from payment_type";
		private DBUtil dbUtil;

		public PaymentTypeDAO(DBUtil dbUtil) {
			this.dbUtil = dbUtil;
		}

		 public int insert(PaymentTypeDTO paymentTypeDTO) throws Exception {
			 Connection connection = null;
			 PreparedStatement pstmt = null;
			 try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(Q_INSERT);
				pstmt.setString(1,paymentTypeDTO.getName());

				 return pstmt.executeUpdate();

			 }catch(Exception e) {
				 e.printStackTrace();
				 throw e;
			 }finally {
				 dbUtil.close(connection,pstmt);
			 }
	}

		 public int update(PaymentTypeDTO paymentTypeDTO) throws Exception {
			 Connection connection = null;
			 PreparedStatement pstmt = null;
			 try {
				connection = dbUtil.getConnection();
				pstmt = connection.prepareStatement(Q_INSERT);
				pstmt.setString(1,paymentTypeDTO.getName());
				pstmt.setInt(2, paymentTypeDTO.getId());
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

		 public PaymentTypeDTO findById(int id) throws Exception {
			 Connection connection = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
			 try {
				 connection = dbUtil.getConnection();
				 pstmt = connection.prepareStatement(Q_FIND_BY_ID);
				 pstmt.setInt(1,id);
				 rs = pstmt.executeQuery();
				 PaymentTypeDTO paymentTypeDTO = null;
				 while(rs.next()) {
					 paymentTypeDTO = new PaymentTypeDTO();
					 paymentTypeDTO.setId(rs.getInt("id"));
					 paymentTypeDTO.setName(rs.getString("payment_type_name"));
					}
				 return paymentTypeDTO;
			 }catch(Exception e) {
				 e.printStackTrace();
				 throw e;
			 }finally {
				 dbUtil.close(connection,pstmt,rs);
			 }
		 }

		 public List<PaymentTypeDTO> findAll() throws Exception{
			 Connection connection = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
			 PaymentTypeDTO paymentTypeDTO = null;
			 try {
				 connection = dbUtil.getConnection();
				 pstmt = connection.prepareStatement(Q_FINDALL);
				 rs = pstmt.executeQuery();
				 List<PaymentTypeDTO> paymentTypeDTOList = new ArrayList<>();
				 while(rs.next()) {
				 paymentTypeDTO = new PaymentTypeDTO();
				 paymentTypeDTO.setId(rs.getInt("id"));
				 paymentTypeDTO.setName(rs.getString("payment_type_name"));
				 paymentTypeDTOList.add(paymentTypeDTO);
				 }
				 return paymentTypeDTOList;

			 }catch(Exception e) {

			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection, pstmt, rs);
		 }
		 }



}
