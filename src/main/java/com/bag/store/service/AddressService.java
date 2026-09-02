package com.bag.store.service;

import com.bag.store.dao.AddressDAO;
import com.bag.store.dto.AddressDTO;
import java.util.*;

public class AddressService {
		private AddressDAO addressDAO;
		private AddressDTO addressDTO;
		
		public AddressService(AddressDAO addressDAO) {
			this.addressDAO = addressDAO;
		}
		
		public int insert(AddressDTO addressDTO) throws Exception {
		return addressDAO.insert(addressDTO);
		}
		
		public int update(AddressDTO addressDTO) throws Exception {
			return addressDAO.update(addressDTO);
		}
		
		public AddressDTO findById(int id) throws Exception {
			return addressDAO.find_by_id(id);
		}

	    public List<AddressDTO> findAll() throws Exception {
	        return addressDAO.findAll();
	    }
	    public List<AddressDTO> findByUserId(int id) throws Exception{
	    	return addressDAO.findByUserId(id);
	    }
}
