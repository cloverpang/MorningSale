<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 24/09/2013
  Time: 7:33:46 AM
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
     <div class="block general T_mainHead"> <span id="currentStoreName"><s:property value="currentStore.storeName"/></span> <span class="T_mainHead2"> &nbsp;&nbsp; 发当天销售<span class="T_mainHead2"> &nbsp;&nbsp;&nbsp;&nbsp;日期: <s:property value="currentDate"/></span> </span></div>
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
     <div id="sentDailyReport" class="blockLS GW1-15" style="padding-top: 8px;display:block;">
         <div class="block GW1-15" style="margin-bottom: 20px;">
                 <div class="R" style="margin-top: 10px">
                     <div class="block textB2" style="width: 900px; margin-bottom: 4px;">
                         <a href="#" onclick="ExchangeStore();">切换店铺</a> 
                         <input id="hde_compareStores" name="hde_compareStores" type="hidden"/>
                         <input id="hde_storeId" name="hde_storeId" type="hidden" value="${storeId}"/>
                         <input id="hde_storeName" name="hde_storeName" type="hidden" value="${currentStore.storeName}"/>
                         <input id="hde_companyId" name="hde_companyId" type="hidden" value="${company.companyId}"/>
                         <input id="hde_productionCategorys" name="hde_productionCategoys" type="hidden" value="${company.productionCategoys}"/>
                         <input id="hde_yesterdayDate" name="hde_yesterdayDate" type="hidden" value="${yesterdayDate}"/>
                         <input id="hde_currentDate" name="hde_currentDate" type="hidden" value="${currentDate}"/>

                         <input id="hde_CompareStoresCount" value="0" type="hidden" />
                         <input id="hde_productionCategorysCount" value="0" type="hidden" />
                     </div>
                 </div>
         </div>
         <div class="block GW1-15" style="margin-bottom: 20px;">
             <div id="compareStoresReportDetail" class="block textB2" style="width: 900px;margin-bottom: 20px;">
                 <table id="detailtable" class="block textB2" style="width: 900px;margin-bottom: 20px;">
                 </table>
             </div>

             <div id="productCategorysReportDetail" class="block textB2" style="width: 900px;margin-bottom: 20px;">
                 <table id="categorytable" class="block textB2" style="width: 900px;margin-bottom: 20px;">
                 </table>
             </div>
         </div>
         <div class="blockLD GW1-15">
             <div class="block GW1-15">
                 <div class="block general">
                     <input id="submitButton" type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                            name="submitButton" value="确定发送" class="submitBoxR textB2" style="margin-left: 0px;display:none;" onclick= "SubmitTodaySaleReport()"/>
                 </div>
             </div>
         </div>
      </div>

    <div id="successTip" class="blockLS GW1-15" style="padding-top: 8px;display:none;">
        <div style="width:948px;" class="block GW1-15">
            <div id="div1" style="float:left;width:300px;;height:300px;">

            </div>
            <div id="div2" style="float:left;width:348px;;height:300px;padding-top:80px;">
               <span id="successMessage" style="font-size:16px;font-weight:bold;text-align:left;color:#333333;">
                   发送成功，谢谢! 请 <a href="<%=ctx%>/user/logout.action">安全退出</a>  系统.
                   <br><br>
                   如果需要重新发送今天的销售数据
                   <br><br>
                   请点击 <a href="<%=ctx%>/report/addDailySaleReport.action">门店销售</a> 重新发送.
                   </span>
            </div>
            <div id="div3" style="float:left;width:300px;;height:300px;">

            </div>
        </div>
    </div>
</div>

 <div id="divCreateDialog" class="pagePop" style="display: none; padding-left: 20px;padding-right: 20px; padding-top: 0px; padding-bottom: 0px; border-collapse: collapse;">
     <div class="block" id="divOuterContainer" style=" border-top: solid 7px rgb(255,0,0);padding-top: 0px; margin: 0px;">
            <div id="divInnerContainer" class="block" style="width: 380px; height: auto;">
                <div class="block">
                    <div class="R" >
                        <div class="block textB2 generalS2" style="width: 380px;">
                            请输入想更换的 商店名称 </div>
                        <div class="block padB2" style="width: 380px;">
                             <input type="text" name="new_storeName"  id="new_storeName" class="inputBoxB" style="width:280px;height:24px;" value="" />
                        </div>
                    </div>
               </div>
    </div>
