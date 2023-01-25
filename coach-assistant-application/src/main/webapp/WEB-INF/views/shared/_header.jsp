<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 14.
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<sec:authorize access="hasAnyRole('ROLE_CLIENT', 'ROLE_COACH')" var="isLoggedIn"/>
<sec:authorize access="hasRole('ROLE_CLIENT')" var="isClient"/>
<sec:authorize access="hasRole('ROLE_COACH')" var="isCoach"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${param.title}"/> - <spring:message code="text.coachassitant" /></title>
    <%-- bootstrap --%>
    <link href="<c:url value="/lib/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <%-- bootstrap icons --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
    <!--google fonts-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap" rel="stylesheet">
    <link href="<c:url value='/css/app.css' />" type="text/css" rel="stylesheet" />
</head>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark fixed-top shadow">
        <div class="container">
            <a class="navbar-brand" href="/"><spring:message code="text.coachassitant" /></a>
            <c:choose>
                <c:when test="${isLoggedIn}">
                    <jsp:include page="_logoutPartial.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="_loginPartial.jsp"/>
                </c:otherwise>
            </c:choose>
            <c:if test="${isLoggedIn}">
                <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
                    <div class="offcanvas-header">
                        <h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel"><spring:message code="text.menu"/></h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                            <c:if test="${isClient}">
                                <jsp:include page="_clientMenuItemsPartial.jsp"/>
                            </c:if>
                            <c:if test="${isCoach}">
                                <jsp:include page="_coachMenuItemsPartial.jsp"/>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </c:if>
        </div>
    </nav>
</header>
<div id="mainContainer" class="container">
    <main class="parallax pb-3">
        <jsp:include page="_notification.jsp"/>
