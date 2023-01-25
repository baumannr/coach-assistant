<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 24.
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hu.elte.webjava.coachassistant.application.webdomain.ExerciseType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title"><spring:message code="page.title.exercise.create" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row justify-content-center mb-5 border border-2 text-wrapper">
        <div class="col container-fix-width">
            <h1 class="fs-2 mb-0 py-3 ps-3"><spring:message code="text.exercise.create" /></h1>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col container-fix-width">
            <form:form modelAttribute="exerciseDTO" action="/exercise/create" method="post"
                       class="card border shadow p-3" >
                <form:hidden path="trainingPlanId" />
                <div class="mb-3">
                    <form:label path="name" cssClass="form-label"><spring:message code="label.exercise.name" /></form:label>
                    <form:input path="name" cssClass="form-control" maxlength="100" />
                    <form:errors cssClass="text-danger" path="name"/>
                </div>
                <div class="mb-3">
                    <form:label path="sets" cssClass="form-label"><spring:message code="label.exercise.sets" /></form:label>
                    <form:input path="sets" type="number" min="1" max="10" cssClass="form-control" />
                    <form:errors cssClass="text-danger" path="sets"/>
                </div>
                <div class="mb-3">
                    <form:label path="rest" cssClass="form-label"><spring:message code="label.exercise.rest" /></form:label>
                    <div class="input-group">
                        <form:input path="rest" type="number" min="1" max="180" cssClass="form-control border-end border-2" />
                        <form:select path="restTimeUnit" cssClass="form-select">
                            <form:options items="${timeUnits}" />
                        </form:select>
                    </div>
                    <form:errors cssClass="text-danger" path="rest" />
                    <form:errors cssClass="text-danger" path="restTimeUnit" />
                </div>
                <div class="mb-3">
                    <legend class="form-label fs-6"><spring:message code="label.exercise.type" /></legend>
                    <form:radiobuttons cssClass="form-check-input" path="exerciseType" items="${exerciseTypes}" element="div class='form-check'"/>
                    <form:errors cssClass="text-danger" path="exerciseType"/>
                </div>
                <div id="repetitionsInputWrapper" class="mb-3${exerciseDTO.exerciseType == ExerciseType.INTERVAL ? " d-none" : ""}">
                    <form:label path="repetitions" cssClass="form-label"><spring:message code="label.exercise.repetitions" /></form:label>
                    <form:input path="repetitions" type="number" min="1" max="30" cssClass="form-control" disabled="${exerciseDTO.exerciseType == ExerciseType.INTERVAL}" />
                    <form:errors cssClass="text-danger" path="repetitions"/>
                </div>
                <div id="lengthAndUnitInputWrapper" class="mb-3${exerciseDTO.exerciseType == ExerciseType.REPETITIVE ? " d-none" : ""}">
                    <form:label path="length" cssClass="form-label"><spring:message code="label.exercise.length" /></form:label>
                    <div class="input-group">
                        <form:input path="length" type="number" min="1" max="180" cssClass="form-control border-end border-2" disabled="${exerciseDTO.exerciseType == ExerciseType.REPETITIVE}" />
                        <form:select path="lengthTimeUnit" cssClass="form-select"  disabled="${exerciseDTO.exerciseType == ExerciseType.REPETITIVE}" >
                            <form:options items="${timeUnits}" />
                        </form:select>
                    </div>
                    <form:errors cssClass="text-danger" path="length"/>
                    <form:errors cssClass="text-danger" path="lengthTimeUnit"/>
                </div>
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary me-2">
                        <i class="bi bi-check-square me-2"></i><spring:message code="text.save" />
                    </button>
                    <a href="/training-plan/details/${exerciseDTO.trainingPlanId}" class="btn btn-secondary">
                        <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.cancel" />
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>
<script src="<c:url value='/js/exercise.js' />"></script>
