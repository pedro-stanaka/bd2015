<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/favicon.jsp"%>
        <title>[BD 2014] Alunos</title>
    </head>
    <body>
        <h1>Edição do usuário <c:out value="${usuario.nome}"/></h1>

        <form action="${pageContext.servletContext.contextPath}/usuario/update" method="POST">
            <label>ID:</label><br>
            <input type="text" name="id_disabled" value="${usuario.id}" disabled><br><br>
            
            <label>Login:</label><br>
            <input type="text" name="login" value="${usuario.login}"><br><br>
            
            <label>Senha:</label><br>
            <input type="password" name="senha" value="${usuario.senha}"><br><br>

            <label>Nome:</label><br>
            <input type="text" name="nome" value="${usuario.nome}"><br><br>

            <label>Data de nascimento:</label><br>
            <input type="date" name="nascimento" value="${usuario.nascimento}"><br><br>

            <input type="hidden" name="id" value="${usuario.id}">

            <input type="submit" value="Enviar">
        </form>

        <h1><a href="${pageContext.servletContext.contextPath}/usuario">Voltar</a></h1>
    </body>
</html>