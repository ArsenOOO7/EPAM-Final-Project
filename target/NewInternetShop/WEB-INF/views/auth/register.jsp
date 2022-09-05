<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<!doctype html>
<html lang="en" class="h-100">

<c:set var = "title" value = "Register" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 1rem">

    <c:if test="${not empty sessionScope.register_error_message}">
        <div class="alert alert-danger mt-4" role="alert">
            <fmt:message key="${sessionScope.register_error_message}" />
        </div>
        <c:remove var="register_error_message" scope="session"/>
    </c:if>

    <h1><fmt:message key = "register.title" /></h1>
    <form action="${pageContext.request.contextPath}/register" method="post" class="row d-flex justify-content-center w-50 gap-3" enctype="multipart/form-data">

        <div class="row d-flex justify-content-center">
            <div class="col-md-5">
                <input type="text" name="name" id="name" class="form-control" placeholder="<fmt:message key = "register.name" />">
            </div>
            <div class="col-md-5">
                <input type="text" name="surname" id="surname" class="form-control" placeholder="<fmt:message key = "register.surname" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="email" name="email" id="email" class="form-control" placeholder="<fmt:message key = "register.email" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="login" name="login" id="login" class="form-control" placeholder="<fmt:message key = "register.login" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-5">
                <input type="text" class="form-control" name="password" id="password" placeholder="<fmt:message key = "register.password" />">
            </div>
            <div class="col-md-5">
                <input type="text" class="form-control" name="repeat_password" id="repeat_password" placeholder="<fmt:message key = "register.repeat.password" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <label for="avatar" class="form-label"><fmt:message key = "register.birth" /></label>
                <input type="date" class="form-control" name="birth_date" id="birth_date">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <label for="avatar" class="form-label"><fmt:message key = "register.avatar" /></label>
                <input type="file" class="form-control"  id="avatar" name="avatar" accept=".jpg, .jpeg, .png">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-auto">
                <button class="btn btn-primary"><fmt:message key = "register.submit" /></button>
            </div>
        </div>

    </form>

    <p>
        <fmt:message key = "register.account" /> <a href="${pageContext.request.contextPath}/login"> <fmt:message key = "register.sign.in" /></a>
    </p>

</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
