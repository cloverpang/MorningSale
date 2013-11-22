<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 18/10/2013
  Time: 6:49:07 AM
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
	<div class="block T_mainHead" style="margin-top: 10px;"><s:property value="company.simpleName"/> <span class="T_mainHead2"> 用户管理</span> <s:hidden name="hde_allRoles" value="%{allRolesStr}"/> </div>
</div>
<div class="block GW7-11" style="width: 343px;">
	<div class="block general">
        <s:if test="companyId != null">
            <input type="button" name="submit" value="+ 添 加 用 户" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick="window.location.href='<%=ctx%>/user/addCompanyNewUser.action?companyId=<s:property value="companyId"/>'"  />
            <input type="button" name="submit" value="+ 批 量 添 加" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick="window.location.href='<%=ctx%>/user/batchAddCompanyUsers.action?companyId=<s:property value="companyId"/>'"  />
        </s:if>
        <s:else>
            <input type="button" name="submit" value="+ 添 加 用 户" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick="window.location.href='<%=ctx%>/user/addOurCompanyNewUser.action'"  />
            <input type="button" name="submit" value="+ 批 量 添 加" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick="window.location.href='<%=ctx%>/user/batchAddOurCompanyUsers.action'"  />
        </s:else>
    </div>
</div>
<div class="block GW12-15">
	<div class="blockR" style=" margin-top: 20px;">
		<div class="blockR" style="padding-left: 5px;">
			<div class="img_search"></div></div>
		<div class="blockR T_Filter"><a id="displayText" onclick='toggleFilter(); return false;' href="#">Show Filter</a></div>
	</div>
</div>
</div>
</div>

<!--hidden area for search-->
<div class="blockLS GW1-15" id="toggleText" style="padding-top: 0px; display:none; ">
<form name="form" id="form">
<div class="block GW1-15" style="background-color: rgb(239,239,239); margin-bottom: 0px; padding: 0px 0px 10px 0px;">
	<div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">上级经理</div>
        <div class="block"><s:textfield name="like_parentManager" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">姓名</div>
        <div class="block"><s:textfield name="like_realName" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
	<div class="block" style="width: 303px; ">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">工作门店</div>
        <div class="block">
            <s:select id="eq_defaultStore" name="eq_defaultStore" list="companyStores" listKey="storeName" listValue="storeName" headerValue="%{defaultSelectLabel}" headerKey="%{defaultSelectValue}" theme="simple" cssStyle="width:200px;"/>
            <s:hidden name="eqInt_company.companyId" id="eqInt_company.companyId" value='%{company.companyId}'></s:hidden>
        </div>
    </div>
	</div>
</div>
<div class="blockLD GW1-15" style="padding-top: 5px; padding-bottom: 20px;">
	<div class="block" style="width: 303px; margin-right: 20px;">&nbsp;</div>
	<div class="block" style="width: 626px;">
		 <div class="block">
           <input name="btnSearch" id="btnSearch" type="button" value="&nbsp;查 找&nbsp;" class="submitBoxR"
                                        onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                        style="" onclick="RefreshData()" />
            </div>
         <div class="block">
           <input name="btnClear" id="btnClear" type="reset" onclick="ShowThisCompanyUserData();"
                                        value="&nbsp;清 空&nbsp;" class="submitBoxR" onmouseover="this.style.backgroundColor='#E1EDF6'"
                                        onmouseout="this.style.backgroundColor='#FFF'" style="" />
         </div>
        </div>
	</div>
</form>
</div>

<!-- Results -->
<div class="block GW1-15">
  <div class="blockLS GW1-15" style="padding: 7px 0 7px 0;">
   <div class="block T_subHead">用户数&nbsp;<span class="text"><span id="search-result-count"></span></span></div>
   <div class="block T_subHead"><div style="float: left"></div> <div id="updatesloading" class="" style="float: left; margin-left: 10px;"><img src="<%=ctx%>/content/images/ajax-loader.gif" /></div></div>
  </div>
