package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.security.Timestamp;
import com.bag.store.dto.PaymentDTO;
import com.bag.store.util.DBUtil;

public class PaymentDAO {
private final String Q_INSERT = "insert into payment (payment_type_id,date_and_time,amount) values(?,?,?)";
private final String Q_UPDATE = "update payment set payment_type_id = ?,date_and_time = ?,amount = ? where id = ?";
private final String Q_DELETE = "delete from payment where id = ?";
private final String Q_FIND_BY_ID = "select * from payment where id = ?";
private final String Q_FINDALL = "select * from payment";
private DBUtil dbUtil;

public PaymentDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}

public int insert(PaymentDTO paymentDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;

try {
	 connection = dbUtil.getConnection();

	pstmt = connection.prepareStatement(Q_INSERT);
	pstmt.setInt(1,paymentDTO.getPaymentTypeId());
	pstmt.setTimestamp(2, paymentDTO.getDateAndTime());
	pstmt.setDouble(3, paymentDTO.getAmount());

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
public int update(PaymentDTO paymentDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_UPDATE);
		pstmt.setInt(1,paymentDTO.getPaymentTypeId());
		pstmt.setTimestamp(2, paymentDTO.getDateAndTime());
		pstmt.setDouble(3, paymentDTO.getAmount());
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
public PaymentDTO find_by_id(int id) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PaymentDTO paymentDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_BY_ID);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			paymentDTO = new PaymentDTO();
			paymentDTO.setId(rs.getInt("id"));
			paymentDTO.setAmount(rs.getDouble("amount"));
			paymentDTO.setPaymentTypeId(rs.getInt("payment_type_id"));
			paymentDTO.setDateAndTime(rs.getTimestamp("date_and_time"));
			}
		return paymentDTO;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}
}
public List<PaymentDTO> findAll() throws Exception{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PaymentDTO paymentDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FINDALL);
		rs = pstmt.executeQuery();
		List<PaymentDTO> paymentDTOList = new ArrayList<>();
		while(rs.next()) {
			paymentDTO = new PaymentDTO();
			paymentDTO.setId(rs.getInt("id"));
			paymentDTO.setAmount(rs.getDouble("amount"));
			paymentDTO.setDateAndTime(rs.getTimestamp("date_and_time"));
			paymentDTO.setPaymentTypeId(rs.getInt("payment_type_id"));
			paymentDTOList.add(paymentDTO);
		}
		return paymentDTOList;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}

}



}
