package com.cloversystem.action.menu;
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
 * Date: 11/10/2013
 * Time: 8:49:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class UpdateMenuAction extends UserBaseAction{
    private String tabName;
    private String tabTitle;
    private String linkUrl;
    private String linkTitle;
    private String extendUrls;
    private String result;

    public String execute() throws Exception
    {
//        HttpServletRequest  request = ServletActionContext.getRequest();
//        String tabTitleFromPage = request.getParameter("tabTitle");
        String tabTitleDecode = URLDecoder.decode(getTabTitle(),"UTF-8");
        //String tabTitleDecode = URLDecoder.decode(URLEncoder.encode(getTabTitle(),"UTF-8"),"UTF-8");
        String linkTitleDecode = URLDecoder.decode(getLinkTitle(),"UTF-8");
        boolean update = adminService.updateMenu(getTabName(),tabTitleDecode,getLinkUrl(),linkTitleDecode,getExtendUrls());

        if(update == true)
        {
          this.result = "true";
        }
        else
        {
          this.result = "false";
        }

        return SUCCESS;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getExtendUrls() {
        return extendUrls;
    }

    public void setExtendUrls(String extendUrls) {
        this.extendUrls = extendUrls;
    }
}
