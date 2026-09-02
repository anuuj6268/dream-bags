<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Card-Payment</title>
<link rel="stylesheet" href = "style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head> 
<body>
<div class="card-container">

    <h2>Card Payment</h2>

    <p>Pay securely using your Credit/Debit Card</p>

    <div class="card-amount">
        <span>Amount to Pay</span>
        <strong>₹<%= session.getAttribute("total") %></strong>
    </div>

    <form action="PaymentServlet" method="post">

        <input type="hidden" name="task" value="cardDone">

     <label for="cardNumber">Card Number</label>

<input type="text"
       id="cardNumber"
       name="cardNumber"
       placeholder="1234 5678 9012 3456"
       pattern="[0-9\s]{13,19}"
       title="Enter a valid card number (digits only)"
       maxlength="19"
       required>

<label for="cardName">Card Holder Name</label>

<input type="text"
       id="cardName"
       name="cardName"
       placeholder="Enter card holder name"
       pattern="[A-Za-z\s]{2,50}"
       title="Enter a valid name (letters only)"
       required>

<div class="card-row">

    <div class="card-field">
        <label for="expiry">Expiry Date</label>
        <input type="text"
               id="expiry"
               name="expiry"
               placeholder="MM/YY"
               pattern="(0[1-9]|1[0-2])\/[0-9]{2}"
               title="Enter a valid expiry date in MM/YY format"
               maxlength="5"
               required>
    </div>

    <div class="card-field">
        <label for="cvv">CVV</label>
        <input type="password"
               id="cvv"
               name="cvv"
               placeholder="CVV"
               pattern="[0-9]{3}"
               title="Enter a valid 3-digit CVV"
               maxlength="3"
               required>
    </div>

</div>

        <button type="submit" class="card-pay-btn">
            Pay Now
        </button>

    </form>

    <a href="Payment.jsp" class="card-back-btn">
        ← Back to Payment
    </a>

    <div class="card-note">
        This is a demo payment page. No real money will be deducted.
    </div>

</div>
</body>
</html>