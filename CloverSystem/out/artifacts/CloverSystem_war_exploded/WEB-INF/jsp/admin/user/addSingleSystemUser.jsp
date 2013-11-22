<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 18/10/2013
  Time: 1:32:53 PM
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
            <div class="block general T_mainHead"><s:property value="company.simpleName"/> <span class="T_mainHead2"> 添加用户 </span></div>
            <div class="blockR">
                <s:if test="companyId != null">
                    <input type="button" name="" value="Back" class="submitBoxR text" style="margin-left: 5px; " onclick="window.location.href='<%=ctx%>/company/manageCompanyUsers.action?companyId=<s:property value="companyId"/>'" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" />
                </s:if>
                <s:else>
                   <input type="button" name="" value=" 返 回 " class="submitBoxR text" style="margin-left: 5px; " onclick="window.location.href='<%=ctx%>/company/manageOurCompanyUsers.action'" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" />
                </s:else>
            </div>
 </div>
</div>

<!-- Results -->
<div class="block GW1-15">

</div>
<!-- Results Table -->
<div class="block GW1-15">
    <s:if test="companyId != null">
        <s:form action="addCompanyNewUser!add" namespace="/user" method="post" theme="simple">
            <div class="blockLS GW1-15" style="padding-top: 8px;">
                <div class="block GL1-4 GW5-15" style="margin-bottom: 20px;">

                    <div class="block">
                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                用户名</div>
                            <div class="R">
                                <s:textfield name="userName" cssClass="inputBoxB" size="50" value="" onblur="TestUserIsExisting(this)"/>
                                <s:textfield name="currentCompanyId" cssClass="inputBoxB" size="50" value="%{companyId}" cssStyle="width:10px;"/>
                                <span id="userIsExisting" style="color:red;display:none;"> &nbsp;&nbsp;此用户名已存在！请更换新的用户名.</span>
                                <span id="userNotExisting" style="color:green;display:none;">&nbsp;&nbsp;此用户名可以使用.</span>
                            </div>
                            <div class="R T_label" style="margin-top:5px">
                                推荐方式1:(公司code + 数字编号，如 <s:property value="company.companyCode"/>1)<br>
                                推荐方式2:(公司code + 下划线 + 用户姓名拼音，如 <s:property value="company.companyCode"/>_name)
                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">

                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                真实姓名</div>
                            <div class="R">
                                <s:textfield name="realName" cssClass="inputBoxB" size="50"/>
                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                上级 Manager</div>
                            <div class="R">
                                <s:textfield name="parentManager" cssClass="inputBoxB" size="50" onclick="ShowSelectManagerDiv();"/>
                            </div>
                            <div class="R T_label" style="margin-top:5px">(角色如为销售经理，上级Manager为空则意味这此用户没有所属上级领导)</div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                默认所属store</div>
                            <div class="R">
                                <s:textfield name="defaultStore" cssClass="inputBoxB" size="50" onclick="ShowSelectStoreDiv();"/>
                            </div>
                            <div class="R T_label" style="margin-top:5px">(角色如为店员，则需要选择店员所工作的店铺)</div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                用户角色</div>
                            <div class="R">
                                <s:checkboxlist id="rolesSelect" name="rolesSelect" list="roleslist" listKey="name" listValue="name" theme="simple"/>
                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="R">

                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            </div>
                            <div class="R">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="blockLD GW1-15">
                    <div class="block GL1-4 GW5-15">
                        <div class="block general">
                            <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                   name="submitButton" value="Save" class="submitBoxR textB2" style="margin-left: 0px;" onclick= "this.form.submit()" />
                            <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                   name="cancelButton" value="Cancel" class="submitBoxR text" style="margin-left: 5px;"
                                   onclick="window.location.href='<%=ctx%>/company/manageCompanyUsers.action?companyId=<s:property value="companyId"/>'" />
                        </div>
                    </div>
                </div>
            </div>
        </s:form>
    </s:if>
    <s:else>
        <s:form action="addOurCompanyNewUser!add" namespace="/user" method="post" theme="simple">
            <div class="blockLS GW1-15" style="padding-top: 8px;">
                <div class="block GL1-4 GW5-15" style="margin-bottom: 20px;">


                    <div class="block">
                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                用户名</div>
                            <div class="R textB2">
                                <s:textfield name="userName" cssClass="inputBoxB" size="50" value="" onblur="TestUserIsExisting(this)"/>
                                <span id="userIsExisting" style="color:red;display:none;"> &nbsp;&nbsp;此用户名已存在！请更换新的用户名.</span>
                                <span id="userNotExisting" style="color:green;display:none;">&nbsp;&nbsp;此用户名可以使用.</span>
                            </div>
                            <div class="R T_label" style="margin-top:5px">
                                推荐方式1:(公司code + 数字编号，如 <s:property value="company.companyCode"/>1)<br>
                                推荐方式2:(公司code + 下划线 + 用户姓名拼音，如 <s:property value="company.companyCode"/>_name)
                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">

                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                真实姓名</div>
                            <div class="R">
                                <s:textfield name="realName" cssClass="inputBoxB" size="50"/>
                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                上级 经理</div>
                            <div class="R">
                                <s:textfield name="parentManager" cssClass="inputBoxB" size="50" onclick="ShowSelectManagerDiv();"/>
                            </div>
                            <div class="R T_label" style="margin-top:5px">(角色如为销售经理，上级Manager为空则意味这此用户没有所属上级领导)</div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                默认所属门店</div>
                            <div class="R">
                                <s:textfield name="defaultStore" cssClass="inputBoxB" size="50" onclick="ShowSelectStoreDiv();"/>
                            </div>
                            <div class="R T_label" style="margin-top:5px">(角色如为店员，则需要选择店员所工作的店铺)</div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                用户角色</div>
                            <div class="R text">
                                <s:checkboxlist id="rolesSelect" name="rolesSelect" list="currentUserRoles" listKey="roleName" listValue="roleName" theme="simple"/>
                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="R">

                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            </div>
                            <div class="R">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="blockLD GW1-15">
                    <div class="block GL1-4 GW5-15">
                        <div class="block general">
                            <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                   name="submitButton" value="保存" class="submitBoxR textB2" style="margin-left: 0px;" onclick= "this.form.submit()" />
                            <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                   name="cancelButton" value="取消" class="submitBoxR text" style="margin-left: 5px;"
                                   onclick="window.location.href='<%=ctx%>/company/manageOurCompanyUsers.action'" />
                        </div>
                    </div>
                </div>
            </div>
        </s:form>
    </s:else>
