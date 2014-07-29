<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.usuario}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/favicon.jsp"%>
        <title>[BD 2014] Início</title>
    </head>
    <body>
        <h1>Bem-vindo, <c:out value="${usuario.nome}"/>!</h1>

        <h1><a href="${pageContext.servletContext.contextPath}/usuario">Usuários</a></h1>

        <h1><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></h1>
    </body>
</html>