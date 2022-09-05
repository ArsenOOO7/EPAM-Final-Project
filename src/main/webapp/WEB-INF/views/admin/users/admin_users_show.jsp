<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html>
<c:set var = "title" value = "Admin panel" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<c:set var="user" value="${requestScope.user}" />

<div class="container-fluid text-center mt-md-3">
    <div class="row justify-content-center">
        <div class="col-md-3">
            <img style="width: 20rem; height: 20rem" class="rounded-circle img-fluid"
                 src="${pageContext.request.contextPath}/image/${user.avatarId}" alt="">
        </div>
        <div class="col-md-5">
            <h1><c:out value="${user.name}" /> <c:out value="${user.surname}" /></h1>

            <div style="margin-top: 1rem; display: flex; flex-direction: column; gap: .2rem; align-items: center;">
                <div class="input-group w-75">
                    <button  disabled class="w-25 btn btn-outline-secondary" type="button" id="button-email"><fmt:message key="admin.users.show.email" /></button>
                    <input disabled type="email" class="form-control" value="${user.email}">
                </div>
                <div class="input-group w-75">
                    <button  disabled class="w-25 btn btn-outline-secondary" type="button" id="button-birthdate"><fmt:message key="admin.users.show.birthday" /></button>
                    <input disabled type="text" class="form-control" value="${user.birthDate}">
                </div>
                <div class="input-group w-75">
                    <button  disabled class="w-25 btn btn-outline-secondary" type="button" id="button-balance"><fmt:message key="admin.users.show.balance" /></button>
                    <input disabled type="number" class="form-control" value="${user.balance}">
                </div>
            </div>



            <div class="row text-center" style="margin-top: 4rem;">
                <c:choose>
                    <c:when test="${user.isBanned()}">
                        <a href="${pageContext.request.contextPath}/admin/users/unban/${user.id}" role="button" tabindex="-1" class="btn btn-success w-75 mx-auto"><fmt:message key="admin.users.show.unban" /></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/admin/users/ban/${user.id}" role="button" tabindex="-1" class="btn btn-danger  w-75 mx-auto"><fmt:message key="admin.users.show.ban" /></a>
                    </c:otherwise>
                </c:choose>
                <div class="btn-group justify-content-center mx-auto mt-md-1" role="group">
                    <a href="${pageContext.request.contextPath}/admin/users/cart/carted/${user.id}" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="cart.blank.carted" /></a>
                    <a href="${pageContext.request.contextPath}/admin/users/cart/purchased/${user.id}" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="cart.blank.purchased" /></a>
                    <a href="${pageContext.request.contextPath}/admin/users/cart/cancelled/${user.id}" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="cart.blank.cancelled" /></a>
                </div>
            </div>

        </div>
    </div>
    <a href="${pageContext.request.contextPath}/admin/users" role="button" tabindex="-1" style="width: 7rem;" class="mt-md-5 mx-auto btn btn-primary"><fmt:message key="all.back" /></a>
</div>
<%@ include file = "/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
