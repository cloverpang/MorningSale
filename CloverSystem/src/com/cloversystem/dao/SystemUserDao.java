package com.cloversystem.dao;
import java.sql.SQLException;
import java.util.*;

import com.cloversystem.dao.dto.SearchCondition;
import com.cloversystem.domain.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 30/09/2013
 * Time: 8:34:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SystemUserDao {
    SystemUser get(String id);

    String save(SystemUser user);

    void update(SystemUser user);

	void delete(SystemUser user);

	void delete(String id);

    List<SystemUser> findAll();

    List<SystemUser> findByCompany(Integer companyId);

    List<SystemUser> findByCompanyAndStore(Integer companyId, String storeName);

    List<String> findByCompanyAndStoreStr(Integer companyId, String storeName);

    List<SystemUser> findByNameAndPass(String name, String pass);

    List<SystemUser> findByName(String name);

    List<SystemUser> findByParentManager(Integer companyId,String parentManager);

    List<SystemUser> searchDataList(final List<SearchCondition> conditions, final int offset, final int pageSize);

    int searchDataListCount(final List<SearchCondition> conditions);

    void batchAddUsers(List<SystemUser> users)throws SQLException;
}
