<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 12. 16.
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title"><spring:message code="page.title.error.404" /></c:set>
<jsp:include page="shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row mb-5">
        <div class="col">
            <div class="text-center">
                <h1 class="display-1 border border-2 text-wrapper">404</h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="text-center">
                <p class="border border-2 text-wrapper fs-5 p-5">
                    <spring:message code="text.http.error.404.message" />
                </p>
            </div>
        </div>
    </div>
</section>
<jsp:include page="shared/_footer.jsp"/>