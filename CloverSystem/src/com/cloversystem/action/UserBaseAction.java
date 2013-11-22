package com.cloversystem.action;

import com.cloversystem.domain.*;
import com.cloversystem.service.*;
import com.cloversystem.util.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 9:15:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserBaseAction extends ActionSupport {

    protected static final Logger logger = Logger.getLogger("ActionLogger");
    protected static final Logger errorLogger = Logger.getLogger("errorLogger");

    protected String Tip;
    protected String Alert;

    protected SystemUser currentUser;
    protected Company currentUserCompany;

    protected List<UserRole> currentUserRoleList;
    protected String currentUseRolesStr;
    
    protected String SAVESUCCESS = "saveSuccess";
    protected String FAILURE = "failure";

    protected String defaultSelectLabel = "All Option";
    protected String defaultSelectValue = "";

    protected CommonUserService userService;

    protected AdminService adminService;

    protected JsonDataService jsonDataService;

    protected SalesManagerService salesManagerService;

    protected StoreSalePersonService storeSalePersonService;

    public SystemUser getCurrentUser() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session  = request.getSession();
        return (SystemUser) session.getAttribute(WebConstant.CURRENTUSER);
    }

    public Company getCurrentUserCompany() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session  = request.getSession();
        return (Company) session.getAttribute(WebConstant.CURRENTUSERCOMPANY);
    }

    public List<UserRole> getCurrentUserRoleList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session  = request.getSession();
        return (List<UserRole>) session.getAttribute(WebConstant.USERROLESLIST);
    }

    public String getCurrentUseRolesStr() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session  = request.getSession();
        return (String) session.getAttribute(WebConstant.USERROLES);
    }

    //    public void setCurrentUser(SystemUser currentUser) {
//        this.currentUser =  currentUser;
//    }

    //setter user
    public void setCommonUserService(CommonUserService userService)
    {
        this.userService = userService;
    }

    //setter admin
    public void setAdminService(AdminService adminService)
    {
        this.adminService = adminService;
    }

    // setter json data service
    public void setJsonDataService(JsonDataService jsonDataService) {
        this.jsonDataService = jsonDataService;
    }

    public void setSalesManagerService(SalesManagerService salesManagerService) {
        this.salesManagerService = salesManagerService;
    }

    public void setStoreSalePersonService(StoreSalePersonService storeSalePersonService) {
        this.storeSalePersonService = storeSalePersonService;
    }

    public String getTip() {
        return Tip;
    }

    public void setTip(String tip) {
        Tip = tip;
    }

    public String getAlert() {
        return Alert;
    }

    public void setAlert(String alert) {
        Alert = alert;
    }

    public String getDefaultSelectValue() {
        return defaultSelectValue;
    }

    public void setDefaultSelectValue(String defaultSelectValue) {
        this.defaultSelectValue = defaultSelectValue;
    }

    public String getDefaultSelectLabel() {
        return defaultSelectLabel;
    }

    public void setDefaultSelectLabel(String defaultSelectLabel) {
        this.defaultSelectLabel = defaultSelectLabel;
    }
}
