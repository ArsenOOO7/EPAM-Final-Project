<%@ page pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<html lang="en" class="h-100">
<c:set var = "title" value = "Home" />
<%@ include file = "/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100 h-100">

<jsp:include page="/WEB-INF/jspf/jsp/header.jsp" />

<div id="carouselExampleFade" class="carousel slide carousel-fade h-100" data-bs-ride="carousel">
  <div class="carousel-inner h-100">
    <div class="carousel-item active">
      <img src="https://content1.rozetka.com.ua/promotions/main_image_ua/original/281561097.jpg" class="carousel-image d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="https://content1.rozetka.com.ua/promotions/main_image_ua/original/281773119.jpg" class="carousel-image d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="https://content2.rozetka.com.ua/promotions/main_image_ua/original/281114663.jpg" class="carousel-image d-block w-100" alt="...">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<style>
    .carousel-image{
      height: 39rem;
    }
</style>

<%@ include file = "/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
