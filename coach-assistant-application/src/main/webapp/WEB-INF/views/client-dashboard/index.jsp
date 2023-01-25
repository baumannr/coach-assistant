<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 24.
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="hu.elte.webjava.coachassistant.application.common.MsgKeys" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<c:set var="title"><spring:message code="page.title.dashboard" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section class="mb-5">
    <div class="row mb-5">
        <div class="col">
            <h1 class="border border-2 text-wrapper mb-0 py-3 ps-3"><spring:message code="text.dashboard" /></h1>
        </div>
    </div>
    <div class="row mb-5">
        <div class="col">
            <div class="card border shadow">
                <div class="card-header border-bottom border-4 p-3">
                    <h2 class="fs-4 mb-0"><spring:message code="text.subscribed.training.plan.data" /></h2>
                </div>
                <c:choose>
                    <c:when test="${empty trainingPlanDTO}">
                        <div class="card-body p-3 text-center">
                            <p class="card-text"><spring:message code="text.no.training.plan.chosen" /></p>
                        </div>
                        <div class="card-footer border-0 p-3 text-center">
                            <a href="/training-plan/index" class="btn btn-primary">
                                <i class="bi bi-arrow-right-square me-2"></i><spring:message code="text.choose.training.plan"/>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
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
                                <dd class="col-sm-9">${trainingPlanDTO.calorieBurn} <spring:message code="text.kcal" /></dd>
                                <dt class="col-sm-3"><spring:message code="label.training.plan.recommendation" /></dt>
                                <dd class="col-sm-9"><spring:message
                                        code="${MsgKeys.GENDER_PREFIX}${trainingPlanDTO.recommendation.toString().toLowerCase()}" /></dd>
                            </dl>
                        </div>
                        <div class="card-footer border-0 p-3">
                            <a href="/training-plan/details/${trainingPlanDTO.id}" class="btn btn-primary me-2">
                                <i class="bi bi-list me-2"></i><spring:message code="text.details" />
                            </a>
                            <form:form cssClass="d-inline" action="/training-plan/unsubscribe">
                                <button class="btn btn-danger"><i class="bi bi-box-arrow-down-left me-2"></i><spring:message code="text.unsubscribe" /></button>
                            </form:form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>
<section class="mb-5">
    <div class="row mb-3">
        <div class="col">
            <h2 class="border border-2 text-wrapper mb-0 py-2 ps-3"><spring:message code="text.weight.chart.title"/></h2>
        </div>
    </div>
    <div class="row mb-5">
        <div class="col">
            <c:choose>
                <c:when test="${empty weightDiaryDTOList}">
                    <div class="card border shadow d-flex justify-content-center align-items-center" style="height: 500px">
                        <p class="fs-5"><spring:message code="text.no.diary.created.yet"/></p>
                        <a href="/weight-diary/index" class="btn btn-primary">
                            <i class="bi bi-arrow-right-square me-2"></i><spring:message code="text.weight.diary.create" />
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <tg:weightDiaryChart diaries="${weightDiaryDTOList}" />
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</section>
<section class="mb-5">
    <div class="row mb-3">
        <div class="col">
            <h2 class="border border-2 text-wrapper mb-0 py-2 ps-3"><spring:message code="text.calories.chart.title"/></h2>
        </div>
    </div>
    <c:choose>
        <c:when test="${empty calorieDiaryDTOList}">
            <div class="card border shadow d-flex justify-content-center align-items-center" style="height: 500px">
                <p class="fs-5"><spring:message code="text.no.diary.created.yet"/></p>
                <a href="/calorie-diary/index" class="btn btn-primary">
                    <i class="bi bi-arrow-right-square me-2"></i><spring:message code="text.calorie.diary.create" />
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <tg:calorieDiaryChart diaries="${calorieDiaryDTOList}" />
        </c:otherwise>
    </c:choose>
</section>
<jsp:include page="../shared/_footer.jsp"/>