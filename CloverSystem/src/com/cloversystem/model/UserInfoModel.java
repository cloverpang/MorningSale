package com.cloversystem.model;

import com.sun.net.httpserver.HttpContext;

import java.io.Serializable;
import java.util.*;
import java.lang.*;
import java.applet.*;
import javax.servlet.http.*;
import javax.servlet.*;

import com.sun.xml.internal.ws.client.RequestContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.servlet.support.RequestContext.*;

import com.cloversystem.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 30/09/2013
 * Time: 12:18:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoModel implements Serializable
{
    public SystemUser currentUser;

    public String userPermissions;

    public List<UserRole> userRoles;

    public String userRolesStr;

    public List<MenuTab> menuTabs;

    //public List<MenuLink> userMenuLinks;

    public UserInfoModel(){}

    public UserInfoModel(SystemUser currentUser, String userPermissions, List<UserRole> userRoles, String userRolesStr, List<MenuTab> menuTabs) {
        this.currentUser = currentUser;
        this.userPermissions = userPermissions;
        this.userRoles = userRoles;
        this.userRolesStr = userRolesStr;
        this.menuTabs = menuTabs;
    }

    public SystemUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(SystemUser currentUser) {
        this.currentUser = currentUser;
    }

    public String getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(String userPermissions) {
        this.userPermissions = userPermissions;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<MenuTab> getMenuTabs() {
        return menuTabs;
    }

    public void setMenuTabs(List<MenuTab> menuTabs) {
        this.menuTabs = menuTabs;
    }

    public String getUserRolesStr() {
        return userRolesStr;
    }

    public void setUserRolesStr(String userRolesStr) {
        this.userRolesStr = userRolesStr;
    }
}
