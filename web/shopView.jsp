<%-- 
    Document   : shopview
    Created on : Jul 7, 2021, 12:19:25 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="images/Flash.png" type="image/gif" />
        <link rel="stylesheet" href="css/shopview.css" />
        <script src="js/clock.js"></script>
        <title>Welcome-Have a nice day</title>
    </head>
    <body>
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
            <div class="third" style="">
                <a id="link" href="MainController" >Home</a>
                <c:if test="${sessionScope.USER_DATA == null}"><a id="link" onclick="document.getElementById('id01').style.display = 'block'" style="width:auto; cursor: pointer;" >Login</a></c:if>
                    <a id="link" style="background-color: black; color: white" href="MainController?btnAction=ViewShop">Shopping</a>
                <c:choose>
                    <c:when test="${sessionScope.USER_DATA.role.roleID eq 'US'}">
                        <a id="link" href="MainController?btnAction=ViewCart">Your cart</a>
                        </c:when>

                    <c:when test="${sessionScope.USER_DATA.role.roleID eq 'AD'}">
                        <a id="link" href="MainController?btnAction=ViewAD">Admin action</a>
                    </c:when>
                </c:choose>  
                <c:if test="${sessionScope.USER_DATA != null}">
                    <a id="link" href="MainController?btnAction=Logout">Logout</a>
                </c:if>
            </div>
        </nav>
        <h1 id="title">CATEGORIES</h1>
        <div id="container">
            <div id="label">
                <a class="icon" title="Mercedes" href="MainController?btnAction=SearchBrand&searchValue=Mercedes"><img style="width: 14rem" src="images/Mercedes-Benz-1926-Present.png" alt=""></a> 
                <a class="icon" title="Audi" href="MainController?btnAction=SearchBrand&searchValue=Audi"><img style="width: 14rem" src="images/Audi-1909-Present.png" alt=""></a>
                <a class="icon" title="Porsche" href="MainController?btnAction=SearchBrand&searchValue=Porsche"><img style="width: 14rem" src="images/Porsche-1931-Present.png" alt=""></a>
                <a class="icon" title="BMW" href="MainController?btnAction=SearchBrand&searchValue=BMW"><img style="width: 14rem" src="images/BMW-1916-Present.png" alt=""></a>      
                <a class="icon" title="Volkswagen" href="MainController?btnAction=SearchBrand&searchValue=Volkswagen"><img style="width: 14rem" src="images/Volkswagen-1937-Present.png" alt=""></a>
            </div>
        </div>
                
        <h1 id="title">ALL PRODUCTS</h1>
        <p id="title" style="margin-top: 2px ;text-align: center; background-color: red; color: white; font-size: 30px; border: none; font-weight: bolder">${requestScope.ERROR}</p>
                <h2 style="color: red; font-style: italic; text-align: center">${requestScope.MESSAGE}</h2>
        <div class="grid-container">
            <c:if test="${not empty sessionScope.PRODUCT}">
                <c:forEach var="pro" items="${sessionScope.PRODUCT}">
                    <div class="grid-item" style="border: 1px solid black">
                        <p style="text-align: center;"><img id="pic" src="images/${pro.image}" alt="${pro.image}"></p>
                        <p id="detail" style="text-align: center; font-size:25px">${pro.productName}</p>
                        <p id="detail">Brand: ${pro.category.categoryID}</p>
                        <p id="detail">Detail: ${pro.detail}</p>
                        <p><button id="price">Price: ${pro.price}</button></p>

                        <%--<c:if test="${pro.quantity == 0 || pro.quantity < 0}">--%>
                        <p id="detail" style="visibility:${(pro.quantity == 0 || pro.quantity < 0)?"":"hidden"}; color: white; background: darkorange;">Sold Out</p>
                        <%--</c:if>--%>
                        <c:choose>
                            <c:when test="${pro.sale eq 'true'}">
                                <a id="buy" href="MainController?btnAction=AddToCart&id=${pro.productID}">Add to cart</a>
                            </c:when>
                            <c:when test="${pro.sale eq 'false'}">
                                <a id="buy" style="background-color: red; cursor: pointer">Not sale</a>
                            </c:when> 
                        </c:choose>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <c:if test="${empty sessionScope.PRODUCT}">
            <h1 style="text-align: center; background-color: #ff006a">Empty Product!</h1>
        </c:if>
        <footer>
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
                    <img src="images/facebook.png" alt="" />
                    <img src="images/pinterest.png" alt="" />
                </div>
            </div>
        </footer>

        <div id="id01" class="modal">

            <form class="modal-content animate" action="LoginServlet" method="POST">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <img src="images/user.png" alt="Avatar" class="avatar">
                </div>

                <div class="containerLogin">
                    <label for="uname"><b><img src="images/user.png" alt=""></b></label>
                    <input id="name" type="text" placeholder="Enter Username" name="id" required>

                    <label for="psw"><img src="images/key.png" alt=""></label>
                    <input id="pass" type="password" placeholder="Enter Password" name="password" required>

                    <button id="login" type="submit">Login</button>
                    <p style="text-align:right;"><a style="color:blue; text-decoration:underline" href="sign_up_form.html">Sign up</a></p>
                </div>
            </form>
        </div>
    </body>
    <style>
        .small{
            background-color: red;
            color: white;
        }
        #show{
            max-width: fit-content;
            margin: 0 auto;
            margin-top: 25px;
            color: blue;

        }
        #show:hover{
            color: red;
            border: 1px solid red;
        }
    </style>
</html>

