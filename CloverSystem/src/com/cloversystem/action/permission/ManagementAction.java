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
import static com.cloversystem.service.CommonUserService.*;

import java.util.*;
import com.cloversystem.domain.*;
import com.cloversystem.model.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 27/09/2013
 * Time: 1:52:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagementAction extends UserBaseAction
{
    private List<Permission> permissions;
    private PermissionsModel permissionsModel;
    private List<Role> roleslist;

    public String execute() throws Exception
    {
        permissionsModel = adminService.getPermissionsModel();
        setPermissionsModel(permissionsModel);

        roleslist = adminService.getAllRoles();
        setRoleslist(roleslist);

        return SUCCESS;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public PermissionsModel getPermissionsModel() {
        return permissionsModel;
    }

    public void setPermissionsModel(PermissionsModel permissionsModel) {
        this.permissionsModel = permissionsModel;
    }

    public List<Role> getRoleslist() {
        return roleslist;
    }

    public void setRoleslist(List<Role> roleslist) {
        this.roleslist = roleslist;
    }
}
