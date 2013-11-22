package com.cloversystem.action.user;
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


import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.cloversystem.service.CommonUserService.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 22/10/2013
 * Time: 12:40:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteUserAction extends UserBaseAction{
    private String userId;
    private String result;

    public String execute() throws Exception
    {
        boolean operate = salesManagerService.deleteUser(getUserId());

        if(operate == true)
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
