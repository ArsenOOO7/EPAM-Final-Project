<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<!doctype html>
<html lang="en" class="h-100">

<c:set var = "title" value = "Login" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 1rem">

    <c:if test="${not empty sessionScope.login_error_message}">
        <fmt:message key="${sessionScope.login_error_message}" var="error">
            <c:if test="${not empty sessionScope.banned}">
                <c:remove var="banned" scope="session"/>
                <fmt:param value="${sessionScope.reason}" />
                <c:remove var="reason" scope="session"/>
                <fmt:param value="${sessionScope.endTime}" />
                <c:remove var="endTime" scope="session"/>
            </c:if>
        </fmt:message>
        <div class="alert alert-danger mt-4" role="alert">
            <c:out value="${error}" />
        </div>
        <c:remove var="login_error_message" scope="session"/>
    </c:if>

    <h1> <fmt:message key = "login.title" /></h1>
    <form action="${pageContext.request.contextPath}/login" method="post" class="row d-flex justify-content-center w-25 gap-3 needs-validation" novalidate>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="text" class="form-control" name="login" id="login" placeholder="<fmt:message key = "login.login" />" required>
                <div class="invalid-feedback">
                    <fmt:message key = "user.invalid.input.login" />
                </div>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="text" class="form-control" name="password" id="password" placeholder="<fmt:message key = "login.password" />" required>
                <div class="invalid-feedback">
                    <fmt:message key = "user.invalid.input.password" />
                </div>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-auto">
                <button class="btn btn-primary"><fmt:message key = "login.submit" /></button>
            </div>
        </div>

    </form>
    <p>
        <fmt:message key = "login.account" /> <a href="${pageContext.request.contextPath}/register"> <fmt:message key = "login.register" /></a>
    </p>
</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
