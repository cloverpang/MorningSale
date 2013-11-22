package com.cloversystem.action.company;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import static com.cloversystem.service.CommonUserService.*;

import java.util.*;
import com.cloversystem.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 15/10/2013
 * Time: 2:12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddCompanyAction extends UserBaseAction{
    private String companyName;
    private String companyCode;
    private String simpleName;
    private String productionCategoys;
    private String logoPath;
    //private boolean isLock;

    public String execute() throws Exception
    {
        return SUCCESS;
    }

    public String add() throws Exception
    {
        Company c = new Company();
        c.setCompanyCode(getCompanyCode());
        c.setCompanyName(getCompanyName());
        c.setSimpleName(getSimpleName());
        c.setProductionCategoys(getProductionCategoys());
        c.setIsLock(false);
        c.setLogoPath(getLogoPath());
        c.setUsers(null);

        boolean add = adminService.addCompany(c);
        if(add)
        {
            setTip("Add Company Success!");
            return SAVESUCCESS;
        }
        else
        {
            setAlert("Add Company Failed!");
            return FAILURE;
        }
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

    public String getProductionCategoys() {
        return productionCategoys;
    }

    public void setProductionCategoys(String productionCategoys) {
        this.productionCategoys = productionCategoys;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
