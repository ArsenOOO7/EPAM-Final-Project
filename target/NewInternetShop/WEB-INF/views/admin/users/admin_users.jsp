<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<!doctype html>
<html lang="en" class="h-100">
<c:set var = "title" value = "Admin panel" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<c:set var="users" value="${requestScope.users}" />
<c:set var="page" value="${requestScope.page}" />
<c:set var="pages" value="${requestScope.pages}" />
<c:set var="query" value="${requestScope.query}" />

<div style="height: 100%; display: flex; align-items: center; flex-direction: column; gap: 1rem">

    <h1>Users</h1>
    <form action="${pageContext.request.contextPath}/admin/users" class="row d-flex justify-content-center w-50 gap-3">

        <div class="row d-flex justify-content-center ">
            <div class="col-md-10">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="name" id="name" placeholder="User name">
                    <button class="btn btn-outline-secondary" type="submit" id="button-search">Search</button>
                </div>
            </div>
            <div class="col-auto mx-auto">
                <div class="form-check text-center">
                    <label class="form-check-label" for="flexCheckDefault">
                        Only banned
                    </label>
                    <input class="form-check-input" type="checkbox" name="banned" value="" id="flexCheckDefault">
                </div>
            </div>
        </div>
    </form>

    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="admin.users.table.id" /></th>
                <th scope="col"><fmt:message key="admin.users.table.name" /></th>
                <th scope="col"><fmt:message key="admin.users.table.surname" /></th>
                <th scope="col"><fmt:message key="admin.users.table.banned" /></th>
                <th scope="col"><fmt:message key="admin.users.table.show" /></th>
            </tr>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.surname}" /></td>
                    <td><fmt:message key="admin.users.table.banned.${user.isBanned()}" /></td>
                    <td><a href="${pageContext.request.contextPath}/admin/users/show/${user.id}"><fmt:message key="admin.users.table.show" /></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<c:set var="path" value="/admin/users" />
<%@ include file ="/WEB-INF/jspf/pages.jspf" %>
<%@ include file = "/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
