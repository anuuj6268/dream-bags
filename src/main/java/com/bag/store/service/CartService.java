package com.bag.store.service;

import com.bag.store.dao.CartDAO;
import com.bag.store.dto.CartDTO;

public class CartService {
private CartDAO cartDAO;




public CartService(CartDAO cartDAO) {
	this.cartDAO = cartDAO;
}

public void insert(CartDTO cartDTO) throws Exception {
	cartDAO.insert(cartDTO);
}

public int getOrCreateCart(CartDTO cartDTO) throws Exception {
CartDTO existCart = cartDAO.findById(cartDTO.getId());

if(existCart != null) {
	return existCart.getId();
}else {
	 cartDAO.insert(cartDTO);
	 CartDTO newCart = cartDAO.findById(cartDTO.getId());
	 return newCart.getId();
	
}
	
	
}


}
