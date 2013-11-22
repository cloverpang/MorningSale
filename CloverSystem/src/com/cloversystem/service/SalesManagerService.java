package com.cloversystem.service;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 8:34:14 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SalesManagerService {
    boolean addUserRole(String userId, String roleName) throws CrException;

    boolean removeUserRole(String userId, String roleName) throws CrException;

    boolean lockUser(String userId) throws CrException;

    boolean deleteUser(String userId) throws CrException;

    boolean resetUserPassword(String userId) throws CrException;

    boolean updateUser(String userId, String realName, String parentManager, String defaultStore) throws CrException;

    boolean addSingleUser(String userName, String realName, String password, String parentManager, String defaultStore, String roles, Company company) throws CrException;

    boolean batchAddUsers(Company company,String password, String inputUsers) throws CrException;

    boolean checkStoreIsExisting(String storeName,Integer companyId);

    boolean addStore(String storeName, String storeManager, String compareStores,Company company) throws CrException;

    boolean deleteStore(String storeId) throws CrException;

    boolean updateStore(String storeId, String storeName, String storeManager, String compareStores) throws CrException;

    List<Store> getCompanyStores(Integer companyId);

    List<SystemUser> getMyManagers(Integer companyId,String realName);

    List<Store> getStoresByManager(Integer companyId,String realName);

    boolean deleteDailyReport(String dailyReportId) throws CrException;
}
