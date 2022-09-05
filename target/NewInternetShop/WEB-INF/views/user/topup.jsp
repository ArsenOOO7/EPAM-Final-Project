<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<!doctype html>
<html lang="en" class="h-100">
<c:set var = "title" value = "Top up balance" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column">

    <c:if test="${not empty sessionScope.top_up_message}">
        <div class="alert alert-danger mt-4" role="alert">
            <fmt:message key="${sessionScope.top_up_message}" />
        </div>
        <c:remove var="top_up_message" scope="session"/>
    </c:if>

    <form action="${pageContext.request.contextPath}/user/topup" method="post" style="display: flex; flex-direction: column; align-items: center">
        <input type="number" min="1" name="money" id="money" placeholder="<fmt:message key="top.up.value" />" class="mx-auto form-control" required>
        <button type="submit" class="btn btn-primary mt-md-3"><fmt:message key="top.up.submit" /></button>
    </form>

    <a href="${pageContext.request.contextPath}/profile" role="button" tabindex="-1" class="btn btn-success mt-5"><fmt:message key="all.back" /></a>

</div>
<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
