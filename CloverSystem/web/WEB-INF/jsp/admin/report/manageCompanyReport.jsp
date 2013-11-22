<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 08/11/2013
  Time: 11:33:22 AM
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
	<div class="block T_mainHead" style="margin-top: 10px;"><s:property value="company.simpleName"/> <span class="T_mainHead2"> 销售日报告 数据管理</span> <input id="hde_reportDate" name="hde_reportDate" type="hidden" value="${currentDate}"/></div>
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
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">门店</div>
        <div class="block">
            <s:hidden name="eqInt_companyId" id="eqInt_companyId" value='%{company.companyId}'></s:hidden>
            <s:select id="eq_storeId" name="eq_storeId" list="companyStores" listKey="storeId" listValue="storeName" headerValue="%{defaultSelectLabel}" headerKey="%{defaultSelectValue}" theme="simple" cssStyle="width:200px;"/>
        </div>
    </div>
	</div>
    <div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">报告发送人</div>
        <div class="block"><s:textfield name="like_reporter" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
	<div class="block" style="width: 303px; ">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">发送日期</div>
        <div class="block">
            <input type="text" id="eq_reportDay" name="eq_reportDay" class="inputBoxB" value="" onclick="showCalendar('eq_reportDay', 'y-mm-dd');"/>
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
           <input name="btnClear" id="btnClear" type="reset" onclick="ShowThisCompanyReportData();"
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
   </div>
   <div class="block T_subHead"><div style="float: left"></div> <div id="updatesloading" class="" style="float: left; margin-left: 10px;"><img src="<%=ctx%>/content/images/ajax-loader.gif" /></div></div>
  </div>
</div>

<!-- Results Table -->
<div class="block GW1-15">
    <div class="block GW1-15">
      <table style="background-color: rgb(FFF,FFF,FFF);" id="TableHeader" border="0">
        <tr style="width: 948px;">
            <th class="textB2 LINE" style="width: 760px; padding-left: 0px; text-align: left;">日报数&nbsp;<span class="text"><span id="search-result-count"></span></span></th>
            <th class="textB2 LINE" style="width: 20px; padding-left: 0px; text-align: left;"></th>
            <th class="textB2 LINE" style="width: 158px; padding-left: 0px; text-align: left;">动作</th>
            <th class="textB2 LINE" style="width: 10px; padding-left: 0px; text-align: left;"></th>
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
          <tr style="width: 948px;" class="{{rowBG}}" id="tablerow-{{item.reportId}}">
            <td width="760px;" class="LINE text" valign="top">
              <span id='itemStoreName-{{item.reportId}}' style="font-weight:bold;">{{item.storeName}}</span>
                <span id='itemReporter-{{item.reportId}}'>{{item.reporter}}</span>
                {{item.reportDay}} {{item.sendTime.hours}}:{{item.sendTime.minutes}}
                <br>
              <span id='itemReportContent-{{item.reportId}}'>{{item.reportContent}}</span>
            </td>
            <td width="20px;" class="LINE text" style="padding-left: 0px;" valign="top"></td>
            <td width="158px;" class="LINE T_name" style="padding-left: 0px;" valign="top">
                    <a href="<%=ctx%>/report/deleteDailyReport.action?dailyReportId={{item.reportId}}" class="T_name" onclick="return DeleteDailyReport(this, 'tablerow-{{item.reportId}}');">delete</a>
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
            ServerURL: '<%=ctx%>/jsonData/getDailyReportJsonData.action',
            Pagesize: 15,
            Norecs: 15,
            CurrentPage: 0,
            Paginate: true,
            ValidRowsFieldName: 'SiteSection',
            ValidRows: ['RR'],
            EmptyDataLabel: '#latest-update-nodata',
            Progress: 'updatesloading',
            SortField:'sendTime',
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

    function ShowThisCompanyReportData() {
        if (window.JsonDataTable) {
            var currentCompanyId = <s:property value="company.companyId"/>;
            //alert(currentCompanyId);
            var options = "eqInt_companyId=" + currentCompanyId;
            // Start from page number 1
            //$.extend(window.JsonDataTable.opts, options);
            window.JsonDataTable.RefreshOptionsData(options);
        }
    }

    function DeleteDailyReport(obj, tablerowId) {
          var answer = confirm('此操作不可逆，你确定删除吗? ');
          if (answer == true) {
              $.ajax({
                  type: 'POST',
                  url: obj.href,
                  success: function(data) {
                      if(data.result == "true")
                      {
                         $('#' + tablerowId).remove();
                         ShowTip("删除成功!");
                      }
                      else
                      {
                         ShowAlert('删除失败!');
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