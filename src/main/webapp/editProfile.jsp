<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bag.store.dto.UserDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
<link rel="stylesheet" href = "style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head> 
<body>

<%@ include file="NavBar.jsp" %>

<%
UserDTO useDTO = (UserDTO) request.getAttribute("userDTO");
%>

<div class="profile-page">
    <div class="profile-heading">Edit Your Profile</div>

    <form class="profile-form" action="UserServlet" method="post">
        <input type="hidden" name="task" value="update">
        <input type="hidden" name="userID" value="<%=useDTO.getId()%>">

        <div>
            <label>Full Name</label>
            <input type="text" name="name" value="<%=useDTO.getName()%>">
        </div>

        <div>
            <label>Email</label>
            <input type="text" name="email" value="<%=useDTO.getEmail()%>">
        </div>

        <div>
            <label>Mobile Number</label>
            <input type="text" name="mobile_number" value="<%=useDTO.getMobile_number()%>">
        </div>

        <input type="submit" value="Update Profile">
    </form>
</div>

</body>
</html>