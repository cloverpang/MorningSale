package com.cloversystem.action.role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.cloversystem.domain.Role;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;

import java.util.*;
import com.cloversystem.domain.*;
import com.cloversystem.model.*;
import com.cloversystem.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 4:15:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditRoleMenuAction extends UserBaseAction{
    private String roleName;
    private String hiddenRoleName;

    private List<MenuTab> menuTabs;
    private String menuLinkCheckedList;

    public String execute() throws Exception
    {
        menuTabs = adminService.getAllMenu();
        setMenuTabs(menuTabs);

        return SUCCESS;
    }

    public String updateRoleMenu()throws Exception{

        HttpServletRequest  request = ServletActionContext.getRequest();
        Enumeration pNames = request.getParameterNames();
        List<String> checkedMenuLinks = new ArrayList<String>();
        while(pNames.hasMoreElements()){
         String name=(String)pNames.nextElement();
         if(!name.equals("hiddenRoleName"))
         {
           String value=request.getParameter(name);
           checkedMenuLinks.add(value);
         }
        }

       menuLinkCheckedList = StringHelper.join(",",checkedMenuLinks);

       boolean update = adminService.updateRoleMenus(hiddenRoleName,menuLinkCheckedList);
       if(update)
       {
          setTip("Updated Success!");
       }
       else
       {
          setAlert("Updated Failed!");
       }

       roleName = hiddenRoleName;

       menuTabs = adminService.getAllMenu();
       setMenuTabs(menuTabs);

       return SUCCESS; 
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getHiddenRoleName() {
        return hiddenRoleName;
    }

    public void setHiddenRoleName(String hiddenRoleName) {
        this.hiddenRoleName = hiddenRoleName;
    }

    public List<MenuTab> getMenuTabs() {
        return menuTabs;
    }

    public void setMenuTabs(List<MenuTab> menuTabs) {
        this.menuTabs = menuTabs;
    }

    public String getMenuLinkCheckedList() {
        return menuLinkCheckedList;
    }

    public void setMenuLinkCheckedList(String menuLinkCheckedList) {
        this.menuLinkCheckedList = menuLinkCheckedList;
    }
}
