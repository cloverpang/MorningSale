package com.cloversystem.action.user;

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
 * Date: 21/10/2013
 * Time: 1:16:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserAddRoleAction extends UserBaseAction{
    private String userId;
    private String roleName;
    private String result;

    public String execute() throws Exception
    {
        boolean operation = salesManagerService.addUserRole(getUserId(),getRoleName());

        if(operation == true)
        {
          this.result = "true";
        }
        else
        {
          this.result = "false";
        }

        return SUCCESS;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
