package com.bag.store.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.AddressDTO;
import com.bag.store.util.DBUtil;

public class AddressDAO {
private final String Q_INSERT = "insert into address (user_id,house_number,landmark,pincode_id) values (?,?,?,?)";
private final String Q_DELETE_BY_ID = "delete from address where id = ? ";
private final String Q_UPDATE_BY_ID = "update address set house_number = ?,landmark = ?,pincode_id = ? where id = ?";
private final String Q_FIND_BY_ID   = "select * from address where id = ?";
private final String Q_FIND_ALL   = "select * from address";

private DBUtil dbUtil;

private AddressDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}
public int insert(AddressDTO addressDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;

try {
	 connection = dbUtil.getConnection();

	pstmt = connection.prepareStatement(Q_INSERT);
	pstmt.setInt(1, addressDTO.getUserId());
	pstmt.setString(2, addressDTO.getHouseNumber());
	pstmt.setString(3, addressDTO.getLandmark());
	pstmt.setInt(4, addressDTO.getPincodeId());


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
public int update(AddressDTO addressDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_UPDATE_BY_ID);
		pstmt.setString(1, addressDTO.getHouseNumber());
		pstmt.setString(2,addressDTO.getLandmark());
		pstmt.setInt(3, addressDTO.getPincodeId());
		pstmt.setInt(4, addressDTO.getId());
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
		pstmt = connection.prepareStatement(Q_DELETE_BY_ID);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
	}catch (Exception e) {
	e.printStackTrace();
	throw e;
	}
}

public AddressDTO find_by_id(int id) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	AddressDTO addressDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_BY_ID);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			addressDTO = new AddressDTO();
			addressDTO.setId(rs.getInt("id"));
			addressDTO.setUserId(rs.getInt("user_id"));
			addressDTO.setHouseNumber(rs.getString("house_number"));
			addressDTO.setLandmark(rs.getString("landmark"));
			addressDTO.setPincodeId(rs.getInt("pincode_id"));
		}
		return addressDTO;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}
}

public List<AddressDTO> findAll() throws Exception{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	AddressDTO addressDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_ALL);
		rs = pstmt.executeQuery();
		List<AddressDTO> addressDTOList = new ArrayList<>();
		while(rs.next()) {
			addressDTO = new AddressDTO();
			addressDTO.setId(rs.getInt("id"));
			addressDTO.setUserId(rs.getInt("user_id"));
			addressDTO.setHouseNumber(rs.getString("house_number"));
			addressDTO.setLandmark(rs.getString("landmark"));
			addressDTO.setPincodeId(rs.getInt("pincode_id"));
			addressDTOList.add(addressDTO);
		}
		return addressDTOList;
	}catch(Exception e) {
		e.printStackTrace();
		throw e;
	}finally {
		dbUtil.close(connection,pstmt,rs);
	}

}


}
