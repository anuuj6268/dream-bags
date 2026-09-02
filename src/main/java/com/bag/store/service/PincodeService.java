package com.bag.store.service;

import com.bag.store.dao.PincodeDAO;
import com.bag.store.dto.PincodeDTO;

public class PincodeService {

	private PincodeDAO pincodeDAO;
	public PincodeService(PincodeDAO pincodeDAO) {
		this.pincodeDAO = pincodeDAO;
	}
	
	public int insert(PincodeDTO pincodeDTO) throws Exception {
		return pincodeDAO.insert(pincodeDTO);
	}
	public int delete(int id) throws Exception {
		return pincodeDAO.delete(id);
	}
	public PincodeDTO findByPincode(int pincode) throws Exception {
		return pincodeDAO.findByPincode(pincode);
	}
	public int findPincodeId(int pincode) throws Exception {
		PincodeDTO pincodeDTO = findByPincode(pincode);
		int pincodeId = pincodeDTO.getPincode_id();
		return pincodeId;
	}
	
	
}
