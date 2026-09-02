<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPI Payment</title>
<link rel="stylesheet" href = "style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<div class="upi-container">

    <h2>UPI Payment</h2>

    <p>Pay securely using your UPI ID</p>

    <div class="upi-amount">
        <span>Amount to Pay</span>
        <strong>₹<%= session.getAttribute("total") %></strong>
    </div>

    <form action="PaymentServlet" method="post">

        <input type="hidden" name="task" value="upiDone">

        <label for="upiId">Enter UPI ID</label>

        <input type="text"
               id="upiId"
               name="upiId"
               placeholder="example@upi"
               required>

        <button type="submit" class="upi-pay-btn">
            Pay Now
        </button>

    </form>

    <a href="Payment.jsp" class="upi-back-btn">
        ← Back to Payment
    </a>

    <div class="upi-note">
        This is a demo payment page. No real money will be deducted.
    </div>

</div>
</body>
</html>