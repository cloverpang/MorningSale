package com.cloversystem.action.store;
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
 * Date: 24/10/2013
 * Time: 1:06:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteStoreAction extends UserBaseAction{
    private String storeId;
    private String result;

    public String execute() throws Exception
    {
        boolean operate = salesManagerService.deleteStore(getStoreId());

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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
