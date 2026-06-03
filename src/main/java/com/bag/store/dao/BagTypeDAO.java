package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.BagTypeDTO;
import com.bag.store.util.DBUtil;

public class BagTypeDAO {
	private final String Q_INSERT = "insert into bag_type(name) values(?)";
	private final String Q_UPDATE = "update bag_type set name = ? where id = ?";
	private final String Q_DELETE = "delete from address where id = ?";
	private final String Q_FIND_BY_ID = "select * from bag_type where id = ?";
	private final String Q_FIND_ALL = "select * from bag_type";
	private DBUtil dbutil;
	public BagTypeDAO(DBUtil dbutil) {
		this.dbutil = dbutil;
	}

	public int insertBag(BagTypeDTO bagTypeDTO) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			connection = dbutil.getConnection();
			pstmt = connection.prepareStatement(Q_INSERT);
			pstmt.setString(1,bagTypeDTO.getName());


			 return pstmt.executeUpdate();

		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbutil.close(connection,pstmt);
		 }
}
	public int updateBag(BagTypeDTO bagTypeDTO) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			 connection = dbutil.getConnection();
			 pstmt = connection.prepareStatement(Q_UPDATE);

			 pstmt.setString(1, bagTypeDTO.getName());
			 pstmt.setInt(2, bagTypeDTO.getId());
			 return pstmt.executeUpdate();

		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbutil.close(connection,pstmt);
		 }
}
	 public int deleteBag(int id) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			 connection = dbutil.getConnection();
			 pstmt = connection.prepareStatement(Q_DELETE);
			 pstmt.setInt(1, id);
			 return pstmt.executeUpdate();
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			dbutil.close(connection,pstmt);
		 }
	 }

	 public BagTypeDTO findById(int id) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 connection = dbutil.getConnection();
			 pstmt = connection.prepareStatement(Q_DELETE);
			 pstmt.setInt(1,id);
			 rs = pstmt.executeQuery();
			 BagTypeDTO bagTypeDTO = null;
			 while(rs.next()) {
				 bagTypeDTO = new BagTypeDTO();
				 bagTypeDTO.setId(rs.getInt("id"));
				 bagTypeDTO.setName(rs.getString("name"));
			 }
			 return bagTypeDTO;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbutil.close(connection,pstmt,rs);
		 }
	 }

	 public List<BagTypeDTO> findAll() throws Exception{
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 BagTypeDTO bagTypeDTO = null;
		 try {
			 connection = dbutil.getConnection();
			 pstmt = connection.prepareStatement(Q_FIND_ALL);
			 rs = pstmt.executeQuery();
			 List<BagTypeDTO> bagTypeDTOList = new ArrayList<>();
			 while(rs.next()) {
			 bagTypeDTO = new BagTypeDTO();
			 bagTypeDTO.setId(rs.getInt("id"));
			 bagTypeDTO.setName(rs.getString("name"));
			 bagTypeDTOList.add(bagTypeDTO);
			 }
			 return bagTypeDTOList;

		 }catch(Exception e) {

		 e.printStackTrace();
		 throw e;
	 }finally {
		 dbutil.close(connection, pstmt, rs);
	 }
	 }



    }










