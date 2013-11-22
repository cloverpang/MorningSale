package com.cloversystem.action.authority;

import com.opensymphony.xwork2.*;

import com.opensymphony.xwork2.interceptor.*;

import com.cloversystem.action.WebConstant;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 26/09/2013
 * Time: 1:37:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserAuthorityInterceptor extends AbstractInterceptor{
        public String intercept(ActionInvocation invocation) throws Exception
        {
            ActionContext ctx = ActionContext.getContext();
		    String level = (String)ctx.getSession().get(WebConstant.LEVEL);
            if (level != null && (level.equals(WebConstant.USERLEVEL)))
		    {
			  return invocation.invoke();
		    }
		    else
		    {
			  return Action.LOGIN;
		    }
        }
}
