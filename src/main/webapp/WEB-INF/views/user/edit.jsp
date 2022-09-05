<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>
<!doctype html>
<html lang="en" class="h-100">
<c:set var = "title" value = "Edit" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />
<c:set value="${sessionScope.user}" var="user"/>

<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 1rem">

    <c:if test="${not empty sessionScope.edit_error_message}">
        <div class="alert alert-danger mt-4" role="alert">
            <fmt:message key="${sessionScope.edit_error_message}" />
        </div>
        <c:remove var="edit_error_message" scope="session"/>
    </c:if>

    <h1><fmt:message key="edit.title" /></h1>
    <form action="${pageContext.request.contextPath}/user/edit" method="post" enctype="multipart/form-data" class="row d-flex justify-content-center w-50 gap-3">
        <div class="row d-flex justify-content-center">
            <div class="col-md-5">
                <input type="text" class="form-control" name="name" id="name" placeholder="<fmt:message key = "edit.name" />" value="${user.name}" required>
            </div>
            <div class="col-md-5">
                <input type="text" class="form-control" name="surname" id="surname" placeholder="<fmt:message key = "edit.surname" />" value="${user.surname}" required>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="email" name="email" id="email" readonly class="form-control" placeholder="<fmt:message key = "edit.email" />" value="${user.email}" required>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <label for="avatar" class="form-label"><fmt:message key = "edit.birth" /></label>
                <input type="date" class="form-control" name="birth_date" id="birth_date" value="${user.birthDate}" required>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <label for="avatar" class="form-label"><fmt:message key = "edit.avatar" /></label>
                <input type="file" class="form-control"  id="avatar" name="avatar" accept=".jpg, .jpeg, .png">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-auto">
                <button class="btn btn-primary"><fmt:message key = "edit.submit" /></button>
            </div>
        </div>

    </form>

    <a href="${pageContext.request.contextPath}/profile" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="all.back" /></a>

</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
