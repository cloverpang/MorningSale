package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 30/09/2013
 * Time: 7:54:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class SystemUser implements Serializable{
    private String userId;
	private String userName;
	private String realName;
    private String password;
    private Company company;
    private String parentManager;
    private boolean isLock;
    private Date createTime;
    private String defaultStore;
    private Set<UserRole> userRoles = new HashSet<UserRole>();

    private String userRolesStr;

    public SystemUser()
	{
	}

    public SystemUser(String userId, String userName, String realName, String password, Company company, String parentManager, boolean islock, Date createTime, String defaultStore, Set<UserRole> userRoles, String userRolesStr) {
        this.userId = userId;
        this.userName = userName;
        this.realName = realName;
        this.password = password;
        this.company = company;
        this.parentManager = parentManager;
        isLock = islock;
        this.createTime = createTime;
        this.defaultStore = defaultStore;
        this.userRoles = userRoles;
        this.userRolesStr = userRolesStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getParentManager() {
        return parentManager;
    }

    public void setParentManager(String parentManager) {
        this.parentManager = parentManager;
    }

    public boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public String getUserRolesStr() {
        return userRolesStr;
    }

    public void setUserRolesStr(String userRolesStr) {
        this.userRolesStr = userRolesStr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDefaultStore() {
        return defaultStore;
    }

    public void setDefaultStore(String defaultStore) {
        this.defaultStore = defaultStore;
    }
}
