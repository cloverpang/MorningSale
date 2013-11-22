package com.cloversystem.action.company;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
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
 * Time: 6:49:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class ManageCompanyUsersAction extends UserBaseAction{
    private Company company;
    private Integer companyId;
    private String allRolesStr;
    private List<Store> companyStores;

    public String execute() throws Exception
    {
        if(getCompanyId() == null)
        {

        }
        else
        {
           company = adminService.getCompany(getCompanyId());
           companyStores = salesManagerService.getCompanyStores(getCompanyId());
        }

        allRolesStr = adminService.getAllRolesStr();

        setCompany(company);
        setAllRolesStr(allRolesStr);
        setCompanyStores(companyStores);
        return SUCCESS;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAllRolesStr() {
        return allRolesStr;
    }

    public void setAllRolesStr(String allRolesStr) {
        this.allRolesStr = allRolesStr;
    }

    public List<Store> getCompanyStores() {
        return companyStores;
    }

    public void setCompanyStores(List<Store> companyStores) {
        this.companyStores = companyStores;
    }
}
