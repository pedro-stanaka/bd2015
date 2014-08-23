<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="alertClass" required="true"%>
<%@attribute name="errorMessage" required="true"%>
<%@attribute name="rollbackErrorMessage" required="false"%>

<c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger fade in error-message <c:out value="${alertClass}"/>">
        <button class="close" type="button" data-dismiss="alert">&times;</button>
        <c:out value="${errorMessage}"/>
    </div>

    <c:remove var="error" scope="session"/>
</c:if>

<c:if test="${not empty sessionScope.rollbackError}">
    <div class="alert alert-danger fade in rollback-error-message <c:out value="${alertClass}"/>">
        <button class="close" type="button" data-dismiss="alert">&times;</button>
        <c:out value="${rollbackErrorMessage}"/>
    </div>

    <c:remove var="rollbackError" scope="session"/>
</c:if>