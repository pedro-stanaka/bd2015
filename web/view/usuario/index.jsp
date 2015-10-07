<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="session"%>
<session:usuario context="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/view/include/head.jsp"%>
        <link href="${pageContext.servletContext.contextPath}/assets/css/navbar.css" rel="stylesheet">
        <link href="${pageContext.servletContext.contextPath}/assets/css/usuario_index.css" rel="stylesheet">
        <title>[BD 2015] Usuários</title>
    </head>
    <body>
        <%@include file="/view/include/navbar.jsp"%>

        <div class="container">
            <div class="text-center div_inserir_excluir">
                <a class="btn btn-lg btn-primary" href="${pageContext.servletContext.contextPath}/usuario/create">
                    Inserir novo usuário
                </a>

                <button class="btn btn-lg btn-warning" data-toggle="modal" data-target=".modal_excluir_usuarios">
                    Excluir múltiplos usuários
                </button>
            </div>

            <form class="form_excluir_usuarios" action="${pageContext.servletContext.contextPath}/usuario/delete" method="POST">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-lg-2 h4">ID</th>
                            <th class="col-lg-5 h4">Login</th>
                            <th class="col-lg-4 h4 text-center">Ação</th>
                            <th class="col-lg-1 h4 text-center">Excluir?</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="usuario" items="${requestScope.usuarioList}">
                        <tr>
                            <td>
                                <span class="h4"><c:out value="${usuario.id}"/></span>
                            </td>
                            <td>
                                <a class="link_visualizar_usuario" href="#" data-href="${pageContext.servletContext.contextPath}/usuario/read?id=${usuario.id}">
                                    <span class="h4"><c:out value="${usuario.login}"/></span>
                                </a>
                            </td>
                            <td class="text-center">
                                <a class="btn btn-default"
                                   href="${pageContext.servletContext.contextPath}/usuario/update?id=${usuario.id}"
                                   data-toggle="tooltip"
                                   data-original-title="Editar">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                </a>
                                <a class="btn btn-default link_excluir_usuario"
                                   href="#"
                                   data-href="${pageContext.servletContext.contextPath}/usuario/delete?id=${usuario.id}"
                                        data-toggle="tooltip"
                                        data-original-title="Excluir">
                                    <i class="glyphicon glyphicon-trash"></i>
                                </a>
                            </td>
                            <td class="text-center">
                                <input class="checkbox-inline" type="checkbox" name="delete" value="${usuario.id}" />
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>

            <session:erro alertClass="usuario-index-alert" errorMessage="${sessionScope.error}" rollbackErrorMessage="${sessionScope.rollbackError}"/>
        </div>

        <div class="modal modal-visualizar-usuario">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        <h4 class="modal-title">Detalhes</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-8">
                                    <p class="p_id"></p>
                                    <p class="p_login"></p>
                                    <p class="p_nome"></p>
                                    <p class="p_nascimento"></p>
                                </div>
                                <div class="col-md-4">
                                    <a href="#" class="thumbnail">
                                        <img class="usuario-img"
                                             src="${pageContext.request.contextPath}/assets/img/default_avatar.png"/>
                                    </a>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" type="button" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade modal_excluir_usuario">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        <h4 class="modal-title">Confirmação</h4>
                    </div>
                    <div class="modal-body">
                        <p>Tem certeza de que deseja excluir este usuário?</p>
                    </div>
                    <div class="modal-footer">
                        <a class="btn btn-danger link_confirmacao_excluir_usuario">Sim</a>
                        <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade modal_excluir_usuarios">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" type="button" data-dismiss="modal"><span>&times;</span></button>
                        <h4 class="modal-title">Confirmação</h4>
                    </div>
                    <div class="modal-body">
                        <p>Tem certeza de que deseja excluir os usuários selecionados?</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-danger button_confirmacao_excluir_usuarios" type="button">Sim</button>
                        <button class="btn btn-primary" type="button" data-dismiss="modal">Não</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="/view/include/scripts.jsp"%>
        <script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
    </body>
</html>