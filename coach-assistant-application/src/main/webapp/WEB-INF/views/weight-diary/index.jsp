<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 24.
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="weightDiaryPagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />
<c:url value="/weight-diary/index" var="pagedLink">
    <c:param name="page" value="~" />
</c:url>

<c:set var="title"><spring:message code="page.title.weight.diary.index" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row mb-5">
        <div class="col">
            <h1 class="border border-2 text-wrapper mb-0 py-3 ps-3"><spring:message code="text.weight.diary" /></h1>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col text-end">
            <a href="/weight-diary/create" class="btn btn-primary shadow">
                <i class="bi bi-plus-square me-2"></i><spring:message code="text.diary.create" />
            </a>
        </div>
    </div>
    <div class="row mb-5">
        <div class="col">
            <div class="card border shadow">
                <div class="card-header border-0 p-3">
                    <h2 class="fs-4 mb-0"><spring:message code="text.entries" /></h2>
                </div>
                <div class="table-responsive">
                    <table class="table align-middle mb-0">
                        <thead class="border-top border-bottom border-4 text-nowrap">
                        <tr>
                            <th class="p-3"><spring:message code="text.date" /></th>
                            <th class="p-3"><spring:message code="text.weight" /></th>
                            <td></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty weightDiaryPagedListHolder.pageList}">
                                <tr class="align-middle">
                                    <td class="text-center p-3" colspan="3">
                                        <p class="mb-0"><spring:message code="text.no.diary.found" /></p>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${weightDiaryPagedListHolder.pageList}" var="diary">
                                    <tr class="align-middle">
                                        <td class="p-3">${diary.createDate}</td>
                                        <td class="p-3">${diary.weight} <spring:message code="text.kg"/></td>
                                        <td class="p-3 text-end">
                                            <a href="/weight-diary/edit/${diary.id}" class="btn btn-primary">
                                                <i class="bi bi-pencil-square me-2"></i><spring:message code="text.edit" />
                                            </a>
                                            <form:form action="/weight-diary/delete/${diary.id}" method="post" class="d-inline">
                                                <button class="btn btn-danger ms-3"><i class="bi bi-x-square me-2"></i><spring:message code="text.delete" /></button>
                                            </form:form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer d-flex border-0 p-3">
                    <tg:paging pagedListHolder="${weightDiaryPagedListHolder}" pagedLink="${pagedLink}" />
                </div>
            </div>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col text-end">
            <a href="/client-dashboard/index" class="btn btn-primary shadow">
                <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.return" />
            </a>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>