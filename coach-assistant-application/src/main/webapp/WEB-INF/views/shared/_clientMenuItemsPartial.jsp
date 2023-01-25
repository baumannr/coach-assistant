<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 11. 15.
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<li class="nav-item">
    <a class="nav-link" href="/client-profile/index"><spring:message code="text.profil" /></a>
</li>
<li class="nav-item">
    <a class="nav-link" href="/client-dashboard/index"><spring:message code="text.dashboard" /></a>
</li>
<li class="nav-item">
    <a class="nav-link" href="/training-plan/index"><spring:message code="text.trainingplans" /></a>
</li>
<li class="nav-item">
    <a class="nav-link" href="/weight-diary/index"><spring:message code="text.weight.diary" /></a>
</li>
<li class="nav-item">
    <a class="nav-link" href="/calorie-diary/index"><spring:message code="text.calorie.diary" /></a>
</li>