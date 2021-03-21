<%-- 
    Document   : viewCart
    Created on : Mar 21, 2021, 10:25:24 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your cart</title>
    </head>
    <body>
        <c:set var="bookCart" value="${sessionScope.CART}"/>
        <c:if test="${not empty bookCart}">
        <c:set var="items" value="${bookCart.items}"/>
        <c:if test="${not empty items}">
            <h1>Your book cart includes</h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="removeBook">
                    <c:forEach var="book" items="${items}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${book.key}
                            </td>
                            <td>
                                ${book.value.title}
                            </td>
                            <td>
                                ${book.value.quantity}
                            </td>
                            <td>
                                ${book.value.quantity * book.value.price}
                            </td>
                            <td>
                                <input type="checkbox" name="chkRemove" value="${book.key}"/>
                            </td>
                        </tr>
                    </c:forEach>
                        <tr>
                            <td colspan="5">
                                <a href="bookStore">Add more books to your cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Remove" name="btAction" />
                            </td>
                        </tr>
                </form>
                </tbody>
            </table>
            
            <form action="checkout" method="POST">
                Name: <input type="text" name="txtCustomerName" value=""/><br/>
                Address: <textarea name="txtCustomerAddress" value=""></textarea><br/>
                <input type="submit" name="btAction" value="Checkout"/>
            </form>
        </c:if>
        </c:if>
        
        <c:if test="${empty bookCart}">
            <h2>Your cart is lost!!!</h2>
        </c:if>
    </body>
</html>
