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
 * Date: 08/11/2013
 * Time: 11:32:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class InsertDailyReportAction extends UserBaseAction{
    private String storeId;
    private Store currentStore;
    private Company company;
    private String currentDate;
    private String yesterdayDate;

    public String execute() throws Exception
    {
        company = getCurrentUserCompany();
        String userDefaultStoreName = getCurrentUser().getDefaultStore();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date nowTime = new Date();
        currentDate = format.format(nowTime);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        calendar.add(calendar.DATE,-1);
        nowTime = calendar.getTime();
        yesterdayDate = format.format(nowTime);

        if(userDefaultStoreName != null && !userDefaultStoreName.equals(""))
        {
          currentStore = storeSalePersonService.getSalePersonDefaultStoreByStoreName(company.getCompanyId(),userDefaultStoreName);
          storeId = currentStore.getStoreId();
        }
        return SUCCESS;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Store getCurrentStore() {
        return currentStore;
    }

    public void setCurrentStore(Store currentStore) {
        this.currentStore = currentStore;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getYesterdayDate() {
        return yesterdayDate;
    }

    public void setYesterdayDate(String yesterdayDate) {
        this.yesterdayDate = yesterdayDate;
    }
}
