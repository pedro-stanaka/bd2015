<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <title>[BD 2014] Usuários</title>
    </head>
    <body>
        <h1>Lista de usuários</h1>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Login</th>
                    <th colspan="2">Ação</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="u" items="${usuarioList}">
                    <tr>
                        <td><c:out value="${u.id}"/></td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/usuario/read?id=${u.id}">
                                <c:out value="${u.login}"/>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/usuario/update?id=${u.id}">Editar</a>
                        </td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/usuario/delete?id=${u.id}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h1><a href="${pageContext.servletContext.contextPath}/usuario/create">Cadastrar usuário</a></h1>

        <h1><a href="${pageContext.servletContext.contextPath}/">Home page</a></h1>
        
        <%@include file="/view/include/scripts.jsp"%>
    </body>
</html>