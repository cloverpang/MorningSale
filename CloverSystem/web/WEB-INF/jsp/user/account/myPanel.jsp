<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 08/11/2013
  Time: 1:58:40 PM
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
<div id="main" class="block" style="margin: 0px; padding: 0px;">
<!--body page header-->
<div class="block GW1-15">
<div class="R GW1-15">
<div class="block T_mainHead" style="padding-top: 10px;padding-bottom:10px;">用户面板</div>
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
    <div id="PanelDivs" class="block GW1-15">
        <div class="block GW1-15">
            <div class="block T_subHead" >
                <span id="chartsTitle"></span>
            </div>
        </div>
        <div style="width:948px;" class="block GW1-15">
            <div id="UpdatePassword" style="float:left;width:300px;;height:400px;">
                <s:form action="updatePassword" namespace="/user" method="post" theme="simple">
                    <div class="block" style="margin-bottom: 10px;">
                        <div class="R" >
                            <div class="block textB2 generalS2" style="width: 250px;">
                                更改密码
                            </div>
                        </div>

                        <div class="R" >
                            <div class="block text generalS2" style="width: 250px;">
                                旧密码 </div>
                            <div class="block padB2" style="width: 250px;">
                                <s:password name="oldPassword" cssClass="inputBoxB" size="30" style="width:200px;height:24px;" value=""/>
                            </div>
                        </div>
                        <div class="R" >
                            <div class="block text generalS2" style="width: 250px;">
                                新密码 </div>
                            <div class="block padB2" style="width: 250px;">
                                <s:password name="newPassword" cssClass="inputBoxB" size="30" style="width:200px;height:24px;" value=""/>
                            </div>
                        </div>
                        <div class="R" >
                            <div class="block text generalS2" style="width: 250px;">
                                重复新密码 </div>
                            <div class="block padB2" style="width: 250px;">
                                <s:password name="repeatPassword" cssClass="inputBoxB" size="30" style="width:200px;height:24px;" value=""/>
                            </div>
                        </div>

                        <div class="R" >
                            <div class="block text generalS2" style="width: 250px;">
                            </div>
                            <div class="block padB2" style="width: 250px;">
                                <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                       name="submitButton" value="保 存" class="submitBoxR textB2" style="margin-left: 0px;" onclick= "this.form.submit()" />
                            </div>
                        </div>
                        <br>
                        <div class="block GW1-3">
                        </div>
                    </div>
                </s:form>
            </div>
            <div id="div2" style="float:left;width:300px;;height:400px;">

            </div>
            <div id="div3" style="float:left;width:348px;;height:400px;">

            </div>
        </div>
    </div>
</div>    

</div>

<%@include file="../../footer.jsp"%>
</body>
</html>