</div>

 <div id="divCreateDialog" class="pagePop" style="display: none; padding-left: 20px;padding-right: 20px; padding-top: 0px; padding-bottom: 0px; border-collapse: collapse;">
     <div class="block" id="divOuterContainer" style=" border-top: solid 7px rgb(255,0,0);padding-top: 0px; margin: 0px;">
            <div id="divInnerContainer" class="block" style="width: 464px; height: auto;">
                <div class="block" style="margin-bottom: 10px;">
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                             非店员 选择一个用户作为新增用户上级经理
                        </div>
                        <div class="block padB2" style="width: 420px;">

                             <div class="block clean-gray" style="width: 420px; border: 1px solid #ccc; margin: 0px 0 10px 0; padding: 10px;">
                                 <div class="block">
                                     <div id="radio">
                                        <%--<s:radio id="parentManagerSelect" name="parentManagerSelect" list="companyUsers" listKey="realName" listValue="realName" onclick="SelectParentManager();" theme="simple"/>--%>
                                         <s:if test="companyUsers.size() > 0">
                                             <s:iterator value="companyUsers" status="index">
                                                 <div class="block textB2" style="padding-left:10px; width: 50px;height:24px;"><a href="#" class="textB2" style="font-size:12px;color:#336699;" onclick="SelectParentManager('<s:property value="realName"/>')"><s:property value="realName"/></a></div>
                                             </s:iterator>
                                         </s:if>
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

 <div id="divCreateDialog2" class="pagePop" style="display: none; padding-left: 20px;padding-right: 20px; padding-top: 0px; padding-bottom: 0px; border-collapse: collapse;">
     <div class="block" id="divOuterContainer2" style=" border-top: solid 7px rgb(255,0,0);padding-top: 0px; margin: 0px;">
            <div id="divInnerContainer2" class="block" style="width: 464px; height: auto;">
                <div class="block" style="margin-bottom: 10px;">
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 430px;">
                             店员 请选择一个门店作为新增用户所工作的门店
                        </div>
                        <div class="block padB2" style="width: 440px;">

                             <div class="block clean-gray" style="width: 440px; border: 1px solid #ccc; margin: 0px 0 10px 0; padding: 10px;">
                                 <div class="block">
                                     <div id="radio2">
                                        <%--<s:radio id="storesSelect" name="storesSelect" list="companyStores" listKey="storeName" listValue="storeName" onclick="SelectDefaultStore();" theme="simple"/>--%>
                                            <s:if test="companyStores.size() > 0">
                                                <s:iterator value="companyStores" status="index">
                                                    <div class="block textB2" style="padding-left:5px; width: 80px;height:24px;"><a href="#" class="textB2" style="font-size:12px;color:#336699;" onclick="SelectDefaultStore('<s:property value="storeName"/>')"><s:property value="storeName"/></a></div>
                                                </s:iterator>
                                            </s:if>
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
        $("#divCreateDialog").dialog('close');
        $("#divCreateDialog2").dialog('close');
        SetupDialog();
    });

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
                //                'Save Update': function() {
                //                    var update = UpdateUser();
                //                    if(update)
                //                    {
                //                       $(this).dialog('close');
                //                    }
                //                },
                'Cancel': function() {
                    $(this).dialog('close');
                }
            }

        });

        $("#divCreateDialog2").dialog({
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
                //                'Save Update': function() {
                //                    var update = UpdateUser();
                //                    if(update)
                //                    {
                //                       $(this).dialog('close');
                //                    }
                //                },
                'Cancel': function() {
                    $(this).dialog('close');
                }
            }

        });
    }

    function ShowSelectManagerDiv(){
        OpenManagerDialog();
    }

    function ShowSelectStoreDiv(){
        OpenStoreDialog();
    }

    function OpenManagerDialog() {

        $('#divCreateDialog').dialog('open');

        return false;
    }

    function OpenStoreDialog() {

        $('#divCreateDialog2').dialog('open');

        return false;
    }

    function SelectParentManager(realName)
    {
        //        var selectedManager = $('input[name="parentManagerSelect"]:checked').val();
        $('input[name="parentManager"]').val(realName);
        $('#divCreateDialog').dialog('close');
    }

    function SelectDefaultStore(storeName)
    {
        //        var selectedManager = $('input[name="storesSelect"]:checked').val();
        $('input[name="defaultStore"]').val(storeName);
        $('#divCreateDialog2').dialog('close');
    }

    function TestUserIsExisting(obj)
    {
        var userName = $(obj).val();
        $.ajax({
            type: 'POST',
            url: '<%=ctx%>/user/checkUserExisting.action?userName=' + userName,
            success: function(data) {
                if(data.result == "true")
                {
                    $('#userNotExisting').hide()
                    $('#userIsExisting').show();
                }
                else
                {
                    $('#userIsExisting').hide();
                    $('#userNotExisting').show();
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
</script>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>