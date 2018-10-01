<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.website.monitoring.tool.model.WebsiteMonitoringModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="/css/main.css"/>
    <script type="text/javascript" src="/scripts/main.js"></script>

    <audio id="audioCritical" src="http://www.soundjay.com/button/beep-07.mp3" autostart="false"></audio>
    <audio id="audioWarning" src="https://www.soundjay.com/button/button-48.mp3" autostart="false"></audio>
</head>
<body>

<%
    List<WebsiteMonitoringModel> x = (ArrayList) request.getAttribute("Model");
    int monitoringPeriod = (int) (x.get(0)).getMonitoringPeriod();
    response.setIntHeader("Refresh", monitoringPeriod);
%>

<div>

    <table id="websiteMonitoring">
            <tr>
                <th style="width: 200px">URL</th>
                <th style="width: 140px">Status</th>
                <th style="width: 200px">About</th>
                <th style="width: 200px">substring</th>
            </tr>

        <c:forEach items="${Model}" var="result">
                <tr>
                    <td style="width: 200px">${result.url}</td>
                    <td style="width: 140px" class="status">${result.status}</td>
                    <td style="width: 200px">${result.about}</td>
                    <td style="width: 200px">${result.substring}</td>
                </tr>
        </c:forEach>

    </table>

    <button class="buttonStart" onclick="startMonitoring(<%=monitoringPeriod%>)">Start</button>
    <button class="buttonStop" onclick="window.stop()">Stop</button>

</div>

<div>
    <p class="about">About:</p><br/>
    <p class="text">Monitoring period = <%=monitoringPeriod%> seconds</p>
</div>

</body>
</html>
