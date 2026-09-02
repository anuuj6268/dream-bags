<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmed</title>
<link rel="stylesheet" href="style.css">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

    <div class="confirmation-container">

        <div class="success-icon">
            ✓
        </div>

        <h1>Order Confirmed!</h1>

        <p class="confirmation-message">
            Thank you for your order. Your order has been placed successfully.
        </p>

        <div class="order-details">

            <div class="detail-row">
                <span>Order ID</span>
                <strong>#<%=request.getSession().getAttribute("orderId")%></strong>
            </div>

            <div class="detail-row">
                <span>Order Date</span>
                <strong><%=request.getSession().getAttribute("orderDate") %></strong>
            </div>

            <div class="detail-row">
                <span>Payment Method</span>
                <strong><%=request.getSession().getAttribute("paymentMode") %></strong>
            </div>

            <div class="detail-row">
                <span>Expected Delivery</span>
                <strong><%=request.getSession().getAttribute("expectedDate") %></strong>
            </div>

        </div>

        <p class="delivery-message">
            Your order will be delivered to your selected address.
        </p>

        <div class="action-buttons">

            <a href="BagServlet" class="continue-shopping">
                Continue Shopping
            </a>

        </div>

        <div class="security">
            🔒 Your order information is safe and secure
        </div>

    </div>

</body>
</html>