package com.bag.store.servlet;

import java.util.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bag.store.dao.AddressDAO;
import com.bag.store.dao.PincodeDAO;
import com.bag.store.dao.UserDAO;
import com.bag.store.dto.AddressDTO;
import com.bag.store.dto.UserDTO;
import com.bag.store.service.AddressService;
import com.bag.store.service.PincodeService;
import com.bag.store.service.UserService;
import com.bag.store.util.DBUtil;

@WebServlet("/AddressServlet")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil dbUtil;
	private AddressService addressService;
	private AddressDAO addressDAO;
	private UserDAO userDAO;
	private UserService userService;
	private PincodeService pincodeService;
	private PincodeDAO pincodeDAO;
	
    public AddressServlet() {
        super(); 
        this.dbUtil = new DBUtil();
        this.addressDAO = new AddressDAO(dbUtil);
        this.addressService = new AddressService(addressDAO);
        this.userDAO = new UserDAO(dbUtil);
        this.userService = new UserService(userDAO, null);
        this.pincodeDAO = new PincodeDAO(dbUtil);
        this.pincodeService = new PincodeService(pincodeDAO);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		if(task.equalsIgnoreCase("showAddress")) {
		try {
			findByUserId(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		if(task.equalsIgnoreCase("saveAddress")) {
			try {
				insert(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void insert(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		String userId = request.getParameter("userId");
		String mobile = request.getParameter("mobile");
		String landmark = request.getParameter("landmark"); 
		String houseNumber = request.getParameter("house_number");
		String pincode = request.getParameter("pincode");
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setHouseNumber(houseNumber);
		addressDTO.setUserId(Integer.parseInt(userId));
		addressDTO.setMobile(mobile);
		addressDTO.setLandmark(landmark);
		int pincodeId = pincodeService.findPincodeId(Integer.parseInt(pincode));
		addressDTO.setPincodeId(pincodeId);
		int addressId = addressService.insert(addressDTO);
		request.getSession().setAttribute("addressId", addressId);
		request.getSession().setAttribute("userId", userId);
		request.getRequestDispatcher("Payment.jsp").forward(request, response);
	}

	public void findByUserId(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception {
		String userId = request.getParameter("userId");
		UserDTO userDTO = userService.findByUserId(Integer.parseInt(userId));
		request.setAttribute("user", userDTO);
		RequestDispatcher rs = request.getRequestDispatcher("address.jsp");
		rs.forward(request, response);
	}
	
	
}
