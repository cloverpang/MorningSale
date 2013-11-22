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
import static com.cloversystem.service.CommonUserService.*;

import java.util.*;
import com.cloversystem.domain.*;
import com.cloversystem.model.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 10/10/2013
 * Time: 11:02:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class ManagementAction extends UserBaseAction{
    private List<MenuTab> menuTabs;
    private List<Role> roleslist;

    public String execute() throws Exception
    {
        menuTabs = adminService.getAllMenu();
        setMenuTabs(menuTabs);

        roleslist = adminService.getAllRoles();
        setRoleslist(roleslist);
        
        return SUCCESS;
    }

    public List<MenuTab> getMenuTabs() {
        return menuTabs;
    }

    public void setMenuTabs(List<MenuTab> menuTabs) {
        this.menuTabs = menuTabs;
    }

    public List<Role> getRoleslist() {
        return roleslist;
    }

    public void setRoleslist(List<Role> roleslist) {
        this.roleslist = roleslist;
    }
}
