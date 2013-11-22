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
 * Date: 17/10/2013
 * Time: 12:14:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeleteCompanyAction extends UserBaseAction{

    private Integer companyId;
    private String result;

    public String execute() throws Exception
    {
        boolean delete = adminService.deleteCompany(getCompanyId());

        if(delete == true)
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
