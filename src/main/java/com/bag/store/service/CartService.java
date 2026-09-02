package com.bag.store.service;
import java.util.*;

import com.bag.store.dao.BagDAO;
import com.bag.store.dao.CartDAO;
import com.bag.store.dao.CartItemDAO;
import com.bag.store.dto.*;
import com.bag.store.service.*;
public class CartService {
private CartDAO cartDAO;
private CartItemDAO cartItemDAO;
private BagDAO bagDAO;


public CartService(CartDAO cartDAO,CartItemDAO cartItemDAO,BagDAO bagDAO) {
	this.cartDAO = cartDAO;
	this.cartItemDAO = cartItemDAO;
	this.bagDAO = bagDAO;
}

public void insert(CartDTO cartDTO) throws Exception {
	cartDAO.insert(cartDTO);
}

public int getOrCreateCart(int userId) throws Exception {

CartDTO existCart = cartDAO.findByUserId(userId);
CartDTO nonExist;
if(existCart != null) {
	return existCart.getId();
}else {
    nonExist = new CartDTO();
	nonExist.setUser_id(userId);
    cartDAO.insert(nonExist);
	nonExist = (CartDTO) cartDAO.findByUserId(userId);
	return nonExist.getId();
	}

	
}

public int getTotal(List<ViewCartDTO> list) {
int total = 0;
	for(ViewCartDTO dto : list) {
		total = total + (dto.getBagPrice()*dto.getQuantity());
	}
	return total;
}


public List<ViewCartDTO> showCart(UserDTO userDTO) throws Exception{
List<ViewCartDTO> list = new ArrayList<>();
int userId = userDTO.getId();
CartDTO cartDTO = cartDAO.findByUserId(userId);
int cartId = cartDTO.getId();
List<CartItemDTO> cartItemlist = cartItemDAO.findCartItemAll(cartId);
for(CartItemDTO cartItemDTO : cartItemlist) {
	int bagId = cartItemDTO.getBag_id();
	ViewCartDTO viewDTO = new ViewCartDTO();
	BagDTO bagDTO = bagDAO.findById(bagId);
	viewDTO.setBagId(bagDTO.getId());
	viewDTO.setBagName(bagDTO.getName());
	viewDTO.setBagPrice(bagDTO.getPrice());
	viewDTO.setBagURL(bagDTO.getUrl());
	viewDTO.setCartItemId(cartItemDTO.getId());
	viewDTO.setQuantity(cartItemDTO.getQuantity());
	list.add(viewDTO);
}
return list;
}


public List<BagDTO> suggestionList(int bagTypeId, List<ViewCartDTO> cartList) throws Exception {

    List<BagDTO> list = new ArrayList<>();

    List<BagDTO> fetchList = bagDAO.findByBagTypeId(bagTypeId);

    for (BagDTO bagDTO : fetchList) {

        boolean alreadyInCart = false;

        for (ViewCartDTO viewDTO : cartList) {

            if (bagDTO.getId() == viewDTO.getBagId()) {
                alreadyInCart = true;
                break;
            }
        }

        if (!alreadyInCart) {
            list.add(bagDTO);
        }
    }

    return list;
}


}
