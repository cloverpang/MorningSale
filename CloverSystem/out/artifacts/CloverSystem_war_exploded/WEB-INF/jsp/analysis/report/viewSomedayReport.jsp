<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 31/10/2013
  Time: 6:32:03 AM
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
<%@include file="../../newHeader.jsp"%>
<div id="main" class="block" style="margin: 0px; padding: 0px;">
<!--body header-->
<div class="block GW1-15">
<div class="R GW1-15" style="margin-bottom: 10px;">
<div class="block GW1-6">
	<div class="block T_mainHead" style="margin-top: 10px;"><s:property value="company.simpleName"/> <span class="T_mainHead2"> 阶段销售分析</span> </div>
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
            <s:hidden name="companyId" id="companyId" value='%{company.companyId}'></s:hidden>
            <s:select id="storeId" name="storeId" list="companyStores" listKey="storeId" listValue="storeName" headerValue="%{defaultSelectLabel}" headerKey="%{defaultSelectValue}" theme="simple" cssStyle="width:200px;"/>
        </div>
    </div>
	</div>
    <div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">开始日期</div>
        <div class="block">
             <input type="text" id="startReportDate" name="startReportDate" class="inputBoxB" value="${startDate}" onclick="showCalendar('startReportDate', 'y-mm-dd');"/>
        </div>
    </div>
	</div>
	<div class="block" style="width: 303px; ">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">结束日期</div>
        <div class="block">
            <input type="text" id="endReportDate" name="endReportDate" class="inputBoxB" value="${endDate}" onclick="showCalendar('endReportDate', 'y-mm-dd');"/>
        </div>
    </div>
	</div>
</div>
<div class="blockLD GW1-15" style="padding-top: 5px; padding-bottom: 20px;">
	<div class="block" style="width: 303px; margin-right: 20px;">&nbsp;</div>
	<div class="block" style="width: 626px;">
		 <div class="block">
           <input name="btnSearch" id="btnSearch" type="button" value="&nbsp;查看阶段分析&nbsp;" class="submitBoxR"
                                        onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                        style="" onclick="RefreshData()" />
            </div>
         <div class="block">
           <input name="btnClear" id="btnClear" type="reset" onclick="ShowThisCompanyStoreData();"
                                        value="&nbsp;清空&nbsp;" class="submitBoxR" onmouseover="this.style.backgroundColor='#E1EDF6'"
                                        onmouseout="this.style.backgroundColor='#FFF'" style="" />
         </div>
        </div>
	</div>
</form>
</div>

<!-- Results -->
<div class="block GW1-15">
  <div class="blockLS GW1-15" style="padding: 7px 0 7px 0;">
   <div class="block T_subHead" style="padding-left:300px;">
       <div id="updatesloading" class="" style="float: left;display:none;">报告生成中 ... 请稍等 <img src="<%=ctx%>/content/images/save-loading.gif" /></div>
   </div>
  </div>
</div>

<!-- Results Table -->
<div class="block GW1-15">
    <div id="ChartsDiv" class="block GW1-15" style="display:none;">
        <div class="block GW1-15">
            <div class="block T_subHead" >
                <span id="chartsTitle"></span>
            </div>
        </div>
        <div style="width:948px;" class="block GW1-15">
            <div id="PieChartContainer" style="float:left;width:448px;;height:200px;">
                pie chart
            </div>
            <div id="BarChartContainer" style="float:left;width:500px;;height:200px;">
                bar chart
            </div>
        </div>
        <div id="LineChartContainer" class="block GW1-15" style="width:948px;;height:300px;">
            line chart
        </div>
    </div>
</div>

