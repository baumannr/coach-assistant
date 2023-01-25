<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
</main>
</div>
<footer class="bg-dark">
    <div class="container text-white">
        <c:choose>
            <c:when test="${LocalDate.now().year == 2022}">
                &copy; 2022 <spring:message code="text.coachassitant"/>
            </c:when>
            <c:otherwise>
                &copy; 2022-${LocalDate.now().year} <spring:message code="text.coachassitant"/>
            </c:otherwise>
        </c:choose>
    </div>
</footer>
<script src="<c:url value="/lib/bootstrap/dist/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/lib/jquery/dist/jquery-3.6.2.min.js"/>"></script>
</body>
</html>
