<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 14.
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title"><spring:message code="page.title.register" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row justify-content-center text-wrapper border border-2 mb-5">
        <div class="col container-fix-width">
            <h1 class="fs-2 mb-0 py-3 ps-3"><spring:message code="text.registration" /></h1>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col container-fix-width">
            <form:form modelAttribute="registerDTO" action="/register" method="post" cssClass="card border shadow p-3">
                <div class="mb-3">
                    <form:label path="lastName" cssClass="form-label"><spring:message code="label.lastname" /></form:label>
                    <form:input path="lastName" maxlength="50" cssClass="form-control" />
                    <form:errors cssClass="text-danger" path="lastName"/>
                </div>
                <div class="mb-3">
                    <form:label path="firstName" cssClass="form-label"><spring:message code="label.firstname" /></form:label>
                    <form:input path="firstName" maxlength="50" cssClass="form-control" />
                    <form:errors cssClass="text-danger" path="firstName"/>
                </div>
                <div class="mb-3">
                    <form:label path="email" cssClass="form-label"><spring:message code="label.email" /></form:label>
                    <form:input path="email" cssClass="form-control" />
                    <form:errors cssClass="text-danger" path="email"/>
                    <c:if test="${errorUserAlreadyExists != null}">
                        <span class="text-danger">${errorUserAlreadyExists}</span>
                    </c:if>
                </div>
                <div class="mb-3">
                    <form:label path="password" cssClass="form-label"><spring:message code="label.password" /></form:label>
                    <form:password path="password" cssClass="form-control" />
                    <form:errors cssClass="text-danger" path="password"/>
                </div>
                <div class="mb-3">
                    <form:label path="confirmPassword" cssClass="form-label"><spring:message code="label.confirm.password" /></form:label>
                    <form:password path="confirmPassword" cssClass="form-control" />
                    <form:errors cssClass="text-danger" path="confirmPassword"/>
                </div>
                <div class="mb-3">
                    <span class="form-label"><spring:message code="label.user.type" /></span>
                    <form:radiobuttons cssClass="form-check-input" path="userType" items="${userTypes}" element="div class='form-check'"/>
                    <form:errors cssClass="text-danger" path="userType"/>
                </div>
                <div class="mb-3">
                    <input type="submit" class="btn btn-primary me-2" value="<spring:message code="text.registration" />" />
                    <a class="btn btn-secondary" href="/"><spring:message code="text.cancel" /></a>
                </div>
            </form:form>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>
