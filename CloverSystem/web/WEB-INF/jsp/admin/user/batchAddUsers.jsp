<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 18/10/2013
  Time: 1:32:33 PM
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
            <div class="block general T_mainHead"><s:property value="company.simpleName"/>  <span class="T_mainHead2"> 批量添加用户</span></div>
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
      <s:form action="batchAddCompanyUsers!add" namespace="/user" method="post" theme="simple">
     <div class="blockLS GW1-15" style="padding-top: 8px;">
            <div class="block GL1-4 GW5-15" style="margin-bottom: 20px;">
                <div class="block">
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司已有用户数</div>
                        <div class="R">
                          <s:textfield id="maxUserCount" name="maxUserCount" cssClass="inputBoxE" size="20" value="%{maxUserCount}" cssStyle="width:50px;height:24px;"/>
                            <input id="hde_companyCode" name="hde_companyCode" type="hidden" value='<s:property value="company.companyCode"/>'/>
                            <s:textfield name="currentCompanyId" cssClass="inputBoxB" size="50" value="%{companyId}" cssStyle="width:10px;height:24px;"/>
                        </div>
                        <div class="R T_label" style="margin-top:5px">(用户名为 公司code + 数字编号，如 mstar1，系统为您自动提示生成)</div>
                        <div class="R T_label" style="margin-top:5px">(请按照格式：用户名,姓名,上级manager:姓名,上级manager:... 格式输入)</div>
                        <div class="R T_label" style="margin-top:5px">(批量新增加的用户没有设置角色,请返回人员管理列表为其设置角色)</div>
                    </div>

                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 400px; margin-bottom: 4px;">新用户数量：<input type="text" id="newUserCount" name="newUserCount" class="inputBoxE" size="10" value="10" style="width:50px;height:24px;"/> &nbsp;<a href="#" onclick="addTemplate1();"> 添加模板</a>&nbsp;&nbsp;<a href="#" onclick="addTemplate2();"> 添加模板(no parentManager)</a>&nbsp;&nbsp;</div>
                        <div class="block textB2" style="width: 400px; margin-bottom: 4px;">
                            新用户列表</div>
                        <div class="R">
                            <s:textarea name="inputUsers" id="inputUsers" rows="8" wrap="true"  cssClass="inputBox2B" cssStyle="width:500px;height:250px;"/>
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
                                onclick="window.location.href='/company/manageCompanyUsers.action?companyId=<s:property value="companyId"/>'" />
                        </div>
                    </div>
                </div>
      </div>
    </s:form>
    </s:if>
    <s:else>
       <s:form action="batchAddOurCompanyUsers!add" namespace="/user" method="post" theme="simple">
     <div class="blockLS GW1-15" style="padding-top: 8px;">
            <div class="block GL1-4 GW5-15" style="margin-bottom: 20px;">
                <div class="block">
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司已有用户数</div>
                        <div class="R">
                          <s:textfield id="maxUserCount" name="maxUserCount" cssClass="inputBoxE" size="20" value="%{maxUserCount}" cssStyle="width:50px;height:24px;"/>
                            <input id="hde_companyCode" name="hde_companyCode" type="hidden" value='<s:property value="company.companyCode"/>'/>
                        </div>
                        <div class="R T_label" style="margin-top:5px">(用户名为 公司code + 数字编号，如 mstar1，系统为您自动提示生成)</div>
                        <div class="R T_label" style="margin-top:5px">(请按照格式：用户名,姓名,上级manager:姓名,上级manager:... 格式输入)</div>
                        <div class="R T_label" style="margin-top:5px">(批量新增加的用户没有设置角色,请返回人员管理列表为其设置角色)</div>
                    </div>

                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 400px; margin-bottom: 4px;">新用户数量：<input type="text" id="newUserCount" name="newUserCount" class="inputBoxE" size="10" value="10" style="width:50px;height:24px;"/> &nbsp;<a href="#" onclick="addTemplate1();"> 添加模板</a>&nbsp;&nbsp;<a href="#" onclick="addTemplate2();"> 添加模板(no parentManager)</a>&nbsp;&nbsp;</div>
                        <div class="block textB2" style="width: 400px; margin-bottom: 4px;">
                            新用户列表</div>
                        <div class="R">
                            <s:textarea name="inputUsers" id="inputUsers" rows="8" wrap="true"  cssClass="inputBox2B" cssStyle="width:500px;height:250px;"/>
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

<script language="javascript" type="text/javascript">
    function addTemplate1()
    {
        var newUserCount = $('#newUserCount').val();
        var existingUserCount = $('#maxUserCount').val();
        var companyCode = $('#hde_companyCode').val();
        var templateStr = "";
        for(var i=0;i<newUserCount;i++)
        {
            var j = i+1;
            var newCount = parseInt(existingUserCount) + j;
            var newUserName = companyCode + newCount;
            var newRealName = "真实姓名" + newCount;
            var newParentManager = "上级Manager" + newCount;
            if(i==0)
            {
                templateStr = templateStr + newUserName + "," + newRealName + "," + newParentManager;
            }
            else
            {
                templateStr = templateStr + ":" + newUserName + "," + newRealName + "," + newParentManager;
            }
        }
        $('#inputUsers').val(templateStr);
    }

    function addTemplate2()
    {
        var newUserCount = $('#newUserCount').val();
        var existingUserCount = $('#maxUserCount').val();
        var companyCode = $('#hde_companyCode').val();
        var templateStr = "";
        for(var i=0;i<newUserCount;i++)
        {
            var j = i+1;
            var newCount = parseInt(existingUserCount) + j;
            var newUserName = companyCode + newCount;
            var newRealName = "真实姓名" + newCount;
            if(i==0)
            {
                templateStr = templateStr + newUserName + "," + newRealName;
            }
            else
            {
                templateStr = templateStr + ":" + newUserName + "," + newRealName;
            }
        }
        $('#inputUsers').val(templateStr);
    }
</script>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>