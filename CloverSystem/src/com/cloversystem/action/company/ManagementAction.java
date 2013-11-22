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
 * Date: 11/10/2013
 * Time: 12:54:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagementAction extends UserBaseAction{
    private List<Company> companys;


    public String execute() throws Exception
    {
//        companys = adminService.getAllCompany();
//        setCompanys(companys);
        return SUCCESS;
    }

    public List<Company> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Company> companys) {
        this.companys = companys;
    }
}
