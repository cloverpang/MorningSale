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
 * Time: 6:15:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class viewOurStoresReportAction extends UserBaseAction{
    private Company company;
    private String currentDate;

    public String execute() throws Exception
    {
        Date nowTime = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(nowTime);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        calendar.add(calendar.DATE,-1);
        Date nowTime2 = calendar.getTime();
        String yesterdayDate = format.format(nowTime2);

        if(calendar.get(Calendar.HOUR_OF_DAY) > 22)
        {
           currentDate =  today;
        }
        else
        {
           currentDate =  yesterdayDate;
        }

        company = getCurrentUserCompany();

        setCompany(company);
        return SUCCESS;
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
}
