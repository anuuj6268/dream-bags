<%@page import="java.util.List"%>
<%@page import="com.bag.store.dto.BagDTO"%>
<%@page import="com.bag.store.dto.BagTypeDTO"%>
<%@page import="com.bag.store.dto.UserDTO" %>
<%@page import="com.bag.store.dto.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Cart</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Space+Mono:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<%@ include file="NavBar.jsp" %>

<div class="cart-page">
    <div class="cart-heading">Shopping Cart</div>

    <!-- cart items -->
    <div class="cart-items">
<% 
List<ViewCartDTO> cartList = (List<ViewCartDTO>) request.getAttribute("cartList");
for(ViewCartDTO viewCartDTO : cartList){
%>
        <div class="cart-item">
            <div class="cart-item-left">
                <div class="cart-item-img"><img src="<%=viewCartDTO.getBagURL()%>" alt="bag"></div>
                <div class="cart-item-info">
                    <h5><%=viewCartDTO.getBagName() %></h5>
                    <p>₹ <%=viewCartDTO.getBagPrice() %></p>
                    <div class="qty-stepper">
                      <form action="CartServlet" method="post">
                        <input type="hidden" name="task" value="decrease">
                        <input type="hidden" name="cartItemId" value="<%=viewCartDTO.getCartItemId()%>">                     
                        <button>-</button>
                      </form>
                        <span><%=viewCartDTO.getQuantity()%></span>
                      <form action="CartServlet" method="post">
                        <input type="hidden" name="task" value="increase">
                        <input type="hidden" name="cartItemId" value="<%=viewCartDTO.getCartItemId()%>">                     
                        <button>+</button>
                      </form>
                    </div>
                </div>
            </div>
            <div class="remove-btn"> 
            	<form action="CartServlet" method="post">
            	<input type="hidden" name="task" value="remove">
            	<input type="hidden" name="cartItemId" value="<%=viewCartDTO.getCartItemId()%>">                       	
            	<button><i class="fa-solid fa-trash"></i></button>
            	</form>
            </div>
        </div>
<%
}
%>
    </div>

    <!-- recommended / add more items carousel -->
    <div class="recommend-section">
        <div class="recommend-heading">You might also like</div>
        <div class="carousel">
            <div class="carousel-arrow"><i class="fa-solid fa-chevron-left"></i></div>
            <div class="carousel-track">
                <% List<BagDTO> suggestionList = (List<BagDTO>)request.getAttribute("suggestionList");
    for(BagDTO bagDTO : suggestionList){
    %>
                <div class="rec-card">
                    <div class="rec-card-img"><img src="<%=bagDTO.getUrl() %>" alt="bag"></div>
                    <div class="rec-card-info">
                        <h5><%=bagDTO.getName() %></h5>
                        <p>₹ <%=bagDTO.getPrice() %></p>
                        <form action = "CartServlet" method= "post">
                        <input type="hidden" name="task" value="addToCart">
                       	<input type="hidden" name="bagId" value = "<%=bagDTO.getId() %>"> 
                        <input type = "submit" placeHolder="Add to Cart"  value="Add To Cart" >
                        </form> 
                    </div>
                </div>
                <% 
	}
    %>
            </div>
            <div class="carousel-arrow"><i class="fa-solid fa-chevron-right"></i></div>
        </div>
    </div>	
	
    <!-- subtotal + checkout -->
    <div class="cart-footer">
        <div class="subtotal">Sub-total: ₹<%=request.getAttribute("total")%></div>
        <form action="AddressServlet" method="get">
        <input type = "hidden" name = "task" value = "showAddress">
        <input type="hidden" name="userId" value = "<%=((UserDTO)session.getAttribute("activeUserDTO")).getId()%>">
        <button class="place-order-btn">Place Order</button>
        </form>
    </div>
</div>
</body>
</html>