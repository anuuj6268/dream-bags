<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Delivery Address</title>
    <link rel="stylesheet" href="style.css">
</head>
<body class="addressBody">
    <div class="address-container">
        <h1>Delivery Address</h1>
        <p class="subtitle">
            Please enter your delivery address to place the order
        </p>

        <form action="AddressServlet" method="post">
        <input type="hidden" name="task" value="saveAddress">
            <div class="form-box">

                <div class="form-group">
                <input type="hidden" name = "userId" value ="<%=request.getParameter("userId")%>">
                    <label>Mobile Number <span>*</span></label>
                    <input type="tel" name="mobile"
                        value="${user.mobile_number}"
                        pattern="[0-9]{10}" required>
                </div>

                <div class="form-group">
                    <label>Pincode <span>*</span></label>
                    <div class="pincode-box">
                        <input type="text" id="pincode" name="pincode"
                            placeholder="Enter 6 digit pincode"
                            maxlength="6" pattern="[0-9]{6}" required>
                    </div>
                </div>

                <div class="form-group">
                    <label>City</label>
                    <input type="text" id="city" placeholder="Can be Auto-filled" >
                </div>

                <div class="form-group">
                    <label>State</label>
                    <input type="text" id="state" placeholder="Can be Auto-filled">
                </div>

                <div class="form-group full-width">
                    <label>House No. / Building / Street <span>*</span></label>
                    <input type="text" name="house_number"
                        placeholder="Enter house no., building, street" required>
                </div>

                <div class="form-group full-width">
                    <label>Landmark</label>
                    <input type="text" name="landmark"
                        placeholder="Nearby landmark">
                </div>

            </div>

            <button type="submit" class="continue-btn">
                <span>Next: Continue</span>
                <span class="arrow">→</span>
            </button>
        </form>

        <div class="security">
            <span>🔒</span>
            <span>Your information is safe and secure</span>
        </div>
    </div>
</body>
</html>