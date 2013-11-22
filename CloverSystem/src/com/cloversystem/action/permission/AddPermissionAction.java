package com.cloversystem.action.permission;

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
 * Date: 27/09/2013
 * Time: 2:24:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddPermissionAction extends UserBaseAction
{
    private String packageName;
	private String actionName;
    private String permissionName;
    private String roles;
    private String rolesSelect;
    private List<Role> roleslist;

    private PermissionsModel permissionsModel;

    public String execute() throws Exception
    {
        boolean add = adminService.addPermission(getPackageName(),getActionName(),getPermissionName(),getRolesSelect());
        //boolean add = false;

        permissionsModel = adminService.getPermissionsModel();
        roleslist = adminService.getAllRoles();
        setPermissionsModel(permissionsModel);
        setRoleslist(roleslist);

        if(add == true)
        {
          setTip("save success!");
          return SAVESUCCESS;
        }
        else
        {
          setAlert("add permission failure!");
          return FAILURE;
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public PermissionsModel getPermissionsModel() {
        return permissionsModel;
    }

    public void setPermissionsModel(PermissionsModel permissionsModel) {
        this.permissionsModel = permissionsModel;
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
}
