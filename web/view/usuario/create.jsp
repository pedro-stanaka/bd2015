<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="session" %>
<session:usuario context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/view/include/head.jsp" %>
    <link href="${pageContext.servletContext.contextPath}/assets/vendor/css/datepicker.min.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
    <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_form.css" rel="stylesheet">
    <title>[BD 2015] Usuários: cadastro</title>
</head>
<body>
    <%@include file="/view/include/navbar.jsp" %>

    <div class="container">
        <h2 class="text-center">Inserção de um novo usuário</h2>

        <h3>${pageContext.servletContext.contextPath}</h3>

        <form
             class="form"
             action="${pageContext.servletContext.contextPath}/usuario/create"
             enctype="multipart/form-data"
             method="POST">
            <div class="form-group">
                <label class="control-label" for="usuario-login">Login</label>
                <input id="usuario-login" class="form-control" type="text" name="login" required autofocus/>
                <p class="help-block"></p>
            </div>


            <div class="form-group">
                <label class="control-label" for="usuario-senha">Senha</label>
                <input id="usuario-senha" class="form-control" type="password" name="senha" required/>
            </div>


            <div class="form-group">
                <label for="usuario-nome" class="control-label">Nome</label>
                <input id="usuario-nome" class="form-control" type="text" name="nome" required/>
            </div>


            <div class="form-group">
                <label for="usuario-nasc" class="control-label">Data de nascimento</label>
                <input id="usuario-nasc" class="form-control datepicker" type="text" name="nascimento"
                       placeholder="dd/mm/yyyy"
                       pattern="\d{2}/\d{2}/\d{4}" required/>
            </div>

            <div class="form-group">
                <label for="usuario-avatar">Foto do perfil</label>
                <input type="file"
                       class="form-control" id="usuario-avatar"
                       name="avatar"
                       accept="image/*" />
            </div>

            <div class="text-center">
                <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
            </div>
        </form>

        <session:erro alertClass="usuario-form-alert" errorMessage="${sessionScope.error}"/>
    </div>

    <%@include file="/view/include/scripts.jsp" %>
    <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap-datepicker.pt-BR.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
</body>
</html>