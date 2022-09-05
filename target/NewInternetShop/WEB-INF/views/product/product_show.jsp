<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html lang="en">
<c:set var = "title" value = "Products" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />


<div class="container justify-content-md-between mt-md-4">
    <div class="mt-4 product-show">
        <div class="row">
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/image/${product.previewImageId}" class="img-fluid" alt="">
            </div>
            <div class="col-md-6">
                <h4><c:out value="${product.shortName}" /></h4>

                <c:choose>
                    <c:when test="${product.amount > 0}">
                        <button class="btn btn-success" disabled><fmt:message key="product.available" /></button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-danger" disabled><fmt:message key="product.not.available" /></button>
                    </c:otherwise>
                </c:choose>
                
                <div class="col mt-md-3">
                    <button class="btn btn-warning" disabled><c:out value="${product.price}" />$</button>
                </div>

                <c:if test="${product.amount > 0}">
                    <c:choose>
                        <c:when test="${not empty user}">

                            <c:if test="${not empty sessionScope.cart_product_error_message}">
                                <div class="alert alert-danger mt-4" role="alert">
                                    <fmt:message key="${sessionScope.cart_product_error_message}" />
                                </div>
                                <c:remove var="cart_product_error_message" scope="session"/>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/cart/add/${product.id}" method="post" class="mt-md-3">
                                <div class="input-group mb-3 w-50">
                                    <input type="number" min="1" class="form-control" placeholder="Amount" name="amount" id="amount">
                                    <button class="btn btn-outline-secondary" type="submit" id="button-addon2"><fmt:message key="product.buy" /></button>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-warning mt-md-3" role="alert">
                                <fmt:message key="product.sign.in.buy" />
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
        </div>
        <div class="row mt-lg-5">
            <div class="col-md-6">
                <p class="description lh-base text-break"><c:out value="${product.description}" />
                </p>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="input-group w-25">
                        <button  disabled class="btn btn-outline-secondary" type="button" id="button-color">Color</button>
                        <input disabled type="text" class="form-control" value="<fmt:message key='color.${product.color.identifier}' />">
                    </div>
                    <div class="input-group w-auto">
                        <button  disabled class="btn btn-outline-secondary" type="button" id="button-size-unit">Size unit</button>
                        <input disabled type="text" class="form-control" value="<fmt:message key='size.unit.${product.sizeUnit.unit}' />">
                    </div>
                    <div class="input-group w-25">
                        <button  disabled class="btn btn-outline-secondary" type="button" id="button-size-value">Size</button>
                        <input disabled type="text" class="form-control" value="${product.sizeValue}">
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
