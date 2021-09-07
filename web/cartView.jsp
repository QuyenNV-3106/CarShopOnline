<%-- 
    Document   : cartview
    Created on : Jul 11, 2021, 11:08:52 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/shopview.css" />
        <script src="js/clock.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                    <input type="text" name="search" placeholder="Enter name product..." id="search-input"/>
                    <button id="buttonS" name="btnAction" value="Search">
                        Search
                    </button>
                </form>
            </div>
            <div class="second">
                <div id="dong_ho">
                    <div id="thoi_gian">
                        <div>
                            <span id="gio">00</span><span>Hours</span>
                        </div>
                        <div>
                            <span id="phut">00</span><span>Minutes</span>
                        </div>
                        <div>
                            <span id="giay">00</span><span>Seconds</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="third" style="margin-right: 20px">
                <a id="link" href="MainController" >Home</a>
                <c:if test="${empty sessionScope.USER_DATA}"><a id="link" onclick="document.getElementById('id01').style.display = 'block'" style="width:auto; cursor: pointer;" >Login</a></c:if>
                    <a id="link" href="MainController?btnAction=ViewShop">Shopping</a>
                <c:choose>
                    <c:when test="${sessionScope.USER_DATA.role.roleID eq 'US'}">
                        <a id="link" href="MainController?btnAction=ViewCart" style="background-color: black; color: white">Your cart</a>
                    </c:when>
                </c:choose>  
                <c:if test="${not empty sessionScope.USER_DATA}">
                    <a id="link" href="MainController?btnAction=Logout" >Logout</a>
                </c:if>
            </div>
        </nav>

        <h1 id="title" style="margin-left: 0">Your Cart</h1>

        <h2 style="color: purple; font-style: italic">Hello ${sessionScope.USER_DATA.userID}</h2>
        <h3 style="color: red; font-style: italic">${requestScope.MSG}</h3>

        <c:choose>
            <c:when test="${sessionScope.CART.getCart().size() == 0 || sessionScope.CART == null}">
                <p style="font-size: 30px; font-weight: bolder; color: #04AA6D">Your cart is Empty</p>
            </c:when>
            <c:when test="${sessionScope.CART.getCart().size() != 0 || sessionScope.CART != null}">
                <div class="table">
                    <table border="1">
                        <tr>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Check delete</th>
                        </tr>
                        <form action="MainController" method="POST">
                            <c:forEach var="pro" items="${sessionScope.CART.getCart().values()}">
                                <tr>
                                    <td style="display: none"><input class="input-cart" type="text" name="id" value="${pro.productID}" readonly></td>
                                    <td><input style="width: 100px" class="input-cart" type="text" name="name" value="${pro.productName}" readonly></td>
                                    <td><input class="input-cart" type="text" name="quantity" value="${pro.quantity}" required></td>
                                    <td><input class="input-cart" type="text" name="price" value="${pro.price * pro.quantity}" readonly></td>
                                    <td><input class="input-cart" type="checkbox" name="check" value="${pro.productID}" readonly></td>
                                </tr>
                                <c:set var="total" value="${total + pro.price * pro.quantity}" />
                            </c:forEach>
                                <td colspan="3" style="border: 0; text-align: right"><button type="submit" name="btnAction" value="Checkout" style="width: 80px; color: blue">Check out</button></td>
                            <td colspan="3" style="width: 55px; border: 0 ;text-align: right" ${empty sessionScope.CART?"hidden":""}><button type="submit" name="btnAction" value="DeleteItems" style="width: 100%; color: blue">Delete</button></td>
                            <c:if test="${not empty sessionScope.CART}"> <tr>Total: <input name="total" type="number" style="font-size: 15px; width: 10% ;font-weight: bolder" value="${total}" readonly></tr></c:if>
                            </form>
                        </table>   
                    </div>
            </c:when>
        </c:choose>

        <footer style="margin-top: 4%;">
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
    <style>
        td{
            text-align: center;
        }
        .input-cart{
            border: 0;
            width: 96%;
        }
        .link-table:visited{
            color: blue;
            text-decoration: none;
        }
        .table{
            width: 50%;
        }
        .table > p{
            font-weight: bolder;
            text-align: right;
        }
        .table>table{
            width: 100%;
        }
    </style>
</html>
