package com.cloversystem.action.authority;

import com.opensymphony.xwork2.*;

import com.opensymphony.xwork2.interceptor.*;

import com.cloversystem.action.WebConstant;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 26/09/2013
 * Time: 2:46:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserPermissionInterceptor extends AbstractInterceptor{
       // define a new permission intercept to check the login user permissions
        public String intercept(ActionInvocation invocation) throws Exception
        {
            String namespace = ServletActionContext.getActionMapping().getNamespace();
            String actionName = ServletActionContext.getActionMapping().getName();
            String localPath = namespace + "/" + actionName + ".action";

            HttpServletRequest request = ServletActionContext.getRequest();
            //String currentUserPermissions = (String)ActionContext.getContext().getSession().get(WebConstant.USERPERMISSIONS);
            HttpSession session  = request.getSession();
            String currentUserPermissions =  (String) session.getAttribute(WebConstant.USERPERMISSIONS);

            if(currentUserPermissions.contains(localPath))
            {
               return invocation.invoke();
            }
            else
            {
               return WebConstant.NOPERMISSION;
            }
//
//            return invocation.invoke();
        }
}