</div>
</div>

 <script language="javascript" type="text/javascript">
    $(document).ready(function() {
        var storeId = $("#hde_storeId").val();
        if(storeId != "")
        {
          CheckTodayReportHasSubmitAndLoadLatestReport(storeId);
          $('#submitButton').show();
        }
        else
        {
          $("#updatesloading").hide();
        }
        SetupDialog();
    });

    function CheckTodayReportHasSubmitAndLoadLatestReport(storeId)
    {
        $.ajax({
            url: '<%=ctx%>/jsonData/getStoresWithReportJsonData.action?getSingleData=true&query=eq_storeId=' + storeId + ':reportDate=' + $('#hde_currentDate').val(),
            type: 'GET',
            dataType: 'json',
            error: function () {
            },
            success: function(data) {
                var dataObj = $.parseJSON(data);
                if(dataObj[0].item[0].dailyReport.reportContent != "")
                {
                    ShowAlert("该店铺今天的销售日报已发送并保存，是否发送新报告覆盖？如不需要请退出。");
                    LoadLastReport(storeId,$('#hde_currentDate').val());
                }
                else
                {
                    LoadLastReport(storeId,$('#hde_yesterdayDate').val());
                }
            }
        });
    }

    function LoadLastReport(storeId,date)
    {
        $.ajax({
            url: '<%=ctx%>/jsonData/getStoresWithReportJsonData.action?getSingleData=true&query=eq_storeId=' + storeId + ':reportDate=' + date,
            type: 'GET',
            dataType: 'json',
            error: function () {
                alert("get compare stores error!");
                $("#updatesloading").hide();
            },
            success: function(data) {
                var dataObj = $.parseJSON(data);

                $("#currentStoreName").html(dataObj[0].item[0].storeName);

                var productionCategorys = $('#hde_productionCategorys').val();

                var compareStores = dataObj[0].item[0].compareStores;
                if(dataObj[0].item[0].dailyReport != null)
                {
                    var reportKeyValuesArray = dataObj[0].item[0].dailyReport.reportKeyValues.split(",");
                    var reportAppendDetailArray = dataObj[0].item[0].dailyReport.reportAppendDetail.split(";");
                }

                $("#hde_compareStores").val(compareStores);
                if(compareStores != null && compareStores != "")
                {
                    var html = "<tr style='height:20px;'><td colspan='3' style='text-align:left;'>销售对比</td></tr>";
                    var index = 0;
                    var compareStoresArray =  compareStores.split(",");
                    if(dataObj[0].item[0].dailyReport != null && reportKeyValuesArray[0] != "")
                    {
                        for(var i =0;i<compareStoresArray.length;i++)
                        {
                            html += "<tr style='height:32px;'>";
                            html += "<td style='text-align:left;width:100px;'>" + compareStoresArray[i] + "<input type='hidden' id='txtStoreName" + i + "' value='" + compareStoresArray[i] + "' /> &nbsp;&nbsp;&nbsp;</td>";
                            html += "<td style='text-align:left;width:200px;'>销售金额 : <input style='width:80px;height:30px;' type='text' onblur='return IsNumeric(this);' id='txtSaleMoney" + i + "' class='input-normal' value='" + reportKeyValuesArray[i] + "' /> 元 &nbsp;&nbsp;&nbsp;</td>";
                            html += "<td style='text-align:left;width:600px;'>策略明细 : <input style='width:450px;height:30px;' type='text' id='txtAppendDetail" + i + "' class='input-normal' value='" + reportAppendDetailArray[i] + "' /> </td>";
                            html += "</tr>";

                            index++;
                        }
                    }
                    else
                    {
                        for(var i =0;i<compareStoresArray.length;i++)
                        {
                            html += "<tr style='height:32px;'>";
                            html += "<td style='text-align:left;width:100px;'>" + compareStoresArray[i] + "<input type='hidden' id='txtStoreName" + i + "' value='" + compareStoresArray[i] + "' /> &nbsp;&nbsp;&nbsp;</td>";
                            html += "<td style='text-align:left;width:200px;'>销售金额 : <input style='width:80px;height:30px;' type='text' onblur='return IsNumeric(this);' id='txtSaleMoney" + i + "' class='input-normal' value='" + "" + "' /> 元 &nbsp;&nbsp;&nbsp;</td>";
                            html += "<td style='text-align:left;width:600px;'>策略明细 : <input style='width:450px;height:30px;' type='text' id='txtAppendDetail" + i + "' class='input-normal' value='" + "" + "' /> </td>";
                            html += "</tr>";

                            index++;
                        }
                    }

                    $("#hde_CompareStoresCount").val(index);
                    $("#detailtable").html("");
                    $("#detailtable").append(html);
                }

                if(productionCategorys != null && productionCategorys != "")
                {
                    var html2 = "<tr style='height:20px;'><td colspan='3' style='text-align:left;'>销售产品明细</td></tr>";
                    var index2 = 0;
                    var productionCategoysArray =  productionCategorys.split(",");
                    for(var i =0;i<productionCategoysArray.length;i++)
                    {
                        html2 += "<tr style='height:32px;'>";
                        html2 += "<td style='text-align:left;width:100px;'>" + productionCategoysArray[i] + "<input type='hidden' id='txtStoreName" + i + "' value='" + productionCategoysArray[i] + "' /> &nbsp;&nbsp;&nbsp;</td>";
                        html2 += "<td style='text-align:left;width:200px;'>销售数量 : <input style='width:80px;height:30px;' type='text' onblur='return IsNumeric(this);' id='txtCategoryCount" + i + "' class='input-normal' value='" + "" + "' /> &nbsp;&nbsp;&nbsp;</td>";
                        html2 += "<td style='text-align:left;width:600px;'>销售金额 : <input style='width:80px;height:30px;' type='text' onblur='return IsNumeric(this);' id='txtCategorySaleValue" + i + "' class='input-normal' value='" + "" + "' /> 元</td>";
                        html2 += "</tr>";

                        index2++;
                    }
                    $("#hde_productionCategorysCount").val(index2);
                    $("#categorytable").html("");
                    $("#categorytable").append(html2);
                }

                $("#updatesloading").hide();
            }
        });
    }

    function SubmitTodaySaleReport(){
        var answer = confirm('确定提交？ ');
        var flag = false;
        if(answer)
        {
          flag = validateForm();
        }
        if(flag)
        {
            $.post("<%=ctx%>/report/submitTodaySaleReport.action", getParam(), function (data) {
                if (data.result == "true") {
                    $('#sentDailyReport').hide();
                    $('#successTip').show();
                    return true;
                }
                else {
                    ShowAlert("提交 失败! 请尝试再次提交.");
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

    function getParam(){
        var reportContent = "";
        var reportKeyValues = "";
        var reportAppendDetail= "";
        var ourSaleValue = 0;
        var maxSaleValue = 0;
        var productionSaleCount = "";
        var productionSaleValue = "";

        var reportCompareStores = $("#hde_compareStores").val();
        var compareStoresArray = reportCompareStores.split(",");

        var productionCategorys = $("#hde_productionCategorys").val();
        var productionCategorysArray = productionCategorys.split(",");

        if(compareStoresArray.length >= 1)
        {
            for(var i =0;i<compareStoresArray.length;i++)
            {
                if (reportContent && reportContent != "") {
                    reportContent += "<br>";
                }
                if (reportKeyValues && reportKeyValues != "") {
                    reportKeyValues += ",";
                }
                if (reportAppendDetail && reportAppendDetail != "") {
                    reportAppendDetail += ";";
                }
                var rowKeyValue = $("#txtSaleMoney" + i).val();
                var rowAppendDetail = $("#txtAppendDetail" + i).val();
                var rowContent = compareStoresArray[i] + " -- " + "金额:" +  rowKeyValue + ",策略:" + rowAppendDetail;
                reportKeyValues += rowKeyValue;
                reportAppendDetail += rowAppendDetail;
                reportContent += rowContent;

                ourSaleValue = $("#txtSaleMoney0").val();
            }

            var reportKeyValuesArray = reportKeyValues.split(",");
            for (var i = 0; i < reportKeyValuesArray.length; i++)
            {
                if (maxSaleValue <= parseInt(reportKeyValuesArray[i]))
                {
                    maxSaleValue = parseInt(reportKeyValuesArray[i]);
                }
            }
        }

        if(productionCategorys != "" && productionCategorysArray.length >= 1)
        {
            var categorySaleRowContent = "<br>本店销售明细为(";
            for(var i =0;i<productionCategorysArray.length;i++)
            {
                if (productionSaleCount && productionSaleCount != "") {
                    productionSaleCount += ",";
                }
                if (productionSaleValue && productionSaleValue != "") {
                    productionSaleValue += ",";
                }
                var rowCount = "0";
                var rowSaleValue = "0";
                if($("#txtCategoryCount" + i).val() != "")
                {
                   rowCount = $("#txtCategoryCount" + i).val();
                }
                if($("#txtCategorySaleValue" + i).val() != "")
                {
                   rowSaleValue = $("#txtCategorySaleValue" + i).val();
                }

                productionSaleCount += rowCount;
                productionSaleValue += rowSaleValue;

                categorySaleRowContent += productionCategorysArray[i] + "&nbsp;" + rowCount + "件" + "&nbsp;金额:" + rowSaleValue + "元&nbsp;&nbsp;";
            }
            categorySaleRowContent +=")";
            reportContent += categorySaleRowContent;
        }


        var para = {
            storeId: $("#hde_storeId").val(),
            storeName: $("#hde_storeName").val(),
            reportCompareStores:reportCompareStores,
            reportContent:reportContent,
            reportKeyValues:reportKeyValues,
            reportAppendDetail:reportAppendDetail,
            ourSaleValue: ourSaleValue,
            maxSaleValue: maxSaleValue,
            productionCategorys:productionCategorys,
            productionSaleCount:productionSaleCount,
            productionSaleValue:productionSaleValue,
            reportDate:$("#hde_currentDate").val()
        };
        return para;
    }

    function validateForm() {
//        var flag = false;
//        var compareStoresCount = parseInt($('#hde_CompareStoresCount').val());
//        var productionCategorysCount = parseInt($('#hde_productionCategorysCount').val());
//
//        for (var i = 0; i < compareStoresCount; i++)
//        {
//            var isNumber = IsNumber($('#txtSaleMoney' + i));
//            if(!isNumber)
//            {
//                alert("销售金额或者销售数字 包含 非数字字符！");
//            }
//        }
//
//        for (var i = 0; i < productionCategorysCount; i++)
//        {
//            var isNumber = IsNumber($('#txtCategoryCount' + i));
//            var isNumber2 = IsNumber($('#txtCategorySaleValue' + i));
//            if(!isNumber || !isNumber2)
//            {
//                alert("销售金额或者销售数字 包含 非数字字符！");
//            }
//        }
//        flag = true;
//        return flag;
        return true;
    }

    function ExchangeStore()
    {
        OpenDialog();
    }

    function ChangeStore()
    {
        if($("#new_storeName").val() == "")
        {
           alert('请输入店名！');
        }
        else
        {
            var findUrl = "<%=ctx%>/jsonData/getStoreJsonData.action?getSingleData=true&query=eq_storeName=" + encodeURI($("#new_storeName").val()) + ":eqInt_company.companyId=" + $("#hde_companyId").val();
            //alert(findUrl);
            $.post(findUrl, function (data) {
                var dataObj = $.parseJSON(data);
                if(dataObj[0].item[0] != null)
                {
                    $("#updatesloading").show();
                    $("#new_storeName").val("");

                    $("#hde_storeId").val(dataObj[0].item[0].storeId);
                    $("#hde_storeName").val(dataObj[0].item[0].storeName),
                    CheckTodayReportHasSubmitAndLoadLatestReport(dataObj[0].item[0].storeId);
                    $('#submitButton').show();
                    return true;
                }
                else {
                    $("#new_storeName").val("");
                    ShowAlert('错误-找不到这个店铺!');
                    return false;
                }
            }, "json");
            return true;
        }
    }

    function getChangeStoreParam()
    {
        var para = {
            storeName: $("#new_storeName").val()
        };
        return para;
    }

    function SetupDialog() {
        $("#divCreateDialog").dialog({
            autoOpen: false,
            position: 'center',
            width: 420,
            modal: true,
            resizable: false,
            overlay: {
                backgroundColor: '#000',
                opacity: 0.5
            },
            buttons: {
                '确定': function() {
                    var exchange = ChangeStore();
                    if(exchange)
                    {
                       $(this).dialog('close');
                    }
                },
                '取消': function() {
                    $(this).dialog('close');
                }
            }

        });
    }

    function OpenDialog() {

        $('#divCreateDialog').dialog('open');

        return false;
    }
 </script>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>