<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 20/09/2013
  Time: 1:08:29 PM
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
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/scripts/markitup/skins/markitup/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/scripts/markitup/sets/html/style.css" />

    <script type="text/javascript" src="<%=ctx%>/scripts/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/admin.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/microsoftAjax.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/microsoftMvcAjax.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/microsoftMvcValidation.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/thickbox.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/swfobject.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/charCount.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/jquery.form.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/markitup/jquery.markitup.js"></script>
    <script type="text/javascript" src="<%=ctx%>/scripts/markitup/sets/html/set.js"></script>

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
	<script>
		alert('<s:property value="tip"/>');
	</script>
</s:if>
		<!-- ARC HEADER -->
        <div class="R">
			<div class="block GW1-15" style="border-top-color: #FF0000; border-top-width: 7px; border-top-style: solid; padding: 0px; margin: 0px;">
                <!-- ARC HEADER -->
                <div class="R GW1-15">
					<div class="blockLL T_login">
						<a href="<%=ctx%>/admin/logout.action">
							Logout</a></div>
					 <div class="blockLL T_login">
                        Welcome, <s:property value="#session.username"/>
                    </div>
					<div class="block general" style="margin-bottom: 10px;">
						<div class="block general" style="margin-right: 10px;">
							<div class="img_logo">
							</div>
						</div>
						<div class="block general" style="margin-top: 0px;">
							<div class="img_CMS">
							</div>
						</div>
					</div>

                </div>
            </div>
        </div>
          <!-- Tab Menus -->
        <div class="R GW1-15" style="border-collapse: collapse; border-bottom: 1px solid #ccc; padding-bottom: 10px; margin-bottom: 10px; margin-top: 10px; z-index: 3;">
            <div class="block">
                <div class="block">
                    <ul id="sddm">
                            <li id="CompanyTab">
                               <a href="<%=ctx%>/admin/addCompany.action">Company Management</a>
                            </li>
                            <li id="SystemTab">
                               <a href="<%=ctx%>/permission/management.action">System Management</a>
                            </li>

                        <script type="text/javascript">
                            $.map($('#sddm li:not(:last)').toArray(), function(n) {
                                $(n).css('border-right', 'none');
                            });
                        </script>

                    </ul>
                    <!--[if lt IE 7]> <div style='clear: both; height: 59px; padding:0 0 0 15px; position: relative;'> <a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/images/upgrade.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." /></a></div> <![endif]-->
                </div>
            </div>
        </div>

    <!-- sublink -->
<div class="R GW1-15">

   <div id="menuLinks" class="block" style="margin: 0px; padding: 0px;">
    <div class="block GW1-15">
       <div class="R GW1-15" style="margin-bottom: 20px;">

      </div>
    </div>
   </div>
</div>
