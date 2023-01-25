<%--
  Created by IntelliJ IDEA.
  User: baumannr
  Date: 2022. 12. 17.
  Time: 8:04
  To change this template use File | Settings | File Templates.
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="diaries" required="true" type="java.util.List"%>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['<spring:message code="text.date" />', '<spring:message code="text.weight" />'],
            <c:forEach items="${diaries}" var="diary">
            ['${diary.createDate}',  ${diary.weight},],
            </c:forEach>
        ]);

        var options = {
            backgroundColor: 'transparent',
            curveType: 'function',
            colors:['yellow'],
            legend: {
                position: 'bottom',
                textStyle:
                    {
                        color: 'white',
                        fontSize: 16
                    }
            },
            hAxis: {
                textStyle: {
                    color: 'white',
                    fontName: 'Roboto',
                    fontSize: 16,
                    bold: false,
                    italic: false }
            },
            vAxis: {
                textStyle: {
                    color: 'white',
                    fontName: 'Roboto',
                    fontSize: 16,
                    bold: false,
                    italic: false
                }
            },
        };

        var chart = new google.visualization.LineChart(document.getElementById('weight_chart'));

        chart.draw(data, options);
    }
</script>
<div id="weight_chart" class="card border shadow" style="height: 500px"></div>

