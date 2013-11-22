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

import static com.cloversystem.service.CommonUserService.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 2:08:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteRoleAction extends UserBaseAction
{
    private String roleName;
	private String description;
    private List<Role> roles;

    public String execute() throws Exception
    {
        boolean delete = adminService.deleteRole(getRoleName());

//        roles = adminService.getAllRoles();
//        setRoles(roles);

//        if(delete == true)
//        {
//          setTip("delete success!");
//          return SAVESUCCESS;
//        }
//        else
//        {
//          setTip("delete role failure!");
//          return FAILURE;
//        }

        boolean result = false;
        if(delete == true)
        {
          result = true;
        }

        //ServletActionContext.getResponse().getWriter().print(getPackageName());
        ServletActionContext.getResponse().getWriter().print(result);

        return null;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
