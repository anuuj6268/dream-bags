package com.bag.store.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bag.store.dao.PincodeDAO;
import com.bag.store.dto.PincodeDTO;
import com.bag.store.service.PincodeService;
import com.bag.store.util.DBUtil;

@WebServlet("/PincodeServlet")
public class PincodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PincodeDAO pincodeDAO;
	private DBUtil dbUtil;
	private PincodeService pincodeService;
    public PincodeServlet() {
    	super();
       dbUtil = new DBUtil();
       pincodeDAO = new PincodeDAO(dbUtil);
       pincodeService = new PincodeService(pincodeDAO);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pincode = request.getParameter("pincode");
		request.setAttribute("pincode", pincode);
		try {
			getPincode(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getPincode(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		String pincode = (String) request.getAttribute("pincode");
		PincodeDTO pincodeDTO = pincodeService.findByPincode(Integer.parseInt(pincode));
		request.setAttribute("pincodeDTO", pincodeDTO);
		request.getRequestDispatcher("address.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
