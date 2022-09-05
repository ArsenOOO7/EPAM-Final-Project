<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>
<!doctype html>
<html class="h-100" lang="en">

<c:set var = "title" value = "Categories" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<c:set var="categories" value="${requestScope.categories}" />
<div style="height: 100%; display: flex; align-items: center; flex-direction: column; gap: 1rem">

    <h1><fmt:message key="categories.list.title" /></h1>
    <a href="${pageContext.request.contextPath}/category/add" role="button" tabindex="-1" class="btn btn-primary"><fmt:message key="categories.list.add" /></a>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="categories.list.id" /></th>
                <th scope="col"><fmt:message key="categories.list.identifier" /></th>
                <th scope="col"><fmt:message key="categories.list.edit" /></th>
                <th scope="col"><fmt:message key="categories.list.remove" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <th scope="row"><c:out value="${category.id}" /></th>
                    <td><c:out value="${category.identifier}" /></td>
                    <td><a href="${pageContext.request.contextPath}/category/edit/${category.id}">Edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/category/delete/${category.id}">Remove</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
