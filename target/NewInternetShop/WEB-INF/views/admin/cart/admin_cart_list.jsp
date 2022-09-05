<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html>
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<c:set var="list" value="${requestScope.carts}" />
<c:set var="page" value="${requestScope.page}" />
<c:set var="pages" value="${requestScope.pages}" />
<c:set var="type" value="${requestScope.type}" />
<c:set var="id" value="${requestScope.id}" />
<c:set var = "statuses" value="${requestScope.statuses}" />

<div class="container">

    <c:if test="${not empty sessionScope.cart_error_message}">
        <div class="alert alert-danger mt-4" role="alert">
            <fmt:message key="${sessionScope.cart_error_message}" />
        </div>
        <c:remove var="product_add_error" scope="session"/>
    </c:if>

    <h1 class="text-center mt-md-3"><fmt:message key="cart.title.${type}" /></h1>

    <c:if test="${not empty list}">

        <div class="row row-cols-1 row-cols-md-4 g-5">
            <c:forEach var="cart" items="${list}">
                <c:set var = "product" value="${cart.product}" />
                <div class="col">
                    <div class="card h-100" style="width: 20rem;">
                        <img src="${pageContext.request.contextPath}/image/${product.previewImageId}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><a href="${pageContext.request.contextPath}/product/show/${product.id}"><c:out value="${product.shortName}" /></a></h5>
                            <p class="card-text">${cart.date}</p>
                            <p class="card-text">${cart.price}$ / ${cart.amount}</p>
                        </div>
                        <div class="card-footer">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                <fmt:message key="admin.user.cart.change" />
                            </button>

                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="admin.user.cart.change.status" /></h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="${pageContext.request.contextPath}/admin/users/cart/edit/${cart.id}" method="post" class="text-center">
                                                <select name="status" id="status" class="form-select mt-md-2">
                                                    <c:forEach var="status" items="${statuses}">
                                                        <c:if test="${status.id > 0}">
                                                            <option value="${status.id}"
                                                                <c:if test="${status.id == cart.status.id}">
                                                                    selected
                                                                </c:if>
                                                            ><fmt:message key="cart.status.${status.identifier}" /></option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <button class="btn btn-primary mx-auto mt-md-2 text-center"><fmt:message key="admin.user.cart.change" /></button>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:set var="path" value="/admin/users/cart/${type}/${id}" />
        <%@ include file ="/WEB-INF/jspf/pages.jspf" %>
    </c:if>
    <c:if test="${empty list}">
        <h1 class="text-center mt-md-5">
            <c:if test="${type.equals('carted')}">
                <fmt:message key="admin.user.cart.empty.carted" />
            </c:if>
            <c:if test="${type.equals('purchased')}">
                <fmt:message key="admin.user.cart.empty.purchased" />
            </c:if>
            <c:if test="${type.equals('cancelled')}">
                <fmt:message key="admin.user.cart.empty.cancelled" />
            </c:if>
        </h1>
    </c:if>
</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
