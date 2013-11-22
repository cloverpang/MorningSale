package com.cloversystem.action.authority;

import com.sun.net.httpserver.HttpContext;
import java.util.*;
import java.lang.*;
import java.applet.*;
import javax.servlet.http.*;
import javax.servlet.*;
import com.sun.xml.internal.ws.client.RequestContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.servlet.support.RequestContext.*;

import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.interceptor.*;
import com.cloversystem.action.WebConstant;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 26/09/2013
 * Time: 1:36:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminAuthorityInterceptor extends AbstractInterceptor{
    	public String intercept(ActionInvocation invocation) throws Exception
        {


            ActionContext ctx = ActionContext.getContext();
		    String level = (String)ctx.getSession().get(WebConstant.LEVEL);
            if (level != null && (level.equals(WebConstant.ADMINLEVEL)))
		    {
			  return invocation.invoke();
		    }
		    else
		    {
			  return WebConstant.ADMINLOGIN;
		    }
        }
}
