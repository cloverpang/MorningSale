<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 25/09/2013
  Time: 12:40:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String ctx = request.getContextPath();
%>
<html>
<head>
    <title>
       JavaEE Simple Sale Analysis System Login Page
    </title>
    <meta content="java EE"
        name="description" />
    <meta content="jsva EE"
        name="keywords" />
    <link rel="icon" type="image/ico" href="<%=ctx%>/content/images/favicon.ico" />
    <link rel="shortcut icon" type="image/ico" href="<%=ctx%>/content/images/favicon.ico" />
    <link href="<%=ctx%>/content/arc.css" rel="stylesheet" type="text/css" />
    <!--[if IE]><link type="text/css" href="<%=ctx%>/content/arc-ie.css" rel="stylesheet" /><![endif]-->
    <link href="<%=ctx%>/content/management/site.css" rel="stylesheet" type="text/css" />
    <link href="<%=ctx%>/content/management/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />
    <link href="<%=ctx%>/content/thickbox.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/scripts/markitup/skins/markitup/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/scripts/markitup/sets/html/style.css" />
    <script src="<%=ctx%>/scripts/jquery-1.6.1.min.js" type="text/javascript"></script>

</head>
<body>

<s:if test="tip!=null">
	<script>
		alert('<s:property value="tip"/>');
	</script>
</s:if>

    <div class="page">
        <div class="R">
            <div class="block GW1-15" style="border-top-color: #FF0000; border-top-width: 7px;
                border-top-style: solid; padding: 0px; margin: 0px;">
                <!-- ARC HEADER -->
                <div class="R GW1-15">
                    <div class="block general">
                        <div class="block general" style="margin-right: 10px;">
                            <div class="img_logo">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="Companies-Result-Content">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="searchResult">
                <tr>
                    <td width="305px" valign="top">
                        <div class="img_login" style="margin-top: 40px;">
                           <div class="block">
                                  <table border="0" cellpadding="0" cellspacing="0" class="loginTable">
            <tr>
                <th style="padding-bottom: 5px; padding-left: 20px; padding-top: 10px; width: 300px;"
                    class="block T_subHead">
                    Log In to Sale Analysis System Admin Module
                </th>
            </tr>
            <tr>
                <td>
                    <div class="R text2R" style="width:370px;padding-left:20px;">
                        </div>
                </td>
            </tr>
             <s:form action="login!adminLogin" namespace="/admin" method="post" theme="simple">
            <tr>
                <td style="padding-left: 20px; padding-bottom: 5px; padding-top: 5px; width: 300px;"
                    class="labelNormal block text">
                    AdminName <span class="text2R"></span>
                </td>
            </tr>
            <tr>
                <td style="padding-bottom: 5px; padding-left: 20px;">
                    <s:textfield name="adminname" cssClass="inputBoxB" size="30"/>
                    <br />
                     <input type="hidden" name="urlhash" id="urlhash" value=""/>
                </td>
            </tr>
            <tr>
                <td style="padding-left: 20px; padding-bottom: 5px; padding-top: 5px; width: 300px;"
                    class="labelNormal block text">
                    Password <span class="text2R"></span>
                </td>
            </tr>
            <tr>
                <td class="text" style="padding-bottom: 5px; padding-left: 20px;">
                    <s:password name="password" cssClass="inputBoxB" size="30"/>
                    <br />
                </td>
            </tr>
            <tr>
                <td style="padding-bottom: 5px; padding-left: 20px;" class="labelNormal text">
                </td>
            </tr>
            <tr>
                <td style="padding: 5px 0px 10px 20px;">
                    <s:submit value="Log On" cssClass="btn submitBoxB" onmousemove="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"/>
                </td>
            </tr>
            <tr>
                <td class="T_pdf" style="padding: 5px 0px 0px 20px;">
                    Forgot Password
                </td>
            </tr>
            </s:form>
            <tr>
                <td class="textR" style="height: 15px; padding-left: 20px; padding-top: 10px;">
                </td>
            </tr>
        </table>
                           </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- Footer -->
        <div class="R">
            <div class="block GW1-15 text">
                <div class="block text" style="padding-bottom: 60px; padding-top: 20px;">
                    <span class="textB2">Any Questions please contact at</span><br />
                    Call  at <a class="T_pdf" href="tel:13418854380">134 1885 4380</a>&nbsp;or email
                    <span class="T_pdf"><a href="mailto:qingzhoubaiyang@163.com">qingzhoubaiyang@163.com</a></span>&nbsp;&nbsp;/&nbsp;
                    QQ at <a class="T_pdf">15880035</a>&nbsp;
                </div>
            </div>
        </div>
    </div>
</body>
</html>