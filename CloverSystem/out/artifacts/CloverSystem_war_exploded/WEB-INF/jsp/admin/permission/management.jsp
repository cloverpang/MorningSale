<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 27/09/2013
  Time: 1:53:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JavaEE Simple Sale Analysis System Admin Module</title>
</head>
<body>
<%@include file="../../header.jsp"%>
<div id="main" class="block" style="margin: 0px; padding: 0px;">
<!--body header-->
<div class="block GW1-15">
<div class="R GW1-15">
<div class="block GW1-6">
	<div class="block T_mainHead" style="margin-top: 10px;">Permission Management</div>
</div>
<div class="block GW7-11" style="width: 343px;">
    <div class="block general"><input type="button" name="submit" value="+Add New Permission" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick='toggleFilter(); return false;' /></div>
</div>
<div class="block GW12-15">
</div>
</div>
</div>

<!--hidden area-->
<div class="blockLS GW1-15" id="toggleText" style="padding-top: 0px; display:none; ">
<s:form action="addPermission" namespace="/permission" method="post" theme="simple">
<div class="block GW1-15" style="background-color: rgb(239,239,239); margin-bottom: 0px; padding: 0px 0px 10px 0px;">
	<div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Package Name </div>
        <div class="block"><s:textfield name="packageName" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Action Name</div>
        <div class="block"><s:textfield name="actionName" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Permission Name </div>
        <div class="block"><s:textfield name="permissionName" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
</div>
<div class="block GW1-15" style="background-color: rgb(239,239,239); margin-bottom: 0px; padding: 0px 0px 10px 0px;">
    <div class="block" style="width: 303px; margin-right:20px;">
     <div class="R" >
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;" >Roles select</div>
        <div class="block"></div>
    </div>
	</div>
    <div class="block" style="width: 626px;">
    <div class="R" >
        <div class="block textB2" style="margin-bottom: 4px;"><s:checkboxlist id="rolesSelect" name="rolesSelect" list="roleslist" listKey="name" listValue="name"></s:checkboxlist></div>
        <div class="block">

        </div>
    </div>
	</div>
</div>
<div class="blockLD GW1-15" style="padding-top: 5px; padding-bottom: 20px;">
	<div class="block" style="width: 303px; margin-right: 20px;">&nbsp;</div>
	<div class="block" style="width: 626px;">
		<div class="block">
            <s:submit value="Save" cssClass="btn submitBoxB" onmousemove="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"/>
		</div>
		<div class="block">
            <input type="button" name="" value="Cancel" class="submitBoxR text" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin-left: 5px;" onclick='toggleFilter(); return false;' />
        </div>
	</div>
</div>
</s:form>
</div>

<!-- Results Table -->
<div class="block GW1-15" id="managePermission">
  <div class="blockLS GW1-15" style="padding-top: 8px;">
            <div class="block GW1-4 textB2">
                <div class="block GW1-3 textB2 generalS">
                    Permissions
                </div>
                <div class="block GW1-3 text" id="toggleText2" style="padding-top: 0px; ">
                    Result (<s:property value="permissionsModel.AllPermissions.size()"/>)
                </div>
            </div>
            <div class="block GW5-15">
                <table style="width: 706px;" id="maintable;">
                    <tr style="width: 706px;">
                        <td class="generalS textB2" style="width: 192px;">Package</td>
                        <td class="generalS textB2" style="width: 514px;">Action</td>
                    </tr>
                     <s:iterator value="permissionsModel.AllPackagePermissions" status="index">
                    <tr class="text" style="width: 706px; vertical-align: top; border-collapse: collapse;">
                        <td colspan="2">
                            <table style="width: 706px; vertical-align: top; border-collapse: collapse;">
                                <tr class="text" style="width: 706px; vertical-align: top;" id="tablerow-<s:property value="packageName"/>">
                                    <td class="textB2 LINE" style="background-color: #e4f0ff; width: 192px; border-collapse: collapse;">
                                       <s:property value="packageName"/><br/>
                                       <a href="<%=ctx%>/permission/deletePermission.action?packageName=<s:property value="packageName"/>&actionName=" class="T_name" onclick="return DeletePermission(this, 'tablerow-<s:property value="packageName"/>');">delete all</a>
                                    </td>
                                    <td class="text LINE" style="background-color: #e4f0ff; width: 514px; vertical-align: top; border-collapse: collapse;">
                                     <table style="width: 514px;">

                                            <s:iterator value="packagePermissions" status="index">
                                            <tr style="width: 514px; vertical-align: top; border-collapse: collapse;" id="tablerow-<s:property value="packageName"/>-<s:property value="action"/>">
                                                <td class="text generalS2" style="width: 240px; height:20px; vertical-align: top; border-collapse: collapse;">
                                                    <div>
                                                       <s:property value="action"/>
                                                    </div>
                                                </td>
                                                <td class="text generalS2" style="width: 240px;height:20px; vertical-align: top; border-collapse: collapse;">
                                                    <div>
                                                      <s:property value="name"/>
                                                    </div>
                                                </td>
                                                <td class="text generalS2" style="width: 34px; height:20px; vertical-align: top; border-collapse: collapse;">
                                                    <div>
                                                      <a href="<%=ctx%>/permission/deletePermission.action?packageName=<s:property value="packageName"/>&actionName=<s:property value="action"/>" class="T_name" onclick="return DeletePermission(this, 'tablerow-<s:property value="packageName"/>-<s:property value="action"/>');">delete</a>
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
</div>
 </div>

<script type="text/javascript">
    function toggleFilter() {
        $("#toggleText").toggle(200);
    }

      function DeletePermission(obj, tablerowId)
      {
          var answer = confirm('Are you sure you want to delete this Permission? ');
          if (answer == true)
          {

              $.ajax({
                  type: 'POST',
                  url: obj.href,
                  success: function(data) {
                      if(data.deleteResult == "true")
                      {
                         $('#' + tablerowId).remove();
                         //alert(data.deleteResult);
                         ShowTip("Deleted Success!");
                      }
                      else
                      {
                         //alert(data.deleteResult);
                         ShowAlert('Delete Failed!');
                      }

                  },
                  error: function(xhr, ajaxOptions, thrownError) {
                      var resp = $(xhr.responseText);
                      if (resp != "") {
                          var al = $(resp).filter("title").html();
                          if (al != "") {
                              //alert(al);
                              ShowAlert('Error!');
                          }
                      }
                  }
              });
          }
          return false;
      }
</script>
</div>
<%@include file="../../footer.jsp"%>
</body>
</html>