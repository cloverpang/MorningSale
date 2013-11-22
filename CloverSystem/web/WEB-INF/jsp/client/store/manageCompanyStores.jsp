<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 23/10/2013
  Time: 2:52:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JavaEE Simple Sale Analysis System Admin Module</title>
</head>
<body>
<%@include file="../../header.jsp"%>
<div id="main" class="block" style="margin: 0px; padding: 0px;">
<!--body header-->
<div class="block GW1-15">
<div class="R GW1-15">
<div class="block GW1-6">
	<div class="block T_mainHead" style="margin-top: 10px;"><s:property value="company.simpleName"/> <span class="T_mainHead2"> 门店管理</span> </div>
</div>
<div class="block GW7-11" style="width: 343px;">
	<div class="block general">
       <input type="button" name="submit" value="+ 添 加 门 店" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick="window.location.href='<%=ctx%>/store/addStore.action'"  />
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
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">门店名称</div>
        <div class="block"><s:textfield name="like_storeName" cssClass="inputBoxB" size="30"/></div>
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
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;"></div>
        <div class="block"><s:hidden name="eqInt_company.companyId" id="eqInt_company.companyId" value='%{company.companyId}'></s:hidden></div>
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
   <div class="block T_subHead">门店数量&nbsp;<span class="text"><span id="search-result-count"></span></span></div>
   <div class="block T_subHead"><div style="float: left"></div> <div id="updatesloading" class="" style="float: left; margin-left: 10px;"><img src="<%=ctx%>/content/images/ajax-loader.gif" /></div></div>
  </div>
</div>
<!-- Results Table -->
<div class="block GW1-15">
    <div class="block GW1-15">
      <table style="background-color: rgb(FFF,FFF,FFF);" id="TableHeader" border="0">
        <tr style="width: 948px;">
            <th class="textB2 LINE" style="width: 180px; padding-left: 0px; text-align: left;">门店名称</th>
            <th class="textB2 LINE" style="width: 120px; padding-left: 0px; text-align: left;">所属经理</th>
            <th class="textB2 LINE" style="width: 270px; padding-left: 0px; text-align: left;">对比品牌</th>
            <th class="textB2 LINE" style="width: 280px; padding-left: 0px; text-align: left;">门店销售员</th>
            <th class="textB2 LINE" style="width: 38px; padding-left: 0px; text-align: left;">编辑</th>
            <th class="textB2 LINE" style="width: 60px; padding-left: 0px; text-align: left;">删除</th>
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
          <tr style="width: 948px;" class="{{rowBG}}" id="tablerow-{{item.storeId}}">
            <td width="180px;" class="LINE text">
              <span id='itemStoreName-{{item.storeName}}'>{{item.storeName}}</span>
            </td>
            <td width="120px;" class="LINE text" style="padding-left: 0px;">
              <span id='itemStoreManager-{{item.storeId}}'>{{item.storeManager}}</span>
            </td>
            <td width="270px;" class="LINE text" style="padding-left: 0px;">
             <span id='itemCompareStores-{{item.storeId}}'>{{item.compareStores}}</span>
            </td>
            <td width="280px;" class="LINE text" style="padding-left: 0px;">
             <span id='itemStoreSales-{{item.storeId}}'>{{item.storeSales}}</span>
            </td>
            <td width="38px;" class="LINE T_name" style="padding-left: 0px;">
               <%--<a href="/company/editCompany.action?companyId={{item.companyId}}" class="T_name">Update</a>--%>
                <a href="#" class="T_name" onclick="return ShowUpdateDiv('{{item.storeId}}');">Edit</a>
            </td>
            <td width="60px;" class="LINE T_name" style="padding-left: 0px;">
                <a href="<%=ctx%>/store/deleteStore.action?storeId={{item.storeId}}" class="T_name" onclick="return DeleteStore(this, 'tablerow-{{item.storeId}}');">delete</a>
            </td>
          </tr>
        </textarea>
    </div>
