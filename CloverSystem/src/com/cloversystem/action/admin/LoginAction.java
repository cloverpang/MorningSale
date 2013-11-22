package com.cloversystem.action.admin;

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

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 25/09/2013
 * Time: 12:28:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginAction extends UserBaseAction {
    private String adminname;

	private String password;

	public void setAdminname(String adminname)
	{
		this.adminname = adminname;
	}
	public String getAdminname()
	{
		return this.adminname;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return this.password;
	}

    public String adminLogin() throws Exception
    {
        ActionContext ctx = ActionContext.getContext();
        int result = adminService.validLogin(getAdminname(),getPassword());
        //int result = LOGIN_SUCCESS;
        if(result == LOGIN_SUCCESS)
        {
             //setTip("login success!");
             ctx.getSession().put(WebConstant.USERNAME, getAdminname());
             ctx.getSession().put(WebConstant.LEVEL, WebConstant.ADMINLEVEL);
             return LOGIN;
        }
        else
        {
            setAlert("login falied!");
            return ERROR;
        }
    }

    public String execute() throws Exception
    {
        return ERROR;
    }
}
