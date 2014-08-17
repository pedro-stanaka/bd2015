<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="session"%>
<session:usuario context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_form.css" rel="stylesheet">
        <title>[BD 2014] Usuários</title>
    </head>
    <body>
        <%@include file="/view/include/navbar.jsp"%>

        <div class="container">
            <h2 class="text-center">Edição do usuário <c:out value="${usuario.nome}"/></h2>

            <form class="form-group" action="${pageContext.servletContext.contextPath}/usuario/update" method="POST">
                <label class="h4">ID</label>
                <input class="form-control" type="text" name="id" value="${usuario.id}" disabled>

                <label class="h4">Login</label>
                <input class="form-control" type="text" name="login" value="${usuario.login}">

                <label class="h4">Senha</label>
                <input class="form-control" type="password" name="senha" value="${usuario.senha}">

                <label class="h4">Nome</label>
                <input class="form-control" type="text" name="nome" value="${usuario.nome}">

                <label class="h4">Data de nascimento</label>
                <input class="form-control" type="date" name="nascimento" value="${usuario.nascimento}">

                <input type="hidden" name="id" value="${usuario.id}">

                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Editar</button>
                </div>
            </form>
        </div>

        <session:erro mensagem="${sessionScope.erro}"/>

        <%@include file="/view/include/scripts.jsp"%>
    </body>
</html>