<%-- 
    Document   : productform_upload
    Created on : Jul 9, 2021, 12:35:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="images/Flash.png" type="image/gif" />
        <title>Insert Product</title>
    </head>
    <body>
        <c:if test="${sessionScope.USER_DATA.role.roleID != 'AD' || sessionScope.USER_DATA == null}">
            <c:redirect url="MainController" />
        </c:if>
        <a href="MainController">Home</a>
        <a href="MainController?btnAction=ViewAD">View Product</a>
        <h1 style="color: red">${requestScope.ERROR}</h1>
        <form action="MainController" method="POST" enctype="multipart/form-data">
            <table width="1000px" border="0px solid" >
                <tr>
                    <td>ID </td>
                    <td>
                        : <input type="text" name="id" placeholder="ID product..." required>
                        <span style="color: red; width: auto">${requestScope.ERROR_ADMIN.errorID}</span>
                    </td>
                </tr>
                <tr>
                    <td>Name </td>
                    <td>
                        : <input type="text" name="name" placeholder="Name product..." required>
                        <span style="color: red">${requestScope.ERROR_ADMIN.errorName}</span>
                    </td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td>
                        : <textarea name="detail" placeholder="Detail..." required></textarea>
                        <span style="color: red">${requestScope.ERROR_ADMIN.errorDetail}</span>
                    </td>
                </tr>
                <tr>
                    <td>Quantity </td>
                    <td>
                        : <input type="number" name="quantity" placeholder="Quantity..." required>
                        <span style="color: red">${requestScope.ERROR_ADMIN.errorQuantity}</span>
                    </td>
                </tr>
                <tr>
                    <td>Price </td>
                    <td>
                        : <input type="number" name="price" placeholder="Price..." required>
                    <span style="color: red">${requestScope.ERROR_ADMIN.errorPrice}</span>
                    </td>
                </tr>
                <tr>
                    <td>Is sold? </td>
                    <td>
                        :  Yes <input type="radio" name="sale" value="true" required>
                        NO  <input type="radio" name="sale" value="false" required>
                        <span style="color: red">${requestScope.ERROR_ADMIN.errorSale}</span>
                    </td>
                </tr>
                <tr>
                    <td>Brand </td>
                    <td>
                        : <select name="cateID">
                            <c:forEach var="cate" items="${sessionScope.BRAND}">
                                <option value="${cate.categoryID}">${cate.categoryID}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">${requestScope.ERROR_ADMIN.errorCateID}</span>
                    </td>
                </tr>
                <tr>
                    <td>Image URL </td>
                    <td>
                        : <input type="file" name="photo" required>
                        <span style="color: red">${requestScope.ERROR_ADMIN.errorImage}</span>
                    </td>
                </tr>
                <tr>
                    <td>      
                        <input type="submit" name="btnAction" value="Insert"> 
                        <input type="reset" value="Reset">
                    </td>
                </tr>
            </table>
        </form>
    </body>
    <style>
        a{
            margin-right: 10px;
            color: blue;
            font-size: 20px;
            border: 1px solid blue;
            text-decoration: none;
        }
        a:hover{
            color: black;
            font-weight: bolder;
            background-color: blueviolet;
        }
    </style>
</html>
