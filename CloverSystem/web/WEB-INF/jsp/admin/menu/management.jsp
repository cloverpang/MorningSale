<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 10/10/2013
  Time: 11:06:03 AM
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
<div class="block GW1-6">
	<div class="block T_mainHead" style="margin-top: 10px;">Menu Management</div>
</div>
<div class="block GW7-11" style="width: 343px;">
    <div class="block general"><input type="button" name="submit" value="+Add New Menu" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick='toggleFilter(); return false;' /></div>
</div>
<div class="block GW12-15">
</div>
</div>
</div>

<!--hidden area-->
<div class="blockLS GW1-15" id="toggleText" style="padding-top: 0px; display:none; ">
<s:form action="addMenu" namespace="/menu" method="post" theme="simple">
<div class="block GW1-15" style="background-color: rgb(239,239,239); margin-bottom: 0px; padding: 0px 0px 10px 0px;">
	<div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Tab Name</div>
        <div class="block" style="width: 200px;"><s:textfield name="tabName" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px;">
    <div class="R" >
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Tab Title</div>
        <div class="block"><s:textfield name="tabTitle" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;"></div>
        <div class="block" style="width: 200px;"></div>
    </div>
	</div>
</div>
<div class="block GW1-15" style="background-color: rgb(239,239,239); margin-bottom: 0px; padding: 0px 0px 10px 0px;">
	<div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">MenuLink Title</div>
        <div class="block" style="width: 200px;"><s:textfield name="linkTitle" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px;margin-right:20px;">
    <div class="R" >
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">MenuLink Url</div>
        <div class="block"><s:textfield name="linkUrl" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Extend Urls</div>
        <div class="block" style="width: 200px;"><s:textarea name="extendUrls" id="extendUrls" rows="4" wrap="true"  cssClass="inputBox2B" cssStyle="width:280px;"/></div>
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
    <div class="block" style="width: 600px;">
    <div class="R" >
        <div class="block textB2" style="margin-bottom: 4px;"><s:checkboxlist id="rolesSelect" name="rolesSelect" list="roleslist" listKey="name" listValue="name"/></div>
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

<!-- Results -->
<div class="block GW1-15">
<table width="" cellpadding="0" cellspacing="0" border="0">
    <tr><td><div class="R GW1-15"><div class="blockLS GW1-15" style="padding: 7px 0 7px 0;">
				<div class="block T_subHead">Menu Tabs&nbsp;<span class="text">(<span id="totalResult"><s:property value="menuTabs.size()"/></span>)</span></div>
                </div>
            </div>
    </td></tr>