<script language="javascript" type="text/javascript">
    $(document).ready(function() {
    });

    function CheckInput()
    {
        var flag = true;
        if($('#storeId').val() == "")
        {
           flag = false;
           ShowAlert("请选择某个店铺！");
           return flag;
        }
        var startDate = $('#startReportDate').val();
        var endDate = $('#endReportDate').val();
        if(!compareDate(startDate,endDate))
        {
           flag = false;
           ShowAlert("开始日期大于结束日期！");
           return flag;
        }
        return flag;
    }

    function RefreshData() {
        $('#ChartsDiv').hide();
        if(CheckInput())
        {
            $('#updatesloading').show();
            var storeId = $('#storeId').val();
            var startDate = $('#startReportDate').val();
            var endDate = $('#endReportDate').val();
            $.ajax({
                type: 'POST',
                url: '<%=ctx%>/jsonData/getPeriodReportJsonData.action?storeId=' + storeId + '&startDate=' + startDate + '&endDate=' + endDate,
                success: function(data) {
                    var dataObj = $.parseJSON(data);
                    var stores =  dataObj.compareStores;
                    var storeValues = dataObj.compareStoresSaleValueArrayStr;
                    var categorys = dataObj.productionCategory;
                    var categoryValues = dataObj.productionCategorySaleValueArrayStr;
                    var dates = dataObj.datesArrayStr;
                    var dateValues = dataObj.datesSaleValueArrayStr;

                    var chartsTitle = $('#storeId option:selected').text() + " : " + startDate + " to " + endDate + " 销售数据分析图表";
                    $('#chartsTitle').html(chartsTitle);
                    LoadReportData(stores,storeValues,categorys,categoryValues,dates,dateValues);
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    var resp = $(xhr.responseText);
                    if (resp != "") {
                        var al = $(resp).filter("title").html();
                        if (al != "") {
                            ShowAlert('Get Data Error!');
                        }
                    }
                }
            });
        }
    }

    function LoadReportData(stores,storeValues,categorys,categoryValues,dates,dateValues)
    {
        var datesArray = dates.split(',');
        var categorysArray = categorys.split(',');
        var storesArray = stores.split(',');

        var dateValuesArray = dateValues.split(',');
        var categoryValuesArray = categoryValues.split(',');
        var storeValuesArray = storeValues.split(',');

        for(var i=0;i<storeValuesArray.length;i++)
        {
          storeValuesArray[i] = parseInt(storeValuesArray[i]);  
        }

        for(var i=0;i<dateValuesArray.length;i++)
        {
            if(dateValuesArray[i] == "")
            {
               dateValuesArray[i] = null;
            }
            else
            {
               dateValuesArray[i] = parseInt(dateValuesArray[i]);
            }
        }

        var productionSaleCategoryArray = new Array();

        for(var i=0;i<categorysArray.length;i++)
        {
            if(categoryValues == "")
            {
                productionSaleCategoryArray[i] = [categorysArray[i],0];
            }
            else
            {
                productionSaleCategoryArray[i] = [categorysArray[i],parseInt(categoryValuesArray[i])];
            }
        }

        var pieChart;
        pieChart = new Highcharts.Chart({
            chart: {
                renderTo: 'PieChartContainer',
                defaultSeriesType: 'pie',
                marginRight: 30,
                marginBottom: 25,
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '销售构成图'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                    },
                    enableMouseTracking: false
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                type: 'pie',
                name: '份额',
                data: productionSaleCategoryArray
            }]
        });

        var barChart;
        barChart = new Highcharts.Chart({
            chart: {
                renderTo: 'BarChartContainer',
                defaultSeriesType: 'bar', 
                marginRight: 150,
                marginBottom: 25
            },
            title: {
                text: '销售对比图'
            },
            xAxis: {
                categories: storesArray,
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Population (millions)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' millions'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -20,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '销售额',
                data: storeValuesArray
            }]
        });

        var lineChart;
        lineChart = new Highcharts.Chart({
            chart: {
                renderTo: 'LineChartContainer',
                defaultSeriesType: 'line', 
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: '销售额走势图',
                x: -20 //center
            },
            xAxis: {
                categories: datesArray
            },
            yAxis: {
                title: {
                    text: 'Sale Value'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                            this.x + ': ' + this.y + '元';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: 0,
                y: 40,
                borderWidth: 0
            },
            exporting: {
                enabled: true
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '本店', //每条线的名称
                data: dateValuesArray//每条线的数据
            }]
        });

        $('#ChartsDiv').show();
        $('#updatesloading').hide();
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