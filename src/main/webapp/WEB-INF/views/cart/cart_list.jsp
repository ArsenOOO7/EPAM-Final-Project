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

<div class="container">

  <c:if test="${not empty sessionScope.cart_error_message}">
    <div class="alert alert-danger mt-4" role="alert">
      <fmt:message key="${sessionScope.cart_error_message}" />
    </div>
    <c:remove var="cart_error_message" scope="session"/>
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
          <div class="btn-group" role="group" aria-label="Basic example">
            <c:if test="${cart.status.id == 1}">
              <div class="btn-group justify-content-center" role="group">
                <a href="${pageContext.request.contextPath}/cart/buy/${cart.id}" role="button" tabindex="-1" class="btn btn-primary"><fmt:message key="cart.view.buy" /></a>
                <a href="${pageContext.request.contextPath}/cart/delete/${cart.id}" role="button" tabindex="-1" class="btn btn-danger"><fmt:message key="cart.view.remove" /></a>
              </div>
            </c:if>
            <c:if test="${cart.status.id == 2}">
              <a href="${pageContext.request.contextPath}/cart/cancel/${cart.id}" role="button" tabindex="-1" class="btn btn-warning"><fmt:message key="cart.view.cancel" /></a>
            </c:if>
          </div>
        </div>
      </div>
    </div>
    </c:forEach>
  </div>


    <c:set var="path" value="/cart/${type}" />
    <%@ include file ="/WEB-INF/jspf/pages.jspf" %>

  </c:if>

    <c:if test="${empty list}">
      <h1 class="text-center mt-md-5">
        <c:if test="${type.equals('carted')}">
          <fmt:message key="cart.empty.carted" />
        </c:if>
        <c:if test="${type.equals('purchased')}">
          <fmt:message key="cart.empty.purchased" />
        </c:if>
        <c:if test="${type.equals('cancelled')}">
          <fmt:message key="cart.empty.cancelled" />
        </c:if>
      </h1>
    </c:if>
</div>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
