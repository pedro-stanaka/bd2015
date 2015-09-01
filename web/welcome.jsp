<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="session"%>
<session:usuario context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"  %>
        <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
        <title>[BD 2015] Início</title>
    </head>
    <body>
        
        <%@include file="/view/include/navbar.jsp"%>
        <a href="https://github.com/pedro-stanaka/bd2015" target="_blank"><img style="position: absolute; top: 51px; left: 0; border: 0;" src="https://camo.githubusercontent.com/567c3a48d796e2fc06ea80409cc9dd82bf714434/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f6c6566745f6461726b626c75655f3132313632312e706e67" alt="Fork me on GitHub" data-canonical-src="https://s3.amazonaws.com/github/ribbons/forkme_left_darkblue_121621.png"></a>
        <div class="container">
            
            <div class="jumbotron">
                <h1>Bem-vindo,
                <jsp:useBean id="usuario" scope="request" type="model.Usuario"/>
                <c:out value="${usuario.nome}"/>!</h1>
                <p>Este é um exemplo de cadastro de usuários para o trabalho da disciplina Bancos de Dados A.</p>
                <p>
                    <a class="btn btn-lg btn-primary" href="https://github.com/pedro-stanaka/bd2015" target="_blank">
                        Saiba mais...
                    </a>
                </p>
            </div>
        </div>

        <%@include file="/view/include/scripts.jsp"%>
    </body>
</html>