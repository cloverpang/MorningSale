package com.cloversystem.action.menu;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;

import java.util.*;
import com.cloversystem.domain.*;
import com.cloversystem.model.*;

import static com.cloversystem.service.CommonUserService.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 10/10/2013
 * Time: 11:04:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddMenuAction extends UserBaseAction{
    private String tabName;
	private String tabTitle;
    private String linkTitle;
    private String linkUrl;
    private String extendUrls;

    private String roles;
    private String rolesSelect;
    private List<Role> roleslist;

    private List<MenuTab> menuTabs;

    public String execute() throws Exception
    {
        boolean add = adminService.addMenu(getTabName(),getTabTitle(),getLinkTitle(),getLinkUrl(),getExtendUrls(),getRolesSelect());
        //boolean add = false;

        menuTabs = adminService.getAllMenu();
        setMenuTabs(menuTabs);

        roleslist = adminService.getAllRoles();
        setRoleslist(roleslist);

        if(add == true)
        {
          setTip("save success!");
          return SAVESUCCESS;
        }
        else
        {
          setAlert("add menu failure!");
          return FAILURE;
        }
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getExtendUrls() {
        return extendUrls;
    }

    public void setExtendUrls(String extendUrls) {
        this.extendUrls = extendUrls;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRolesSelect() {
        return rolesSelect;
    }

    public void setRolesSelect(String rolesSelect) {
        this.rolesSelect = rolesSelect;
    }

    public List<Role> getRoleslist() {
        return roleslist;
    }

    public void setRoleslist(List<Role> roleslist) {
        this.roleslist = roleslist;
    }

    public List<MenuTab> getMenuTabs() {
        return menuTabs;
    }

    public void setMenuTabs(List<MenuTab> menuTabs) {
        this.menuTabs = menuTabs;
    }
}
