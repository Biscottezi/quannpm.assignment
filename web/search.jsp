<%-- 
    Document   : search
    Created on : Mar 18, 2021, 10:03:18 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color="red">
            Welcome, ${sessionScope.LASTNAME}
        </font><br/>
        <a href="logout">Log out</a><br/>
        <h1>Search Page</h1>
        <form action="search">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" /><br/>
        </form><br/>
        
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Last name</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="update">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.username}
                                <input type="hidden" name="txtUsername" value="${dto.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" value="${dto.password}" />
                            </td>
                            <td>
                                ${dto.lastname}
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdmin" value="ON"
                                <c:if test="${dto.role}">
                                    checked="checked"
                                </c:if>
                                    />
                            </td>
                            <td>
                                <c:url var="deleteLink" value="delete">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
                                    <c:param name="txtSearchValue" value="${searchValue}"/>
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                            </td>
                            <td>
                                <input type="submit" name="btAction" value="Update"/>
                                <input type="hidden" name="txtSearchValue" value="${searchValue}" />
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
            </c:if>
            <c:if test="${empty result}">
                <h2>
                    No record is matched!!!
                </h2>
            </c:if>
        </c:if>
    </body>
</html>
