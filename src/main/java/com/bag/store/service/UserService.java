package com.bag.store.service;
import com.bag.store.dao.UserDAO;
import com.bag.store.dto.UserDTO;

public class UserService {
private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public int insert(UserDTO userDTO) throws Exception {
		int count = userDAO.insert(userDTO);
		return count;
	}
	public UserDTO login(String email,String password) throws Exception{
		UserDTO userDTO = userDAO.login(email, password);
		return userDTO;
	}
	public int updateByID(UserDTO userDTO) throws Exception {
		return userDAO.update(userDTO);
	}
}
