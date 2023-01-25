<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 15.
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="d-flex flex-grow-1 justify-content-end align-items-center gap-3 me-3">
    <span class="nav-link text-white">
      <spring:message code="text.loggedin" /> <sec:authentication property="principal.lastName"/> <sec:authentication property="principal.firstName"/>
    </span>
    <form:form cssClass="d-inline mb-0" action="/logout" method="post">
        <button type="submit" class="btn btn-outline-light border-secondary">
            <spring:message code="text.logout" />
        </button>
    </form:form>
</div>
