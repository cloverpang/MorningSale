package com.cloversystem.service.impl;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;
import com.cloversystem.service.*;
import com.cloversystem.dao.*;
import com.cloversystem.service.base.*;

import java.sql.SQLException;
import java.text.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 8:43:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class SalesManagerServiceImpl extends BaseService implements SalesManagerService {
    public boolean addUserRole(String userId, String roleName) throws CrException {
        UserRole r = new UserRole();
        r.setRoleName(roleName);
        r.setUserId(userId);
        r.setSystemUser(systemUserDao.get(userId));
        if(userRoleDao.selectByIdAndName(userId,roleName).size() == 0)
        {
          int add = userRoleDao.save(r);
        }
        return true;
    }

    public boolean removeUserRole(String userId, String roleName) throws CrException {
        userRoleDao.deleteByIdAndName(userId,roleName);
        return true;
    }

    public boolean lockUser(String userId) throws CrException {
        SystemUser c = systemUserDao.get(userId);
        boolean currentLock = c.getIsLock();
        if(currentLock == true)
        {
            c.setIsLock(false);
        }
        else
        {
           c.setIsLock(true);
        }
        systemUserDao.update(c);
        return true;
    }

    public boolean deleteUser(String userId) throws CrException {
        systemUserDao.delete(userId);
        return true;
    }

    public boolean resetUserPassword(String userId) throws CrException {
        SystemUser c = systemUserDao.get(userId);
        c.setPassword(c.getCompany().getCompanyCode().concat("123456"));
        systemUserDao.update(c);
        return true;
    }

    public boolean updateUser(String userId, String realName, String parentManager, String defaultStore) throws CrException {
        SystemUser c = systemUserDao.get(userId);
        c.setRealName(realName);
        c.setParentManager(parentManager);
        c.setDefaultStore(defaultStore);
        systemUserDao.update(c);
        return true;
    }

    public boolean addSingleUser(String userName, String realName, String password, String parentManager, String defaultStore, String roles, Company company) throws CrException {
        SystemUser c = new SystemUser();
        String newGuid = UUID.randomUUID().toString();

        Date nowTime = new Date();
                
        c.setUserId(newGuid);
        c.setUserName(userName);
        c.setRealName(realName);
        c.setPassword(password);
        c.setParentManager(parentManager);
        c.setDefaultStore(defaultStore);
        c.setIsLock(false);
        c.setCompany(company);
        c.setCreateTime(nowTime);
        if(roles.equals(""))
        {
          c.setUserRoles(null);
        }
        else
        {
            Set<UserRole> s = new HashSet<UserRole>();
            String[] roleStringList = roles.split(",");
            for(String role : roleStringList)
            {
                UserRole r = new UserRole();
                r.setRoleName(role.replace(" ",""));
                r.setUserId(newGuid);
                r.setSystemUser(c);
                s.add(r);
            }
            c.setUserRoles(s);
        }

        String add = systemUserDao.save(c);
        if(add != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean batchAddUsers(Company company,String password,String inputUsers) throws CrException {
        List<SystemUser> userList = new ArrayList<SystemUser>();
        String[] usersList = inputUsers.split(":");
        for(String oneUser : usersList)
        {
           SystemUser c = new SystemUser();
           String newGuid = UUID.randomUUID().toString();
           Date nowTime = new Date();

           String[] oneUserArray = oneUser.split(",");
           if(oneUserArray.length == 2)
           {
              c.setUserName(oneUserArray[0]);
              c.setRealName(oneUserArray[1]);
           }
           if(oneUserArray.length == 3)
           {
              c.setUserName(oneUserArray[0]);
              c.setRealName(oneUserArray[1]);
              c.setParentManager(oneUserArray[2]);
           }

            c.setUserId(newGuid);
            c.setPassword(password);
            c.setIsLock(false);
            c.setCompany(company);
            c.setCreateTime(nowTime);
            c.setDefaultStore("");

            Set<UserRole> s = new HashSet<UserRole>();
            c.setUserRoles(s);
           userList.add(c);
        }

        try
        {
          systemUserDao.batchAddUsers(userList);
          return true;
        }
        catch(SQLException e)
        {
           System.out.println(e.toString());
           e.printStackTrace();
           return false;
        }
    }

    public boolean checkStoreIsExisting(String storeName, Integer companyId) {
        if(storeDao.findByNameAndCompanyId(storeName,companyId).size() >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean addStore(String storeName, String storeManager, String compareStores, Company company) throws CrException {
        Store s = new Store();
        String newGuid = UUID.randomUUID().toString();
        Date nowTime = new Date();

        s.setStoreId(newGuid);
        s.setCompany(company);
        s.setStoreName(storeName);
        s.setStoreManager(storeManager);
        s.setCompareStores(compareStores);
        s.setCreateTime(nowTime);
        String add = storeDao.save(s);
        if(add != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean deleteStore(String storeId) throws CrException {
        storeDao.delete(storeId);
        return true;
    }

    public boolean updateStore(String storeId, String storeName, String storeManager, String compareStores) throws CrException {
        Store s = storeDao.get(storeId);
        s.setStoreName(storeName);
        s.setStoreManager(storeManager);
        s.setCompareStores(compareStores);
        storeDao.update(s);
        return true;
    }

    public List<Store> getCompanyStores(Integer companyId) {
        return storeDao.findByCompanyId(companyId);
    }

    public List<SystemUser> getMyManagers(Integer companyId,String realName) {
        List<SystemUser> topManagers = systemUserDao.findByParentManager(companyId,realName);
        List<SystemUser> totalManagers = new ArrayList<SystemUser>();
        getMyAllManagers(companyId,topManagers,totalManagers);
        System.out.println(totalManagers.size());
        return totalManagers;
    }

    private List<SystemUser> getMyTopManagers(Integer companyId,String realName)
    {
        return systemUserDao.findByParentManager(companyId,realName);
    }

    private List<SystemUser> getMyAllManagers(Integer companyId,List<SystemUser> myManagers,List<SystemUser> allManagers)
    {
        if(myManagers != null && myManagers.size() > 0)
        {
            for(SystemUser u : myManagers)
            {
                allManagers.add(u);
                List<SystemUser> childManagers = getMyTopManagers(companyId,u.getRealName());
                getMyAllManagers(companyId,childManagers,allManagers);
            }
        }
        return allManagers;
    }

    public List<Store> getStoresByManager(Integer companyId,String realName) {
        List<Store> myStores = storeDao.findByManagerAndCompanyId(realName,companyId);
        if(myStores == null || myStores.size() == 0)
        {
            List<SystemUser> myManagers =  getMyManagers(companyId,realName);
            List<Store> userStores = new ArrayList<Store>();
            for(int i = 0;i< myManagers.size();i++)
            {
                List<Store> thisUserStores = storeDao.findByManagerAndCompanyId(myManagers.get(i).getRealName(),companyId);
                if(thisUserStores != null && thisUserStores.size() > 0)
                {
                    userStores = thisUserStores;
                    break;
                }
                else
                {
                    continue;
                }
            }
            return userStores;
        }
        else
        {
            return myStores;
        }
    }

    public boolean deleteDailyReport(String dailyReportId) throws CrException {
        dailyReportDao.delete(dailyReportId);
        return true;
    }
}
