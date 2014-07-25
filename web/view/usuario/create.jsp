<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <title>[BD 2014] Usuários</title>
    </head>
    <body>
        <h1>Cadastro de usuário</h1>

        <form action="${pageContext.servletContext.contextPath}/usuario/create" method="POST">
            <label>Login:</label><br>
            <input type="text" name="login"><br><br>
            
            <label>Senha:</label><br>
            <input type="password" name="senha"><br><br>
            
            <label>Nome:</label><br>
            <input type="text" name="nome"><br><br>

            <label>Data de nascimento:</label><br>
            <input type="date" name="nascimento"><br><br>

            <input type="submit" value="Enviar">
        </form>

        <h1><a href="${pageContext.servletContext.contextPath}/usuario">Voltar</a></h1>
    </body>
</html>