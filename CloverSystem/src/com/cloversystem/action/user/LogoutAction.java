package com.cloversystem.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import static com.cloversystem.service.CommonUserService.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 7:02:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class LogoutAction extends UserBaseAction{
    private static final Logger log = Logger.getLogger(LogoutAction.class);
    private String username;

    public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

    public String execute() throws Exception
    {
        String userCompanyAndName = getCurrentUserCompany().getSimpleName() + getCurrentUser().getRealName();
        ActionContext ctx = ActionContext.getContext();
        //ctx.getSession().put(WebConstant.LEVEL, null);
        //ctx.getSession().put(WebConstant.USERNAME, null);
        ctx.getSession().clear();
        setTip("logout success!");

        log.info(userCompanyAndName + " logout!");

        return SUCCESS;
    }

}
