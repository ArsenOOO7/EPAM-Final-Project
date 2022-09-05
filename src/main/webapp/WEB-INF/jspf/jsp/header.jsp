<%@ include file ="/WEB-INF/jspf/page/page.jspf" %>

<!doctype html>
<html lang="en">
<head>
    <c:if test = "${not empty cookie['language']}">
        <fmt:setLocale value="${cookie['language'].value}"/>
    </c:if>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-dark">
    <div class="container">
        <a href="" class="navbar-brand text-white" >
            <img src="https://www.epam.com/content/dam/epam/global/logo_white-blue.svg" alt="" width="90" height="25">
            <fmt:message key="panel.title" />
        </a>
        <div class="navbar-collapse collapse justify-content-end" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/product" class="nav-link text-white"><fmt:message key="panel.products" /></a>
                </li>

                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/login" class="nav-link text-white"><fmt:message key="panel.sign.in" /></a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/register" class="nav-link text-white"><fmt:message key="panel.sign.up" /></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${sessionScope.user.status.status == 1}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/admin/users" class="nav-link text-white"><fmt:message key="panel.users" /></a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/category" class="nav-link text-white"><fmt:message key="panel.categories" /></a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user.status.status == 0}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/profile" class="nav-link text-white"><fmt:message key="panel.profile" /></a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/cart" class="nav-link text-white"><fmt:message key="panel.cart" /></a>
                            </li>
                        </c:if>
                    </c:otherwise>
                </c:choose>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false"><fmt:message key="panel.select.language" /></a>
                    <ul class="dropdown-menu  dropdown-menu-dark">
                        <li><a class="dropdown-item text-white lang" href="#" id="ua">Українська</a></li>
                        <li><a class="dropdown-item text-white lang" href="#" id="en">English</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>