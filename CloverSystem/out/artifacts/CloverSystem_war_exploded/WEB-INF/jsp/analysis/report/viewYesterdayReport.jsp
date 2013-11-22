<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 25/10/2013
  Time: 2:13:20 PM
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
<div class="block GW1-15" style="margin: 0px; padding-bottom: 10px;">
<div class="R GW1-15">
<div class="block T_mainHead" style="margin-top: 10px;"><s:property value="company.simpleName"/> <span class="T_mainHead2"> 当日销售简报</span> &nbsp;&nbsp;&nbsp;&nbsp;<span class="T_mainHead2"> 日期: <s:property value="currentDate"/></span></div>
</div>
</div>

<!--the sale manager div-->
<s:if test="myManagers.size() > 0">
<div class="blockLS GW1-15" id="salesManagerList" style="padding-top: 0px; ">

<div class="block GW1-15" style="background-color: rgb(239,239,239); margin-bottom: 0px; padding: 10px 0px 10px 0px;">
  <s:iterator value="myManagers" status="index">
    <div class="block" style="width: 55px; margin-right:10px;">
    <div class="R">
        <div class="block textB2" style="padding-left:10px; width: 70px;"><a href="#" class="textB2" style="font-size:12px;color:#336699;" onclick="RefreshData('<s:property value="realName"/>')"><s:property value="realName"/></a></div>
    </div>
	</div>
  </s:iterator>
</div>

</div>
</s:if>

<!-- Results -->
<div class="block GW1-15">
  <div class="blockLS GW1-15" style="padding: 7px 0 7px 0;">
   <div class="block T_subHead">
       &nbsp;<span class="text"><span id="search-result-count"></span></span>
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
            ServerURL: '<%=ctx%>/jsonData/getSalesReportJsonData.action',
            Pagesize: 2,
            Norecs: 2,
            CurrentPage: 0,
            Paginate: false,
            ValidRowsFieldName: 'SiteSection',
            ValidRows: ['RR'],
            EmptyDataLabel: '#latest-update-nodata',
            Progress: 'updatesloading',
            SortField:'createTime',
            SortOrder:'desc'
        };


        var obj = new CloverJsonDataTemplateItem(opt);
        //obj.GetData();
        window.JsonDataTable = obj;
        window.JsonDataTable.FilterData();
    });

    function RefreshData(sale) {
        if (window.JsonDataTable) {
            var selectManager = encodeURI(sale);
            var options = "selectManager=" + selectManager;
            window.JsonDataTable.RefreshOptionsData(options);
        }
    }
</script>

</div>
<%@include file="../../footer.jsp"%>
</body>
</html>