</div>
<!-- Results Table -->
<div class="block GW1-15">
    <div class="block GW1-15">
      <table style="background-color: rgb(FFF,FFF,FFF);" id="TableHeader" border="0">
        <tr style="width: 948px;">
            <th class="textB2 LINE" style="width: 85px; padding-left: 0px; text-align: left;">用户名</th>
            <th class="textB2 LINE" style="width: 85px; padding-left: 0px; text-align: left;">姓名</th>
            <th class="textB2 LINE" style="width: 130px; padding-left: 0px; text-align: left;">上级经理</th>
            <th class="textB2 LINE" style="width: 270px; padding-left: 0px; text-align: left;">角色</th>
            <th class="textB2 LINE" style="width: 240px; padding-left: 0px; text-align: left;">工作门店</th>
            <th class="textB2 LINE" style="width: 38px; padding-left: 0px; text-align: left;">修改</th>
            <th class="textB2 LINE" style="width: 50px; padding-left: 0px; text-align: left;">锁定</th>
            <th class="textB2 LINE" style="width: 50px; padding-left: 0px; text-align: left;">删除</th>
        </tr>
      </table>
      <table style="background-color: rgb(233,247,254);" id="SearchDataList" border="0">
      </table>
       <center>
            <div class="dataNotFound" id="latest-update-nodata" style="display: none">
       </div>
        </center>
        <!-- pagination div -->
        <div class="blockLD general GW1-15">
            <br/>
            <div class="blockR">
                <div class="pagination">
                </div>
            </div>
        </div>
        <textarea title="TableRow" class="tempest-template" style="display: none" cols="0" rows="0">
          <tr style="width: 948px;" class="{{rowBG}}" id="tablerow-{{item.userId}}">
            <td width="85px;" class="LINE text">
              {{item.userName}}
            </td>
            <td width="85px;" class="LINE text" style="padding-left: 0px;">
              <span id='itemRealName-{{item.userId}}'>{{item.realName}}</span>
            </td>
            <td width="130px;" class="LINE text" style="padding-left: 0px;">
              <span id='itemParentManager-{{item.userId}}'>{{item.parentManager}}</span>
            </td>
            <td width="270px;" class="LINE text" style="padding-left: 0px;">
             <span id='itemUserRolesStr-{{item.userId}}'>{{item.userRolesStr}}</span>
            </td>
            <td width="240px;" class="LINE text" style="padding-left: 0px;">
             <span id='itemDefaultStore-{{item.userId}}'>{{item.defaultStore}}</span>
            </td>
            <td width="38px;" class="LINE T_name" style="padding-left: 0px;">
               <%--<a href="/company/editCompany.action?companyId={{item.companyId}}" class="T_name">Update</a>--%>
                <a href="#" class="T_name" onclick="return ShowUpdateDiv('{{item.userId}}');">Edit</a>
            </td>
            <td width="50px;" class="LINE T_name" style="padding-left: 0px;">
               <a href="<%=ctx%>/user/lockUser.action?userId={{item.userId}}" class="T_name" onclick="return LockUser(this, 'lockrow-{{item.userId}}');"><span id="lockrow-{{item.userId}}" class="T_lock_{{item.isLock}}">{{item.isLock}}</span></a>
            </td>
            <td width="50px;" class="LINE T_name" style="padding-left: 0px;">
                <a href="<%=ctx%>/user/deleteUser.action?userId={{item.userId}}" class="T_name" onclick="return DeleteUser(this, 'tablerow-{{item.userId}}');">delete</a>
            </td>
          </tr>
        </textarea>
    </div>
