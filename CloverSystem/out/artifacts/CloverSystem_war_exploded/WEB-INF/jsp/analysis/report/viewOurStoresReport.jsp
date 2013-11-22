<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 31/10/2013
  Time: 6:32:15 AM
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
<div class="R GW1-15" style="margin-bottom: 10px;">
<div class="block GW1-6">
	<div class="block T_mainHead" style="margin-top: 10px;"><s:property value="company.simpleName"/> <span class="T_mainHead2"> 销售日报告 检索查看</span> <input id="hde_reportDate" name="hde_reportDate" type="hidden" value="${currentDate}"/></div>
</div>
<div class="block GW7-11" style="width: 343px;">
	<div class="block general">

    </div>
</div>
<div class="block GW12-15">
	<div class="blockR" style=" margin-top: 20px;">
		<div class="blockR" style="padding-left: 5px;">
			<div class="img_search"></div></div>
		<div class="blockR T_Filter"><a id="displayText" onclick='toggleFilter(); return false;' href="#">Hide Filter</a></div>
	</div>
</div>
</div>
</div>

<!--hidden area for search-->
<div class="blockLS GW1-15" id="toggleText" style="padding-top: 0px;">
<form name="form" id="form">
<div class="block GW1-15" style="background-color: rgb(239,239,239); margin-bottom: 0px; padding: 0px 0px 10px 0px;">
	<div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">门店名称</div>
        <div class="block">
            <s:hidden name="eqInt_company.companyId" id="eqInt_company.companyId" value='%{company.companyId}'></s:hidden>
            <s:textfield name="like_storeName" cssClass="inputBoxB" size="30"/>
        </div>
    </div>
	</div>
    <div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">门店所属经理</div>
        <div class="block"><s:textfield name="like_storeManager" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
	<div class="block" style="width: 303px; ">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">日期</div>
        <div class="block">
            <input type="text" id="reportDate" name="reportDate" class="inputBoxB" value="${currentDate}" onclick="showCalendar('reportDate', 'y-mm-dd');"/>
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
           <input name="btnClear" id="btnClear" type="reset" onclick="ShowThisCompanyStoreData();"
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
   <div class="block T_subHead">
       Store Count&nbsp;<span class="text"><span id="search-result-count"></span></span>
   </div>
   <div class="block T_subHead"><div style="float: left"></div> <div id="updatesloading" class="" style="float: left; margin-left: 10px;"><img src="<%=ctx%>/content/images/ajax-loader.gif" /></div></div>
  </div>
</div>

<!-- Results Table -->
<div class="block GW1-15">
    <div class="block GW1-15">
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
          <tr style="width: 948px;" class="{{rowBG}}" id="tablerow-{{item.storeId}}">
            <td width="560px;" class="LINE text" valign="top">
              <span id='itemStoreName-{{item.storeName}}' style="font-weight:bold;">{{item.storeName}}</span>
                <span id='itemReporter-{{item.storeId}}'>{{item.dailyReport.reporter}}</span>
                {{item.dailyReport.reportDay}}
                <br>
              <span id='itemReportContent-{{item.storeId}}'>{{item.dailyReport.reportContent}}</span>
            </td>
            <td width="20px;" class="LINE text" style="padding-left: 0px;" valign="top"></td>
            <td width="358px;" class="LINE T_name" style="padding-left: 0px;" valign="top">
                <script type="text/javascript">
                    $(function() {
                        var compareStores = "{{item.dailyReport.reportCompareStores}}";
                        var reportKeyValues = "{{item.dailyReport.reportKeyValues}}";
                        var maxValue = "{{item.maxSaleValue}}";
                        var compareStoresArray = compareStores.split(",");
                        var reportKeyValuesArray = reportKeyValues.split(",");
                        if(reportKeyValues != null && reportKeyValues!= "")
                        {
                            var html = "<tr style='height:12px;'>&nbsp;</tr>";
                            for (var i = 0; i < compareStoresArray.length;i++)
                            {
                                var barColor = "#cccccc";
                                if (i != 0) {
                                    barColor = "#336699";
                                }
                                else {
                                    barColor = "#336699";
                                }
                                var barWidth = parseInt(reportKeyValuesArray[i]) / parseInt(maxValue) * 200;
                                html += "<tr class='{{rowBG}}'>";
                                html += "<td style='text-align:left;width:60px;height:10px;'>" + compareStoresArray[i] + "</td>";
                                html += "<td style='text-align:left;width:230px;height:10px;'>" + "<div style='width:" + barWidth + "px;height:10px; background-color:" + barColor + "' />" + "</td>";
                                html += "<td style='text-align:right;width:68px;height:10px;'>" + reportKeyValuesArray[i] + "元</td>";
                                html += "</tr>";
                            }
                            $('#chartTable-{{item.storeId}}').html("");
                            $('#chartTable-{{item.storeId}}').append(html);
                        }
                    });
                </script>
                <div id="chart-{{item.storeId}}" style="width:358px;">
                    <table id="chartTable-{{item.storeId}}" class="text">
                    </table>
                </div>
            </td>
            <td width="10px;" class="LINE text" style="padding-left: 0px;" valign="top"></td>
          </tr>
        </textarea>
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
            ServerURL: '<%=ctx%>/jsonData/getStoresWithReportJsonData.action',
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
    });

    function RefreshData() {
        if (window.JsonDataTable) {

            //toggle();
            var options = { CurrentPage: 0};
            // Start from page number 1
            $.extend(window.JsonDataTable.opts, options);
            window.JsonDataTable.FilterData();
        }
    }

    function ShowThisCompanyStoreData() {
        if (window.JsonDataTable) {
            var currentCompanyId = <s:property value="company.companyId"/>;
            var currentDate = $('#hde_reportDate').val();
            //alert(currentCompanyId);
            var options = "eqInt_company.companyId=" + currentCompanyId + ":reportDate=" + currentDate;
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
</script>

</div>
<%@include file="../../footer.jsp"%>
</body>
</html>