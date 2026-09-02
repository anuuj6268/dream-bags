package com.bag.store.service;

import com.bag.store.dao.CartItemDAO;
import com.bag.store.dto.CartDTO;
import com.bag.store.dto.CartItemDTO;
import com.sun.net.httpserver.Request;

public class CartItemService {
private CartItemDAO cartItemDAO;
public CartItemService(CartItemDAO cartItemDAO) {
	this.cartItemDAO = cartItemDAO;
}

public void insert(CartItemDTO cartItemDTO) throws Exception {
	cartItemDAO.insert(cartItemDTO);
}

public void addItemIntoTheCart(int cartId,int bagId) throws Exception {
	CartItemDTO cartItemDTO = (CartItemDTO) cartItemDAO.findByCartIdAndBagId(cartId,bagId);
	if(cartItemDTO != null) {
		int count = cartItemDTO.getQuantity()+1;
		cartItemDTO.setQuantity(count);
		cartItemDAO.update(cartItemDTO);
	}
	else {
	   cartItemDTO = new CartItemDTO();
	   cartItemDTO.setBag_id(bagId);
	   cartItemDTO.setCart_id(cartId);
	   cartItemDTO.setQuantity(1);
	   cartItemDAO.insert(cartItemDTO);
	}
}

public void increase(int cartItemId,int quantity) throws Exception{
	quantity = quantity+1;
	cartItemDAO.updateQuantity(cartItemId, quantity);
}
public void decrease(int cartItemId,int quantity) throws Exception {
if(quantity == 1) {
	remove(cartItemId);
	return;
}
	quantity = quantity-1;
	cartItemDAO.updateQuantity(cartItemId, quantity);
}
public int remove(int cartItemId) throws Exception {
	return cartItemDAO.delete(cartItemId);
}
public int getQuantity(int cartItemId) throws Exception {
    return cartItemDAO.getQuantity(cartItemId);
}



}
