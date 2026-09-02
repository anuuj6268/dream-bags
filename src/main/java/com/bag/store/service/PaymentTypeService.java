package com.bag.store.service;

import com.bag.store.dao.PaymentTypeDAO;
import com.bag.store.dto.PaymentTypeDTO;
import com.bag.store.util.DBUtil;

public class PaymentTypeService {
	private PaymentTypeDAO paymentTypeDAO;
	public PaymentTypeService(PaymentTypeDAO paymentTypeDAO) {
		this.paymentTypeDAO = paymentTypeDAO;
	}
	
	public String findName(int id) throws Exception {
		PaymentTypeDTO paymentTypeDTO = paymentTypeDAO.findById(id);
		return paymentTypeDTO.getName();
	}
}
