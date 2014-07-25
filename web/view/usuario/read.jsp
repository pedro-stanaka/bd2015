<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <title>[BD 2014] Usuários</title>
    </head>
    <body>
        <h1>Visualização do usuário <c:out value="${usuario.nome}"/></h1>
        
        <ul>
            <li>ID: <c:out value="${usuario.id}"/></li>
            <li>Login: <c:out value="${usuario.login}"/></li>
            <li>Senha: <c:out value="${usuario.senha}"/></li>
            <li>Nome: <c:out value="${usuario.nome}"/></li>
            <li>Data de nascimento: <c:out value="${usuario.nascimento}"/></li>
        </ul>
        
        <h1><a href="${pageContext.servletContext.contextPath}/usuario">Voltar</a></h1>
    </body>
</html>