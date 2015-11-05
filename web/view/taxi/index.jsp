<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <div class="row">
        <div class="col-md-10">
            <div id="my-map">

            </div>
        </div>
    </div>

</div> <!-- container -->

<%@include file="/view/include/scripts.jsp"%>
<script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/map.js"></script>
</body>
</html>