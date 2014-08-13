<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="session"%>
<session:usuario context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
        <title>[BD 2014] Início</title>
    </head>
    <body>
        <%@include file="/view/include/navbar.jsp"%>

        <div class="container">
            <div class="jumbotron">
                <h1>Bem-vindo, <c:out value="${usuario.nome}"/>!</h1>
                <p>Este é um exemplo inicial para o trabalho da disciplina Bancos de Dados A.</p>
                <p>
                    <a class="btn btn-lg btn-primary"
                       href="https://github.com/oliveiraph17/bd2014"
                       target="_blank"
                    >
                        Download do projeto
                    </a>
                </p>
            </div>
        </div>

        <session:erro mensagem="${sessionScope.erro}"/>

        <%@include file="/view/include/scripts.jsp"%>
    </body>
</html>