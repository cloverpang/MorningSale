<%@ page import="com.sun.net.httpserver.HttpContext" %>
<%@ page import="java.util.*" %>
<%@ page import="com.cloversystem.model.SubLink" %>
<%@ page import="com.cloversystem.util.StringHelper" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.cloversystem.model.*" %>
<%@ page import="com.cloversystem.domain.*" %>
<%--
  Created by IntelliJ IDEA.
  User: cpang
  Date: 04/10/2013
  Time: 6:41:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<MenuTab> totalMenuTabs =  (List<MenuTab>) request.getSession().getAttribute("menuTabs");
%>
<!-- Tab Menus -->
<div class="R GW1-15" style="border-collapse: collapse; border-bottom: 1px solid #ccc; padding-bottom: 10px; margin-bottom: 10px; margin-top: 10px; z-index: 3;">
            <div class="block">
                <div class="block">
                    <ul id="sddm">
                        <%if(totalMenuTabs != null){%>
                          <%for(MenuTab tab : totalMenuTabs){%>
                            <% if(tab.getTabUrl() != null){%>
                             <li id="<%=tab.getTabName()%>">
                                     <a href="<%=ctx.concat(tab.getTabUrl())%>"><%=tab.getTabTitle()%></a>
                             </li>
                            <%}%>
                          <%}%>
                        <%}%>

                        <script type="text/javascript">
                            $.map($('#sddm li:not(:last)').toArray(), function(n) {
                                $(n).css('border-right', 'none');
                            });
                        </script>

                    </ul>
                    <!--[if lt IE 7]> <div style='clear: both; height: 59px; padding:0 0 0 15px; position: relative;'> <a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/images/upgrade.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." /></a></div> <![endif]-->
                </div>
            </div>
        </div>

<!-- sublink -->
<div class="R GW1-15">
   <div id="menuLinks" class="block" style="margin: 0px; padding: 0px;">
    <div class="block GW1-15">
       <div class="R GW1-15" style="margin-bottom: 10px;">
<%
    String selectedMenu = "";
    List<SubLink> links = new ArrayList<SubLink>();
    //String localPath = request.getRequestURI();
    String namespace = ServletActionContext.getActionMapping().getNamespace();
    String actionName = ServletActionContext.getActionMapping().getName();
    String localPath = namespace + "/" + actionName + ".action";

    if(totalMenuTabs != null)
    {
      for(MenuTab tab : totalMenuTabs)
      {
         List<MenuLink> menuLinks = tab.getTabMenuLinks();
         List<String> tabAllExtendUrls = tab.getTabAllExtendUrls();
         //String tabAllExtendUrlStr = StringHelper.join(",",tabAllExtendUrls);
         String tabAllExtendUrlStr = tab.getTabAllExtendUrlsStr();
          
         if(tabAllExtendUrlStr.contains(localPath))
         {
           selectedMenu = tab.getTabName();
           for(MenuLink menuLink : menuLinks)
           {
              links.add(new SubLink(
                    menuLink.getExtendUrls(),
                    menuLink.getLinkTitle(),
                    ctx.concat(menuLink.getLinkUrl())
               ));
           }
         }
      }
    }
%>
 <%--<%=totalMenuTabs.get(3).getTabAllExtendUrlsStr()%>--%>
    <% if (links.size() > 1){ %>
     <div class="block GW1-15" style="margin-bottom: 20px;">
      <div class="block T_nav" id="PageLinks" style="clear: both;">
        <% for(SubLink link : links){ %>
        <%=link.GetAnchorTag()%>
        &nbsp;&nbsp;
        <%} %>
    </div>
     </div>
    <% } %>
    <script type='text/javascript'>
      currentSelectedMenu = '<%=selectedMenu %>';
      $(document).ready(setSelectedMenu);
    </script>
      </div>
    </div>
   </div>
</div>