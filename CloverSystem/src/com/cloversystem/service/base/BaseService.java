package com.cloversystem.service.base;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;
import com.cloversystem.service.*;
import com.cloversystem.dao.*;
import org.apache.log4j.Logger;


/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 26/09/2013
 * Time: 9:59:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class BaseService {
    protected static final Logger logger = Logger.getLogger("ServiceLogger");
    protected static final Logger errorLogger = Logger.getLogger("errorLogger");

    public static final String Valid_NoUser = "noUser";

    protected AdminDao adminDao;
    protected SystemUserDao systemUserDao;
    protected UserRoleDao userRoleDao;
    protected CompanyDao companyDao;
    protected StoreDao storeDao;
    protected DailyReportDao dailyReportDao;
    protected RoleDao roleDao;
    protected PermissionDao permissionDao;
    protected MenuDao menuDao;

    protected UserInfoModel userInfoModel;
    protected PermissionsModel permissionsModel;

    public void setAdminDao(AdminDao adminDao)
	{
		this.adminDao = adminDao;
	}

    public void setSystemUserDao(SystemUserDao systemUserDao)
	{
		this.systemUserDao = systemUserDao;
	}

    public void setUserRoleDao(UserRoleDao userRoleDao)
	{
		this.userRoleDao = userRoleDao;
	}

    public void setCompanyDao(CompanyDao companyDao)
	{
		this.companyDao = companyDao;
	}

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setPermissionsModel(PermissionsModel permissionsModel) {
        this.permissionsModel = permissionsModel;
    }

    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public void setDailyReportDao(DailyReportDao dailyReportDao) {
        this.dailyReportDao = dailyReportDao;
    }
}
