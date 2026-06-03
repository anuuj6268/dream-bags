package com.bag.store.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.bag.store.dao.CartDAO;
import com.bag.store.dao.CartItemDAO;
import com.bag.store.dto.CartDTO;
import com.bag.store.dto.CartItemDTO;
import com.bag.store.dto.UserDTO;
import com.bag.store.service.CartItemService;
import com.bag.store.service.CartService;
import com.bag.store.util.DBUtil;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil dbUtil;
	private CartService cartService;
	private CartDAO cartDAO;
	private CartItemService cartItemService;
	private CartItemDAO cartItemDAO;
    public CartServlet() {
        super();
        this.dbUtil = new DBUtil();
        this.cartDAO = new CartDAO(dbUtil);
        this.cartItemDAO = new CartItemDAO(dbUtil);
        this.cartService = new CartService(cartDAO);
        this.cartItemService = new CartItemService(cartItemDAO);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String task = request.getParameter("task");

	
	if(task.equalsIgnoreCase("addToCart")) {
		try {
			addtoCart(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}

private void addtoCart(HttpServletRequest request, HttpServletResponse response) throws Exception {

	HttpSession session = request.getSession();
	int bagId = Integer.parseInt(request.getParameter("bagId"));
	UserDTO userDTO = (UserDTO)session.getAttribute("activeUserDTO");
    CartDTO cartDTO = new CartDTO();
    cartDTO.setUser_id(userDTO.getId());
    int cartId = cartService.getOrCreateCart(cartDTO);
    CartItemDTO cartItemDTO = new CartItemDTO();
    cartItemDTO.setBag_id(bagId);
    cartItemDTO.setCart_id(cartId);
    cartItemDTO.setQuantity(1);
    cartItemService.insert(cartItemDTO);
    response.sendRedirect("BagServlet");
}

}
