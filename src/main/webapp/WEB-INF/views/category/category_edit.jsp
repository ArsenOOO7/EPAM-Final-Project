<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html class="h-100">
<c:set var = "title" value = "Edit category" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />


<c:set var="category" value="${requestScope.category}"/>

<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 1rem">

    <c:if test="${not empty sessionScope.product_edit_error}">
        <div class="alert alert-danger mt-4" role="alert">
            <fmt:message key="${sessionScope.category_error}" />
        </div>
        <c:remove var="category_error" scope="session"/>
    </c:if>

    <h1><fmt:message key="category.edit.title" /></h1>
    <form action="${pageContext.request.contextPath}/category/edit/${category.id}" method="post" class="row d-flex justify-content-center w-50 gap-3">

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="text" class="form-control" name="identifier" id="identifier" value="${category.identifier}" placeholder="<fmt:message key="category.work.identifier" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="text" class="form-control" name="locale_ua" id="locale_ua" value="${category.localeUA}" placeholder="<fmt:message key="category.work.locale.ua" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="text" class="form-control" name="locale_en" id="locale_en" value="${category.localeEN}" placeholder="<fmt:message key="category.work.locale.en" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-auto">
                <button type="submit" class="btn btn-primary"><fmt:message key="category.work.edit.submit" /></button>
            </div>
        </div>

    </form>

</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
