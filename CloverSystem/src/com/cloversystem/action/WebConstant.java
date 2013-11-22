package com.cloversystem.action;

import com.cloversystem.model.*;
import com.cloversystem.dao.UserRoleDao;

/**
 * Description:
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public interface WebConstant
{
    String CURRENTUSER = "currentUser";
    String CURRENTUSERCOMPANY = "currentUserCompany";
	String ADMINLEVEL = "admin";
    String USERLEVEL = "user";
	String LEVEL = "level";

    String USERNAME = "username";
    String REALNAME = "realName";



    String ADMINLOGIN = "adminlogin";

    String NOPERMISSION = "nopermission";

    String USERROLES = "user_roles";

    String USERROLESLIST = "user_roles_list";

    String USERPERMISSIONS = "user_permissions";

    String MENUTABS = "menuTabs";
}