</div>

 <div id="divCreateDialog" class="pagePop" style="display: none; padding-left: 20px;padding-right: 20px; padding-top: 0px; padding-bottom: 0px; border-collapse: collapse;">
     <div class="block" id="divOuterContainer" style=" border-top: solid 7px rgb(255,0,0);padding-top: 0px; margin: 0px;">
            <div id="divHeader" class="block T_mainHead Header" style="width: 464px; margin-top: 5px; font-size:12px;">
                更改店铺信息  <input id="hde_storeId" name="hde_storeId" type="hidden"/>
            </div>
            <div id="divInnerContainer" class="block" style="width: 464px; height: auto;">
                <div class="block" style="margin-bottom: 10px;">
                    <div class="R" >
                        <div class="block textB2 generalS2" style="width: 400px;">
                            店铺名称 </div>
                        <div class="block padB2" style="width: 400px;">
                             <input type="text" name="new_storeName"  id="new_storeName" class="inputBoxB" style="width:200px;height:24px;" value="" disabled="false" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            所属 Manager 设定 <span style="font-weight:normal;">(如修改,请填写上级经理的真实姓名)</span></div>
                        <div class="block padB2" style="width: 400px;">
                            <input type="text" name="new_storeManager"  id="new_storeManager" class="inputBoxB" style="width:200px;height:24px;" value="" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            对比店铺集合</div>
                        <div class="block padB2" style="width: 400px;">
                            <input type="text" name="new_compareStores"  id="new_compareStores" class="inputBoxB" onblur="ReplaceDot(this);" style="width:360px;height:24px;" value="" />
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
            ServerURL: '<%=ctx%>/jsonData/getStoreJsonData.action',
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

    function ShowThisCompanyStoreData() {
        if (window.JsonDataTable) {
            var currentCompanyId = <s:property value="company.companyId"/>;
            //alert(currentCompanyId);
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
                    var update = UpdateStore();
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

    function ShowUpdateDiv(storeId){
        $.ajaxSetup({ cache: false });

        $.getJSON('<%=ctx%>/jsonData/getStoreJsonData.action?getSingleData=true&query=eq_storeId=' + storeId, null,
                function(data) {
                    var dataObj = $.parseJSON(data);//转换为json对象
                    //alert(dataObj[0].item[0].companyId);

                    $('#hde_storeId').val(storeId);
                    $('#new_storeName').val(dataObj[0].item[0].storeName);
                    $('#new_storeManager').val(dataObj[0].item[0].storeManager);
                    $('#new_compareStores').val(dataObj[0].item[0].compareStores);
                    OpenDialog();
                });
    }

    function OpenDialog() {

        $('#divCreateDialog').dialog('open');

        return false;
    }

    function UpdateStore(){
        var flag = validateForm();
        if (flag)
        {
            $.post("<%=ctx%>/store/updateStore.action", getParam(), function (data) {
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

    function DeleteStore(obj, tablerowId) {
          var answer = confirm('此操作不可逆，你确定删除吗? ');
          if (answer == true) {
              $.ajax({
                  type: 'POST',
                  url: obj.href,
                  success: function(data) {
                      //alert(data);

                      if(data.result == "true")
                      {
                         $('#' + tablerowId).remove();
                         ShowTip("Deleted Success!");
                      }
                      else
                      {
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

    function validateForm() {
        var flag = true;
        if($('#new_realName').val() == '')
        {
            alert('you should input the real name');
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
            storeId: $("#hde_storeId").val(),
            storeName: $("#new_storeName").val(),
            storeManager: $("#new_storeManager").val(),
            compareStores:$("#new_compareStores").val()
        };
        return para;
    }

    function RefreshUpdatedData(){
        $('#itemStoreName-' + getParam().storeId).html(getParam().storeName);
        $('#itemStoreManager-' + getParam().storeId).html(getParam().storeManager);
        $('#itemCompareStores-' + getParam().storeId).html(getParam().compareStores);
    }
</script>

</div>
<%@include file="../../footer.jsp"%>
</body>
</html>