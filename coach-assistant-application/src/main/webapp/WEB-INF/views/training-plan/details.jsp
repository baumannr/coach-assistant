<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 24.
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hu.elte.webjava.coachassistant.application.common.MsgKeys" %>
<%@ page import="hu.elte.webjava.coachassistant.application.webdomain.ExerciseType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<sec:authorize access="hasRole('ROLE_COACH')" var="isCoach"/>
<sec:authorize access="hasRole('ROLE_CLIENT')" var="isClient"/>

<jsp:useBean id="exercisePagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />
<c:url value="/training-plan/details/${trainingPlanDTO.id}" var="pagedLink">
    <c:param name="page" value="~" />
</c:url>

<c:set var="title"><spring:message code="page.title.training.plan.details" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row mb-5">
        <div class="col">
            <h1 class="border border-2 text-wrapper mb-0 py-3 ps-3"><spring:message code="text.training.plan.details" /></h1>
        </div>
    </div>
    <div class="row mb-5">
        <div class="col">
            <div class="card border shadow">
                <div class="card-header border-bottom border-4 p-3">
                    <h2 class="fs-4 mb-0"><spring:message code="text.training.plan.data" /></h2>
                </div>
                <div class="card-body p-3">
                    <dl class="row mb-0">
                        <dt class="col-sm-3"><spring:message code="label.training.plan.name" /></dt>
                        <dd class="col-sm-9">${trainingPlanDTO.name}</dd>
                        <dt class="col-sm-3"><spring:message code="label.training.plan.training.type" /></dt>
                        <dd class="col-sm-9"><spring:message
                                code="${MsgKeys.TRAINING_TYPE_PREFIX}${trainingPlanDTO.trainingType.toString().toLowerCase()}"/></dd>
                        <dt class="col-sm-3"><spring:message code="text.training.plan.length" /></dt>
                        <dd class="col-sm-9">${trainingPlanDTO.length} <spring:message code="time.unit.minutes" /></dd>
                        <dt class="col-sm-3"><spring:message code="text.training.plan.calorie.burn" /></dt>
                        <dd class="col-sm-9">${trainingPlanDTO.calorieBurn} kcal</dd>
                        <dt class="col-sm-3"><spring:message code="label.training.plan.recommendation" /></dt>
                        <dd class="col-sm-9"><spring:message
                                code="${MsgKeys.GENDER_PREFIX}${trainingPlanDTO.recommendation.toString().toLowerCase()}"/></dd>
                    </dl>
                </div>
                <div class="card-footer border-0 p-3">
                    <c:if test="${isCoach}">
                        <a href="/training-plan/edit/${trainingPlanDTO.id}" class="btn btn-primary">
                            <i class="bi bi-pencil-square me-2"></i><spring:message code="text.edit" />
                        </a>
                        <form:form action="/training-plan/delete/${trainingPlanDTO.id}" method="post" cssClass="d-inline">
                            <button class="btn btn-danger ms-3"><i class="bi bi-x-square me-2"></i><spring:message code="text.delete" /></button>
                        </form:form>
                    </c:if>
                    <c:if test="${isClient}">
                        <c:choose>
                            <c:when test="${clientSubscriptionButton == 'subscribe'}">
                                <form:form action="/training-plan/subscribe/${trainingPlanDTO.id}" method="post" cssClass="d-inline">
                                    <button class="btn btn-primary"><i class="bi bi-box-arrow-in-up-right me-2"></i><spring:message code="text.subscribe" /></button>
                                </form:form>
                            </c:when>
                            <c:when test="${clientSubscriptionButton == 'change'}">
                                <form:form action="/training-plan/change-subscription/${trainingPlanDTO.id}" method="post" cssClass="d-inline">
                                    <button class="btn btn-primary"><i class="bi bi-box-arrow-in-up-right me-2"></i><spring:message code="text.change" /></button>
                                </form:form>
                            </c:when>
                            <c:otherwise>
                                <form:form cssClass="d-inline" action="/training-plan/unsubscribe">
                                    <button class="btn btn-danger"><i class="bi bi-box-arrow-down-left me-2"></i><spring:message code="text.unsubscribe" /></button>
                                </form:form>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<section>

    <div class="row mb-3">
        <div class="col">
            <h2 class="border border-2 text-wrapper mb-0 py-2 ps-3"><spring:message code="text.exercise.overview" /></h2>
        </div>
    </div>
    <c:if test="${isCoach}">
        <div class="row mb-3">
            <div class="col text-end">
                <a href="/exercise/create/${trainingPlanDTO.id}" class="btn btn-primary shadow">
                    <i class="bi bi-plus-square me-2"></i><spring:message code="text.exercise.create" />
                </a>
            </div>
        </div>
    </c:if>
    <div class="row mb-5">
        <div class="col">
            <div class="card border shadow">
                <div class="card-header border-0 p-3">
                    <h3 class="fs-4 mb-0"><spring:message code="text.exercises" /></h3>
                </div>
                <div class="table-responsive">
                    <table class="table align-middle mb-0">
                        <thead class="border-top border-bottom border-4">
                            <tr class="align-middle">
                                <th class="p-3"><spring:message code="label.exercise.name" /></th>
                                <th class="p-3"><spring:message code="label.exercise.sets" /></th>
                                <th class="p-3"><spring:message code="label.exercise.rest" /></th>
                                <th class="p-3"><spring:message code="text.repetitions.or.length" /></th>
                                <c:if test="${isCoach}">
                                    <td class="p-3"></td>
                                </c:if>
                            <tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty exercisePagedListHolder.pageList}">
                                    <tr class="align-middle">
                                        <td class="p-3 text-center" colspan="${isCoach ? 5 : 4}">
                                            <p class="mb-0"><spring:message code="text.no.exercises.found"/></p>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${exercisePagedListHolder.pageList}" var="exercise">
                                        <tr class="align-middle">
                                            <td class="p-3">${exercise.name}</td>
                                            <td class="p-3">${exercise.sets}</td>
                                            <td class="p-3">
                                                    ${exercise.rest} <spring:message
                                                    code="${MsgKeys.TIME_UNIT_PREFIX}${exercise.restTimeUnit.toString().toLowerCase()}"/>
                                            </td>
                                            <td class="p-3">
                                                <c:choose>
                                                    <c:when test="${exercise.exerciseType == ExerciseType.REPETITIVE}">
                                                        ${exercise.repetitions} <spring:message code="text.repetitions" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${exercise.length} <spring:message
                                                            code="${MsgKeys.TIME_UNIT_PREFIX}${exercise.lengthTimeUnit.toString().toLowerCase()}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <c:if test="${isCoach}">
                                                <td class="p-3 text-end">
                                                    <a href="/exercise/edit/${exercise.id}" class="btn btn-primary">
                                                        <i class="bi bi-pencil-square me-2"></i><spring:message code="text.edit" />
                                                    </a>
                                                    <form:form action="/exercise/delete/${exercise.id}" method="post" cssClass="d-inline">
                                                        <button class="btn btn-danger ms-3"><i class="bi bi-x-square me-2"></i><spring:message code="text.delete" /></button>
                                                    </form:form>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer d-flex border-0 p-3">
                    <tg:paging pagedListHolder="${exercisePagedListHolder}" pagedLink="${pagedLink}" />
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col text-end">
            <a href="/training-plan/index" class="btn btn-primary shadow">
                <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.return" />
            </a>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>
