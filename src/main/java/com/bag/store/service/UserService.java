package com.bag.store.service;
import com.bag.store.dao.UserDAO;
import com.bag.store.dto.CartDTO;
import com.bag.store.dto.UserDTO;

public class UserService {
private UserDAO userDAO;
private CartService cartService;

	public UserService(UserDAO userDAO,CartService cartService) {
		this.userDAO = userDAO;
		this.cartService = cartService;
	}
	public int insert(UserDTO userDTO) throws Exception {
		int count = userDAO.insert(userDTO);
		cartService.getOrCreateCart(count);
		return count;
	}
	public UserDTO login(String email,String password) throws Exception{
		UserDTO userDTO = userDAO.login(email, password);
		return userDTO;
	}
	public UserDTO findByUserId(int id) throws Exception {
		return userDAO.find_by_id(id);
	}
	public int updateByID(UserDTO userDTO) throws Exception {
		return userDAO.update(userDTO);
	}
}
