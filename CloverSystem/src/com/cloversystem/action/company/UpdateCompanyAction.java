package com.cloversystem.action.company;
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
 * Date: 17/10/2013
 * Time: 12:14:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpdateCompanyAction extends UserBaseAction{
    private Integer companyId;
    private String companyName;
    private String companyCode;
    private String simpleName;
    private String productionCategoys;
    private String logoPath;

    private String result;

    public String execute() throws Exception
    {
        boolean update = adminService.updateCompany(getCompanyId(),getCompanyName(),getSimpleName(),getProductionCategoys(), getLogoPath());
        
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getProductionCategoys() {
        return productionCategoys;
    }

    public void setProductionCategoys(String productionCategoys) {
        this.productionCategoys = productionCategoys;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
