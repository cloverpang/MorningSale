package com.cloversystem.action.permission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;

import java.util.HashMap;
import java.util.Map;

import static com.cloversystem.service.CommonUserService.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 07/10/2013
 * Time: 8:52:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeletePermissionAction extends UserBaseAction
{
    private String packageName;
    private String actionName;
    private String deleteResult;

    public String execute() throws Exception
    {
        boolean delete = adminService.deletePermission(getPackageName(),getActionName());

        if(delete == true)
        {
          this.deleteResult = "true";
        }
        else
        {
          this.deleteResult = "false";
        }
        
        return SUCCESS;
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

    public String getDeleteResult() {
        return deleteResult;
    }

    public void setDeleteResult(String deleteResult) {
        this.deleteResult = deleteResult;
    }
}
