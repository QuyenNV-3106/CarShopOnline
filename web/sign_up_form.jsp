
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="images/Flash.png" type="image/gif" />
        <link rel="stylesheet" href="css/signup.css" />
        <title>Sign up</title>
    </head>
    <body>
        <c:if test="${USER_DATA != null}">
            <c:redirect url="MainController"/>
        </c:if>
        <form action="SignUpServlet" method="post">
            <h1>SIGN UP</h1>
            <i><h1 style="color: red; font-style: italic; font-size: 1rem; font-weight: bolder; text-align: center">${error}</h1></i>
            <div class="input-group">
                <div class="item">
                    <img class="item1" src="images/user.png" />
                    <span style="color: red">${requestScope.ERROR.userIDError}</span>
                    <input placeholder="Username..." name="id" type="text" required/>
                </div>
                <div class="item">
                    <img class="item1" src="images/name.png">
                    <span style="color: red">${requestScope.ERROR.nameError}</span>
                    <input placeholder="Your Full Name..." name="name" type="text" required/>
                </div>
                <div class="item">
                    <img class="item1" src="images/key.png">
                    <span style="color: red">${requestScope.ERROR.passwordError}</span>
                    <input placeholder="Password..." name="password" type="password" required/>
                </div>
                <div class="item">
                    <img class="item1" src="images/confirm.png">
                    <span style="color: red">${requestScope.ERROR.confirmError}</span>
                    <input placeholder="Confirm Password..." name="confirm" type="password" required/>
                </div>
            </div>
            <br />
            <button type="submit" value="CreateUser" name="btnAction">SIGN UP</button>
            <p style="text-align:right"><a style="color:blue; font-weight: bolder;" href="MainController">Home</a></p>
        </form>
    </body>
</html>

