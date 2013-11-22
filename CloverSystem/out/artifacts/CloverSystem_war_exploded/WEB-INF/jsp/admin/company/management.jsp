<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 11/10/2013
  Time: 12:53:57 PM
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
	<div class="block T_mainHead" style="margin-top: 10px;">Company Management</div>
</div>
<div class="block GW7-11" style="width: 343px;">
	<div class="block general"><input type="button" name="submit" value="+Add New Company" class="submitBoxR textB2" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" style="margin: 0px; width: 160px;"  onclick="window.location.href='<%=ctx%>/company/addCompany.action'"  /></div>
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
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Company Name</div>
        <div class="block"><s:textfield name="like_companyName" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
    <div class="block" style="width: 303px; margin-right:20px;">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Company ID</div>
        <div class="block"><s:textfield name="gt_companyId" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
	<div class="block" style="width: 303px; ">
    <div class="R">
        <div class="block textB2" style="margin-bottom: 4px; width: 200px;">Company Code</div>
        <div class="block"><s:textfield name="eq_companyCode" cssClass="inputBoxB" size="30"/></div>
    </div>
	</div>
</div>
<div class="blockLD GW1-15" style="padding-top: 5px; padding-bottom: 20px;">
	<div class="block" style="width: 303px; margin-right: 20px;">&nbsp;</div>
	<div class="block" style="width: 626px;">
		 <div class="block">
           <input name="btnSearch" id="btnSearch" type="button" value="&nbsp;Search&nbsp;" class="submitBoxR"
                                        onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                        style="" onclick="RefreshData()" />
            </div>
         <div class="block">
           <input name="btnClear" id="btnClear" type="reset" onclick="window.JsonDataTable.ShowAllData();"
                                        value="&nbsp;Clear&nbsp;" class="submitBoxR" onmouseover="this.style.backgroundColor='#E1EDF6'"
                                        onmouseout="this.style.backgroundColor='#FFF'" style="" />
         </div>
        </div>
	</div>
</form>
</div>

<!-- Results -->
<div class="block GW1-15">
  <div class="blockLS GW1-15" style="padding: 7px 0 7px 0;">
   <div class="block T_subHead">Company Count&nbsp;<span class="text"><span id="search-result-count"></span></span></div>
   <div class="block T_subHead"><div style="float: left"></div> <div id="updatesloading" class="" style="float: left; margin-left: 10px;"><img src="<%=ctx%>/content/images/ajax-loader.gif" /></div></div>
  </div>
</div>
<!-- Results Table -->
<div class="block GW1-15">
    <div class="block GW1-15">
      <table style="background-color: rgb(FFF,FFF,FFF);" id="TableHeader" border="0">
        <tr style="width: 948px;">
            <th class="textB2 LINE" style="width: 140px; padding-left: 0px; text-align: left;">Company Code</th>
            <th class="textB2 LINE" style="width: 320px; padding-left: 0px; text-align: left;">Company Name</th>
            <th class="textB2 LINE" style="width: 140px; padding-left: 0px; text-align: left;">Simple Name</th>
            <th class="textB2 LINE" style="width: 168px; padding-left: 0px; text-align: left;">Actions</th>
            <th class="textB2 LINE" style="width: 60px; padding-left: 0px; text-align: left;">Edit</th>
            <th class="textB2 LINE" style="width: 60px; padding-left: 0px; text-align: left;">Lock</th>
            <th class="textB2 LINE" style="width: 60px; padding-left: 0px; text-align: left;">Delete</th>
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
          <tr style="width: 948px;" class="{{rowBG}}" id="tablerow-{{item.companyId}}">
            <td width="140px;" class="LINE text">
              {{item.companyCode}}
            </td>
            <td width="320px;" class="LINE text" style="padding-left: 0px;">
              <span id='itemCompanyName-{{item.companyId}}'>{{item.companyName}}</span>
            </td>
            <td width="140px;" class="LINE text" style="padding-left: 0px;">
              <span id='itemSimpleName-{{item.companyId}}'>{{item.simpleName}}</span>
            </td>
            <td width="168px;" class="LINE T_name" style="padding-left: 0px;">
               <a href="<%=ctx%>/company/manageCompanyUsers.action?companyId={{item.companyId}}" class="T_name">Manage Users</a>
            </td>
            <td width="60px;" class="LINE T_name" style="padding-left: 0px;">
                <a href="#" class="T_name" onclick="return ShowUpdateDiv('{{item.companyId}}');">Edit</a>
            </td>
            <td width="60px;" class="LINE T_name" style="padding-left: 0px;">
               <a href="<%=ctx%>/company/lockCompany.action?companyId={{item.companyId}}" class="T_name" onclick="return LockCompany(this, 'lockrow-{{item.companyId}}');"><span id="lockrow-{{item.companyId}}" class="T_lock_{{item.isLock}}">{{item.isLock}}</span></a>
            </td>
            <td width="60px;" class="LINE T_name" style="padding-left: 0px;">
                <a href="<%=ctx%>/company/deleteCompany.action?companyId={{item.companyId}}" class="T_name" onclick="return DeleteCompany(this, 'tablerow-{{item.companyId}}');">delete</a>
            </td>
          </tr>
        </textarea>
    </div>