</div>

 <div id="divCreateDialog" class="pagePop" style="display: none; padding-left: 20px;padding-right: 20px; padding-top: 0px; padding-bottom: 0px; border-collapse: collapse;">
     <div class="block" id="divOuterContainer" style=" border-top: solid 7px rgb(255,0,0);padding-top: 0px; margin: 0px;">
            <div id="divHeader" class="block T_mainHead Header" style="width: 464px; margin-top: 5px; font-size:12px;">
                更改用户信息  <input id="hde_userId" name="hde_userId" type="hidden"/>
            </div>
            <div id="divInnerContainer" class="block" style="width: 464px; height: auto;">
                <div class="block" style="margin-bottom: 10px;">
                    <div class="R" >
                        <div class="block textB2 generalS2" style="width: 400px;">
                            真实姓名 </div>
                        <div class="block padB2" style="width: 400px;">
                             <input type="text" name="new_realName"  id="new_realName" class="inputBoxB" style="width:200px;height:24px;" value="" disabled="false" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            上级 Manager 设定 <span style="font-weight:normal;">(如修改,请填写上级经理的真实姓名)</span></div>
                        <div class="block padB2" style="width: 400px;">
                            <input type="text" name="new_parentManager"  id="new_parentManager" class="inputBoxB" style="width:200px;height:24px;" value="" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            默认工作店铺 <span style="font-weight:normal;">(如修改,必须填写准确的店铺名,只是店员需要配置修改)</span></div>
                        <div class="block padB2" style="width: 400px;">
                            <input type="text" name="new_defaultStore"  id="new_defaultStore" class="inputBoxB" style="width:200px;height:24px;" value="" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                             用户角色设定
                        </div>
                        <div class="block padB2" style="width: 400px;">

                             <div class="block clean-gray" style="width: 400px; border: 1px solid #ccc; margin: 0px 0 10px 0; padding: 10px;">
                                 <div class="block">
                                     <div id="rolesUpdateDiv">

                                     </div>
                                 </div>
                             </div>

                        </div>
                </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            重置密码 (恢复为默认密码&nbsp;&nbsp; '<s:property value="company.companyCode"/>123456')
                        </div>
                        <div class="block padB2" style="width: 400px;">

                            <div class="block clean-gray" style="width: 400px; border: 1px solid #ccc; margin: 0px 0 10px 0; padding: 10px;">
                                <div class="block">
                                    <div id="resetPasswordDiv">
                                       <input type="button" name="" value="&nbsp;重置该用户密码&nbsp;" class="submitBoxR text" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin-left: 0px;height:24px;" onclick="ResetUserPassword()"/>
                                        <span id="resetPasswordTipMessage" style="color:red;display:none;">密码重置成功！</span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <br>
                    <div class="block GW1-3">
                </div>
               </div>
    </div>
