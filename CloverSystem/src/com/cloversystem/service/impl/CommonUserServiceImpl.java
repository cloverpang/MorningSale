package com.cloversystem.service.impl;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;
import com.cloversystem.service.*;
import com.cloversystem.dao.*;
import com.cloversystem.service.base.*;
import com.cloversystem.util.*;

import java.text.*;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 8:47:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommonUserServiceImpl extends BaseService implements CommonUserService{

    //user login
    public boolean validLogin(String user , String pass)
    {
        //if(user.equals("clover"))
        List<SystemUser> validUsers = systemUserDao.findByNameAndPass(user,pass);
        if (validUsers.size()>= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean userIsExisting(String userName) {
        List<SystemUser> validUsers = systemUserDao.findByName(userName);
        if (validUsers.size()>= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean updatePassword(String userName, String oldPassword, String newPassword) {
        String userId = getUserId(userName,oldPassword);
        SystemUser c = systemUserDao.get(userId);
        c.setPassword(newPassword);
        systemUserDao.update(c);
        return true;
    }

    //user login and get id
    public String getUserId(String user , String pass)
    {
        List<SystemUser> validUsers = systemUserDao.findByNameAndPass(user,pass);
        if (validUsers.size()>= 1)
        {
            return validUsers.get(0).getUserId();
        }
        else
        {
            return null;
        }
    }

    public UserInfoModel getCurrentUserInfo(String userId)
    {
        UserInfoModel currentUserInfoModel = userInfoModel;
        currentUserInfoModel.currentUser = systemUserDao.get(userId);
        List<UserRole> userRoles = userRoleDao.findByUserId(userId);
        currentUserInfoModel.userRoles = userRoles;
        currentUserInfoModel.userRolesStr = getUserRolesStr(userRoles);

        List<String> rolesPermissionsId = getRolesPermissions(currentUserInfoModel.userRoles);
        currentUserInfoModel.userPermissions = StringHelper.join(",",rolesPermissionsId);

        currentUserInfoModel.menuTabs = getRolesMenuTabs(currentUserInfoModel.userRoles);

        return currentUserInfoModel;
    }

    private List<String> getRolesPermissions(List<UserRole> roles) {
        List<String> rolesPermissionsId = new ArrayList<String>();
        for(UserRole role : roles)
        {
            List<String>  permissionsId = permissionDao.findRolePermissionsId(role.getRoleName());
            rolesPermissionsId.addAll(permissionsId);
        }
        return StringHelper.removeDuplicate(rolesPermissionsId);
    }

    private List<MenuTab> getRolesMenuTabs(List<UserRole> roles){
       List<MenuTab> menuTabs = new ArrayList<MenuTab>();
       for(UserRole role : roles)
       {
         List<MenuTab>  roleMenuTab = menuDao.findRoleMenuTabs(role.getRoleName());
         menuTabs.addAll(roleMenuTab);
       }

        //System.out.println("menuTabs size" + String.valueOf(menuTabs.size()));
        
        for(int i=0;i<menuTabs.size() - 1;i++)
        {
            MenuTab tab_i = menuTabs.get(i);
            List<MenuLink> MenuLinks_i = tab_i.getTabMenuLinks();
            for(int j=menuTabs.size() - 1;j>i;j--)
            {
               MenuTab tab_j = menuTabs.get(j);

               if(tab_i.getTabName().equals(tab_j.getTabName()))
               {
                  MenuLinks_i.addAll(tab_j.getTabMenuLinks());
                  String tabAllExtendUrlsStr = tab_i.getTabAllExtendUrlsStr().concat(",").concat(tab_j.getTabAllExtendUrlsStr());

                  tab_i.setTabAllExtendUrlsStr(tabAllExtendUrlsStr); 
                  menuTabs.remove(tab_j);
               }
            }
        }

        for(int i=0;i<menuTabs.size();i++)
        {
            List<MenuLink> MenuLinks_i = menuTabs.get(i).getTabMenuLinks();
            //System.out.println("cut before menuLinks size" + String.valueOf(MenuLinks_i.size()));
            for(int m=0;m<MenuLinks_i.size() - 1;m++)
            {
                MenuLink link_m = MenuLinks_i.get(m);
                for(int n=MenuLinks_i.size() - 1;n>m;n--)
                {
                    MenuLink link_n = MenuLinks_i.get(n);
                    if(link_m.getLinkUrl().trim().equals(link_n.getLinkUrl().trim()))
                    {
                        MenuLinks_i.remove(link_n);
//                        System.out.println("current cut number" + String.valueOf(n));
//                        System.out.println("current cut link" + link_n.getLinkUrl());
//                        System.out.println("current menuLinks size" + String.valueOf(MenuLinks_i.size()));
                    }
                }
            }
            //System.out.println("cut after menuLinks size" + String.valueOf(MenuLinks_i.size()));
        }
       return menuTabs;
    }

    private String getUserRolesStr(List<UserRole> usersRoles){
        List<String> list = new ArrayList<String>();
        for(UserRole u : usersRoles)
        {
           list.add(u.getRoleName());
        }
        return StringHelper.join(",",list);
    }
}
