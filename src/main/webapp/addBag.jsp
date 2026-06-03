
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>admin</title>
    <style>
    
        .add-container{
            width: 800px;
            
        }
        input{
            padding: 10px 20px;
            border-radius: 10px;
            width: 400px;
        }
    </style>
</head>
<body style="display: flex;flex-direction: column; align-items: center; justify-content: center; gap: 20px; ">
    <div style ="box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08), 0 1px 3px rgba(0, 0, 0, 0.05); border-radius:20px;display:flex;flex-direction:column; justify-content:center;align-items:center;
    width:600px; padding:20px; margin-top:40px;" }>
    <h1 style = "font-size:30px;">Enter Bag Details To Add in inventory</h1>
    <div class="add-container">
        <form action="BagServlet" method="post" id="forum" style="display: flex; flex-direction: column;justify-content: center; align-items:center; gap: 40px; margin-top:30px;">
<input type="hidden" name="task" value="add-bag">
<input type="text" placeholder="Enter Bag Name " name="name">
<input type="text" placeholder="Enter Bag Type Id " name ="bag-type">
<input type="text" placeholder="Enter Bag-Price " name="price">
<input type="text" placeholder="Enter Bag Material " name="material">
<input type="text" placeholder="Enter Bag Img URL " name="url">
<input type="submit" name="submit" value="submit">
        </form>
    </div>



</body>
</html>