</div>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function() {
             var opt = {
            Name: 'JsonDataTable',
            Section: 'RR',
            Subsection: 'RR1',
            Tbl: '#SearchDataList',
            HeaderResultID: '#search-result-count',
            SearchControl: '#form',
            Template: 'TableRow',
            ServerURL: '<%=ctx%>/jsonData/getUserJsonData.action',
            Pagesize: 15,
            Norecs: 15,
            CurrentPage: 0,
            Paginate: true,
            ValidRowsFieldName: 'SiteSection',
            ValidRows: ['RR'],
            EmptyDataLabel: '#latest-update-nodata',
            Progress: 'updatesloading',
            SortField:'createTime',
            SortOrder:'desc'
        };


        var obj = new CloverJsonDataTemplateItem(opt);
        //obj.GetData();
        // assign   to window so we can retrieve later
        window.JsonDataTable = obj;
        window.JsonDataTable.FilterData();

        $("#divCreateDialog").dialog('close');
        SetupDialog();
    });

    function RefreshData() {
        if (window.JsonDataTable) {

            toggle();
            var options = { CurrentPage: 0};
            // Start from page number 1
            $.extend(window.JsonDataTable.opts, options);
            window.JsonDataTable.FilterData();
        }
    }

    function ShowThisCompanyUserData() {
        if (window.JsonDataTable) {
            var currentCompanyId = <s:property value="company.companyId"/>;
            var options = "eqInt_company.companyId=" + currentCompanyId;
            // Start from page number 1
            //$.extend(window.JsonDataTable.opts, options);
            window.JsonDataTable.RefreshOptionsData(options);
        }
    }

    function toggleFilter() {
        $("#toggleText").toggle(200);
        if ($.trim($("#displayText").text()) == "Show Filter") {
            $("#displayText").text('Hide Filter');
        }
        else {
            $("#displayText").text('Show Filter');
        }
    }

    function SetupDialog() {
        $("#divCreateDialog").dialog({
            autoOpen: false,
            position: 'center',
            width: 500,
//            height: 360,
            modal: true,
            resizable: false,
            overlay: {
                backgroundColor: '#000',
                opacity: 0.5
            },
            buttons: {
                'Save Update': function() {
                    var update = UpdateUser();
                    if(update)
                    {
                       $(this).dialog('close');
                    }
                },
                'Cancel': function() {
                    $(this).dialog('close');
                }
            }

        });
    }

    function RemoveUserRole(userId,roleName)
    {
        $.ajax({
            type: 'POST',
            url: '<%=ctx%>/user/userRemoveRole.action?userId=' + userId + '&roleName=' + roleName,
            success: function(data) {
                if(data.result == "true")
                {
                    ShowUpdateDiv(userId);
                    //alert("Remove Success!");
                }
                else
                {
                    ShowAlert('Remove Failed!');
                }

            },
            error: function(xhr, ajaxOptions, thrownError) {
                var resp = $(xhr.responseText);
                if (resp != "") {
                    var al = $(resp).filter("title").html();
                    if (al != "") {
                        ShowAlert('Error!');
                    }
                }
            }
        });
    }

    function AddUserRole(userId,roleName)
    {
        $.ajax({
            type: 'POST',
            url: '<%=ctx%>/user/userAddRole.action?userId=' + userId + '&roleName=' + roleName,
            success: function(data) {
                if(data.result == "true")
                {
                    ShowUpdateDiv(userId);
                    //alert("Add Success!");
                }
                else
                {
                    ShowAlert('Add Failed!');
                }

            },
            error: function(xhr, ajaxOptions, thrownError) {
                var resp = $(xhr.responseText);
                if (resp != "") {
                    var al = $(resp).filter("title").html();
                    if (al != "") {
                        ShowAlert('Error!');
                    }
                }
            }
        });
    }

    function ShowUpdateDiv(userId){
        $.ajaxSetup({ cache: false });

        $.getJSON('<%=ctx%>/jsonData/getUserJsonData.action?getSingleData=true&query=eq_userId=' + userId, null,
                function(data) {
                    var dataObj = $.parseJSON(data);//转换为json对象
                    //alert(dataObj[0].item[0].companyId);

                    $('#hde_userId').val(userId);
                    $('#new_realName').val(dataObj[0].item[0].realName);
                    $('#new_parentManager').val(dataObj[0].item[0].parentManager);
                    $('#new_defaultStore').val(dataObj[0].item[0].defaultStore);

                    $('#itemUserRolesStr-' + getParam().userId).html(dataObj[0].item[0].userRolesStr);

                    var allRoles = $('#hde_allRoles').val();
                    var rolesArray = new Array();
                    rolesArray = allRoles.split(',');

                    var userRolesStr = dataObj[0].item[0].userRolesStr;

                    $('#rolesUpdateDiv').html("");
                    var html = "";
                    for(i=0;i<rolesArray.length;i++)
                    {
                        if(contains(userRolesStr,rolesArray[i],false))
                        {
                            html +='<span id="userRoleRow-' + rolesArray[i] + "-" + userId +'">';
                            html += "<li style='padding: 3px 0px; text-indent:0;'>";
                            html += '<input type="submit" value="Remove From" style="width:100px;" class="submitBoxR textB2" onclick="RemoveUserRole(' + "'" + userId + "'" + ",'" + rolesArray[i] + "'" + ');"/>';
                            html += "&nbsp;&nbsp;" + rolesArray[i] + "</li>";
                            html +='</span>';
                        }
                        else
                        {
                            html +='<span id="userRoleRow-' + rolesArray[i] + "-" + userId +'">';
                            html += "<li style='padding: 3px 0px; text-indent:0;'>";
                            html += '<input type="submit" value="Add To" style="width:70px;" class="submitBoxR textB2" onclick="AddUserRole(' + "'" + userId + "'" + ",'" + rolesArray[i] + "'" + ');"/>';
                            html += "&nbsp;&nbsp;" + rolesArray[i] + "</li>";
                            html +='</span>';
                        }
                    }
                    //alert(html);
                    $("#rolesUpdateDiv").append(html);
                    OpenDialog();
                });
    }

    function OpenDialog() {

        $('#resetPasswordTipMessage').hide();
        $('#divCreateDialog').dialog('open');

        return false;
    }

    function UpdateUser(){
        var flag = validateForm();
        if (flag)
        {
            $.post("<%=ctx%>/user/updateUser.action", getParam(), function (data) {
                if (data.result == "true") {
                    RefreshUpdatedData();
                    ShowTip("更新 成功!");
                    return true;
                }
                else {
                    //alert(getParam().companyCode);
                    ShowAlert("更新 失败!");
                    return false;
                }
            }, "json");
            //alert("error!");
            return true;
        }
        else
        {
            return false;
        }
    }

    function ResetUserPassword()
    {
        var userId = $("#hde_userId").val();
        $.ajax({
            type: 'POST',
            url: '<%=ctx%>/user/resetUserPassword.action?userId=' + userId,
            success: function(data) {
                //alert(data);

                if(data.result == "true")
                {
                    $('#resetPasswordTipMessage').show();
                }
                else
                {
                    alert('重置密码 失败!');
                }

            },
            error: function(xhr, ajaxOptions, thrownError) {
                var resp = $(xhr.responseText);
                if (resp != "") {
                    var al = $(resp).filter("title").html();
                    if (al != "") {
                        ShowAlert('Ajax Error!');
                    }
                }
            }
        });
    }

    function DeleteUser(obj, tablerowId) {
          var answer = confirm('确定删除该用户么? ');
          if (answer == true) {
              $.ajax({
                  type: 'POST',
                  url: obj.href,
                  success: function(data) {
                      //alert(data);

                      if(data.result == "true")
                      {
                         $('#' + tablerowId).remove();
                         ShowTip("删除 成功!");
                      }
                      else
                      {
                         ShowAlert('删除 失败!');
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

    function LockUser(obj, lockrowId) {
          var answer = confirm('确定 对该用户 锁定 or 解除锁定 么 ');
          if (answer == true) {
              $.ajax({
                  type: 'POST',
                  url: obj.href,
                  success: function(data) {
                      //alert(data);

                      if(data.result == "true")
                      {
                          if ($('#' + lockrowId).html() == 'false') {
                              $('#' + lockrowId).html('true');
                              $('#' + lockrowId).css('color','red');
                          }
                          else {
                              $('#' + lockrowId).html('false');
                              $('#' + lockrowId).css('color','black');
                          }
                         ShowTip("锁定 操作成功!");
                      }
                      else
                      {
                         ShowAlert('锁定 失败!');
                      }

                  },
                  error: function(xhr, ajaxOptions, thrownError) {
                      var resp = $(xhr.responseText);
                      if (resp != "") {
                          var al = $(resp).filter("title").html();
                          if (al != "") {
                              ShowAlert('Error!');
                          }
                      }
                  }
              });
          }
          return false;
      }

    function validateForm() {
        var flag = true;
        if($('#new_realName').val() == '')
        {
            ShowAlert('you should input the real name');
            $('#new_realName').addClass('inputRequired');
            flag = false;
        }
        else
        {
           $('#new_realName').removeClass('inputRequired');
        }
        return flag;
    }

    function getParam(){
        var para = {
            userId: $("#hde_userId").val(),
            realName: $("#new_realName").val(),
            parentManager: $("#new_parentManager").val(),
            defaultStore:$("#new_defaultStore").val()
        };
        return para;
    }

    function RefreshUpdatedData(){
        $('#itemRealName-' + getParam().userId).html(getParam().realName);
        $('#itemParentManager-' + getParam().userId).html(getParam().parentManager);
        $('#itemDefaultStore-' + getParam().userId).html(getParam().defaultStore);
    }
</script>

</div>
<%@include file="../../footer.jsp"%>
</body>
</html>