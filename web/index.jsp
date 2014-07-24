<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/favicon.jsp"%>
        <title>[BD 2014] Início</title>
    </head>
    <body>
        <h1><a href="<c:out value="${pageContext.servletContext.contextPath}"/>/usuario">Usuários</a></h1>
    </body>
</html>