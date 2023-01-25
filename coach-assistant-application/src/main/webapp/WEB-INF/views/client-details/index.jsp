<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 24.
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hu.elte.webjava.coachassistant.application.common.MsgKeys" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<c:set var="title"><spring:message code="page.title.client.details" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section class="mb-5">
    <div class="row mb-5">
        <div class="col">
            <h1 class="border border-2 text-wrapper mb-0 py-3 ps-3">
                ${subscribedClientDTO.lastName} ${subscribedClientDTO.firstName} <spring:message code="text.data.of"/>
            </h1>
        </div>
    </div>
    <div class="row justify-content-center mb-3">
        <div class="col">
            <div class="card border shadow">
                <div class="card-header border-bottom border-4 p-3">
                    <h2 class="fs-4 mb-0"><spring:message code="text.profil"/></h2>
                </div>
                <div class="card-body p-3">
                    <dl class="row mb-0">
                        <dt class="col-sm-3 mb-1"><spring:message code="label.lastname"/></dt>
                        <dd class="col-sm-9">${subscribedClientDTO.lastName}</dd>
                        <dt class="col-sm-3 mb-1"><spring:message code="label.firstname"/></dt>
                        <dd class="col-sm-9">${subscribedClientDTO.firstName}</dd>
                        <dt class="col-sm-3 mb-1"><spring:message code="label.training.plan.name"/></dt>
                        <dd class="col-sm-9">${subscribedClientDTO.subscribedTrainingPlanName}</dd>
                        <dt class="col-sm-3 mb-1"><spring:message code="text.client.age"/></dt>
                        <dd class="col-sm-9">${subscribedClientDTO.age}</dd>
                        <dt class="col-sm-3 mb-1"><spring:message code="label.height"/></dt>
                        <dd class="col-sm-9">${subscribedClientDTO.height} <c:if test="${subscribedClientDTO.height != null}"><spring:message code="text.cm" /></c:if></dd>
                        <dt class="col-sm-3 mb-1"><spring:message code="label.gender"/></dt>
                        <c:choose>
                            <c:when test="${subscribedClientDTO.gender != null}">
                                <dd class="col-sm-9"><spring:message code="${MsgKeys.GENDER_PREFIX.toString().toLowerCase()}${subscribedClientDTO.gender.toString().toLowerCase()}"/></dd>
                            </c:when>
                            <c:otherwise>
                                <dd class="col-sm-9"></dd>
                            </c:otherwise>
                        </c:choose>
                    </dl>
                </div>
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
                        <p class="fs-5"><spring:message code="text.no.weight.diary.data.available"/></p>
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
                <p class="fs-5"><spring:message code="text.no.calorie.diary.data.available"/></p>
            </div>
        </c:when>
        <c:otherwise>
            <tg:calorieDiaryChart diaries="${calorieDiaryDTOList}" />
        </c:otherwise>
    </c:choose>
</section>
<section>
    <div class="row mb-3">
        <div class="col text-end">
            <a href="/coach-dashboard/index" class="btn btn-primary shadow">
                <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.return" />
            </a>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>
