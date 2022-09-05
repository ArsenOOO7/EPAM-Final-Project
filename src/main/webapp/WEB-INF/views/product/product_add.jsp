<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html class="h-100">
<c:set var = "title" value = "Add product" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />


<div style="height: 100%; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 1rem">

    <c:if test="${not empty sessionScope.product_add_error}">
        <div class="alert alert-danger mt-4" role="alert">
            <fmt:message key="${sessionScope.product_add_error}" />
        </div>
        <c:remove var="product_add_error" scope="session"/>
    </c:if>

    <h1><fmt:message key="product.add.title" /></h1>
    <form action="${pageContext.request.contextPath}/product/add"  enctype="multipart/form-data" method="post" class="row d-flex justify-content-center w-50 gap-3">

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="text" class="form-control"  id="shortname" name="shortname" placeholder="<fmt:message key="product.add.shortname" />" required>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <input type="text" class="form-control" id="fullname" name="fullname" placeholder="<fmt:message key="product.add.fullname" />" required>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <div class="form-floating">
                    <textarea class="form-control" id="description" name="description" required></textarea>
                    <label for="description"><fmt:message key="product.add.description" /></label>
                </div>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-5">
                <input type="number" min="1" id="price" name="price" class="form-control" placeholder="<fmt:message key="product.add.price" />" required>
            </div>
            <div class="col-md-5">
                <input type="number" min="1" id="amount" name="amount" class="form-control" placeholder="<fmt:message key="product.add.amount" />" required>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-10">
                <label for="preview_image" class="form-label"><fmt:message key="product.add.preview" /></label>
                <input type="file" class="form-control"  id="preview_image" name="preview_image" accept=".jpg, .jpeg, .png" required>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-md-3">
                <label for="color" class="form-label" ><fmt:message key="product.add.color" /></label>
                <select name="color" id="color" class="form-select">
                    <option value=""></option>
                    <c:forEach var="color" items="${requestScope.colors}">
                        <option value="${color.identifier}"><fmt:message key="color.${color.identifier}"/> </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3">
                <label for="size_unit" class="form-label"><fmt:message key="product.add.size.unit" /></label>
                <select name="size_unit" id="size_unit" class="form-select">
                    <option value=""></option>
                    <c:forEach var="sizeUnit" items="${requestScope.units}">
                        <c:if test="${sizeUnit.id > 0}">
                            <option value="${sizeUnit.unit}"><fmt:message key="size.unit.${sizeUnit.unit}"/> </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="col-md-3">
                <label for="category" class="form-label"><fmt:message key="product.add.category" /></label>
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

        <div class="row d-flex justify-content-center">
            <div class="col-auto">
                <input type="number" min="1" id="size_value" name="size_value" class="form-control" placeholder="<fmt:message key="product.add.size.value" />" required>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <div class="col-auto">
                <button class="btn btn-primary"><fmt:message key="product.add.submit" /></button>
            </div>
        </div>

    </form>

</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
