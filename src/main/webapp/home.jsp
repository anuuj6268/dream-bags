<%@page import="java.util.List"%>
<%@page import="com.bag.store.dto.BagDTO"%>
<%@page import="com.bag.store.dto.BagTypeDTO"%>
<%@page import="com.bag.store.dto.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>Dream Bags</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Space+Mono:wght@400;700&display=swap" rel="stylesheet">
</head>

<body>
<%@ include file = "NavBar.jsp" %>
    <div class="category">
    <ul>
        <li><a href="BagServlet?type=1">BACKPACK BAG</a></li>
        <li><a href="BagServlet?type=2">DAY PACK BAG</a></li>
        <li><a href="BagServlet?type=3">BELT BAG</a></li>
        <li><a href="BagServlet?type=4">BUM AND WAIST BAG</a></li>
        <li><a href="BagServlet?type=5">DRAWSTRING BAG</a></li>
        <li><a href="BagServlet?type=6">RUCKSACK</a></li>
        <li><a href="BagServlet?type=7">SATCHEL BAG</a></li>
        <li><a href="BagServlet?type=8">KNAPSACK BAG</a></li>
        <li><a href="BagServlet?type=9">HANDBAG</a></li>
        <li><a href="BagServlet?type=10">CROSSBODY BAG</a></li>
        <li><a href="BagServlet?type=11">FUNCTIONAL BAG</a></li>
        <li><a href="BagServlet?type=12">TRUNKS</a></li>
    </ul>





    </div>

<div class="container">
    <div class="container-heading"></div>
    <div class="container-body">

<%
if(request.getAttribute("bagDTOList")!=null){
List<BagDTO> bagDTOList = (List)request.getAttribute("bagDTOList");

for(BagDTO bagDTO : bagDTOList){
%>

<div class="card">
    <div class="upper-card">
    <img src="<%=bagDTO.getUrl()%>">
    </div>
    <div class="lower-card">
  <div class="lower-left">
    <a href="">Backpack Bags</a>
    <h5 id="title"><%=bagDTO.getName()%></h5>
  </div>
  <div class="lower-right">
    <h5 id="price">₹<%=bagDTO.getPrice()%></h5>
    <form action="CartServlet" method="post">
      <input type="hidden" name="task" value="addToCart">
      <input type="hidden" name="bagId" value="<%=bagDTO.getId()%>">
      <input type="submit" value="Add To Cart">
    </form>
  </div>
</div>
</div>
<%
}
}
%>
    </div>

</div>
</body>
</html>