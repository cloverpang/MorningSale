<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 24/10/2013
  Time: 11:23:46 AM
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
            <div class="block general T_mainHead"> <s:property value="company.simpleName"/> <span class="T_mainHead2">添加新门店</span></div>
            <div class="blockR">
<input type="button" name="" value=" 返 回 " class="submitBoxR text" style="margin-left: 5px; " onclick="window.location.href='<%=ctx%>/store/manageOurCompanyStores.action'" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" />
            </div>
 </div>
</div>

<!-- Results -->
<div class="block GW1-15">

</div>
<!-- Results Table -->
<div class="block GW1-15">
<s:form action="addStore!add" namespace="/store" method="post" theme="simple">
            <div class="blockLS GW1-15" style="padding-top: 8px;">
                <div class="block GL1-4 GW5-15" style="margin-bottom: 20px;">


                    <div class="block">

                        <div class="R" style="margin-top: 10px">

                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                店铺名称</div>
                            <div class="R">
                                <s:textfield name="storeName" cssClass="inputBoxB" size="50"/>
                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                                店铺 Manager</div>
                            <div class="R">
                                <s:textfield name="storeManager" cssClass="inputBoxB" size="50" onclick="ShowSelectDiv();"/>
                            </div>
                        </div>

                        <div class="R" style="margin-top: 10px">
                            <div class="block textB2" style="width: 500px; margin-bottom: 4px;">
                                比较的商铺 名称集合.请用 , 分割 注意是英文输入法下的逗号</div>
                            <div class="R">
                                <s:textfield name="compareStores" cssClass="inputBoxB" size="50" value="本店," onblur="ReplaceDot(this);" cssStyle="width:500px;"/>
                            </div>
                            <div class="R T_label" style="margin-top:5px">(如： 本店,拉比,丑丑,爱儿赫玛)</div>
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
                                   onclick="window.location.href='<%=ctx%>/store/manageOurCompanyStores.action'" />
                        </div>
                    </div>
                </div>
            </div>
        </s:form>
</div>

 <div id="divCreateDialog" class="pagePop" style="display: none; padding-left: 20px;padding-right: 20px; padding-top: 0px; padding-bottom: 0px; border-collapse: collapse;">
     <div class="block" id="divOuterContainer" style=" border-top: solid 7px rgb(255,0,0);padding-top: 0px; margin: 0px;">
            <div id="divInnerContainer" class="block" style="width: 464px; height: auto;">
                <div class="block" style="margin-bottom: 10px;">
                    <div class="R">
                        <div class="block generalS2 textB2" style="width: 220px;">
                             选择一位用户作为该门店所属经理
                        </div>
                        <div class="block padB2" style="width: 420px;">

                             <div class="block clean-gray" style="width: 420px; border: 1px solid #ccc; margin: 0px 0 10px 0; padding: 10px;">
                                 <div class="block">
                                     <div id="radio">
                                        <%--<s:radio id="storeManagerSelect" name="storeManagerSelect" list="companyUsers" listKey="realName" listValue="realName" onclick="SelectParentManager();" theme="simple"/>--%>
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

<script language="javascript" type="text/javascript">
 $(document).ready(function() {
        $("#divCreateDialog").dialog('close');
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
    }

  function ShowSelectDiv(){
     OpenDialog();
  }

   function OpenDialog() {

        $('#divCreateDialog').dialog('open');

        return false;
    }

    function SelectParentManager(realName)
    {
//        var selectedManager = $('input[name="storeManagerSelect"]:checked').val();
        $('input[name="storeManager"]').val(realName);
        $('#divCreateDialog').dialog('close');
    }
</script>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>