<%@ page import="com.cloversystem.model.*" %>
<%@ page import="com.cloversystem.domain.*" %>
<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 20/09/2013
  Time: 2:33:27 PM
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
<%@include file="header.jsp"%>
<div id="main" class="block" style="margin: 0px; padding: 0px;">
<!--body page header-->
<div class="block GW1-15">
<div class="R GW1-15">
<div class="block T_mainHead" style="padding-top: 10px;padding-bottom:10px;">欢迎使用 Morning Sale 销售分析系统!</div>
</div>
</div>

<!-- Results -->
<div class="block GW1-15">
<table width="" cellpadding="0" cellspacing="0" border="0">
    <tr><td>
       <div class="R GW1-15">
        <div class="blockLS GW1-15" style="padding: 7px 0 7px 0;">
           <span class="textB2"> 您所拥有的角色是: <s:property value="userInfoModel.userRolesStr"/></span>
           <input type="hidden" id="userRolesStr" value="${userInfoModel.userRolesStr}"/>
       </div>
    </td></tr>
</table>
</div>

<!-- Results Table -->
<div class="block GW1-15">
    <div id="IntroDiv" class="block GW1-15" style="padding-top: 8px;display:block;">
        <div class="block GW1-15">
            <div class="block T_subHead" >
                <span id="chartsTitle"></span>
            </div>
        </div>
        <div style="width:948px;" class="block GW1-15">
            <div id="SalePersonContainer" style="float:left;width:300px;;height:400px;">
                店员,发日销售
            </div>
            <div id="SaleManagerContainer" style="float:left;width:300px;;height:400px;">
                业务员,查看 日销售分析,阶段分析
            </div>
            <div id="CompanyManagerContainer" style="float:left;width:348px;;height:400px;">
                销售总监,管理公司门店和人员 并查看综合分析
            </div>
        </div>
    </div>

    <div id="redirectTip" class="blockLS GW1-15" style="padding-top: 8px;display:none;">
        <div style="width:948px;" class="block GW1-15">
            <div id="div1" style="float:left;width:300px;;height:300px;">

            </div>
            <div id="div2" style="float:left;width:348px;;height:300px;padding-top:60px;">
               <span id="redirectMessage" style="font-size:16px;font-weight:bold;text-align:left;color:#333333;">
                   请稍等, 系统正在帮你跳转...<br>
                   <img src="<%=ctx%>/content/images/save-loading.gif" border="0"/>
               </span>
            </div>
            <div id="div3" style="float:left;width:300px;;height:300px;">

            </div>
        </div>
    </div>
</div>

 <script language="javascript" type="text/javascript">
    $(document).ready(function() {
        var userRolesStr = $('#userRolesStr').val();

        if(userRolesStr == "店员")
        {
            $('#IntroDiv').hide();
            $('#redirectTip').show();
            window.location.href = '<%=ctx%>/report/addDailySaleReport.action';
        }

        if(contains(userRolesStr,"业务员"))
        {
            $('#IntroDiv').hide();
            $('#redirectTip').show();
            window.location.href = '<%=ctx%>/report/viewYesterdayReport.action';
        }

    });
</script>
</div>

<%@include file="footer.jsp"%>
</body>
</html>