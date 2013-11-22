package com.cloversystem.action.role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.cloversystem.domain.Role;
import com.opensymphony.xwork2.*;
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
 * Date: 27/09/2013
 * Time: 5:13:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddRoleAction extends UserBaseAction
{
    private String roleName;
	private String description;
    private List<Role> roles;
    
    public String execute() throws Exception
    {
        boolean add = adminService.addRole(getRoleName(),getDescription());
        //boolean add = false;

        roles = adminService.getAllRoles();
        setRoles(roles);
        
        if(add == true)
        {
          setTip("save success!");
          return SAVESUCCESS;
        }
        else
        {
          setAlert("add role failure!");
          return FAILURE;
        }
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
