<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 12. 14.
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="org.springframework.util.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="pagedListHolder" required="true" type="org.springframework.beans.support.PagedListHolder"%>
<%@ attribute name="pagedLink" required="true" type="java.lang.String"%>

<div class="d-flex align-items-center text-nowrap">
    <spring:message  code="text.page" />
    <c:choose>
        <c:when test="${empty pagedListHolder.pageList}">0 / 0</c:when>
        <c:otherwise>${pagedListHolder.page + 1} / ${pagedListHolder.pageCount}</c:otherwise>
    </c:choose>
</div>

<div class="ms-auto">
    <div class="pagination-container">
        <ul class="pagination text-align-center mb-0">
            <c:choose>
                <c:when test="${not empty pagedListHolder.pageList and pagedListHolder.pageCount == 1}">
                    <li class="page-item active">
                        <a class="page-link">1</a>
                    </li>
                </c:when>
                <c:when test="${pagedListHolder.pageCount > 1}">
                    <c:if test="${!pagedListHolder.firstPage}">
                        <li class="page-item">
                            <a class="page-link" href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage() - 1))%>"><</a>
                        </li>
                    </c:if>
                    <c:if test="${pagedListHolder.firstLinkedPage > 0}">
                        <li class="page-item">
                            <a class="page-link" href="<%=StringUtils.replace(pagedLink, "~", "0")%>">1</a>
                        </li>
                    </c:if>
                    <c:if test="${pagedListHolder.firstLinkedPage > 1}">
                        <li class="page-item"><span class="pagingDots">...</span><li>
                    </c:if>
                    <c:forEach begin="${pagedListHolder.firstLinkedPage}"
                               end="${pagedListHolder.lastLinkedPage}" var="i">
                        <c:choose>
                            <c:when test="${pagedListHolder.page == i}">
                                <li class="page-item active"><a class="page-link" href="#">${i+1}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i")))%>">${i+1}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 2}">
                        <li class="page-item"><span class="pagingDots">...</span></li>
                    </c:if>
                    <c:if test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 1}">
                        <li class="page-item">
                            <a class="page-link" href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPageCount() - 1))%>">${pagedListHolder.pageCount}</a>
                        </li>
                    </c:if>
                    <c:if test="${!pagedListHolder.lastPage}">
                        <li class="page-item">
                            <a class="page-link" href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage() + 1))%>">></a>
                        </li>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <li class="disabled page-item">
                        <a class="page-link"><</a>
                    </li>
                    <li class="disabled page-item">
                        <a class="page-link">></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