</div>

 <div id="divCreateDialog" class="pagePop" style="display: none; padding-left: 20px;padding-right: 20px; padding-top: 0px; padding-bottom: 0px; border-collapse: collapse;">
     <div class="block" id="divOuterContainer" style=" border-top: solid 7px rgb(255,0,0);padding-top: 0px; margin: 0px;">
            <div id="divHeader" class="block T_mainHead Header" style="width: 464px; margin-top: 5px; font-size:12px;">
                Update Company  <input id="hde_companyId" name="hde_companyId" type="hidden"/>
            </div>
            <div id="divInnerContainer" class="block" style="width: 464px; height: auto;">
                <div class="block" style="margin-bottom: 10px;">
                    <div class="R" >
                        <div class="block textB2 generalS2" style="width: 400px;">
                            Company Name </div>
                        <div class="block padB2" style="width: 400px;">
                             <input type="text" name="new_companyName"  id="new_companyName" class="inputBoxB" style="width:200px;height:24px;" value="" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            Simple Name </div>
                        <div class="block padB2" style="width: 400px;">
                            <input type="text" name="new_simpleName"  id="new_simpleName" class="inputBoxB" style="width:200px;height:24px;" value="" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            Company Code</div>
                        <div class="block padB2" style="width: 400px;">
                            <input type="text" name="new_companyCode"  id="new_companyCode" class="inputBoxB" style="width:200px;height:24px;" value="" disabled="false" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            Production Categorys </div>
                        <div class="block padB2" style="width: 400px;">
                            <input type="text" name="new_productionCategoys"  id="new_productionCategoys" class="inputBoxB" style="width:360px;height:24px;" onblur="ReplaceDot(this);" value="" />
                        </div>
                    </div>
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 400px;">
                            Company Logo Path</div>
                        <div class="block padB2" style="width: 400px;">
                            <input type="text" name="new_logoPath"  id="new_logoPath" class="inputBoxB" style="width:360px;height:24px;" value="" />
                        </div>
                    </div>
                </div>
                <div class="block GW1-3">
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
            ServerURL: '<%=ctx%>/jsonData/getCompanyJsonData.action',
            Pagesize: 15,
            Norecs: 15,
            CurrentPage: 0,
            Paginate: true,
            ValidRowsFieldName: 'SiteSection',
            ValidRows: ['RR'],
            EmptyDataLabel: '#latest-update-nodata',
            Progress: 'updatesloading',
            SortField:'companyId',
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

            var options = { CurrentPage: 0 };
            // Start from page number 1
            $.extend(window.JsonDataTable.opts, options);
            window.JsonDataTable.FilterData();
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
                    var update = UpdateCompany();
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

    function ShowUpdateDiv(id){
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

                    //Hidden field
                    //$('#AdminId').val(data.AdminId);

                    //$(".ui-dialog-buttonpane button:contains('Save')").button().show();
                    //$(".ui-dialog-buttonpane button:contains('Cancel')").button().show();

                    OpenDialog();
                });
    }

    function OpenDialog() {

        $('#divCreateDialog').dialog('open');

        return false;
    }

    function UpdateCompany(){
        var flag = validateForm();
        if (flag)
        {
            $.post("<%=ctx%>/company/updateCompany.action", getParam(), function (data) {
                if (data.result == "true") {
                    RefreshUpdatedData();
                    ShowTip("save success!");
                    return true;
                }
                else {
                    //alert(getParam().companyCode);
                    ShowAlert("save failed!");
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

    function DeleteCompany(obj, tablerowId) {

          var answer = confirm('Are you sure you want to delete this Company? ');
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

    function LockCompany(obj, lockrowId) {

          var answer = confirm('Are you sure you want to lock/unlock this Company? ');
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
                         ShowTip("Lock/Unlock Success!");
                      }
                      else
                      {
                         ShowAlert('Lock/Unlock Failed!');
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

    function RefreshUpdatedData(){
        $('#itemCompanyName-' + getParam().companyId).html(getParam().companyName);
        $('#itemSimpleName-' + getParam().companyId).html(getParam().simpleName);
    }

</script>

</div>
<%@include file="../../footer.jsp"%>
</body>
</html>