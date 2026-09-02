package com.bag.store.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.AddressDTO;
import com.bag.store.util.DBUtil;

public class AddressDAO {
private final String Q_INSERT = "insert into address (user_id,house_number,landmark,pincode_id,mobile) values (?,?,?,?,?)";
private final String Q_DELETE_BY_ID = "delete from address where id = ? ";
private final String Q_UPDATE_BY_ID = "update address set house_number = ?,landmark = ?,pincode_id = ?,mobile = ? where id = ?";
private final String Q_FIND_BY_ID   = "select * from address where id = ?";
private final String Q_FIND_ALL   = "select * from address";
private final String Q_FIND_BY_USER_ID = "select * from address where user_id = ?";

private DBUtil dbUtil;

public AddressDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}
public int insert(AddressDTO addressDTO) throws Exception {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
try {
	 connection = dbUtil.getConnection();

	   pstmt = connection.prepareStatement(
		        Q_INSERT,
		        Statement.RETURN_GENERATED_KEYS
		    );
	pstmt.setInt(1, addressDTO.getUserId());
	pstmt.setString(2, addressDTO.getHouseNumber());
	pstmt.setString(3, addressDTO.getLandmark());
	pstmt.setInt(4, addressDTO.getPincodeId());
	pstmt.setString(5, addressDTO.getMobile());
	pstmt.executeUpdate();

     rs = pstmt.getGeneratedKeys();

    if (rs.next()) {
        return rs.getInt(1);
    }
    return 0;
}
catch(Exception e) {
	e.printStackTrace();
	throw e;
}
finally {
	dbUtil.close(connection,pstmt,rs);
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
		pstmt.setString(5, addressDTO.getMobile());
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
			addressDTO.setMobile(rs.getString("mobile"));

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
			addressDTO.setMobile(rs.getString("mobile"));

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

public List<AddressDTO> findByUserId(int id) throws Exception{
Connection connection = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
AddressDTO addressDTO = null;
	try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_FIND_BY_USER_ID);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		ArrayList<AddressDTO> list = new ArrayList<>();
		while(rs.next()) {
			addressDTO = new AddressDTO();
			addressDTO.setId(rs.getInt("id"));
			addressDTO.setUserId(rs.getInt("user_id"));
			addressDTO.setHouseNumber(rs.getString("house_number"));
			addressDTO.setLandmark(rs.getString("landmark"));
			addressDTO.setPincodeId(rs.getInt("pincode_id"));
			addressDTO.setMobile(rs.getString("mobile"));

			list.add(addressDTO);	
		}
		return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			dbUtil.close(connection, pstmt, rs);
		}

}



}
