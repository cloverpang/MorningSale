package com.cloversystem.dao.impl;

import java.sql.SQLException;
import java.util.*;

import com.cloversystem.dao.dto.SearchCondition;
import com.cloversystem.domain.*;
import com.cloversystem.dao.base.*;
import com.cloversystem.dao.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 30/09/2013
 * Time: 9:37:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyDaoHibernate extends CloverHibernateDaoSupport implements CompanyDao{
    public Company get(Integer id) {
        return (Company)getHibernateTemplate().get(Company.class , id);
    }

    public Integer save(Company company) {
        return (Integer)getHibernateTemplate().save(company);
    }

    public void update(Company company) {
        getHibernateTemplate().update(company);
    }

    public void delete(Company company) {
        getHibernateTemplate().delete(company);
    }

    public void delete(Integer id) {
        getHibernateTemplate().delete(get(id));
    }

    public List<Company> findAll() {
        return (List<Company>)getHibernateTemplate().find("from Company");
    }

    public List<Company> searchByNameAndCode(String name, String code) {
        return (List<Company>)getHibernateTemplate().find("from Company where companyName = ? and companyCode = ?", new String[]{name , code});
    }

    public List<Company> findListByPage(final String hql,final String[] values, final int offset, final int pageSize){
        List<Company> list = new ArrayList<Company>();
        if(values!=null)
        {
           list =  findByPage(hql,values, offset, pageSize);
        }
        else
        {
           list = findByPage(hql, offset, pageSize); 
        }
        return list;
    }

    public int dymicSearchResultCount(final String hql, final String[] values) {
        int i = 0;
        if(values!=null)
        {
          i = searchResultCount(hql,values);
        }
        else
        {
          i = searchResultCount(hql);
        }
        return i;
    }

    public List<Company> searchDataList(final List<SearchCondition> conditions, final int offset, final int pageSize) {
        List<Company> list = searchData(Company.class,conditions,offset,pageSize);
        return list;
    }

    public int searchDataListCount(final List<SearchCondition> conditions) {
        return searchDataCount(Company.class,conditions);
    }
}
