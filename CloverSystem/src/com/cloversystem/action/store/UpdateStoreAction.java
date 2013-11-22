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
 * Time: 1:06:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpdateStoreAction extends UserBaseAction{
    private String storeId;
    private String result;
    private String storeName;
    private String storeManager;
    private String compareStores;

    public String execute() throws Exception
    {
        boolean operate = salesManagerService.updateStore(getStoreId(),getStoreName(),getStoreManager(),getCompareStores());

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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getCompareStores() {
        return compareStores;
    }

    public void setCompareStores(String compareStores) {
        this.compareStores = compareStores;
    }
}
