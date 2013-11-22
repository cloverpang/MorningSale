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
 * Date: 28/10/2013
 * Time: 1:50:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class DailyReportDaoHibernate extends CloverHibernateDaoSupport implements DailyReportDao{
    public DailyReport get(String id) {
        return (DailyReport)getHibernateTemplate().get(DailyReport.class , id);
    }

    public String save(DailyReport dailyReport) {
        return (String)getHibernateTemplate().save(dailyReport);
    }

    public void update(DailyReport dailyReport) {
        getHibernateTemplate().update(dailyReport);
    }

    public void delete(DailyReport dailyReport) {
        getHibernateTemplate().delete(dailyReport);
    }

    public void delete(String id) {
       getHibernateTemplate().delete(get(id));
    }

    public List<DailyReport> findAll() {
        return (List<DailyReport>)getHibernateTemplate().find("from DailyReport");
    }

    public List<DailyReport> findByCompanyId(Integer companyId) {
       return (List<DailyReport>)getHibernateTemplate().find("from DailyReport where companyId = ?", companyId);
    }

    public List<DailyReport> findByStoreId(String storeId) {
        return (List<DailyReport>)getHibernateTemplate().find("from DailyReport where storeId = ? order by sendTime desc", storeId);
    }

    public DailyReport getTopOneReportByStoreId(String storeId, String date) {
        List<DailyReport> list = findByTop("from DailyReport where storeId = '" + storeId + "' and reportDay = '" + date + "' order by sendTime desc",1);
        if(list != null && list.size() > 0)
        {
          return list.get(0);
        }
        else
        {
            return null;
        }       
    }

    public List<DailyReport> searchDataList(List<SearchCondition> conditions, int offset, int pageSize) {
       List<DailyReport> list = searchData(DailyReport.class,conditions,offset,pageSize);
       return list;
    }

    public int searchDataListCount(List<SearchCondition> conditions) {
       return searchDataCount(DailyReport.class,conditions);
    }
}
