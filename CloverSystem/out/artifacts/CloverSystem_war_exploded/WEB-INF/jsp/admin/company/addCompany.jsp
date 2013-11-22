<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 20/09/2013
  Time: 2:33:27 PM
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
            <div class="block general T_mainHead">Add New Company <span class="T_mainHead2"> </span></div>
            <div class="blockR"> <input type="button" name="" value="Back" class="submitBoxR text" style="margin-left: 5px; " onclick="window.location.href='<%=ctx%>/company/management.action'" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'" /></div>
 </div>
</div>

<!-- Results -->
<div class="block GW1-15">

</div>
<!-- Results Table -->
<div class="block GW1-15">
    <s:form action="addCompany!add" namespace="/company" method="post" theme="simple">
     <div class="blockLS GW1-15" style="padding-top: 8px;">
            <div class="block GL1-4 GW5-15" style="margin-bottom: 20px;">


                <div class="block">
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司名称</div>
                        <div class="R">
                          <s:textfield name="companyName" cssClass="inputBoxB" size="50"/>
                        </div>
                    </div>
                    <div class="R" style="margin-top: 10px">

                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司简称</div>
                        <div class="R">
                             <s:textfield name="simpleName" cssClass="inputBoxB" size="50"/>
                        </div>
                        <div class="R T_label" style="margin-top:5px">(字母和数字)</div>
                    </div>

                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司 Code</div>
                        <div class="R">
                              <s:textfield name="companyCode" cssClass="inputBoxB" size="50"/>
                        </div>
                    </div>
                    <div class="R" style="margin-top: 10px">

                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司产品分类</div>
                        <div class="R">
                             <s:textfield name="productionCategoys" cssClass="inputBoxB" size="100" onblur="ReplaceDot(this);" cssStyle="width:360px;"/>
                        </div>
                        <div class="R T_label" style="margin-top:5px">(此项可为空，为空则表示 每天日报 中不包含销售分类明细信息,用 英文逗号 ',' 隔开)</div>
                    </div>
                    <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            公司 Logo Url</div>
                        <div class="R">
                           <s:textfield name="logoPath" cssClass="inputBoxB" size="50" cssStyle="width:360px;"/>
                        </div>
                    </div>

                  <div class="R" style="margin-top: 10px">
                        <div class="R">

                        </div>
                    </div>


                   <div class="R" style="margin-top: 10px">
                        <div class="block textB2" style="width: 250px; margin-bottom: 4px;">
                            上传Logo </div>
                        <div class="R">
                             <input type="file" style="height: 22px;" name="BookIcon" id="BookIcon" size="50" />
                        </div>
                    </div>
                  
                </div>
                </div>
            <div class="blockLD GW1-15">
                    <div class="block GL1-4 GW5-15">
                        <div class="block general">
                             <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                name="submitButton" value="Save Changes" class="submitBoxR textB2" style="margin-left: 0px;" onclick= "this.form.submit()" />
                            <input type="button" onmouseover="this.style.backgroundColor='#E1EDF6'" onmouseout="this.style.backgroundColor='#FFF'"
                                name="cancelButton" value="Cancel" class="submitBoxR text" style="margin-left: 5px;"
                                onclick="window.location.href='/company/management.action'" />
                        </div>
                    </div>
                </div>
      </div>
    </s:form>
</div>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>