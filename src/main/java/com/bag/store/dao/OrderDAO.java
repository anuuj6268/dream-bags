package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.OrderDTO;
import com.bag.store.util.DBUtil;

public class OrderDAO {
private final String Q_INSERT = "insert into orders (payment_id,user_id) values(?,?)";
private final String Q_UPDATE = "update orders set payment_id = ?,user_id = ? where id = ?";
private final String Q_DELETE = "delete from orders where id = ?";
private final String Q_FIND_BY_ID = "select * from orders where id = ?";
private final String Q_FINDALL = "select * from orders";
private DBUtil dbUtil;

public OrderDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}

public int insert(OrderDTO orderDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;

try {
	 connection = dbUtil.getConnection();

	pstmt = connection.prepareStatement(Q_INSERT);
	pstmt.setInt(1, orderDTO.getPayment_id());
	pstmt.setInt(2, orderDTO.getUser_id());


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
public int update(OrderDTO orderDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_UPDATE);
		pstmt.setInt(1,orderDTO.getPayment_id());
		pstmt.setInt(2,orderDTO.getUser_id());
		pstmt.setInt(3, orderDTO.getId());
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
public OrderDTO find_by_id(int id) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	OrderDTO orderDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_BY_ID);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			orderDTO = new OrderDTO();
			orderDTO.setId(rs.getInt("id"));
			orderDTO.setPayment_id(rs.getInt("payment_id"));
			orderDTO.setUser_id(rs.getInt("user_id"));}
		return orderDTO;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}
}
public List<OrderDTO> findAll() throws Exception{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	OrderDTO orderDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FINDALL);
		rs = pstmt.executeQuery();
		List<OrderDTO> orderDTOList = new ArrayList<>();
		while(rs.next()) {
			orderDTO = new OrderDTO();
			orderDTO.setId(rs.getInt("id"));
			orderDTO.setPayment_id(rs.getInt("payment_id"));
			orderDTO.setUser_id(rs.getInt("user_id"));
			orderDTOList.add(orderDTO);
		}
		return orderDTOList;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}

}



}
