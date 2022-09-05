<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>
<!doctype html>
<html class="h-100" lang="en">
<c:set var = "title" value = "Products" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<c:set var="user" value="${sessionScope.user}" />

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />
<c:if test="${not empty user}" >
    <c:if test="${user.status.status == 1}">
        <a href="${pageContext.request.contextPath}/product/add" role="button" tabindex="-1" class="w-25 mx-auto mt-2 btn btn-primary"><fmt:message key="product.add.title" /></a>
    </c:if>
</c:if>


<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 1rem; margin-top: 2rem">

    <form id = "search_form" class="row d-flex justify-content-center w-50 gap-3">

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <div class="input-group mb-3">
                    <input type="search" id = "search_product" name = "search_product" class="form-control" placeholder="<fmt:message key="product.search.form.looking" />">
                    <button class="btn btn-outline-secondary" type="submit" id="button-search"><fmt:message key="product.search.form.submit" /></button>
                </div>
            </div>
        </div>
        <div class="row d-flex justify-content-center">
            <div class="col-md-5">
                <input type="number" name = "min_price" id = "min_price" min="1" class="form-control" placeholder="<fmt:message key="product.search.form.price.min" />">
            </div>
            <div class="col-md-5">
                <input type="number" name = "max_price" id = "max_price" min="1" class="form-control" placeholder="<fmt:message key="product.search.form.price.max" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-5">
                <input type="number" id = "min_size" name = "min_size" min="1" class="form-control" placeholder="<fmt:message key="product.search.form.size.min" />">
            </div>
            <div class="col-md-5">
                <input type="number" id = "max_size" name = "max_size" min="1" class="form-control" placeholder="<fmt:message key="product.search.form.size.max" />">
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-4">
                <label for="color" class="form-label"><fmt:message key="product.search.form.color" /></label>
                <select name="color" id="color" class="form-select">
                    <option value=""></option>
                    <c:forEach var="color" items="${requestScope.colors}">
                        <option value="${color.identifier}"><fmt:message key="color.${color.identifier}"/> </option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-md-4">
                <label for="category" class="form-label"><fmt:message key="product.search.form.category" /></label>
                <select name="category" id="category" class="form-select">
                    <option value=""></option>
                    <c:forEach var="category" items="${requestScope.categories}">
                        <c:choose>
                            <c:when test="${locale.equals('ua')}">
                                <c:set var="category_locale" value="${category.localeUA}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="category_locale" value="${category.localeEN}" />
                            </c:otherwise>
                        </c:choose>
                        <option value="category_${category.id}">${category_locale}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </form>

    <div class="row">
        <div class="col-auto">
            <h5 class="text-center"><fmt:message key="product.search.sort.name" /></h5>
            <div class="btn-group" role="group" aria-label="Basic example">
                <button type="button" name = "asc" id = "name_a" class="btn btn-primary">A-Z</button>
                <button type="button" name = "desc" id = "name_d" class="btn btn-primary">Z-A</button>
            </div>
        </div>
        <div class="col-auto">
            <h5 class="text-center"><fmt:message key="product.search.sort.price" /></h5>
            <div class="btn-group" role="group" aria-label="Basic example">
                <button type="button" name = "asc" id = "price_a" class="btn btn-success">A-Z</button>
                <button type="button" name = "desc" id = "price_d" class="btn btn-success">Z-A</button>
            </div>
        </div>
    </div>

    <div class="col-auto">
        <button type="button" name = "novelty" id = "novelty" class="btn btn-success"><fmt:message key="product.search.sort.novelty" /></button>
    </div>

</div>

<hr class="mt-5">

<div class="container">
<c:choose>
<c:when test = "${not empty requestScope['list']}">
    <div class="row row-cols-1 row-cols-md-4 g-5">
    <c:set var = "products" value = "${requestScope['list']}" />
    <c:set var = "pages" value = "${requestScope['pages']}" />
    <c:set var = "page" value = "${requestScope['page']}" />
    <c:set var = "query" value = "${requestScope['query']}" />

    <c:forEach var = "product" items = "${products}">
        <div class="col">
            <div class="card h-100" style="width: 20rem;">
                <img src="${pageContext.request.contextPath}/image/${product.previewImageId}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><c:out value = "${product.shortName}" /></h5>
                    <p class="card-text text-truncate"><c:out value = "${product.fullName}" /></p>
                </div>
                <div class="card-footer">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <a href="${pageContext.request.contextPath}/product/show/${product.id}" role="button" tabindex="-1" class="btn btn-primary"><fmt:message key="product.search.result.more" /></a>
                        <c:if test="${not empty user}" >
                            <c:if test="${user.status.status == 1}">
                                <a href="${pageContext.request.contextPath}/product/edit/${product.id}" role="button" tabindex="-1" class="btn btn-success"><fmt:message key="product.search.result.edit" /></a>
                                <a href="${pageContext.request.contextPath}/product/delete/${product.id}" role="button" tabindex="-1" class="btn btn-danger"><fmt:message key="product.search.result.delete" /></a>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    </div>

    <c:set var="path" value="/product" />
    <%@ include file ="/WEB-INF/jspf/pages.jspf" %>

</c:when>
<c:otherwise>
    <h1> <fmt:message key="product.search.result.nothing" /> </h1>
</c:otherwise>
</c:choose>
</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

<script src = "${pageContext.request.contextPath}/public/js/product.js"></script>

</body>
</html>
