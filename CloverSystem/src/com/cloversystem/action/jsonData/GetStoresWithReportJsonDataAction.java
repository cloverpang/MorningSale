package com.cloversystem.action.jsonData;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cloversystem.action.UserBaseAction;
import com.cloversystem.dao.dto.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 31/10/2013
 * Time: 8:54:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetStoresWithReportJsonDataAction extends UserBaseAction{
    private String result;

    public String execute() throws Exception
    {
        HttpServletRequest  request = ServletActionContext.getRequest();

        BaseDataFilter filter = new BaseDataFilter();

        String getSingleData = request.getParameter("getSingleData");
        if(getSingleData != null && getSingleData.equals("true"))
        {
            filter.getSingleData = true;
            filter.noOfItems = 1;
            filter.pageSize = 1;
            filter.currentPage = 0;
            filter.query = request.getParameter("query");
        }
        else
        {
            filter.getSingleData = false;
            filter.noOfItems = Integer.parseInt(request.getParameter("noOfItems"));
            filter.pageSize = Integer.parseInt(request.getParameter("pageSize"));
            filter.currentPage = Integer.parseInt(request.getParameter("currentPage"));
            filter.query = request.getParameter("query");
            filter.sortName = request.getParameter("sortName");
            filter.sortOrder = request.getParameter("sortOrder");
        }

        String[] queryStr = filter.query.split(":");
        int i = queryStr.length - 1;
        String selectDate = queryStr[i].split("=")[1];
        System.out.println(selectDate);
        if(selectDate.equals(""))
        {
            Date nowTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            selectDate = format.format(nowTime);
        }

        result = jsonDataService.getStoresWithReportJsonList(filter,selectDate).toString();

        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
