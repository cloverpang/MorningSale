
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
	<div class="block T_mainHead" style="margin-top: 10px;">Role Management</div>
</div>
<div class="block GW7-11" style="width: 343px;">
	<div class="block general"><input type="button" name="submit" value="+Add New Role" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick='toggleFilter(); return false;' /></div>
</div>
<div class="block GW12-15">
	<div class="blockR" style=" margin-top: 20px;">
		<div class="blockR" style="padding-left: 5px;">
	    </div>
	</div>
</div>
</div>
</div>

<!--hidden area-->
<div class="blockLS GW1-15" id="toggleText" style="padding-top: 0px; display:none; ">
<s:form action="addRole" namespace="/role" method="post" theme="simple">
<div class="block GW1-15" style="background-color: rgb(239,239,239); margin-bottom: 0px; padding: 0px 0px 10px 0px;">
	<div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">角色名称 <span class="text">(Role Name)</span></div>
        <div class="block text" style="width: 200px;"><s:textfield name="roleName" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px;">
    <div class="R" >
        <div class="block textB2" style="margin-bottom: 4px;">角色描述 <span class="text">(Description)</span></div>
        <div class="block"><s:textfield name="description" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
	<div class="block" style="width: 303px; margin-right: 20px;">

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

<!-- Results -->
<div class="block GW1-15">
<table width="" cellpadding="0" cellspacing="0" border="0">
    <tr><td><div class="R GW1-15"><div class="blockLS GW1-15" style="padding: 7px 0 7px 0;">
				<div class="block T_subHead">Results&nbsp;<span class="text">(<span id="totalResult"><s:property value="roles.size()"/></span>)</span></div>
                </div>
            </div>
    </td></tr>
</table>
</div>
<!-- Results Table -->
<div class="block GW1-15">
    <table style="background-color: rgb(FFF,FFF,FFF);"cellpadding="0" cellspacing="0" border="0">
        <tr style="width: 948px;">
            <th class="textB2 LINE" style="width: 180px; padding-left: 0px; text-align: left;">Role Name</th>
            <th class="textB2 LINE" style="width: 308px; padding-left: 10px; text-align: left;">Description</th>
            <th class="textB2 LINE" style="width: 180px; padding-left: 10px; text-align: left;">Actions</th>
            <th class="textB2 LINE" style="width: 180px; padding-left: 10px; text-align: left;"></th>
            <th class="textB2 LINE" style="width: 100px; padding-left: 10px; text-align: left;"></th>
        </tr>
        <s:iterator value="roles" status="index">
         <s:if test="#index.odd == true">
          <tr style="width: 948px;" class="RowOdd" id="tablerow-<s:property value="name"/>">
            <td width="180px;" class="LINE text">
              <s:property value="name"/>
            </td>
            <td width="340px;" class="LINE text" style="padding-left: 10px;">
              <s:property value="desciption"/>
            </td>
            <td width="180px;" class="LINE T_name" style="padding-left: 10px;">
               <a href="<%=ctx%>/role/editRolePermission.action?roleName=<s:property value="name"/>" class="T_name">Edit Permission</a>
            </td>
            <td width="180px;" class="LINE T_name" style="padding-left: 10px;">
               <a href="<%=ctx%>/role/editRoleMenu.action?roleName=<s:property value="name"/>" class="T_name">Edit Menu</a>
            </td>
            <td width="68px;" class="LINE T_name" style="padding-left: 10px;">
                <a href="<%=ctx%>/role/deleteRole.action?roleName=<s:property value="name"/>" class="T_name" onclick="return DeleteRole(this, 'tablerow-<s:property value="name"/>');">delete</a>
            </td>
        </tr>
         </s:if>
         <s:else>
           <tr style="width: 948px;" class="RowEven" id="tablerow-<s:property value="name"/>">
            <td width="180px;" class="LINE text">
              <s:property value="name"/>
            </td>
            <td width="340px;" class="LINE text" style="padding-left: 10px;">
              <s:property value="desciption"/>
            </td>
            <td width="180px;" class="LINE T_name" style="padding-left: 10px;">
               <a href="<%=ctx%>/role/editRolePermission.action?roleName=<s:property value="name"/>" class="T_name">Edit Permission</a>
            </td>
            <td width="180px;" class="LINE T_name" style="padding-left: 10px;">
               <a href="<%=ctx%>/role/editRoleMenu.action?roleName=<s:property value="name"/>" class="T_name">Edit Menu</a>
            </td>
            <td width="68px;" class="LINE T_name" style="padding-left: 10px;">
               <a href="<%=ctx%>/role/deleteRole.action?roleName=<s:property value="name"/>" class="T_name" onclick="return DeleteRole(this, 'tablerow-<s:property value="name"/>');">delete</a>
            </td>
        </tr>
         </s:else>
        </s:iterator>
    </table>
    <br/>
</div>

<script type="text/javascript">
    function toggleFilter() {
        $("#toggleText").toggle(200);
    }

    function DeleteRole(obj, tablerowId) {

          var answer = confirm('Are you sure you want to delete this Role? ');
          if (answer == true) {
              $.ajax({
                  type: 'POST',
                  url: obj.href,
                  success: function(data) {

                      if(data == "true")
                      {
                         $('#' + tablerowId).remove();
                         ShowTip("Deleted Success!");
                      }
                      else
                      {
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