package com.cloversystem.action.jsonData;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cloversystem.action.UserBaseAction;
import com.cloversystem.dao.dto.*;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 06/11/2013
 * Time: 2:19:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetPeriodReportJsonDataAction extends UserBaseAction{
    private String result;

    public String execute() throws Exception
    {
        HttpServletRequest  request = ServletActionContext.getRequest();

        Date nowTime = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(nowTime);

        String storeId = request.getParameter("storeId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");


        result = jsonDataService.getPeriodReportJsonObject(storeId,startDate,endDate).toString();

        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
