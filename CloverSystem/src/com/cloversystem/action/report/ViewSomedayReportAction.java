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
 * Date: 31/10/2013
 * Time: 6:15:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewSomedayReportAction extends UserBaseAction{
    private Company company;
    private List<Store> myStores;
    private List<Store> companyStores;
    private String startDate;
    private String endDate;

    public String execute() throws Exception
    {
        company = getCurrentUserCompany();
        companyStores = salesManagerService.getCompanyStores(company.getCompanyId());
        myStores = salesManagerService.getStoresByManager(company.getCompanyId(),getCurrentUser().getRealName());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date nowTime = new Date();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(nowTime);
        calendar1.add(calendar1.DATE,-1);
        nowTime = calendar1.getTime();
        endDate = format.format(nowTime);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(nowTime);
        calendar2.add(calendar2.DATE,-11);
        nowTime = calendar2.getTime();
        startDate = format.format(nowTime);

        return SUCCESS;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Store> getMyStores() {
        return myStores;
    }

    public void setMyStores(List<Store> myStores) {
        this.myStores = myStores;
    }

    public List<Store> getCompanyStores() {
        return companyStores;
    }

    public void setCompanyStores(List<Store> companyStores) {
        this.companyStores = companyStores;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
