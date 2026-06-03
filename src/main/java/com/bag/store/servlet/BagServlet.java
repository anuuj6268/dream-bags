package com.bag.store.servlet;

import java.io.IOException;
import java.util.List;

import com.bag.store.dao.BagDAO;
import com.bag.store.dto.BagDTO;
import com.bag.store.service.BagService;
import com.bag.store.util.DBUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/BagServlet")
public class BagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil dbUtil;
	private BagService bagService;
	private BagDAO bagDAO;
    public BagServlet() {
        super();
        this.dbUtil = new DBUtil();
        this.bagDAO = new BagDAO(dbUtil);
        this.bagService = new BagService(bagDAO);
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String task = request.getParameter("type");
	if(task == null) {
		findAll(request,response);
	}
	else {
		int bagTypeId = Integer.parseInt(task);
		if(bagTypeId>0) {
			findByBagTypeId(request,response);
		}
	}
	}


	private void findAll(HttpServletRequest request, HttpServletResponse response) {
try {
	List<BagDTO> bagDTOList = bagService.findAll();

	if(!bagDTOList.isEmpty()) {
		request.setAttribute("bagDTOList", bagDTOList);
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}
	}catch(Exception e) {
		e.printStackTrace();
	}


	}

	private void findByBagTypeId(HttpServletRequest request,HttpServletResponse response) {
try {
	String task = request.getParameter("type");
	int id = Integer.parseInt(task);
	List<BagDTO> bagDTOList = bagService.findByBagTypeID(id);
	if (!bagDTOList.isEmpty()) {
		request.setAttribute("bagDTOList", bagDTOList);
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}
}catch(Exception e) {
	e.printStackTrace();
}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String task = request.getParameter("task");
	if(task.equalsIgnoreCase("add-bag")) {
		addBag(request,response);
	}
	}

	private void addBag(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");
		String bagType = request.getParameter("bag-type");
		int bagTypeId = Integer.parseInt(bagType);
		String price = request.getParameter("price");
		int priceInt = Integer.parseInt(price);
		String material = request.getParameter("material");
		String url = request.getParameter("url");

		try {
			BagDTO bagDTO = new BagDTO();
			bagDTO = new BagDTO();
			bagDTO.setName(name);
			bagDTO.setBagType(bagTypeId);
			bagDTO.setPrice(priceInt);
			bagDTO.setMaterial(material);
			bagDTO.setUrl(url);
			bagService.insert(bagDTO);
		    RequestDispatcher rd = request.getRequestDispatcher("addBag.jsp");
		    rd.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}




	}


}
