package com.cloversystem.action.report;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import java.util.HashMap;
import java.util.Map;


import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.cloversystem.service.CommonUserService.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 08/11/2013
 * Time: 11:32:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteDailyReportAction extends UserBaseAction{
    private String dailyReportId;
    private String result;

    public String execute() throws Exception
    {
        boolean operate = salesManagerService.deleteDailyReport(getDailyReportId());

        if(operate == true)
        {
          this.result = "true";
        }
        else
        {
          this.result = "false";
        }

        return SUCCESS;
    }


    public String getDailyReportId() {
        return dailyReportId;
    }

    public void setDailyReportId(String dailyReportId) {
        this.dailyReportId = dailyReportId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
