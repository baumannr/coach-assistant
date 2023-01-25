<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 12. 04.
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${not empty messageSuccess}">
    <div class="toast-container position-fixed top-0 end-0 p-3 mt-5">
        <div id="toastSuccess" class="toast align-items-center alert alert-success p-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex mx-2">
                <div class="toast-body fs-6">
                    ${messageSuccess}
                </div>
                <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
    <script src="<c:url value='/js/app.js' />"></script>
    <script src="<c:url value='/lib/bootstrap/dist/js/bootstrap.bundle.min.js' />"></script>
    <script type="text/javascript">
        showToast('toastSuccess');
    </script>
</c:if>
<c:if test="${not empty messageWarning}">
    <div class="toast-container position-fixed top-0 end-0 p-3 mt-5">
        <div id="toastWarning" class="toast align-items-center alert alert-warning p-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex mx-2">
                <div class="toast-body fs-6">
                        ${messageWarning}
                </div>
                <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
    <script src="<c:url value='/js/app.js' />"></script>
    <script src="<c:url value='/lib/bootstrap/dist/js/bootstrap.bundle.min.js' />"></script>
    <script type="text/javascript">
        showToast('toastWarning');
    </script>
</c:if>
