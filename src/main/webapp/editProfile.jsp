<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.bag.store.dto.UserDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Profile</title>
    <style>
body{
    margin: 0;
    padding: 0;
    background: #f6f6f6;
    font-family: 'Space Mono', monospace;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

h1{
    margin-bottom: 30px;
    color: #111;
}

form{
    width: 350px;
    background: white;
    padding: 40px;
    border-radius: 20px;
    box-shadow: 0 2px 12px rgba(0,0,0,0.08);
    display: flex;
    flex-direction: column;
    gap: 20px;
}

form input[type="text"]{
    padding: 12px;
    border: 1px solid #dcdcdc;
    border-radius: 10px;
    outline: none;
    font-size: 14px;
}

form input[type="text"]:focus{
    border-color: black;
}

form input[type="submit"]{
    padding: 12px;
    border: none;
    border-radius: 10px;
    background: black;
    color: white;
    cursor: pointer;
    font-size: 15px;
    transition: 0.3s ease;
}

form input[type="submit"]:hover{
    background: #333;
}</style>
</head>
<body>
    <h1>Edit Profile</h1>
<%
if(request.getAttribute("userDTO")!=null){
	
UserDTO userDTO = (UserDTO)request.getAttribute("userDTO");

%>
    <form action="UserServlet" method="post">
<input type="hidden" name="task" value="update">
<input type="hidden" name = "userID"  value="<%=userDTO.getId() %>"/>
<input type="text" name="name" value="<%=userDTO.getName()%>"/>
<input type="text" name="mobile_number" value="<%=userDTO.getMobile_number()%>"/>
<input type="text" name="email" value="<%=userDTO.getEmail()%>"/>
<input type="submit" name="submit">
    </form>
    <%
    }
    %>
</body>
</html>