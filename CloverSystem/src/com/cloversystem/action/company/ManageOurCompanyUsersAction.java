package com.cloversystem.action.company;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.cloversystem.util.StringHelper;
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
 * Time: 11:00:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class ManageOurCompanyUsersAction extends UserBaseAction{
    private Company company;
    private List<Role> roles;
    private String allRolesStr;
    private List<Store> companyStores;

    public String execute() throws Exception
    {
        company = getCurrentUserCompany();
        allRolesStr = getCurrentUseRolesStr();
        companyStores = salesManagerService.getCompanyStores(company.getCompanyId());

        setCompanyStores(companyStores);
        setCompany(company);
        setRoles(roles);
        return SUCCESS;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
