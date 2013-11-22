package com.cloversystem.action.store;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
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
 * Date: 24/10/2013
 * Time: 11:22:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddStoreAction extends UserBaseAction{
    private Company company;
    private List<SystemUser> companyUsers;

    private String storeName;
    private String storeManager;
    private String compareStores;

    public String execute() throws Exception
    {
        setUpPageModel();
        return SUCCESS;
    }

    public String add() throws Exception
    {
        setUpPageModel();

        boolean storeIsExisting = salesManagerService.checkStoreIsExisting(getStoreName(),getCurrentUserCompany().getCompanyId());

        if(storeIsExisting == true)
        {
            setAlert("The Store has existing, don't add again!");
            return FAILURE;
        }

        if(getStoreName().equals("") || getStoreManager().equals(""))
        {
            setAlert("Please input the store name or select the manager!");
            return FAILURE;
        }

        boolean add = salesManagerService.addStore(getStoreName(), getStoreManager(), getCompareStores(), company);
        if(add)
        {
            setTip("Add Store Success!");
            return SAVESUCCESS;
        }
        else
        {
            setAlert("Add Store Failed!");
            return FAILURE;
        }
    }

    private void setUpPageModel()
    {
        company = getCurrentUser().getCompany();
        companyUsers = adminService.getUsersInCompany(company.getCompanyId());
        setCompany(company);
        setCompanyUsers(companyUsers);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public List<SystemUser> getCompanyUsers() {
        return companyUsers;
    }

    public void setCompanyUsers(List<SystemUser> companyUsers) {
        this.companyUsers = companyUsers;
    }
}
