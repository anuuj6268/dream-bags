<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>login</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Space+Mono:wght@400;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css"></head>
<body class="sign-body">
<%
String error = (String) request.getAttribute("error");

if (error != null) {
%>

<script>
    alert("<%= error %>");
</script>

<%
}
%>
    <div class="signup-container">
    <div class="sign-up">
    <div class="a"><a href="home.jsp"><img src="dream.png" alt="" id="logo"></a></div>
    <div class="b"><h1 id="login">Login</h1>
        <p>Continue using with your email Id and Password</p>
    <div class="sing-down">
    <form action="UserServlet" id="forum" method = "post">
    <input type = "hidden" name="task" value = "login">
    <input type="text" name ="email" placeholder="Enter Your Email">
    <input type="text" name = "password" placeholder="Password">
    <input type="submit" value="submit">
    <p class="p-log">Don't have an Account?  <a href="signup.jsp">Sign-up</a></p>
    </form>
    </div>
    </div>
    </div>
    </div>
</body>
</html>