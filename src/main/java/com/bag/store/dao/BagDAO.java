package com.bag.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dto.BagDTO;
import com.bag.store.util.DBUtil;

public class BagDAO {
	private final String Q_INSERT = "insert into bag(name,bag_type_id,price,material,url) value (?,?,?,?,?)";
	private final String Q_UPDATE = "update bag set set name = ?,bag_type = ?,price = ?,material = ?,url=?";
	private final String Q_DELETE = "delete from bag where id = ?";
	private final String Q_FIND_BY_ID = "select * from bag where id = ?";
	private final String Q_FIND_ALL = "select * from bag";
	private final String Q_FIND_BY_BAGTYPE_ID = "select * from bag where bag_type_id = ?";
	private final String Q_SEARCH = "select b.* from bag b Join bag_type bt on b.bag_type_id = bt.id where b.name like ? or b.material like ? or bt.name like ? ";
	private DBUtil dbUtil;

	 public BagDAO(DBUtil dbUtil) {
		 this.dbUtil = dbUtil;
	 }

	 public int insertBag(BagDTO bagDTO) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_INSERT);
			pstmt.setString(1,bagDTO.getName());
			pstmt.setInt(2, bagDTO.getBagType());
			pstmt.setInt(3, bagDTO.getPrice());
			pstmt.setString(4, bagDTO.getMaterial());
			pstmt.setString(5, bagDTO.getUrl());


			 return pstmt.executeUpdate();

		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection,pstmt);
		 }
}
	 public int updateBag(BagDTO bagDTO) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_UPDATE);

			 pstmt.setString(1, bagDTO.getName());
			 pstmt.setInt(2, bagDTO.getBagType());
			 pstmt.setInt(3, bagDTO.getPrice());
			 pstmt.setString(4, bagDTO.getMaterial());
			 pstmt.setString(5, bagDTO.getUrl());
return pstmt.executeUpdate();

		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection,pstmt);
		 }
	 }

	 public int deleteBag(int id) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_DELETE);
			 pstmt.setInt(1, id);
			 return pstmt.executeUpdate();
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			dbUtil.close(connection,pstmt);
		 }
	 }

	 public BagDTO findById(int id) throws Exception {
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			 pstmt.setInt(1,id);
			 rs = pstmt.executeQuery();
			 BagDTO bagDTO = null;
			 while(rs.next()) {
				 bagDTO = new BagDTO();
				 bagDTO.setId(rs.getInt("id"));
				 bagDTO.setName(rs.getString("name"));
				 bagDTO.setPrice(rs.getInt("price"));
				 bagDTO.setBagType(rs.getInt("bag_type_id"));
				 bagDTO.setMaterial(rs.getString("material"));
				 bagDTO.setUrl(rs.getString("url"));
			 }
			 return bagDTO;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection,pstmt,rs);
		 }
	 }

	 public List<BagDTO> findByBagTypeId(int id) throws Exception{
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 BagDTO bagDTO = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FIND_BY_BAGTYPE_ID);
			 pstmt.setInt(1, id);
			 rs = pstmt.executeQuery();
			 List<BagDTO> bagDTOList = new ArrayList<>();
			 while(rs.next()) {
		     bagDTO = new BagDTO();
			 bagDTO.setId(rs.getInt("id"));
			 bagDTO.setName(rs.getString("name"));
			 bagDTO.setBagType(rs.getInt("bag_type_id"));
			 bagDTO.setPrice(rs.getInt("price"));
			 bagDTO.setMaterial(rs.getString("material"));
			 bagDTO.setUrl(rs.getString("url"));
			 bagDTOList.add(bagDTO);
			 }
			 return bagDTOList;
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;

		 }finally {
			 dbUtil.close(connection, pstmt, rs);
		 }


	 }


	 public List<BagDTO>search(String keyword) throws Exception{
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_SEARCH);
			
			 pstmt.setString(1,"%"+keyword+"%");
			 pstmt.setString(2,"%"+keyword+"%");
			 pstmt.setString(3,"%"+keyword+"%");
			 rs = pstmt.executeQuery();
			 List<BagDTO> list = new ArrayList<>();
			 while(rs.next()) {
				 BagDTO bagDTO = new BagDTO();
				 bagDTO.setId(rs.getInt("id"));
				 bagDTO.setBagType(rs.getInt("bag_type_id"));
				 bagDTO.setMaterial(rs.getString("material"));
				 bagDTO.setName(rs.getString("name"));
				 bagDTO.setPrice(rs.getInt("price"));
				 bagDTO.setUrl(rs.getString("url"));
				 list.add(bagDTO);
			 }
			 return list;
		 
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			 dbUtil.close(connection, pstmt, rs);
		 }
		 
		 
	 }
	 
	 
	 
	 
	 

	 public List<BagDTO> findAll() throws Exception{
		 Connection connection = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 BagDTO bagDTO = null;
		 try {
			 connection = dbUtil.getConnection();
			 pstmt = connection.prepareStatement(Q_FIND_ALL);
			 rs = pstmt.executeQuery();
			 List<BagDTO> bagDTOList = new ArrayList<>();
			 while(rs.next()) {
			 bagDTO = new BagDTO();
			 bagDTO.setId(rs.getInt("id"));
			 bagDTO.setName(rs.getString("Name"));
			 bagDTO.setBagType(rs.getInt("bag_type_id"));
			 bagDTO.setPrice(rs.getInt("price"));
			 bagDTO.setMaterial(rs.getString("Material"));
			 bagDTO.setUrl(rs.getString("url"));
			 bagDTOList.add(bagDTO);
			 }
			 return bagDTOList;

		 }catch(Exception e) {

		 e.printStackTrace();
		 throw e;
	 }finally {
		 dbUtil.close(connection, pstmt, rs);
	 }
	 }


	 }
