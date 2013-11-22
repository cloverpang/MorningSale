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

import static com.cloversystem.service.CommonUserService.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 10/10/2013
 * Time: 11:04:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteMenuAction extends UserBaseAction{
    private String tabName;
    private String linkUrl;
    private String deleteResult;

    public String execute() throws Exception
    {
        boolean delete = adminService.deleteMenu(getTabName(),getLinkUrl());

        if(delete == true)
        {
          this.deleteResult = "true";
        }
        else
        {
          this.deleteResult = "false";
        }

        return SUCCESS;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getDeleteResult() {
        return deleteResult;
    }

    public void setDeleteResult(String deleteResult) {
        this.deleteResult = deleteResult;
    }
}
