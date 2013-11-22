package com.cloversystem.action.store;
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
 * Date: 23/10/2013
 * Time: 2:51:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManageOurCompanyStoresAction extends UserBaseAction{
    private Company company;

    public String execute() throws Exception
    {
        Integer companyId = getCurrentUser().getCompany().getCompanyId();
        company = adminService.getCompany(companyId);

        setCompany(company);
        return SUCCESS;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
