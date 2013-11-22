<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 04/10/2013
  Time: 4:15:47 PM
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

<!--body header-->
<div class="block GW1-15">
 <div class="R GW1-15">
            <div class="block general T_mainHead"><s:property value="roleName"/> 's <span class="T_mainHead2"> Menu Management</span></div>
            <div class="blockR"> <input type="button" name="" value="Back" class="submitBoxR text" style="margin-left: 5px; " onclick="window.location.href='<%=ctx%>/role/management.action'" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" /></div>
 </div>
</div>

<!-- Results Table -->
<div class="block GW1-15" id="updateRoleMenu">
  <div class="blockLS GW1-15" style="padding-top: 8px;">
      <div class="block GW1-4 textB2">
                <div class="block GW1-3 textB2 generalS">
                    Menus
                </div>
                <div class="block GW1-3 textB2 generalS"><input type="checkbox" id="selectAll" onclick="ToggleChecks(this);" />Select All</div>
                <div class="block GW1-3 text" id="toggleText2" style="padding-top: 0px; display:none; ">

                </div>
            </div>
      <s:form action="editRoleMenu!updateRoleMenu" namespace="/role" method="post" theme="simple">
       <s:hidden name="hiddenRoleName" id="hiddenRoleName" value="%{roleName}"></s:hidden>
       <div class="block GW5-15">
                <table style="width: 706px;" id="maintable;">
                    <tr style="width: 706px;">
                        <td class="generalS textB2" style="width: 192px;">Tab</td>
                        <td class="generalS textB2" style="width: 140px;">MenuLink Title</td>
                        <td class="generalS textB2" style="width: 374px;">MenuLink Url</td>
                    </tr>
                    <s:iterator value="menuTabs" status="index">
                    <tr class="text" style="width: 706px; vertical-align: top; border-collapse: collapse;">
                        <td colspan="3">
                            <table style="width: 706px; vertical-align: top; border-collapse: collapse;">
                                <tr class="text" style="width: 706px; vertical-align: top;" id="tablerow-<s:property value="tabName"/>">
                                    <td class="textB2 LINE" style="background-color: #e4f0ff; width: 192px; border-collapse: collapse;">
                                       <s:property value="TabName"/>
                                    </td>
                                    <td class="text LINE" style="background-color: #e4f0ff; width: 514px; vertical-align: top; border-collapse: collapse;">
                                     <table style="width: 514px;">

                                            <s:iterator value="tabMenuLinks" status="index" id="tabMenuLinks">
                                            <tr style="width: 514px; vertical-align: top; border-collapse: collapse;" id="tablerow<s:property value="linkUrlExcuteStr"/>">
                                                <td class="text generalS2" style="width: 140px; height:20px; vertical-align: top; border-collapse: collapse;">
                                                    <div>
                                                       <s:set name="roleList" value="linkRoles"></s:set>
                                                       <input type="checkbox" name='MenuLinkId_<s:property value="linkUrlExcuteStr"/>' id='MenuLinkId_<s:property value="linkUrlExcuteStr"/>'
                                                            value='<s:property value="linkUrl"/>' <s:if test="roleName in #roleList">checked</s:if>/>
                                                       <s:property value="linkTitle"/>
                                                    </div>
                                                </td>
                                                <td class="text generalS2" style="width: 360px;height:20px; vertical-align: top; border-collapse: collapse;">
                                                    <div>
                                                      <s:property value="linkUrl"/>
                                                    </div>
                                                </td>
                                                <td class="text generalS2" style="width: 14px; height:20px; vertical-align: top; border-collapse: collapse;">
                                                    <div>

                                                    </div>
                                                </td>
                                            </tr>
                                            </s:iterator>

                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    </s:iterator>
                </table>
            </div>
       <div class="block GW1-15" style="margin-bottom: 20px; margin-top: 0px; padding: 0px;">
                <div class="block GL1-4 GW5-15" style="border-top: #ccc 1px solid;">
                    <div class="block general">
                        <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                name="submitButton" value="Save Changes" class="submitBoxR textB2" style="margin-left: 0px;" onclick= "this.form.submit()" />
                    </div>
                </div>
            </div>
      </s:form>
</div>
 </div>

<script type="text/javascript">
    function ToggleChecks(obj) {
        var chkboxs = $(":checkbox");
        if (obj.checked == true)
         {
                $(chkboxs).each(function()
                {
                    this.checked = true;
                });
         }

       if (obj.checked == false)
       {
                $(chkboxs).each(function() {
                   this.checked = false;
                });
       }
    }
</script>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>