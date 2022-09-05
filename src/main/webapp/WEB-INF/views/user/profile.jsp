<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html>
<c:set var = "title" value = "Profile" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div class="container-fluid text-center mt-md-3">
    <div class="row justify-content-center">
        <div class="col-md-3">
            <c:choose>
                <c:when test="${user.avatarId > 0}">
                    <img style="width: 20rem; height: 20rem" class="rounded-circle img-fluid"
                         src="${pageContext.request.contextPath}/image/${user.avatarId}" alt="">
                </c:when>
                <c:otherwise>
                    <img style="width: 20rem; height: 20rem" class="rounded-circle img-fluid"
                         src="https://cdn4.iconfinder.com/data/icons/e-commerce-181/512/477_profile__avatar__man_-512.png" alt="">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-5">
            <h1><c:out value="${user.name}" /> <c:out value="${user.surname}" /></h1>

            <div style="margin-top: 1rem; display: flex; flex-direction: column; gap: .2rem; align-items: center;">
                <div class="input-group w-75">
                    <button  disabled class="w-25 btn btn-outline-secondary" type="button" id="button-email"><fmt:message key="user.profile.email" /></button>
                    <input disabled type="email" class="form-control" value="${user.email}">
                </div>
                <div class="input-group w-75">
                    <button  disabled class="w-25 btn btn-outline-secondary" type="button" id="button-birthdate"><fmt:message key="user.profile.birthdate" /></button>
                    <input disabled type="text" class="form-control" value="${user.birthDate}">
                </div>
                <div class="input-group w-75">
                    <button  disabled class="w-25 btn btn-outline-secondary" type="button" id="button-balance"><fmt:message key="user.profile.balance" /></button>
                    <input disabled type="number" class="form-control" value="${user.balance}">
                </div>
            </div>

            <div class="row text-center" style="margin-top: 4rem;">
                <div class="btn-group justify-content-center" role="group">
                    <a href="${pageContext.request.contextPath}/user/edit" role="button" tabindex="-1" class="btn btn-primary"><fmt:message key="user.profile.edit" /></a>
                    <a href="${pageContext.request.contextPath}/user/edit/password" role="button" tabindex="-1" class="btn btn-warning"><fmt:message key="user.profile.password.change" /></a>
                    <a href="${pageContext.request.contextPath}/user/topup" role="button" tabindex="-1" class="btn btn-danger"><fmt:message key="user.profile.top.up" /></a>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
