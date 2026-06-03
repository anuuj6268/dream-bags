package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.PincodeDTO;
import com.bag.store.util.DBUtil;

public class PincodeDAO {
private final String Q_INSERT = "insert into pincode (city,state) values (?,?)";
private final String Q_UPDATE = "update pincode set city = ? ,state = ? where id = ?";
private final String Q_DELETE_BY_ID = "delete from pincode where id = ?";
private final String Q_FIND_BY_ID = "select * from pincode where id = ?";
private final String Q_FINDALL = "select * from pincode";
private DBUtil dbUtil = null;

public PincodeDAO(DBUtil dbUtil) {
	this.dbUtil = dbUtil;
}
public int insert(PincodeDTO pincodeDTO) throws Exception {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_INSERT);
		pstmt.setString(1,pincodeDTO.getCity());
		pstmt.setString(2, pincodeDTO.getState());

		 return pstmt.executeUpdate();

	 }catch(Exception e) {
		 e.printStackTrace();
		 throw e;
	 }finally {
		 dbUtil.close(connection,pstmt);
	 }
}

public int update(PincodeDTO pincodeDTO) throws Exception {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 try {
		connection = dbUtil.getConnection();
		pstmt = connection.prepareStatement(Q_INSERT);
		pstmt.setString(1,pincodeDTO.getCity());
		pstmt.setString(2, pincodeDTO.getState());
		pstmt.setInt(3, pincodeDTO.getPincode_id());
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

public PincodeDTO findById(int id) throws Exception {
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 try {
		 connection = dbUtil.getConnection();
		 pstmt = connection.prepareStatement(Q_FIND_BY_ID);
		 pstmt.setInt(1,id);
		 rs = pstmt.executeQuery();
		 PincodeDTO pincodeDTO = null;
		 while(rs.next()) {
			 pincodeDTO = new PincodeDTO();
			 pincodeDTO.setCity(rs.getString("city"));
			 pincodeDTO.setState(rs.getString("state"));
			}
		 return pincodeDTO;
	 }catch(Exception e) {
		 e.printStackTrace();
		 throw e;
	 }finally {
		 dbUtil.close(connection,pstmt,rs);
	 }
}

public List<PincodeDTO> findAll() throws Exception{
	 Connection connection = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 PincodeDTO pincodeDTO = null;
	 try {
		 connection = dbUtil.getConnection();
		 pstmt = connection.prepareStatement(Q_FINDALL);
		 rs = pstmt.executeQuery();
		 List<PincodeDTO> pincodeDTOList = new ArrayList<>();
		 while(rs.next()) {
		 pincodeDTO = new PincodeDTO();
		 pincodeDTO.setCity(rs.getString("city"));
		 pincodeDTO.setState(rs.getString("state"));
		 pincodeDTOList.add(pincodeDTO);
		 }
		 return pincodeDTOList;

	 }catch(Exception e) {

	 e.printStackTrace();
	 throw e;
}finally {
	 dbUtil.close(connection, pstmt, rs);
}
}



}
