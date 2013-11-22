package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 27/05/2013
 * Time: 2:59:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Company implements Serializable{
    private Integer companyId;
    private String companyName;
    private String companyCode;
    private String simpleName;
    private String productionCategoys;
    private String logoPath;
    private boolean isLock;
    private Set<SystemUser> users = new HashSet<SystemUser>();
    private Set<Store> stores = new HashSet<Store>();

    public Company()
	{
	}

    public Company(Integer companyId, String companyName, String companyCode, String simpleName, String productionCategoys, String logoPath, boolean lock, Set<SystemUser> users, Set<Store> stores) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.simpleName = simpleName;
        this.productionCategoys = productionCategoys;
        this.logoPath = logoPath;
        isLock = lock;
        this.users = users;
        this.stores = stores;
    }

    public void setCompanyId(Integer companyId)
    {
       this.companyId = companyId;
    }

    public Integer getCompanyId()
    {
       return this.companyId;
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

    public boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    public Set<SystemUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SystemUser> users) {
        this.users = users;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }
}
