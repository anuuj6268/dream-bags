<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>signup</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<link href="https://fonts.googleapis.com/css2?family=Space+Mono:wght@400;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
</head>
<body class="sign-body">
    <div class="signup-container">
    <div class="sign-up">
    <div class="a"><a href="home.jsp"><img src="dream.png" alt="" id="logo"></a></div>
    <div class="b"><h1>Sign up</h1>
    <h5>Create an account</h5></div></div>
    <div class="sing-down">
    <form action="UserServlet" id="forum" method="post">
    <input type = "hidden" name="task" value="signup">
    <input type="text" name = "name" placeholder="Enter Your Full Name">
    <input type="text" name = "mobile_number" placeholder="Enter Your Mobile Number">
    <input type="text" name = "email" placeholder="Enter Your Email">
    <input type="text" name = "password" placeholder="Password">
    <input type="submit" value="submit">
    <p>Already have an account?  <a href="login.jsp">Log-in</a></p>
    </form>
</div>

</div>



</body>
</html>