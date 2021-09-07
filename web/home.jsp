<%-- 
    Document   : index
    Created on : Jul 6, 2021, 12:33:57 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="images/Flash.png" type="image/gif" />
        <link rel="stylesheet" href="css/mycss.css" />
        <title>Home Page</title>
    </head>
    <c:choose>
        <c:when test="${not empty requestScope.ERROR}">
            <body onload="document.getElementById('id01').style.display = 'block'">
            </c:when>
            <c:when test="${sessionScope.USER_DATA != null}">
            <body onload='alert("Welcome ${sessionScope.USER_DATA.userName}")';>
            </c:when>
        </c:choose>
        <%--<c:if test="${empty items}"><c:set var="items" scope="session" value="${0*0}"></c:set></c:if>--%>
        <div style="height: 20rem; margin-bottom: 2%">
            <nav>
                <a id="link" style="background-color: black; color: white" href="MainController" >Home</a>
                <c:if test="${empty sessionScope.USER_DATA}"><a id="link" onclick="document.getElementById('id01').style.display = 'block'" style="width:auto; cursor: pointer;" >Login</a></c:if>
                <c:choose>
                    <c:when test="${sessionScope.USER_DATA.role.roleID eq 'US'}">
                        <a id="link" href="MainController?btnAction=ViewCart">Your cart</a>
                        </c:when>

                    <c:when test="${sessionScope.USER_DATA.role.roleID eq 'AD'}">
                        <a id="link" href="MainController?btnAction=ViewAD">Admin action</a>
                    </c:when>
                </c:choose>  
                <a id="link" href="MainController?btnAction=ViewShop">Shopping</a>
                <c:if test="${not empty sessionScope.USER_DATA}">
                    <a id="link" href="MainController?btnAction=Logout" >Logout</a>
                </c:if>
                <div class="search">
                    <form action="MainController">
                        <input type="text" name="search" placeholder="Enter name product..." id="search-input"/>
                        <button id="buttonS" name="btnAction" value="Search">
                            Search
                        </button>
                    </form>
                </div>
            </nav>
        </div>
        <h1 style="color:black; line-height:45px; font-size: 4rem; max-width:fit-content; margin:0 auto; margin-bottom: 25px;">Brands</h1>
        <div id="label">
            <a title="Mercedes">
                <img
                    id="logo"
                    style="width: 18em; height: 10rem"
                    src="images/Mercedes-Benz-1926-Present.png"
                    alt="logo"
                    /></a>
            <a title="Audi"
               ><img
                    id="logo"
                    style="width: 16rem; height: 10rem"
                    src="images/Audi-1909-Present.png"
                    alt="logo"
                    /></a>
            
            <a title="Porsche"
               ><img
                    id="logo"
                    style="width: 16rem; height: 10rem"
                    src="images/Porsche-1931-Present.png"
                    alt="logo"
                    /></a>
            <a title="BWM"
               ><img
                    id="logo"
                    style="width: 18rem; height: 10rem"
                    src="images/BMW-1916-Present.png"
                    alt="logo"
                    /></a>
            <a title="Volkswagen"
               ><img
                    id="logo"
                    style="width: 17rem; height: 10rem"
                    src="images/Volkswagen-1937-Present.png"
                    alt="logo"
                    /></a>
        </div>
        <footer>
            <div>
                <img
                    src="images/Flash.png"
                    alt="logo"
                    style="width: 10%; float: left"
                    />
                <h1 style="color: red">Car Paradise</h1>
                <h1>CONTACT US:</h1>
                <h2>Phone: <span style="color: yellow">03327123321</span></h2>
                <h2>Mail: <span style="color: yellow">admin@gmail.com</span></h2>
                <h2>Address: <span style="color: yellow">Vietnam, Ho Chi Minh City</span></h2>
            </div>
            <div>
                <h1 style="margin-top: 5rem">FOllOW US:</h1>
                <div>
                    <img src="images/google.png" alt="" />
                    <img src="images/facebook.png" alt="" />
                    <img src="images/pinterest.png" alt="" />
                </div>
            </div>
        </footer>

        <div id="id01" class="modal" style="height: auto">
            <form style="padding: 10px" class="modal-content animate" action="MainController" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <img src="images/user.png" alt="Avatar" class="avatar">
                </div>

                <div class="containerLogin">
                    <i><h1 style="color: red; font-style: italic; font-size: 1rem; font-weight: bolder; text-align: center">${requestScope.ERROR}</h1></i>
                    <label for="uname"><b><img src="images/user.png" alt=""></b></label>
                    <input type="text" placeholder="Enter UserID" name="id" required>
                    <label for="psw"><img src="images/key.png" alt=""></label>
                    <input type="password" placeholder="Enter Password" name="password" required>

                    <button type="submit" value="Login" name="btnAction">Login</button>
                    <p style="text-align:right;"><a style="color:blue; text-decoration:underline" href="MainController?btnAction=Signup">Sign up</a></p>
                </div>
            </form>
        </div>
    </body>
</html>
