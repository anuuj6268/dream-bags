<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.bag.store.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Navbar</title>
</head>
<body>
    <div class="navBar">
        <div class="left">
        <a href="BagServlet" value = "TaskAll"><img src="dream.png" alt="" id="logo"></a>
        <form action = "BagServlet" method = "get">
        <input type="hidden" name = "type" value="search">
        <input type="search" placeholder="Search for BAGS" name="keyword"> 
        <input type="submit" hidden> 
</form>
</div>
<div class="right">
<div class="icon-container">
<%

UserDTO userDTO = (UserDTO)session.getAttribute("activeUserDTO");
if(userDTO == null){
%>
        <a href="login.jsp" class="icon-link" aria-label="Profile">
            <i class="fa-regular fa-user"></i>
        </a>
<%     
}
        		else{ %>

<div class="profile-menu">

<a class="icon-link">
    <i class="fa-regular fa-user"></i>
</a>

<div class="dropdown">
    <a href="UserServlet?task=editProfile">Edit Profile</a>
    <a href="LogoutServlet">Logout</a>
</div>

</div>
<%
}
%>

        <a href="CartServlet?task=getCartItems" class="icon-link" aria-label="Shopping Cart">
            <i class="fa-solid fa-cart-shopping"></i>
        </a>
    </div>
</div>

    </div>

</body>
</html>