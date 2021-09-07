<%-- 
    Document   : checkout_form.jsp
    Created on : Jul 12, 2021, 2:45:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/shopview.css" />
        <link rel="stylesheet" href="css/checkout.css">
        <script src="js/clock.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="icon" href="images/Flash.png" type="image/gif" />
        <title>Your cart</title>
    </head>
    <body>
        <c:if test="${sessionScope.USER_DATA.role.roleID != 'US' || sessionScope.USER_DATA == null}">
            <c:redirect url="MainController" />
        </c:if>
        <nav class="navbar">
            <div class="first">
                <form action="MainController">
                    <input
                        type="text"
                        name="searchvalue"
                        placeholder="Enter name product..."
                        id="search-input"
                        value=""
                        />
                    <button id="buttonS" name="btnAction" value="Search">
                        Search
                    </button>
                </form>
            </div>
            <div class="second">
                <div id="dong_ho">
                    <div id="thoi_gian">
                        <div><span id="gio">00</span><span>Hours</span></div>
                        <div><span id="phut">00</span><span>Minutes</span></div>
                        <div><span id="giay">00</span><span>Seconds</span></div>
                    </div>
                </div>
            </div>
            <div class="third" style="margin-right: 20px">
                <a id="link" href="MainController">Home</a>
                <c:if test="${empty sessionScope.USER_DATA || sessionScope.USER_DATA == null}"
                      ><a
                        id="link"
                        onclick="document.getElementById('id01').style.display = 'block'"
                        style="width: auto; cursor: pointer"
                        >Login</a
                    ></c:if
                >
                <a id="link" href="MainController?btnAction=ViewShop">Shopping</a>
                <c:choose>
                    <c:when test="${sessionScope.USER_DATA.role.roleID eq 'US'}">
                        <a
                            id="link"
                            href="MainController?btnAction=ViewCart"
                            style="background-color: black; color: white"
                            >Your cart <span class="small"></span></a>
                        </c:when>
                    </c:choose>
                    <c:if test="${not empty sessionScope.USER_DATA || sessionScope.USER_DATA != null}">
                    <a id="link" href="MainController?btnAction=Logout">Logout</a>
                </c:if>
            </div>
        </nav>

        <div class="flex-container">
            <span>We already send a verification  code to your email.</span>

            <form action="MainController" method="POST">
                <input type="text" name="code" >
                <input type="submit" name="btnAction" value="Verify">
            </form>
        </div>

        <footer style="margin-top: 4%">
            <div>
                <img
                    src="images/Flash.png"
                    alt="logo"
                    style="width: 10%; float: left"
                    />
                <h1 style="color: red">Electric Shop</h1>
                <h1>CONTACT US</h1>
                <h2>Phone: 03327123321</h2>
                <h2>Mail: admin@gmail.com</h2>
                <h2>Address: VietNam, Ho Chi Minh City</h2>
            </div>
            <div>
                <h1 style="margin-top: 5rem">FOllOW US</h1>
                <div>
                    <img src="images/google.png" alt="" />
                    <img src="images/instagram.png" alt="" />
                    <img src="images/facebook.png" alt="" />
                    <img src="images/pinterest.png" alt="" />
                </div>
            </div>
        </footer>
    </body>
</html>

