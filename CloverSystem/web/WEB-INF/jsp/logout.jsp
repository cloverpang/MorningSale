<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 24/09/2013
  Time: 10:04:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String ctx = request.getContextPath();
%>
<script type="text/javascript" src="<%=ctx%>/scripts/common.js"></script>
<s:if test="tip!=null">
	<script type="text/javascript">
        $(function() {
            ShowTip('<s:property value="tip"/>');
        });
	</script>
</s:if>
<s:if test="alert!=null">
	<script type="text/javascript">
        $(function() {
            ShowAlert('<s:property value="alert"/>');
        });
	</script>
</s:if>