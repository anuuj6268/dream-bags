<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Bag - Dream Bags</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<%@ include file="NavBar.jsp" %>

<div class="admin-page">
    <div class="admin-heading">Add New Bag</div>

    <form class="admin-form" action="BagServlet" method="post">
        <input type="hidden" name="task" value="add-bag">

        <div class="admin-field">
            <label>Bag Name</label>
            <input type="text" name="name" placeholder="Bag name" required>
        </div>

        <div class="admin-field">
            <label>Bag Type ID</label>
            <input type="text" name="bag-type" placeholder="e.g. 1" required>
        </div>

        <div class="admin-field">
            <label>Price</label>
            <input type="number" name="price" placeholder="e.g. 1749" required>
        </div>

        <div class="admin-field">
            <label>Material</label>
            <input type="text" name="material" placeholder="e.g. Canvas" required>
        </div>

        <div class="admin-field">
            <label>Image URL</label>
            <input type="text" name="url" placeholder="e.g. backpack1.png" required>
        </div>

        <button type="submit" class="admin-submit-btn">Add Bag</button>
    </form>
</div>

</body>
</html>