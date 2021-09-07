<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="images/Flash.png" type="image/gif" />
        <title>Admin Action</title>
    </head>
    <body>
        <c:if test="${sessionScope.USER_DATA.role.roleID != 'AD' || sessionScope.USER_DATA == null}">
            <c:redirect url="MainController" />
        </c:if>
        <h2 style="color: purple; font-style: italic">Hello ${sessionScope.USER_DATA.userName}</h2>

        <form style="padding-bottom: 1%" action="MainController" >
            <input style="width: 30%; border: 2px solid black" type="search" placeholder="Search..." name="search">
            <button name="btnAction" value="SearchAD">Search With Name</button>
        </form>
        <c:if test="${not empty requestScope.ERROR}">
            <h3 style="color: red; font-style: italic">${requestScope.ERROR}</h3>
        </c:if>
        <c:if test="${not empty requestScope.ERROR_ADMIN.errorID}">
            <span style="color: red; font-style: italic">${requestScope.ERROR_ADMIN.errorID}</span><br>
        </c:if>
        <c:if test="${not empty requestScope.ERROR_ADMIN.errorName}">
            <span style="color: red; font-style: italic">${requestScope.ERROR_ADMIN.errorName}</span><br>
        </c:if>
        <c:if test="${not empty requestScope.ERROR_ADMIN.errorDetail}">
            <span style="color: red; font-style: italic">${requestScope.ERROR_ADMIN.errorDetail}</span><br>
        </c:if>
        <c:if test="${not empty requestScope.ERROR_ADMIN.errorImage}">
            <span style="color: red; font-style: italic">${requestScope.ERROR_ADMIN.errorImage}</span><br>
        </c:if>
        <c:if test="${not empty requestScope.ERROR_ADMIN.errorQuantity}">
            <span style="color: red; font-style: italic">${requestScope.ERROR_ADMIN.errorQuantity}</span><br>
        </c:if>
        <c:if test="${not empty requestScope.ERROR_ADMIN.errorPrice}">
            <span style="color: red; font-style: italic">${requestScope.ERROR_ADMIN.errorPrice}</span><br>
        </c:if>
        <c:if test="${not empty requestScope.ERROR_ADMIN.errorSale}">
            <span style="color: red; font-style: italic">${requestScope.ERROR_ADMIN.errorSale}</span><br>
        </c:if>
        <c:if test="${not empty requestScope.ERROR_ADMIN.errorCateID}">
            <span style="color: red; font-style: italic">${requestScope.ERROR_ADMIN.errorCateID}</span>
        </c:if>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Brand</th>
                <th colspan="2">Image</th>

                <th>Price</th>
                <th>Is sold</th>
                <th colspan="2">Option</th>
            </tr>
            <c:if test="${empty sessionScope.PRODUCT}"> <tr><td style="border: 0"><p style="font-size: 20px; font-weight: bolder">Empty Product</p></td></tr></c:if>

            <c:forEach var="pro" items="${sessionScope.PRODUCT}">
                <tr>
                <form action="MainController" method="POST" enctype="multipart/form-data">
                    <td><input type="text" name="id" value="${pro.productID}" readonly></td>
                    <td><input type="text" name="name" value="${pro.productName}" required></td>
                    <td><textarea type="text" name="detail" value="${pro.detail}" required>${pro.detail}</textarea></td>
                    <td><input type="number" name="quantity" value="${pro.quantity}" required></td>
                    <td style="width: 7%">
                        <select name="cateID" style="width: 100%">
                            <c:forEach var="br" items="${sessionScope.BRAND}">
                                <option ${pro.category.categoryID eq br.categoryID?"selected":""} value="${br.categoryID}">${br.categoryID}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td style="width: 14%"><img style="width: 75%;" src="images/${pro.image}" alt="${pro.image}"/></td>
                    <td><input type="file" name="photo"></td>
                    <td><input type="number" name="price" value="${pro.price}" required></td>
                    <td style="width: 5%">
                        <select name="sale" style="width: 100%">
                            <option value="${pro.sale}">${pro.sale eq true?"Yes":"No"}</option>
                            <option value="${pro.sale eq true?false:true}">${pro.sale eq true?"No":"Yes"}</option>
                        </select>
                    </td>
                    <td style="width: 55px; background-color: #EFEFEF"><button type="submit" value="Update" name="btnAction" style="cursor: pointer; width: 55px; color: blue; border: 1px solid black">Update</button></td>
                    <td style="background-color: #EFEFEF"><a href="MainController?id=${pro.productID}&btnAction=Delete" style="width: 100%;color: blue; text-decoration: none; border: 1px solid black; border-radius: 1px" onclick="return confirm('Are you sure?')">Delete</a></td>
                </form>           
            </tr>
        </c:forEach>
    </table>
    <a id="out" href="MainController" style="text-decoration: none"><button style="width: 10%; color: blue; cursor: pointer">Home</button></a>
    <a href="MainController?btnAction=InsertPage" style="text-decoration: none"><button style="width: 10%; color: blue; cursor: pointer">Add Product</button></a>
    <a id="out" href="MainController?btnAction=Logout" style="text-decoration: none"><button style="width: 10%; color: blue; cursor: pointer">Logout</button></a>
    <style>
        td{
            text-align: center;
        }
        input{
            border: 0;
            width: 96%;
        }
        a:visited{
            color: blue;
            text-decoration: none;
        }

    </style>
</body>
</html>