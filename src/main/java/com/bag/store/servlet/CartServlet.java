package com.bag.store.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bag.store.dao.BagDAO;
import com.bag.store.dao.CartDAO;
import com.bag.store.dao.CartItemDAO;
import com.bag.store.dto.BagDTO;
import com.bag.store.dto.CartDTO;
import com.bag.store.dto.CartItemDTO;
import com.bag.store.dto.UserDTO;
import com.bag.store.dto.ViewCartDTO;
import com.bag.store.service.BagService;
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
	private BagDAO bagDAO;
	private BagService bagService;
    public CartServlet() {
        super();
        this.dbUtil = new DBUtil();
        this.cartDAO = new CartDAO(dbUtil);
        this.bagDAO = new BagDAO(dbUtil);
        this.cartItemDAO = new CartItemDAO(dbUtil);
        this.cartService = new CartService(cartDAO,cartItemDAO,bagDAO);
        this.cartItemService = new CartItemService(cartItemDAO);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		if(task.equalsIgnoreCase("getCartItems")) {
			try {
				getCartItems(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String task = request.getParameter("task");

	
	if(task.equalsIgnoreCase("addToCart")) {
		try {
			UserDTO userDTO = (UserDTO) request.getSession().getAttribute("activeUserDTO");
			if(userDTO != null) {
			addtoCart(request,response);
			}
			else {
			response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(task.equalsIgnoreCase("increase")) {
		try {
			increase(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(task.equalsIgnoreCase("decrease")) {
		try {
			decrease(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(task.equalsIgnoreCase("remove")) {
		try {
			remove(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

private void increase(HttpServletRequest request, HttpServletResponse response) throws Exception {
	int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
	int quantity = cartItemService.getQuantity(cartItemId);
	cartItemService.increase(cartItemId, quantity);
	UserDTO userDTO = (UserDTO) request.getSession().getAttribute("activeUserDTO");
	List<ViewCartDTO> list = cartService.showCart(userDTO);
	int total = cartService.getTotal(list);
	setSuggestions(list, request);
	request.setAttribute("cartList", list);
	request.setAttribute("total", total);
	RequestDispatcher rs = request.getRequestDispatcher("Cart.jsp");
	rs.forward(request, response);
}

private void decrease(HttpServletRequest request, HttpServletResponse response) throws Exception {
int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
int quantity = cartItemService.getQuantity(cartItemId);
cartItemService.decrease(cartItemId, quantity);
UserDTO userDTO = (UserDTO) request.getSession().getAttribute("activeUserDTO");
List<ViewCartDTO> list = cartService.showCart(userDTO);
int total = cartService.getTotal(list);
setSuggestions(list, request);
request.setAttribute("total", total);
request.setAttribute("cartList", list);
RequestDispatcher rs = request.getRequestDispatcher("Cart.jsp");
rs.forward(request, response);
}

private void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
	int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
	cartItemService.remove(cartItemId);
	UserDTO userDTO = (UserDTO) request.getSession().getAttribute("activeUserDTO");
	List<ViewCartDTO> list = cartService.showCart(userDTO);
	int total = cartService.getTotal(list);
	request.setAttribute("cartList", list);
	setSuggestions(list, request);
	request.setAttribute("total", total);
	RequestDispatcher rs = request.getRequestDispatcher("Cart.jsp");
	rs.forward(request, response);
}

private void addtoCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
	UserDTO userDTO = (UserDTO) request.getSession().getAttribute("activeUserDTO");
	int cartId = cartService.getOrCreateCart(userDTO.getId());
	int bagId = Integer.parseInt(request.getParameter("bagId"));
	cartItemService.addItemIntoTheCart(cartId, bagId);
	response.sendRedirect("BagServlet");

}



private void getCartItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
	UserDTO userDTO = (UserDTO) request.getSession().getAttribute("activeUserDTO");
	if(userDTO==null) {
		response.sendRedirect("login.jsp");
		return;
	}
	List<ViewCartDTO> cartList = cartService.showCart(userDTO);
	int total = cartService.getTotal(cartList);
	request.setAttribute("cartList", cartList);
	request.setAttribute("total", total);
	request.getSession().setAttribute("total", total);
	setSuggestions(cartList, request);
	RequestDispatcher rs = request.getRequestDispatcher("Cart.jsp");
	rs.forward(request, response);
 }
private void setSuggestions(List<ViewCartDTO> cartList,HttpServletRequest request) throws Exception {

List<BagDTO> suggestionList = new ArrayList<>();

for(ViewCartDTO viewDTO : cartList) {

BagDTO bagDTO = bagDAO.findById(viewDTO.getBagId());

int bagTypeId = bagDTO.getBagType();

suggestionList.addAll(cartService.suggestionList(bagTypeId, cartList));
}

request.setAttribute("suggestionList", suggestionList);
}

}
