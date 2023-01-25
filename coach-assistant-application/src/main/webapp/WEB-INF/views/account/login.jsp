<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 14.
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title"><spring:message code="page.title.login" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row justify-content-center text-wrapper border border-2 mb-5">
        <div class="col container-fix-width">
            <h1 class="fs-2 mb-0 py-3 ps-3"><spring:message code="text.login" /></h1>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col container-fix-width">
            <form:form action="login" method="post" cssClass="card border shadow p-3">
                <div class="mb-3">
                    <label class="form-label" for="username"><spring:message code="label.email" /></label>
                    <input class="form-control" id="username" type="text" name="username" />
                </div>
                <div class="mb-3">
                    <label class="form-label" for="password"><spring:message code="label.password" /></label>
                    <input class="form-control" id="password" type="password" name="password" />
                    <c:if test="${param.error}">
                        <span class="text-danger"><spring:message code="text.error.login" /></span>
                    </c:if>
                </div>
                <div class="mb-3">
                    <input type="submit" value="<spring:message code="text.login" />" class="btn btn-primary me-2" />
                    <a class="btn btn-secondary" href="/"><spring:message code="text.cancel" /></a>
                </div>
            </form:form>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>