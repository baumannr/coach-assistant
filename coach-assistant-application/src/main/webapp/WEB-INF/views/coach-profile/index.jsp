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

<c:set var="title"><spring:message code="page.title.profile.index" /></c:set>
<jsp:include page="../shared/_header.jsp">
    <jsp:param name="title" value="${title}" />
</jsp:include>
<section>
    <div class="row justify-content-center mb-5 border border-2 text-wrapper">
        <div class="col container-fix-width">
            <h1 class="fs-2 mb-0 py-3 ps-3"><spring:message code="text.profil" /></h1>
        </div>
    </div>
    <div class="row justify-content-center mb-3">
        <div class="col container-fix-width">
            <div class="card border shadow">
                <div class="card-header border-bottom border-4 p-3">
                    <h2 class="fs-4 mb-0"><spring:message code="text.profil.data" /></h2>
                </div>
                <div class="card-body p-3">
                    <dl class="row mb-0">
                        <dt class="col-sm-4"><spring:message code="label.lastname" /></dt>
                        <dd class="col-sm-8">${coachDTO.lastName}</dd>
                        <dt class="col-sm-4"><spring:message code="label.firstname" /></dt>
                        <dd class="col-sm-8">${coachDTO.firstName}</dd>
                        <dt class="col-sm-4"><spring:message code="label.email" /></dt>
                        <dd class="col-sm-8">${coachDTO.email}</dd>
                    </dl>
                </div>
                <div class="card-footer border-0 p-3">
                    <a href="/coach-profile/edit" class="btn btn-primary me-2">
                        <i class="bi bi-pencil-square me-2"></i><spring:message code="text.edit" />
                    </a>
                    <a href="/coach-dashboard/index" class="btn btn-primary shadow">
                        <i class="bi bi-arrow-left-square me-2"></i><spring:message code="text.return" />
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../shared/_footer.jsp"/>