</table>
</div>
<!-- Results Table -->
<div class="block GW1-15">
    <s:if test="menuTabs!=null">
      <div class="block GW1-15">
      </div>
      <table width="948" cellpadding="0" cellspacing="0" border="0">
        <tr style="width: 948px;">
            <th class="textB2 LINE" style="width: 188px; padding-left: 0px; text-align: left;">Tab Name</th>
            <th class="textB2 LINE" style="width: 180px; padding-left: 0px; text-align: left;">Tab Title</th>
            <th class="textB2 LINE" style="width: 580px; padding-left: 0px; text-align: left;">Link Title/Url</th>
        </tr>
       </table>
      <table width="948" cellpadding="0" cellspacing="0" border="0">
      <s:iterator value="menuTabs" status="index">
       <tr style="width: 948px;" class="RowOdd" id="tablerow-<s:property value="tabName"/>">
            <td width="188px;" class="LINE text">
             <s:property value="tabName"/> (
                <a href="#" class="T_name" onclick="return ShowUpdateDiv('editTabName-<s:property value="tabName"/>');">edit</a> |
                <a href="<%=ctx%>/menu/deleteMenu.action?tabName=<s:property value="tabName"/>&linkUrl=" class="T_name" onclick="return DeleteMenu(this, 'tablerow-<s:property value="tabName"/>');">delete</a>)
                <div id="editTabName-<s:property value="tabName"/>" style="padding-top: 0px;display:none;">
                    New TabTitle
                    <br/>
                    <input type="text" name="inputTabTitle-<s:property value="tabName"/>"  id="inputTabTitle-<s:property value="tabName"/>" class="inputBoxB" style="width:100px;height:24px;" value="<s:property value="tabTitle"/>" />
          
                    <input type="button" name="" value="Update" class="submitBox text" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin-left: 0px;height:24px;" onclick="return UpdateTabTitle('<s:property value="tabName"/>','editTabName-<s:property value="tabName"/>');" />
                </div>
            </td>
           <td width="180px;" class="LINE text" style="padding-left: 0px;text-align: left;">
             <span id='tabTitle-<s:property value="tabName"/>'><s:property value="tabTitle"/></span>
            </td>
            <td width="580px;" class="LINE text" style="padding-left: 0px;text-align: left;">
              <table width="580" cellpadding="0" cellspacing="0" border="0">
               <s:iterator value="tabMenuLinks" status="index">
                   <tr style="width: 580px;" id="tablerow-<s:property value="linkUrlExcuteStr"/>">
                       <td class="text generalS2" style="width: 200px; height:20px; vertical-align: top; border-collapse: collapse;">
                          <span id='linkTitle-<s:property value="linkUrlExcuteStr"/>'><s:property value="linkTitle"/></span>
                           (
                           <a href="#" class="T_name" onclick="return ShowUpdateDiv('editMenuLink-<s:property value="linkUrlExcuteStr"/>');">edit</a> |
                           <a href="<%=ctx%>/menu/deleteMenu.action?tabName=<s:property value="tabName"/>&linkUrl=<s:property value="linkUrl"/>" class="T_name" onclick="return DeleteMenu(this, 'tablerow-<s:property value="linkUrlExcuteStr"/>');">delete</a>)
                       </td>
                       <td class="text generalS2" style="width: 380px; height:20px; vertical-align: top; border-collapse: collapse;">
                        <a href="<%=ctx%>/<s:property value="linkUrl"/>"><s:property value="linkUrl"/> </a>
                               <%--<s:iterator value="extendUrls" status="index">--%>
                              <%--<div><s:property/></div>--%>
                            <%--</s:iterator>--%>
                           <%--<s:property value="extendUrls"/>--%>
                       </td>
                   </tr>

                   <tr style="width: 580px;" class="text generalS2">
                       <td colspan="2">
                          <div id="editMenuLink-<s:property value="linkUrlExcuteStr"/>" style="padding-top: 0px;display:none;">
                           <div class="block">
                               New Link <br/>
                              <input type="text" name="inputLinkTitle-<s:property value="linkUrlExcuteStr"/>"  id="inputLinkTitle-<s:property value="linkUrlExcuteStr"/>" class="inputBoxB" style="width:200px;height:24px;" value="<s:property value="linkTitle"/>" />
                           </div>
                           <div class="block">
                               New Extend Urls <br/>
                             <textarea rows="4" name="inputExtendUrls-<s:property value="linkUrlExcuteStr"/>" id="inputExtendUrls-<s:property value="linkUrlExcuteStr"/>" class="inputBox2B" style="width:400px;"><s:property value="extendUrls"/></textarea>
                           </div>
                           <div class="block"> <br/>
                            &nbsp;<input type="button" name="" value="Update" class="submitBoxR text" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin-left: 0px;height:24px;" onclick="return UpdateLink('<s:property value="tabName"/>','<s:property value="linkUrlExcuteStr"/>','<s:property value="linkUrl"/>','editMenuLink-<s:property value="linkUrlExcuteStr"/>');" />
                            <br/><br/><br/>&nbsp;<input type="button" name="" value="Cancel" class="submitBoxR text" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin-left: 0px;height:24px;" onclick="return ShowUpdateDiv('editMenuLink-<s:property value="linkUrlExcuteStr"/>');" />
                           </div>
                          </div>
                       </td>
                   </tr>

               </s:iterator>
              </table>
            </td>
        </tr>
     </s:iterator>

    </table>
  </s:if>
<br />
</div>

 <script type="text/javascript">
    function toggleFilter() {
        $("#toggleText").toggle(200);
    }

    function ShowUpdateDiv(updateDivId)
    {
       $('#' + updateDivId).toggle(10);
    }

    function UpdateTabTitle(tabName,updateDivId)
    {
        var newTabTitle = $('#inputTabTitle-' + tabName).val();
        var newTabTitleEncode = encodeURI(newTabTitle);
        //alert(newTabTitleEncode);
        $.ajax({
            type: 'POST',
            url: '<%=ctx%>/menu/updateMenu.action?tabName=' + tabName + '&tabTitle=' + newTabTitleEncode + '&linkUrl=&linkTitle=&extendUrls=',
            success: function(data) {
                if(data.result == "true")
                {
                    $('#' + updateDivId).toggle(10);
                    $('#tabTitle-' + tabName).html(newTabTitle);
                    ShowTip("Updated Success!");
                }
                else
                {
                    $('#' + updateDivId).toggle(10);
                    //$('#tabTitle-' + tabName).html(newTabTitle);
                    ShowAlert('Updated Failed!');
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

    function UpdateLink(tabName,linkUrlExcuteStr,linUrl,updateDivId)
    {
        var newLinkTitle = $('#inputLinkTitle-' + linkUrlExcuteStr).val();
        var newLinkTitleEncode = encodeURI(newLinkTitle);

        var newExtendUrls = $('#inputExtendUrls-' + linkUrlExcuteStr).val();
        $.ajax({
            type: 'POST',
            url: '<%=ctx%>/menu/updateMenu.action?tabName=' + tabName + '&tabTitle=&linkUrl=' + linUrl + '&linkTitle=' + newLinkTitleEncode + '&extendUrls=' + newExtendUrls,
            success: function(data) {
                if(data.result == "true")
                {
                    $('#' + updateDivId).toggle(10);
                    $('#linkTitle-' + linkUrlExcuteStr).html(newLinkTitle);
                    ShowTip("Updated Success!");
                }
                else
                {
                    $('#' + updateDivId).toggle(10);
                    ShowAlert('Updated Failed!');
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

      function DeleteMenu(obj, tablerowId)
      {
          var answer = confirm('Are you sure you want to delete this Item? ');
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