package com.cloversystem.action.report;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.cloversystem.domain.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 25/10/2013
 * Time: 2:13:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewYesterdayReportAction extends UserBaseAction{
    private Company company;
    private List<SystemUser> myManagers;
    private String currentDate;
    
    public String execute() throws Exception
    {
        Date nowTime = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = format.format(nowTime);

        company = getCurrentUserCompany();
        myManagers = salesManagerService.getMyManagers(company.getCompanyId(),getCurrentUser().getRealName());
        return SUCCESS;
    }

    public List<SystemUser> getMyManagers() {
        return myManagers;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setMyManagers(List<SystemUser> myManagers) {
        this.myManagers = myManagers;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
