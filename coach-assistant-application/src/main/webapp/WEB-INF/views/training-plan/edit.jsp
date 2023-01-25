<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 24.
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title"><spring:message code="page.title.training.plan.edit" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row justify-content-center mb-5 border border-2 text-wrapper">
        <div class="col container-fix-width">
            <h1 class="fs-2 mb-0 py-3 ps-3"><spring:message code="text.training.plan.edit" /></h1>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col container-fix-width">
            <form:form modelAttribute="trainingPlanDTO" action="/training-plan/edit" method="post"
                       class="card border shadow p-3" >
                <form:hidden path="id"/>
                <div class="mb-3">
                    <form:label path="name" cssClass="form-label"><spring:message code="label.training.plan.name" /></form:label>
                    <form:input path="name" cssClass="form-control" maxlength="100" />
                    <form:errors cssClass="text-danger" path="name"/>
                </div>
                <div class="mb-3">
                    <form:label path="trainingType" cssClass="form-label"><spring:message code="label.training.plan.training.type" /></form:label>
                    <form:select path="trainingType" cssClass="form-select">
                        <form:option value="" label="Válassz!" />
                        <form:options items="${trainingTypes}" />
                    </form:select>
                    <form:errors cssClass="text-danger" path="trainingType"/>
                </div>
                <div class="mb-3">
                    <form:label path="length" cssClass="form-label"><spring:message code="label.training.plan.length" /></form:label>
                    <form:input path="length" type="number" min="1" max="120" cssClass="form-control" />
                    <form:errors cssClass="text-danger" path="length"/>
                </div>
                <div class="mb-3">
                    <form:label path="calorieBurn" cssClass="form-label"><spring:message code="label.training.plan.calorie.burn" /></form:label>
                    <form:input path="calorieBurn" type="number" min="1" max="1000" cssClass="form-control" />
                    <form:errors cssClass="text-danger" path="calorieBurn"/>
                </div>
                <div class="mb-3">
                    <legend class="form-label fs-6"><spring:message code="label.training.plan.recommendation" /></legend>
                    <form:radiobuttons cssClass="form-check-input" path="recommendation" items="${genders}"
                                       element="div class='form-check'"/>
                    <form:errors cssClass="text-danger" path="recommendation"/>
                </div>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary me-2">
                        <i class="bi bi-check-square me-2"></i><spring:message code="text.save" />
                    </button>
                    <a href="/training-plan/index" class="btn btn-secondary">
                        <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.cancel" />
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>
<script src="<c:url value='/js/training-plan.js' />"></script>