<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 06/11/2013
  Time: 7:42:21 AM
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
     <div class="block general T_mainHead"> <s:property value="company.simpleName"/>  <span class="T_mainHead2">更改公司信息 </span><input type="hidden" id="hde_companyId" value="${company.companyId}"/> </div>
     <div id="updatesloading" class="" style="float: left; margin-left: 10px;">
         <img src="<%=ctx%>/content/images/ajax-loader.gif" />
     </div>
     <div class="blockR"></div>
 </div>
</div>

<!-- Results -->
<div class="block GW1-15">

</div>
<!-- Results Table -->
<div class="block GW1-15">
     <div class="blockLS GW1-15" style="padding-top: 8px;">
            <div class="block GL1-4 GW5-15" style="margin-bottom: 20px;">
                <div class="block">
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司名称</div>
                        <div class="R">
                         <input type="text" name="new_companyName"  id="new_companyName" class="inputBoxB" style="width:200px;height:24px;" value="" />
                        </div>
                    </div>
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司简称</div>
                        <div class="R">
                             <input type="text" name="new_simpleName"  id="new_simpleName" class="inputBoxB" style="width:200px;height:24px;" value="" />
                        </div>
                        <div class="R T_label" style="margin-top:5px">(字母和数字)</div>
                    </div>
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司 Code</div>
                        <div class="R">
                             <input type="text" name="new_companyCode"  id="new_companyCode" class="inputBoxB" style="width:200px;height:24px;" value="" disabled="false" />
                        </div>
                    </div>
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司产品分类</div>
                        <div class="R">
                             <input type="text" name="new_productionCategoys"  id="new_productionCategoys" class="inputBoxB" style="width:360px;height:24px;" onblur="ReplaceDot(this);" value="" />
                        </div>
                        <div class="R T_label" style="margin-top:5px">(此项可为空，为空则表示 每天日报 中不包含销售分类明细信息,用 英文逗号 ',' 隔开)</div>
                    </div>
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司 Logo Url</div>
                        <div class="R">
                           <input type="text" name="new_logoPath"  id="new_logoPath" class="inputBoxB" style="width:360px;height:24px;" value="" />
                        </div>
                    </div>
                  <div class="R" style="margin-top: 10px">
                        <div class="R">
                        </div>
                    </div>

                </div>
                </div>
            <div class="blockLD GW1-15">
                    <div class="block GL1-4 GW5-15">
                        <div class="block general">
                             <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                name="submitButton" value=" 保 存 " class="submitBoxR textB2" style="margin-left: 0px;" onclick= "UpdateCompany();" />
                        </div>
                    </div>
                </div>
      </div>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function() {
        var companyId = $('#hde_companyId').val();
        LoadCompanyInfo(companyId);
    });

    function LoadCompanyInfo(id){
        $.ajaxSetup({ cache: false });

        $.getJSON('<%=ctx%>/jsonData/getCompanyJsonData.action?getSingleData=true&query=eqInt_companyId=' + id, null,

                function(data) {
                    var dataObj = $.parseJSON(data);//转换为json对象
                    //alert(dataObj[0].item[0].companyId);

                    $('#hde_companyId').val(dataObj[0].item[0].companyId);
                    $('#new_companyName').val(dataObj[0].item[0].companyName);
                    $('#new_simpleName').val(dataObj[0].item[0].simpleName);
                    $('#new_productionCategoys').val(dataObj[0].item[0].productionCategoys);
                    $('#new_companyCode').val(dataObj[0].item[0].companyCode);
                    $('#new_logoPath').val(dataObj[0].item[0].logoPath);

                    $("#updatesloading").hide();
                });
    }

    function UpdateCompany(){
        var flag = validateForm();
        if (flag)
        {
            $.post('<%=ctx%>/company/updateCompany.action', getParam(), function (data) {
                if (data.result == "true") {
                    ShowTip("更新成功!");
                    return true;
                }
                else {
                    ShowAlert("更新失败!");
                    return false;
                }
            }, "json");
            return true;
        }
        else
        {
            return false;
        }
    }

    function validateForm() {
        var flag = true;
        if($('#new_companyName').val() == '')
        {
            //alert('you should input the company name');
            $('#new_companyName').addClass('inputRequired');
            flag = false;
        }
        else
        {
           $('#new_companyName').removeClass('inputRequired');
        }
        return flag;
    }

    function getParam(){
        var para = {
            companyId: $("#hde_companyId").val(),
            companyName: $("#new_companyName").val(),
            simpleName: $("#new_simpleName").val(),
            productionCategoys:  $("#new_productionCategoys").val(),
            companyCode: $("#new_companyCode").val(),
            logoPath: $("#new_logoPath").val()
        };
        return para;
    }
</script>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>