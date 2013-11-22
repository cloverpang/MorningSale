package com.cloversystem.service;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 25/09/2013
 * Time: 1:07:42 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AdminService {
    public static final int LOGIN_FAIL = 0;
    public static final int LOGIN_SUCCESS = 1;

    int validLogin(String user , String pass);

    List<Role> getAllRoles();

    String getAllRolesStr();

    boolean addRole(String roleName, String description) throws CrException;

    boolean deleteRole(String roleName) throws CrException;

    List<Permission> getAllPermissions();

    boolean addPermission(String packageName, String action, String name, String roles) throws CrException;

    boolean deletePermission(String packageName, String action) throws CrException;;

    PermissionsModel getPermissionsModel();

    boolean updateRolePermissions(String roleName, String pemissionCheckedList);

    List<MenuTab> getAllMenu();

    boolean addMenu(String tabName, String tabTitle, String linkTitle, String linkUrl, String extendUrls, String roles);

    boolean deleteMenu(String tabName, String linkUrl);

    boolean updateRoleMenus(String roleName, String menuLinkCheckedList);

    boolean updateMenu(String tabName, String tabTitle, String linkUrl, String linkTitle, String extendUrls);

    List<Company> getAllCompany();

    boolean addCompany(Company company) throws CrException;

    boolean updateCompany(Integer companyId, String compangName, String simpleName, String productionCategoys,String logoPath) throws CrException;

    boolean deleteCompany(Integer companyId) throws CrException;

    boolean lockCompany(Integer companyId) throws CrException;

    Company getCompany(Integer companyId) throws CrException;

    List<SystemUser> getUsersInCompany(Integer companyId);
}
