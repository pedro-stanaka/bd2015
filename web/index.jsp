<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/favicon.jsp"%>
        <title>[BD 2014] Login</title>
    </head>
    <body>
        <form action="${pageContext.servletContext.contextPath}/login" method="POST">
            <label>Login:</label><br>
            <input type="text" name="login"><br><br>

            <label>Senha:</label><br>
            <input type="password" name="senha"><br><br>

            <input type="submit" value="Login">
        </form>
    </body>
</html>