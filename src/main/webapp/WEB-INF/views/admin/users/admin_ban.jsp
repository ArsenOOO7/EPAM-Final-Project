<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html class="h-100">
<c:set var = "title" value = "Admin Ban" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<c:set value="${requestScope.id}" var="id" />

<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 1rem">
    <h1><fmt:message key="admin.ban.title" /></h1>
    <form action="${pageContext.request.contextPath}/admin/users/ban/${id}" method="post" class="row d-flex justify-content-center w-50 gap-3">

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="text" name="reason" id="reason" class="form-control" placeholder="<fmt:message key="admin.ban.reason" />">
            </div>
        </div>
        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="number" min="1" name="end_time" id="end_time" class="form-control" placeholder="<fmt:message key="admin.ban.time" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-auto">
                <button class="btn btn-primary"><fmt:message key="admin.ban.submit" /></button>
            </div>
        </div>

    </form>
</div>

</body>
</html>
