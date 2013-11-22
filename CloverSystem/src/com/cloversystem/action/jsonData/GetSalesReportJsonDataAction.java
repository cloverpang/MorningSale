package com.cloversystem.action.jsonData;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cloversystem.action.UserBaseAction;
import com.cloversystem.dao.dto.*;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 05/11/2013
 * Time: 12:35:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetSalesReportJsonDataAction extends UserBaseAction{
    private String result;

    public String execute() throws Exception
    {
        HttpServletRequest  request = ServletActionContext.getRequest();

        String currentDate = null;
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

        String quaryStr = request.getParameter("query");
        String currentSelectManager = null;
        if(quaryStr == null || quaryStr.equals(""))
        {
           currentSelectManager = getCurrentUser().getRealName();
        }
        else
        {
           currentSelectManager = URLDecoder.decode(quaryStr.split("=")[1],"UTF-8"); 
        }

        result = jsonDataService.getSalesReportJsonList(getCurrentUserCompany().getCompanyId(),currentSelectManager,currentDate).toString();

        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
