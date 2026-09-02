package com.bag.store.service;

import com.bag.store.dao.OrderDAO;
import com.bag.store.dto.OrderDTO;
import java.util.*;

public class OrderService {
	private OrderDAO orderDAO;
	public OrderService(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public int insert(OrderDTO orderDTO) throws Exception {
		return orderDAO.insert(orderDTO);
	}
	public int delete(int id) throws Exception {
		return orderDAO.deleteById(id);
	}
	public OrderDTO getOrder(int id) throws Exception {
		return orderDAO.find_by_id(id);
	}
	public List<OrderDTO> getAllOrders() throws Exception{
		return orderDAO.findAll();
	}
}
