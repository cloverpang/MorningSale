package com.cloversystem.action.user;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import static com.cloversystem.service.CommonUserService.*;

import java.util.*;
import com.cloversystem.domain.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 18/10/2013
 * Time: 1:31:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddOurCompanyNewUserAction extends UserBaseAction{
    private String autoSetUserName;

    private Company company;
    private List<Role> roleslist;
    private List<UserRole> currentUserRoles;
    private String allRolesStr;

    private String userName;
    private String realName;
    private String parentManager;
    private String rolesSelect;
    private String defaultStore;

    private List<SystemUser> companyUsers;
    private List<Store> companyStores;

    public String execute() throws Exception
    {
        setUpPageModel();
        return SUCCESS;
    }

    public String add() throws Exception
    {
        setUpPageModel();

        String defaultpassword = company.getCompanyCode().concat("123456");
        boolean userIsExist = userService.userIsExisting(getUserName());
        if(userIsExist == true)
        {
           setAlert("The UserName is existing! please change another one!");
           return FAILURE;
        }
        else
        {
            boolean add = salesManagerService.addSingleUser(getUserName(), getRealName(), defaultpassword, getParentManager(),getDefaultStore(), getRolesSelect(), company);
            if(add)
            {
                setTip("Add User Success!");
                return SAVESUCCESS;
            }
            else
            {
                setAlert("Add User Failed!");
                return FAILURE;
            }
        }
    }

    private void setUpPageModel()
    {
        company = getCurrentUserCompany();
        roleslist = adminService.getAllRoles();
        currentUserRoles = getCurrentUserRoleList();
        allRolesStr = getCurrentUseRolesStr();

        companyUsers = adminService.getUsersInCompany(company.getCompanyId());
        companyStores = salesManagerService.getCompanyStores(company.getCompanyId());
        int existingUsers = companyUsers.size() + 1;
        autoSetUserName = company.getCompanyCode().concat(String.valueOf(existingUsers));

        setRoleslist(roleslist);
        setCurrentUserRoles(currentUserRoles);
        setCompany(company);
        setCompanyUsers(companyUsers);
        setCompanyStores(companyStores);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Role> getRoleslist() {
        return roleslist;
    }

    public void setRoleslist(List<Role> roleslist) {
        this.roleslist = roleslist;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getParentManager() {
        return parentManager;
    }

    public void setParentManager(String parentManager) {
        this.parentManager = parentManager;
    }

    public String getRolesSelect() {
        return rolesSelect;
    }

    public void setRolesSelect(String rolesSelect) {
        this.rolesSelect = rolesSelect;
    }

    public String getAllRolesStr() {
        return allRolesStr;
    }

    public void setAllRolesStr(String allRolesStr) {
        this.allRolesStr = allRolesStr;
    }

    public String getAutoSetUserName() {
        return autoSetUserName;
    }

    public void setAutoSetUserName(String autoSetUserName) {
        this.autoSetUserName = autoSetUserName;
    }

    public List<SystemUser> getCompanyUsers() {
        return companyUsers;
    }

    public void setCompanyUsers(List<SystemUser> companyUsers) {
        this.companyUsers = companyUsers;
    }

    public List<Store> getCompanyStores() {
        return companyStores;
    }

    public void setCompanyStores(List<Store> companyStores) {
        this.companyStores = companyStores;
    }

    public String getDefaultStore() {
        return defaultStore;
    }

    public void setDefaultStore(String defaultStore) {
        this.defaultStore = defaultStore;
    }

    public List<UserRole> getCurrentUserRoles() {
        return currentUserRoles;
    }

    public void setCurrentUserRoles(List<UserRole> currentUserRoles) {
        this.currentUserRoles = currentUserRoles;
    }
}
