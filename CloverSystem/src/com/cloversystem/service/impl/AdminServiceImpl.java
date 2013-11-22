package com.cloversystem.service.impl;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;
import com.cloversystem.service.*;
import com.cloversystem.dao.*;
import com.cloversystem.service.base.*;
import com.cloversystem.util.StringHelper;

import java.text.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 25/09/2013
 * Time: 1:08:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminServiceImpl extends BaseService implements AdminService{

    //admin login
    public int validLogin(String user , String pass)
    {
        if (adminDao.findByNameAndPass(user,pass).size()>= 1)
        {
            return LOGIN_SUCCESS;
        }
        else
        {
            return LOGIN_FAIL;
        }
    }

    public List<Role> getAllRoles()
    {
        List<Role> result = new ArrayList<Role>();
        result = roleDao.findAll();
        return result;
    }

    public String getAllRolesStr() {
        List<String> roles = new ArrayList<String>();
        roles = roleDao.findAllRoles();
        return StringHelper.join(",",roles);
    }

    public boolean addRole(String roleName, String description) throws CrException
    {
        Role role = new Role();
        role.setName(roleName);
        role.setDesciption(description);
        
        return roleDao.add(role);
    }

    public boolean deleteRole(String roleName) throws CrException {
        boolean deleteRoleInPermission = permissionDao.deleteRoleInPermissions(roleName);
        boolean deleteRoleInMenu = menuDao.deleteRoleInMenus(roleName);
        boolean deleteRole = roleDao.delete(roleName);
        if(deleteRoleInPermission && deleteRoleInMenu && deleteRole)
        {
           return true;
        }
        else
        {
           return false;
        }
    }

    public List<Permission> getAllPermissions() {
        List<Permission> result = new ArrayList<Permission>();
        result = permissionDao.findAll();
        return result;
    }

    public boolean addPermission(String packageName, String action, String name, String roles) throws CrException {
        Permission permission = new Permission();
        permission.setName(name);
        permission.setAction(action);
        permission.setPackageName(packageName);
        //permission.setId(packageName + "_" + action);
        permission.setId("/" + packageName + "/" + action + ".action");

        String[] roleStringList = roles.split(",");
        permission.setRoles(roleStringList);

        return permissionDao.add(permission);
    }

    public boolean deletePermission(String packageName, String action) throws CrException {
        return permissionDao.delete(packageName,action);
    }

    public PermissionsModel getPermissionsModel()
    {
        PermissionsModel permissionModel = permissionsModel;
        permissionModel.AllPermissions = permissionDao.findAll();
        permissionModel.AllPackages = permissionDao.findAllPackages();
        permissionModel.AllPackagePermissions = permissionDao.findAllPackagePermission();
        return permissionModel;
    }

    public boolean updateRolePermissions(String roleName, String pemissionCheckedList) {
        return permissionDao.updateRolePermissions(roleName,pemissionCheckedList);
    }

    public List<MenuTab> getAllMenu() {
       return menuDao.findAllMenuTabs();
    }

    public boolean addMenu(String tabName, String tabTitle, String linkTitle, String linkUrl, String extendUrls, String roles) {
        String[] roleStringList = roles.split(",");
        if(linkUrl.equals(""))
        {
            MenuTab menuTab = new MenuTab();
            menuTab.setTabName(tabName);
            menuTab.setTabTitle(tabTitle);
            menuTab.setTabRoles(roleStringList);
            return menuDao.addMenuTab(menuTab);
        }
        else
        {
            MenuLink menuLink = new MenuLink();
            menuLink.setLinkTitle(linkTitle);
            menuLink.setLinkUrl(linkUrl);
            menuLink.setLinkUrlExcuteStr(extendUrls);
            menuLink.setLinkRoles(roleStringList);
            return menuDao.addMenuLink(menuLink, tabName, tabTitle);
        }
    }

    public boolean deleteMenu(String tabName, String linkUrl) {
       return menuDao.delete(tabName,linkUrl);
    }

    public boolean updateRoleMenus(String roleName, String menuLinkCheckedList) {
       return menuDao.updateRoleMenus(roleName,menuLinkCheckedList);
    }

    public boolean updateMenu(String tabName, String tabTitle, String linkUrl, String linkTitle, String extendUrls) {
        if(linkUrl.equals(""))
        {
           return menuDao.updateMenuTabTitle(tabName,tabTitle);
        }
        else
        {
           return menuDao.updateMenuLink(tabName,linkUrl,linkTitle,extendUrls);
        }
    }

    public List<Company> getAllCompany() {
        return companyDao.findAll();
    }

    public boolean addCompany(Company company) throws CrException {
        int add = companyDao.save(company);
        return true;
    }

    public boolean updateCompany(Integer companyId,String compangName, String simpleName, String productionCategoys,String logoPath) throws CrException {
        Company c = companyDao.get(companyId);
        c.setCompanyName(compangName);
        c.setLogoPath(logoPath);
        c.setSimpleName(simpleName);
        c.setProductionCategoys(productionCategoys);
        companyDao.update(c);
        return true;
    }

    public boolean deleteCompany(Integer companyId) throws CrException {
        companyDao.delete(companyId);
        return true;
    }

    public boolean lockCompany(Integer companyId) throws CrException {
        Company c = companyDao.get(companyId);
        boolean currentLock = c.getIsLock();
        if(currentLock == true)
        {
            c.setIsLock(false);
        }
        else
        {
           c.setIsLock(true);
        }
        companyDao.update(c);
        return true;
    }

    public Company getCompany(Integer companyId) throws CrException {
        Company c = companyDao.get(companyId);
        return c;
    }

    public List<SystemUser> getUsersInCompany(Integer companyId){
        return systemUserDao.findByCompany(companyId);
    }
}
