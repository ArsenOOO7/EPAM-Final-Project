<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>
<!doctype html>
<html lang="en" class="h-100">
<c:set var = "title" value = "Error" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div class="container">
    <div class="d-flex align-items-center justify-content-center mt-md-5">
        <div class="text-center">
            <h1 class="text-danger">Opps!</h1>
            <h3 class="display-1 fw-bold">${requestScope['jakarta.servlet.error.status_code']}</h3>
            <p class="fs-3"> ${requestScope['jakarta.servlet.error.exception']}</p>
            <p class="lead">
                <c:choose>
                    <c:when test="${requestScope['jakarta.servlet.error.status_code'] == 404}">
                        Page not found!
                    </c:when>
                    <c:otherwise>
                        ${requestScope['jakarta.servlet.error.message']}
                    </c:otherwise>
                </c:choose>
            </p>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Go Home</a>
        </div>
    </div>
</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
