<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="mensagem" required="true"%>

<c:if test="${not empty sessionScope.erro}">
    <span class="hidden erro">${mensagem}</span>
    <c:remove var="erro" scope="session"/>
</c:if>