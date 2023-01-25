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
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="subscribedClientsPagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />
<c:url value="/coach-dashboard/index" var="pagedLink">
    <c:param name="page" value="~" />
</c:url>

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
    <div class="row mb-3">
        <div class="col">
            <div class="card border shadow">
                <div class="card-header border-0 p-3">
                    <div>
                        <h3 class="fs-4"><spring:message code="text.subscribed.clients" /></h3>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table justify-table-col-evenly-8 mb-0">
                        <thead class="border-top border-bottom border-4">
                        <tr class="align-middle">
                            <th class="p-3"><spring:message code="text.client.name" /></th>
                            <th class="p-3"><spring:message code="text.client.age" /></th>
                            <th class="p-3"><spring:message code="text.client.gender" /></th>
                            <th class="p-3"><spring:message code="text.client.height" /></th>
                            <th class="p-3"><spring:message code="text.training.plan" /></th>
                            <th class="p-3"><spring:message code="text.weight" /></th>
                            <th class="p-3"><spring:message code="text.calorie" /></th>
                            <td class="p-3"></td>
                        </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not anyTrainingPlan}">
                                    <tr>
                                        <td colspan="8" class="text-center p-3">
                                            <p class="fs-6"><spring:message code="text.no.training.plan.created" /></p>
                                            <a href="/training-plan/create" class="btn btn-primary">
                                                <spring:message code="text.training.plan.create" />
                                            </a>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${empty subscribedClientsPagedListHolder.pageList}">
                                    <tr>
                                        <td colspan="8" class="text-center p-3">
                                            <p class="fs-6 mb-0"><spring:message code="text.client.not.found" /></p>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${subscribedClientsPagedListHolder.pageList}" var="client" >
                                        <tr class="align-middle">
                                            <td class="p-3">${client.lastName} ${client.firstName}</td>
                                            <td class="p-3">${client.age != null ? client.age : "N/A"}</td>
                                            <td class="p-3">
                                                <c:choose>
                                                    <c:when test="${client.gender != null}">
                                                        <spring:message code="gender.${client.gender.toString().toLowerCase()}" />
                                                    </c:when>
                                                    <c:otherwise>N/A</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="p-3">
                                                <c:choose>
                                                    <c:when test="${client.height != null}">
                                                        ${client.height} <spring:message code="text.cm" />
                                                    </c:when>
                                                    <c:otherwise>N/A</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="p-3">${client.subscribedTrainingPlanName}</td>
                                            <td class="p-3">
                                                <c:choose>
                                                    <c:when test="${client.lastWeight != null}">
                                                        ${client.lastWeight} <spring:message code="text.kg" />
                                                    </c:when>
                                                    <c:otherwise>N/A</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="p-3">
                                                <c:choose>
                                                    <c:when test="${client.lastCalorie != null}">
                                                        ${client.lastCalorie} <spring:message code="text.kcal" />
                                                    </c:when>
                                                    <c:otherwise>N/A</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="p-3 text-end text-nowrap">
                                                <a href="/client-details/index/${client.id}" class="btn btn-primary">
                                                    <i class="bi bi-list me-2"></i><spring:message code="text.details" />
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer d-flex border-0 p-3">
                    <tg:paging pagedListHolder="${subscribedClientsPagedListHolder}" pagedLink="${pagedLink}" />
                </div>
            </div>
        </div>
    </div>
</section>
<section class="mb-5">
    <div class="row mb-3">
        <div class="col">
            <h2 class="border border-2 text-wrapper mb-0 py-2 ps-3"><spring:message code="text.training.plan.trend.chart.title"/></h2>
        </div>
    </div>
    <c:choose>
        <c:when test="${not anyTrainingPlan}">
            <div class="card border shadow d-flex justify-content-center align-items-center" style="height: 500px">
                <p class="fs-5"><spring:message code="text.no.training.plan.created"/></p>
            </div>
        </c:when>
        <c:when test="${empty subscribedClientsPagedListHolder.pageList}">
            <div class="card border shadow d-flex justify-content-center align-items-center" style="height: 500px">
                <p class="fs-5"><spring:message code="text.client.not.found"/></p>
            </div>
        </c:when>
        <c:otherwise>
            <tg:trainingPlanTrendChart data="${trainingPlanTrendChartData}" />
        </c:otherwise>
    </c:choose>
</section>
<jsp:include page="../shared/_footer.jsp"/>