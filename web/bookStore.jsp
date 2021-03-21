<%-- 
    Document   : bookStore
    Created on : Mar 20, 2021, 10:51:02 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Welcome to Kinokuniya Bookstore</h1><br/>
        
        <c:set var="bookList" value="${requestScope.BOOKLIST}"/>
        <c:if test="${not empty bookList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${bookList}" varStatus="counter">
                <form action="addToCart">
                    <tr>
                        <td>
                            ${counter.count}
                        </td>
                        <td>
                            ${book.id}
                            <input type="hidden" name="txtBookId" value="${book.id}" />
                        </td>
                        <td>
                            ${book.title}
                        </td>
                        <td>
                            ${book.price}
                        </td>
                        <td>
                            <input type="submit" name="btAction" value="Add to cart"/>
                        </td>
                    </tr>
                </form>
                    </c:forEach>
                </tbody>
            </table>
            
            <form action="viewCart">
                <input type="submit" name="btAction" value="View your cart"/>
            </form>
        </c:if>
        

    </body>
</html>
