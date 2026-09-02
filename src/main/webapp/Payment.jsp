<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment</title>
    <link rel="stylesheet" href="style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="payment-container">

        <!-- Heading -->
        <h1>Payment</h1>

        <p class="subtitle">
            Choose your preferred payment method
        </p>


        <!-- Payment Options -->
        <form action="PaymentServlet" method="post">
				<input type="hidden" name = "task" value = "paymentTypeId">
            <div class="payment-box">
			
                <!-- Cash on Delivery -->
                <label class="payment-option">

                    <input type="radio"
                           name="paymentTypeId"
                           value="1"
                           required>

                    <div class="payment-icon">
                        ₹
                    </div>

                    <div class="payment-info">
                        <h3>Cash on Delivery</h3>
                        <p>Pay when your order is delivered</p>
                    </div>

                </label>


                <!-- UPI -->
                <label class="payment-option">

                    <input type="radio"
                           name="paymentTypeId"
                           value="2">

                    <div class="payment-icon">
                        UPI
                    </div>

                    <div class="payment-info">
                        <h3>UPI</h3>
                        <p>Pay using any UPI application</p>
                    </div>

                </label>


                <!-- Card -->
                <label class="payment-option">

                    <input type="radio"
                           name="paymentTypeId"
                           value="3">

                    <div class="payment-icon">
                        💳
                    </div>

                    <div class="payment-info">
                        <h3>Credit / Debit Card</h3>
                        <p>Pay securely using your card</p>
                    </div>

                </label>

            </div>


            <!-- Place Order -->
            <button type="submit" class="place-order-btn">

                <span>Place Order</span>

                <span class="arrow">→</span>

            </button>

        </form>


        <!-- Security -->
        <div class="security">
            🔒 Your payment information is safe and secure
        </div>

    </div>
</body>
</html>