package com.cloversystem.dao.impl;

import java.sql.SQLException;
import java.util.*;

import com.cloversystem.dao.dto.SearchCondition;
import com.cloversystem.domain.*;
import com.cloversystem.dao.base.*;
import com.cloversystem.dao.*;
import com.cloversystem.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 30/09/2013
 * Time: 8:43:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class SystemUserDaoHibernate extends CloverHibernateDaoSupport implements SystemUserDao{
    public SystemUser get(String id) {
        return (SystemUser)getHibernateTemplate().get(SystemUser.class , id);
    }

    public String save(SystemUser user) {
        return (String)getHibernateTemplate().save(user);
    }

    public void update(SystemUser user) {
        getHibernateTemplate().update(user);
    }

    public void delete(SystemUser user) {
        getHibernateTemplate().delete(user);
    }

    public void delete(String id) {
        getHibernateTemplate().delete(get(id));
    }

    public List<SystemUser> findAll() {
        return (List<SystemUser>)getHibernateTemplate().find("from SystemUser");
    }

    public List<SystemUser> findByCompany(Integer companyId) {
        return (List<SystemUser>)getHibernateTemplate().find("from SystemUser where company.companyId = ?", companyId);
    }

    public List<SystemUser> findByParentManager(Integer companyId,String parentManager) {
        return (List<SystemUser>)getHibernateTemplate().find("from SystemUser where company.companyId = ? and parentManager = ?", new Object[]{companyId , parentManager});
    }

    public List<SystemUser> findByCompanyAndStore(Integer companyId, String storeName) {
        return (List<SystemUser>)getHibernateTemplate().find("from SystemUser where company.companyId = ? and defaultStore = ?", new Object[]{companyId , storeName});
    }

    public List<String> findByCompanyAndStoreStr(Integer companyId, String storeName) {
        return (List<String>)getHibernateTemplate().find("select realName from SystemUser where company.companyId = ? and defaultStore = ?", new Object[]{companyId , storeName});
    }

    public List<SystemUser> findByNameAndPass(String name, String pass) {
        return (List<SystemUser>)getHibernateTemplate().find("from SystemUser where userName = ? and password = ?", new String[]{name , pass});
    }

    public List<SystemUser> findByName(String name) {
        return (List<SystemUser>)getHibernateTemplate().find("from SystemUser where userName = ?", name);
    }

    public List<SystemUser> searchDataList(List<SearchCondition> conditions, int offset, int pageSize) {
       List<SystemUser> list = searchData(SystemUser.class,conditions,offset,pageSize);
       return list;
    }

    public int searchDataListCount(List<SearchCondition> conditions) {
       return searchDataCount(SystemUser.class,conditions);
    }

    //batch insert
    public void batchAddUsers(List<SystemUser> users) throws SQLException {
		Session session =  getSession();
		Transaction tx = session.beginTransaction();
        int userSize = users.size();
        if(userSize > 0)
        {
            for (int i = 0 ; i < userSize ; i++ )
            {
                session.save(users.get(i));
                if (i % 5 == 0)
                {
                    session.flush();
                    session.clear();
                }
            }
        }
		tx.commit();
		releaseSession(session);
    }
}
