<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html>
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div class="container d-flex justify-content-center mt-lg-5">
    <div class="btn-group" role="group" aria-label="Basic outlined example">
        <a href="${pageContext.request.contextPath}/cart/carted" role="button" class="btn btn-outline-primary"><fmt:message key="cart.blank.carted" /></a>
        <a href="${pageContext.request.contextPath}/cart/purchased" role="button" class="btn btn-outline-primary"><fmt:message key="cart.blank.purchased" /></a>
        <a href="${pageContext.request.contextPath}/cart/cancelled" role="button" class="btn btn-outline-primary"><fmt:message key="cart.blank.cancelled" /></a>
    </div>
</div>


<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
