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
 * Time: 4:15:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditRolePermissionAction extends UserBaseAction
{
    private String roleName;
    private String hiddenRoleName;
    private PermissionsModel permissionsModel;

    private String pemissionCheckedList;

    public String execute() throws Exception
    {
        permissionsModel = adminService.getPermissionsModel();
        setPermissionsModel(permissionsModel);
        
        return SUCCESS;
    }

    public String updateRolePermission()throws Exception
    {
        HttpServletRequest  request = ServletActionContext.getRequest();
        Enumeration pNames = request.getParameterNames();
        List<String> checkedPermissions = new ArrayList<String>();
        while(pNames.hasMoreElements()){
         String name=(String)pNames.nextElement();
         if(!name.equals("hiddenRoleName"))
         {
           String value=request.getParameter(name);
           checkedPermissions.add(value);
         }
        }

        pemissionCheckedList = StringHelper.join(",",checkedPermissions);

        boolean update = adminService.updateRolePermissions(hiddenRoleName,pemissionCheckedList);
        if(update)
        {
           setTip("Updated Success!");
        }
        else
        {
           setAlert("Updated Failed!");
        }
        roleName = hiddenRoleName;
        permissionsModel = adminService.getPermissionsModel();
        setPermissionsModel(permissionsModel);
        return SUCCESS;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public PermissionsModel getPermissionsModel() {
        return permissionsModel;
    }

    public void setPermissionsModel(PermissionsModel permissionsModel) {
        this.permissionsModel = permissionsModel;
    }

    public String getHiddenRoleNamee() {
        return hiddenRoleName;
    }

    public void setHiddenRoleName(String hiddenRoleName) {
        this.hiddenRoleName = hiddenRoleName;
    }

    public String getPemissionCheckedList() {
        return pemissionCheckedList;
    }

    public void setPemissionCheckedList(String pemissionCheckedList) {
        this.pemissionCheckedList = pemissionCheckedList;
    }
}
