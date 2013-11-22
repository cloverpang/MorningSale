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
 * Date: 23/10/2013
 * Time: 12:47:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class BatchAddCompanyUsersAction extends UserBaseAction{

    private Integer companyId;
    private Integer currentCompanyId;

    private Company company;
    private String allRolesStr;

    private int maxUserCount;
    private String inputUsers;

    public String execute() throws Exception
    {
        company = adminService.getCompany(getCompanyId());
        allRolesStr = adminService.getAllRolesStr();
        maxUserCount = adminService.getUsersInCompany(company.getCompanyId()).size();

        return SUCCESS;
    }

    public String add() throws Exception
    {
        company = adminService.getCompany(getCurrentCompanyId());
        allRolesStr = adminService.getAllRolesStr();

        setCompanyId(getCurrentCompanyId());
        
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCurrentCompanyId() {
        return currentCompanyId;
    }

    public void setCurrentCompanyId(Integer currentCompanyId) {
        this.currentCompanyId = currentCompanyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getAllRolesStr() {
        return allRolesStr;
    }

    public void setAllRolesStr(String allRolesStr) {
        this.allRolesStr = allRolesStr;
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
}
