package com.bag.store.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.bag.store.dao.OrderDAO;
import com.bag.store.dao.PaymentDAO;
import com.bag.store.dao.PaymentTypeDAO;
import com.bag.store.dto.OrderDTO;
import com.bag.store.dto.PaymentDTO;
import com.bag.store.service.OrderService;
import com.bag.store.service.PaymentService;
import com.bag.store.service.PaymentTypeService;
import com.bag.store.util.DBUtil;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PaymentDAO paymentDAO;
	private DBUtil dbUtil;
	private PaymentService paymentService;
	private OrderDAO orderDAO;
	private OrderService orderService;
	private PaymentTypeDAO paymentTypeDAO;
	private PaymentTypeService paymentTypeService;
    public PaymentServlet() {
        super();
        this.dbUtil = new DBUtil();
        this.paymentDAO = new PaymentDAO(dbUtil);
        this.paymentService = new PaymentService(paymentDAO);
        this.orderDAO = new OrderDAO(dbUtil);
        this.orderService = new OrderService(orderDAO);
        this.paymentTypeDAO = new PaymentTypeDAO(dbUtil);
        this.paymentTypeService = new PaymentTypeService(paymentTypeDAO);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		if(task.equalsIgnoreCase("upiDone")) {
			request.getRequestDispatcher("Confirmed.jsp").forward(request, response);
		}
		else if(task.equalsIgnoreCase("cardDone")) {
			request.getRequestDispatcher("Confirmed.jsp").forward(request, response);
		}
		else if(task.equalsIgnoreCase("paymentTypeId")) {
			try {
				insert(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int paymentTypeId = Integer.parseInt(request.getParameter("paymentTypeId"));
		Timestamp dateAndTime = new Timestamp(System.currentTimeMillis());
		LocalDate expectedDate = dateAndTime.toLocalDateTime()
                .toLocalDate()
                .plusDays(4);

request.getSession().setAttribute("expectedDate", expectedDate);
		int amount = (int)request.getSession().getAttribute("total");
		int userId = Integer.parseInt((String)request.getSession().getAttribute("userId"));
		int addressId = (int)request.getSession().getAttribute("addressId");
		request.getSession().setAttribute("orderDate", dateAndTime);
		String paymentMode = paymentTypeService.findName(paymentTypeId);
		request.getSession().setAttribute("paymentMode", paymentMode);
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setAmount(amount);
		paymentDTO.setDateAndTime(dateAndTime);
		paymentDTO.setPaymentTypeId(paymentTypeId);
		paymentService.insert(paymentDTO);
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setPayment_id(paymentTypeId);
		orderDTO.setUser_id(userId);
		orderDTO.setAddress_id(addressId);
		orderDTO.setAmount(amount);
		orderDTO.setOrder_date(dateAndTime);
		orderDTO.setOrder_status("Order Placed");
		int orderId = orderService.insert(orderDTO);
		request.getSession().setAttribute("orderId", orderId);		
		if(paymentTypeId == 1) {
			request.getRequestDispatcher("Confirmed.jsp").forward(request, response);
		}
		else if(paymentTypeId == 2) {
			request.getRequestDispatcher("UPI.jsp").forward(request, response);
		}
		else if(paymentTypeId == 3) {
			request.getRequestDispatcher("Card.jsp").forward(request, response);
		}
	}
	}
