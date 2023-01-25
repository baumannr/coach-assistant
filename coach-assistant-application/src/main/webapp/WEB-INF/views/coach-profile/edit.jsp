<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 24.
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title"><spring:message code="page.title.profile.edit" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row justify-content-center mb-5 border border-2 text-wrapper">
        <div class="col container-fix-width">
            <h1 class="fs-2 mb-0 py-3 ps-3"><spring:message code="text.profile.edit" /></h1>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col container-fix-width">
            <form:form modelAttribute="coachDTO" action="/coach-profile/edit" method="post"
                       class="card border shadow p-3" >
                <form:hidden path="id"/>
                <form:hidden path="email"/>
                <div class="mb-3">
                    <form:label path="lastName" cssClass="form-label"><spring:message code="label.lastname"/></form:label>
                    <form:input path="lastName" cssClass="form-control" maxlength="50" />
                    <form:errors cssClass="text-danger" path="lastName"/>
                </div>
                <div class="mb-3">
                    <form:label path="firstName" cssClass="form-label"><spring:message code="label.firstname"/></form:label>
                    <form:input path="firstName" cssClass="form-control" maxlength="50" />
                    <form:errors cssClass="text-danger" path="firstName"/>
                </div>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary me-2">
                        <i class="bi bi-check-square me-2"></i><spring:message code="text.save" />
                    </button>
                    <a href="/coach-profile/index" class="btn btn-secondary">
                        <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.cancel" />
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>
