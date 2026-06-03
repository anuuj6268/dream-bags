package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.OrderItemDTO;
import com.bag.store.util.DBUtil;

public class OrderItemDAO {
private final String Q_INSERT = "insert into order_item(order_id,bag_id) values (?,?)";
private final String Q_DELETE = "delete from order_item where id = ?";
private final String Q_UPDATE = "update order_item set order_id = ?,bag_id = ? where id = ?";
private final String Q_FIND_BY_ID = "select * from order_item where id = ?";
private final String Q_FINDALL = "select * from order_item";
private DBUtil dbUtil;

public OrderItemDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}
public int insert(OrderItemDTO orderItemDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;

try {
	 connection = dbUtil.getConnection();

	pstmt = connection.prepareStatement(Q_INSERT);
	pstmt.setInt(1, orderItemDTO.getOrder_id());
	pstmt.setInt(2, orderItemDTO.getBag_id());


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
public int update(OrderItemDTO orderItemDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_UPDATE);
		pstmt.setInt(1,orderItemDTO.getOrder_id());
		pstmt.setInt(2,orderItemDTO.getBag_id());
		pstmt.setInt(3, orderItemDTO.getId());
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

public OrderItemDTO find_by_id(int id) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	OrderItemDTO orderItemDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_BY_ID);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			orderItemDTO = new OrderItemDTO();
			orderItemDTO.setId(rs.getInt("id"));
			orderItemDTO.setOrder_id(rs.getInt("order_id"));
			orderItemDTO.setBag_id(rs.getInt("bag_id"));
	     }
		return orderItemDTO;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}
}

public List<OrderItemDTO> findAll() throws Exception{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	OrderItemDTO orderItemDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FINDALL);
		rs = pstmt.executeQuery();
		List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
		while(rs.next()) {
			orderItemDTO = new OrderItemDTO();
			orderItemDTO.setId(rs.getInt("id"));
			orderItemDTO.setOrder_id(rs.getInt("order_id"));
			orderItemDTO.setBag_id(rs.getInt("bag_id"));
			orderItemDTOList.add(orderItemDTO);
		}
		return orderItemDTOList;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}

}

}
