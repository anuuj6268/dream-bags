package com.bag.store.service;

import java.util.List;

import com.bag.store.dao.BagDAO;
import com.bag.store.dto.BagDTO;

public class BagService {
private BagDAO bagDAO;

public BagService(BagDAO bagDAO) {
	this.bagDAO = bagDAO;
}


public int insert(BagDTO bagDTO) throws Exception {
	return bagDAO.insertBag(bagDTO);
}
public List<BagDTO> findByBagTypeID(int BagTypeId) throws Exception{
	return bagDAO.findByBagTypeId(BagTypeId);
}

public List<BagDTO> findAll() throws Exception{
	return bagDAO.findAll();
}
}
