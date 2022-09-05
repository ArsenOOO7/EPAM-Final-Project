<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<!doctype html>
<html class="h-100">
<c:set var = "title" value = "Change password" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 1rem;">

    <c:if test="${not empty sessionScope.password_change_message}">
        <div class="alert alert-danger mt-4" role="alert">
            <fmt:message key="${sessionScope.password_change_message}" />
        </div>
        <c:remove var="password_change_message" scope="session"/>
    </c:if>

    <h1 class="mb-md-5">
        <fmt:message key="password.change.title" />
    </h1>

    <form action="${pageContext.request.contextPath}/user/edit/password" method="post" class="row d-flex justify-content-center w-50 gap-3">
        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="password" name="old_password" id="old_password" class="form-control" placeholder="<fmt:message key="password.change.old" />" required>
            </div>
        </div>
        <div class="row d-flex justify-content-center">
            <div class="col-md-5">
                <input type="password" name="new_password" id="new_password" class="form-control" placeholder="<fmt:message key="password.change.new" />" required
                       oninvalid="setCustomValidity('<fmt:message key="user.invalid.input.password.pattern" />')"
                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"
                >
            </div>
            <div class="col-md-5">
                <input type="password" name="repeat_password" id="repeat_password" class="form-control" placeholder="<fmt:message key="password.change.repeat" />" required>
            </div>
        </div>
        <div class="row d-flex justify-content-center">
            <div class="col-auto">
                <button class="btn btn-primary"><fmt:message key="password.change.submit" /></button>
            </div>
        </div>
    </form>

    <a href="${pageContext.request.contextPath}/profile" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="all.back" /></a>

</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
