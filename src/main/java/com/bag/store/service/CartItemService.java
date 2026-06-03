package com.bag.store.service;

import com.bag.store.dao.CartItemDAO;
import com.bag.store.dto.CartItemDTO;

public class CartItemService {
private CartItemDAO cartItemDAO;
public CartItemService(CartItemDAO cartItemDAO) {
	this.cartItemDAO = cartItemDAO;
}

public void insert(CartItemDTO cartItemDTO) throws Exception {
	cartItemDAO.insert(cartItemDTO);
}

}
