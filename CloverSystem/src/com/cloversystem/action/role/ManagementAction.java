package com.cloversystem.action.role;

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

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 27/09/2013
 * Time: 5:13:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagementAction extends UserBaseAction
{
    private List<Role> roles;


    public String execute() throws Exception
    {
        roles = adminService.getAllRoles();
        setRoles(roles);
        return SUCCESS;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
