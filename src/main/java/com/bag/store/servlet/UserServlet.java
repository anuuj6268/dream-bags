package com.bag.store.servlet;

import java.io.IOException;

import com.bag.store.dao.BagDAO;
import com.bag.store.dao.CartDAO;
import com.bag.store.dao.CartItemDAO;
import com.bag.store.dao.UserDAO;
import com.bag.store.dto.CartDTO;
import com.bag.store.dto.UserDTO;
import com.bag.store.service.CartService;
import com.bag.store.service.UserService;
import com.bag.store.util.DBUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DBUtil dbUtil;
	private UserService userService;
	private UserDAO userDAO;

	public UserServlet() {
	    super();
	    this.dbUtil = new DBUtil();
	    this.userDAO = new UserDAO(dbUtil);

	    CartDAO cartDAO = new CartDAO(dbUtil);
	    CartItemDAO cartItemDAO = new CartItemDAO(dbUtil);
	    BagDAO bagDAO = new BagDAO(dbUtil);
	    CartService cartService = new CartService(cartDAO, cartItemDAO, bagDAO);

	    this.userService = new UserService(userDAO, cartService);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("URL : " + request.getRequestURL());
        System.out.println("Query : " + request.getQueryString());

        String task = request.getParameter("task");
        System.out.println("Task = " + task);

        if (task == null) {
            System.out.println("Task is NULL");
            return;
        }

        if (task.equalsIgnoreCase("editProfile")) {
            editProfile(request, response);
        }
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String task = request.getParameter("task");
	if(task.equalsIgnoreCase("signup")){
		signup(request,response);
	}
	else if(task.equalsIgnoreCase("login")){
		login(request,response);
	}else if(task.equalsIgnoreCase("update")){
		updateById(request,response);
	}
	}
	
	
	private void signup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobileNumber = request.getParameter("mobile_number");
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		userDTO.setName(name);
		userDTO.setMobile_number(mobileNumber);
		userDTO.setPassword(password);
		try {
			int count = userService.insert(userDTO);
			if(count>0) {
				System.out.println("Success");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.forward(request, response);

			}
			else {
				System.out.println("Failed to save");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
				request.setAttribute("status", "Failed");
				request.setAttribute("message", "Unable to create User account.");
				request.setAttribute("redirectUrl", "signup.jsp");
				requestDispatcher.forward(request, response);					}
		}catch(Exception e) {
		System.out.println("Failed to save due to : " + e.getMessage());
		e.printStackTrace();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
		request.setAttribute("status", "Failed");
		request.setAttribute("message", "Unable to create User account due to: " + e.getMessage());
		request.setAttribute("redirectUrl", "signup.jsp");
		requestDispatcher.forward(request, response);


		}

		}
	
	
	 private void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			UserDTO userDTO = userService.login(email, password);
			if(userDTO!=null) {
				request.getSession().setAttribute("activeUserDTO", userDTO);
				response.sendRedirect("BagServlet");
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("login.jsp");
			request.setAttribute("message", "unable to login user account due to "+e.getMessage());
			}
	}

	private void updateById(HttpServletRequest request, HttpServletResponse response) {
		   System.out.println("updateById() called");
		String task = request.getParameter("userID");
		int id = Integer.parseInt(task);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDTO.setName(request.getParameter("name"));
		userDTO.setMobile_number(request.getParameter("mobile_number"));
		userDTO.setEmail(request.getParameter("email"));

		try {
			int count = userService.updateByID(userDTO);
			response.sendRedirect("BagServlet");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void editProfile(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute("activeUserDTO");
		request.setAttribute("userDTO", userDTO);
		RequestDispatcher rd = request.getRequestDispatcher("editProfile.jsp");
		rd.forward(request, response);
		
			
	}
	
	
	
	
}
