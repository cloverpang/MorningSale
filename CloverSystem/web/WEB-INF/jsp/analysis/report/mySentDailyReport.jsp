<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 09/11/2013
  Time: 2:17:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JavaEE Simple Sale Analysis System</title>
</head>
<body>
<%@include file="../../header.jsp"%>
<!--body page header-->
<div class="block GW1-15">
<div class="R GW1-15">
<div class="block T_mainHead" style="padding-top: 10px;padding-bottom:10px;"><s:property value="sendSuccessTipMessage"/> </div>
</div>
</div>

<!-- Results -->
<div class="block GW1-15">
<table width="" cellpadding="0" cellspacing="0" border="0">
    <tr><td><div class="R GW1-15"><div class="blockLS GW1-15" style="padding: 7px 0 7px 0;">
				<div class="block T_subHead"></div>
                </div>
            </div>
    </td></tr>
</table>
</div>

<!-- Results Table -->
<div class="block GW1-15">
    <div id="ChartsDiv" class="block GW1-15">
        <div class="block GW1-15">
            <div class="block T_subHead" >
                <span id="chartsTitle"></span>
            </div>
        </div>
        <div style="width:948px;" class="block GW1-15">
            <div id="PieChartContainer" style="float:left;width:448px;;height:100px;">

            </div>
            <div id="BarChartContainer" style="float:left;width:500px;;height:100px;">

            </div>
        </div>
        <div id="LineChartContainer" class="block GW1-15" style="width:948px;;height:200px;">

        </div>
    </div>
</div>
<%@include file="../../footer.jsp"%>
</body>
</html>