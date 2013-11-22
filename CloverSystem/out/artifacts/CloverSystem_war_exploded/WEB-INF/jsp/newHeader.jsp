<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 04/11/2013
  Time: 7:32:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String ctx = request.getContextPath();
%>
    <link rel="icon" type="image/ico" href="<%=ctx%>/content/images/favicon.ico"/>
    <link rel="shortcut icon" type="image/ico" href="<%=ctx%>/content/images/favicon.ico"/>
    <link href="<%=ctx%>/content/arc.css" rel="stylesheet" type="text/css" />
    <!--[if IE]><link type="text/css" href="<%=ctx%>/content/arc-ie.css" rel="stylesheet" /><![endif]-->
    <link href="<%=ctx%>/content/management/site.css" rel="stylesheet" type="text/css" />
    <link href="<%=ctx%>/content/management/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />
    <link href="<%=ctx%>/content/thickbox.css" rel="stylesheet" type="text/css" />
    <link href="<%=ctx%>/content/pagenation.css" rel="stylesheet" type="text/css" />
    <link href="<%=ctx%>/content/calendar.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="<%=ctx%>/scripts/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/jquery.form.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/jquery.tempest.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/jquery.validate.min.js"></script>

    <script type="text/javascript" src="<%=ctx%>/scripts/chart/highcharts.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/chart/exporting.js"></script> 

    <script type="text/javascript" src="<%=ctx%>/scripts/common.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/cloverLib/calendar.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/cloverLib/cloverJsonDataTemplateItem.js"></script>

    <script type="text/javascript">
        var currentSelectedMenu = '';

        $(document).ready(function() {
            setSelectedMenu();
        });

        function setSelectedMenu() {
            if (currentSelectedMenu == null || currentSelectedMenu == '' || currentSelectedMenu == 'undefined') {
                return;
            }
            $('#' + currentSelectedMenu + ' > a').css('background-color', '#e4f0ff');
            $('#' + currentSelectedMenu + ' > a').css('color', '#000000');
        }
    </script>
<div class="page">
<s:if test="tip!=null">
	<script type="text/javascript">
        $(function() {
            ShowTip('<s:property value="tip"/>');
        });
	</script>
</s:if>
<s:if test="alert!=null">
	<script type="text/javascript">
        $(function() {
            ShowAlert('<s:property value="alert"/>');
        });
	</script>
</s:if>
<!-- ARC HEADER -->
<div class="R">
			<div class="block GW1-15" style="border-top-color: #FF0000; border-top-width: 7px; border-top-style: solid; padding: 0px; margin: 0px;">
                <!-- ARC HEADER -->
                <div class="R GW1-15">
                    <div class="blockLL T_login">
                        <a href="<%=ctx%>/user/myPanel.action">
                            个人面板</a>&nbsp;&nbsp;
                        <a href="<%=ctx%>/user/logout.action">
                            退出</a>
                    </div>
					 <div class="blockLL T_login">
                        欢迎您, <s:property value="#session.realName"/>
                    </div>
                    <div class="block general" style="margin-bottom: 10px;">
                        <div class="block general" style="margin-right: 10px;">
                            <%--<div class="img_logo">--%>
                            <%--</div>--%>
                            <span id="logoTitle" style="font-weight:bold;font-size:24px;color:#FF0000;"><s:property value="#session.currentUserCompany.simpleName"/></span>
                        </div>
                        <div class="block general" style="margin-top: 0px;">
                            <%--<div class="img_CMS">--%>
                            <%--</div>--%>
                            <span id="morningSaleTitle" style="font-weight:normal;font-size:24px;color:#666666;"> Morning Sale</span>
                        </div>
                    </div>

                </div>
            </div>
</div>
<%@include file="menu.jsp"%>