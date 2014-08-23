<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="session"%>
<session:usuario context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <link href="${pageContext.servletContext.contextPath}/assets/vendor/css/datepicker.min.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_form.css" rel="stylesheet">
        <title>[BD 2014] Usuários</title>
    </head>
    <body>
        <%@include file="/view/include/navbar.jsp"%>

        <div class="container">
            <h2 class="text-center">Inserção de um novo usuário</h2>

            <form class="form-group" action="${pageContext.servletContext.contextPath}/usuario/create" method="POST">
                <label class="h4">Login</label>
                <input class="form-control" type="text" name="login" required autofocus>

                <label class="h4">Senha</label>
                <input class="form-control" type="password" name="senha" required>

                <label class="h4">Nome</label>
                <input class="form-control" type="text" name="nome" required>

                <label class="h4">Data de nascimento</label>
                <input class="form-control datepicker" type="text" name="nascimento" placeholder="dd/mm/yyyy" pattern="\d{2}/\d{2}/\d{4}" required>

                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Inserir</button>
                </div>
            </form>

            <session:erro alertClass="usuario-form-alert" errorMessage="${sessionScope.error}"/>
        </div>

        <%@include file="/view/include/scripts.jsp"%>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
    </body>
</html>