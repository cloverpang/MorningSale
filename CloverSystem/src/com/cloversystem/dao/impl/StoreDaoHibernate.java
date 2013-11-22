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
 * Date: 23/10/2013
 * Time: 2:45:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class StoreDaoHibernate extends CloverHibernateDaoSupport implements StoreDao {
    public Store get(String id) {
        return (Store)getHibernateTemplate().get(Store.class , id);
    }

    public String save(Store store) {
        return (String)getHibernateTemplate().save(store);
    }

    public void update(Store store) {
       getHibernateTemplate().update(store);
    }

    public void delete(Store store) {
        getHibernateTemplate().delete(store);
    }

    public void delete(String id) {
         getHibernateTemplate().delete(get(id));
    }

    public List<Store> findAll() {
        return (List<Store>)getHibernateTemplate().find("from Store order by createTime desc");
    }

    public List<Store> findByCompanyId(Integer companyId) {
        return (List<Store>)getHibernateTemplate().find("from Store where company.companyId = ? order by createTime desc", companyId);
    }

    public List<Store> findByNameAndCompanyId(String name, Integer companyId) {
        return (List<Store>)getHibernateTemplate().find("from Store where storeName = ? and company.companyId = ? order by createTime desc", new Object[]{name , companyId});
    }

    public List<Store> findByManagerAndCompanyId(String storeManager, Integer companyId) {
        return (List<Store>)getHibernateTemplate().find("from Store where storeManager = ? and company.companyId = ? order by createTime desc", new Object[]{storeManager , companyId});
    }

    public List<Store> searchDataList(List<SearchCondition> conditions, int offset, int pageSize) {
       List<Store> list = searchData(Store.class,conditions,offset,pageSize);
       return list;
    }

    public int searchDataListCount(List<SearchCondition> conditions) {
       return searchDataCount(Store.class,conditions);
    }
}
