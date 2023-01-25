<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 24.
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hu.elte.webjava.coachassistant.application.common.MsgKeys" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<sec:authorize access="hasRole('ROLE_COACH')" var="isCoach"/>
<sec:authorize access="hasRole('ROLE_CLIENT')" var="isClient"/>

<jsp:useBean id="trainingPlanPagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />
<c:url value="/training-plan/index" var="pagedLink">
    <c:param name="page" value="~" />
</c:url>

<c:set var="title"><spring:message code="page.title.training.plan.index" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row mb-5">
        <div class="col">
            <h1 class="border border-2 text-wrapper mb-0 py-3 ps-3"><spring:message code="text.trainingplans" /></h1>
        </div>
    </div>
    <c:if test="${isCoach}">
        <div class="row mb-3">
            <div class="col text-end">
                <a href="/training-plan/create" class="btn btn-primary shadow">
                    <i class="bi bi-plus-square me-2"></i><spring:message code="text.training.plan.create" />
                </a>
            </div>
        </div>
    </c:if>
    <div class="row mb-5">
        <div class="col">
            <div class="card border shadow">
                <div class="card-header border-0 p-3">
                    <h2 class="fs-4 mb-0"><spring:message code="text.trainingplans" /></h2>
                </div>
                <div class="table-responsive">
                    <table class="table align-middle mb-0">
                        <thead class="border-top border-bottom border-4 text-nowrap">
                            <tr>
                                <th class="p-3"><spring:message code="label.training.plan.name" /></th>
                                <th class="p-3"><spring:message code="label.training.plan.training.type" /></th>
                                <th class="p-3"><spring:message code="label.training.plan.length" /></th>
                                <th class="p-3"><spring:message code="label.training.plan.calorie.burn" /></th>
                                <th class="p-3"><spring:message code="label.training.plan.recommendation" /></th>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty trainingPlanPagedListHolder.pageList}">
                                <tr class="align-middle">
                                    <td colspan="6" class="text-center p-3">
                                        <p class="mb-0"><spring:message code="text.no.training.plan.${isCoach ? 'created' : 'found'}" /></p>
                                    </td>
                                </tr>
                            </c:when>
                        </c:choose>
                            <c:forEach items="${trainingPlanPagedListHolder.pageList}" var="trainingPlan">
                                <tr class="align-middle">
                                    <td class="p-3">${trainingPlan.name}</td>
                                    <td class="p-3"><spring:message
                                            code="${MsgKeys.TRAINING_TYPE_PREFIX}${trainingPlan.trainingType.toString().toLowerCase()}" />
                                    </td>
                                    <td class="p-3">${trainingPlan.length}</td>
                                    <td class="p-3">${trainingPlan.calorieBurn}</td>
                                    <td class="p-3"><spring:message
                                            code="${MsgKeys.GENDER_PREFIX}${trainingPlan.recommendation.toString().toLowerCase()}" />
                                    </td>
                                    <td class="p-3 text-end">
                                        <a href="/training-plan/details/${trainingPlan.id}" class="btn btn-primary ms-2 text-nowrap">
                                            <i class="bi bi-list me-2"></i><spring:message code="text.details" />
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer d-flex border-0 p-3">
                    <tg:paging pagedListHolder="${trainingPlanPagedListHolder}" pagedLink="${pagedLink}" />
                </div>
            </div>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col text-end">
            <c:if test="${isCoach}">
                <a href="/coach-dashboard/index" class="btn btn-primary shadow">
                    <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.return" />
                </a>
            </c:if>
            <c:if test="${isClient}">
                <a href="/client-dashboard/index" class="btn btn-primary shadow">
                    <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.return" />
                </a>
            </c:if>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>