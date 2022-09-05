<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html>
<c:set var = "title" value = "Admin panel" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div class="container">
    <h1><fmt:message key="admin.welcome" /></h1>
    <div class="row text-center" style="margin-top: 4rem;">
        <div class="btn-group justify-content-center mx-auto mt-md-1" role="group">
            <a href="${pageContext.request.contextPath}/admin/users" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="admin.blank.users" /></a>
            <a href="${pageContext.request.contextPath}/admin/users" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="admin.blank.products" /></a>
            <a href="${pageContext.request.contextPath}/admin/users" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="admin.blank.categories" /></a>
        </div>
    </div>
</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
