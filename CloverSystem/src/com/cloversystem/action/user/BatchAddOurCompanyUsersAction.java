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
import static com.cloversystem.service.CommonUserService.*;

import java.util.*;
import com.cloversystem.domain.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 18/10/2013
 * Time: 1:31:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class BatchAddOurCompanyUsersAction extends UserBaseAction{
    private Company company;
    private String allRolesStr;
    private int maxUserCount;
    private String inputUsers;

    public String execute() throws Exception
    {
        setUpPageModel();
        return SUCCESS;
    }

    public String add() throws Exception
    {
        setUpPageModel();
        if(getInputUsers().equals(""))
        {
            setAlert("New users is null!");
            return FAILURE;
        }

        String defaultpassword = company.getCompanyCode().concat("123456");
        boolean add = salesManagerService.batchAddUsers(company,defaultpassword,getInputUsers());
        if(add)
        {
            setTip("Batch Add Users Success!");
            return SAVESUCCESS;
        }
        else
        {
            setAlert("Batch Add Users Failed!");
            return FAILURE;
        }
    }

    private void setUpPageModel()
    {
        company = getCurrentUserCompany();
        Integer companyId = company.getCompanyId();

        maxUserCount = adminService.getUsersInCompany(company.getCompanyId()).size();
        //allRolesStr = adminService.getAllRolesStr();
        allRolesStr = getCurrentUseRolesStr();

        setCompany(company);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public String getInputUsers() {
        return inputUsers;
    }

    public void setInputUsers(String inputUsers) {
        this.inputUsers = inputUsers;
    }

    public String getAllRolesStr() {
        return allRolesStr;
    }

    public void setAllRolesStr(String allRolesStr) {
        this.allRolesStr = allRolesStr;
    }
}
