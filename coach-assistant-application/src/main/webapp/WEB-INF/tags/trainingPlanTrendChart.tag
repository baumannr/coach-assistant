<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 12. 17.
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="data" required="true" type="java.util.Map"%>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load("current", { packages: ["corechart"] });
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['<spring:message code="text.training.plan" />', '<spring:message code="text.number.of.subscriber" />'],
            <c:forEach items="${data}" var="item">
                ['${item.key} - ${item.value} <spring:message code="text.person" />',  ${item.value},],
            </c:forEach>
        ]);

        var options = {
            pieHole: 0.5,
            pieSliceBorderColor: 'transparent',
            backgroundColor: 'transparent',
            fontName: 'Roboto',
            sliceVisibilityThreshold: 0,
            legend: { position: 'right', textStyle: { color: 'white', fontSize: 16 } }
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
    }
</script>

<div id="donutchart" class="card border shadow" style="height: 500px"></div>