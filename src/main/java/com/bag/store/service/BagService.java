package com.bag.store.service;

import java.util.List;

import com.bag.store.dao.BagDAO;
import com.bag.store.dao.BagTypeDAO;
import com.bag.store.dto.BagDTO;
import com.bag.store.util.DBUtil;

public class BagService {
private BagDAO bagDAO;

public BagService(BagDAO bagDAO) {
	this.bagDAO = bagDAO;
}

public String findByName(int id) throws Exception {
	DBUtil dbutil = new DBUtil(); 
	BagTypeDAO bagTypeDAO = new BagTypeDAO(dbutil);
	return bagTypeDAO.findNameById(id);
}
public int insert(BagDTO bagDTO) throws Exception {
	return bagDAO.insertBag(bagDTO);
}
public List<BagDTO> findByBagTypeID(int BagTypeId) throws Exception{
	return bagDAO.findByBagTypeId(BagTypeId);
}

public List<BagDTO> search(String keyword) throws Exception{
	return bagDAO.search(keyword);
}

public List<BagDTO> findAll() throws Exception{
	return bagDAO.findAll();
}
}
