package com.bag.store.service;

import com.bag.store.dao.PaymentDAO;
import com.bag.store.dto.PaymentDTO;
import java.util.*;
public class PaymentService {
	private PaymentDAO paymentDAO;

		public PaymentService(PaymentDAO paymentDAO) {
			this.paymentDAO = paymentDAO;
		}
		
		public int insert(PaymentDTO paymentDTO) throws Exception {
			return paymentDAO.insert(paymentDTO);
		}
		
		public int delete(int id) throws Exception {
			return paymentDAO.deleteById(id);
		}
		public PaymentDTO getPaymentById(int id) throws Exception {
			return paymentDAO.find_by_id(id);
		}
		public List<PaymentDTO> getAllPayment() throws Exception{
			return paymentDAO.findAll();
			}
}
