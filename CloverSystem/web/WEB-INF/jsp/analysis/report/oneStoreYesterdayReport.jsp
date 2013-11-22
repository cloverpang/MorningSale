<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 30/10/2013
  Time: 12:57:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String ctx = request.getContextPath();
%>
<%--${param.storeId}--%>
<div id="updatesloading-${param.storeId}" class="" style="float: left; margin-left: 10px;"><img src="<%=ctx%>/content/images/ajax-loader.gif" /></div>
<div id="storeId-${param.storeId}" value="data"/>
<script language="javascript" type="text/javascript">
    $(document).ready(function() {
        var storeId = "${param.storeId}";
        //alert("currentId is " + storeId);
        $.ajax({
            url: '<%=ctx%>/jsonData/getStoreJsonData.action?getSingleData=true&query=eq_storeId=' + storeId,
            type: 'GET',
            dataType: 'json',
            error: function () {
                $("#storeId-${param.storeId}").html("Get data error!");
                $("#updatesloading-${param.storeId}").hide();
            },
            success: function(data) {
                var dataObj = $.parseJSON(data);
                $("#storeId-${param.storeId}").html(dataObj[0].item[0].compareStores);
                $("#updatesloading-${param.storeId}").hide();
            }
        });
    });
</script>