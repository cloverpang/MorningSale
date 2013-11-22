package com.cloversystem.dao.base;

import com.cloversystem.dao.dto.*;
import com.cloversystem.domain.*;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class CloverHibernateDaoSupport extends HibernateDaoSupport
{
    protected static final Logger logger = Logger.getLogger("DaoLogger");
    protected static final Logger errorLogger = Logger.getLogger("errorLogger");

	public List findByTop(final String hql, 
		final int topCount)
	{

		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
		{

			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{

				List result = session.createQuery(hql)
					.setFirstResult(0)
					.setMaxResults(topCount)
					.list();
				return result;
			}
		});
		return list;
	}

	public List findByPage(final String hql, 
		final int offset, final int pageSize)
	{

		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
		{

			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{

				List result = session.createQuery(hql)
					.setFirstResult(offset)
					.setMaxResults(pageSize)
					.list();
				return result;
			}
		});
		return list;
	}


	public List findByPage(final String hql , final Object value ,
		final int offset, final int pageSize)
	{

		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
		{

			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{

				List result = session.createQuery(hql)
					.setParameter(0, value) 
					.setFirstResult(offset)
					.setMaxResults(pageSize)
					.list();
				return result;
			}
		});
		return list;
	}

	public List findByPage(final String hql, final String[] values,
		final int offset, final int pageSize)
	{
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
		{

			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{

				Query query = session.createQuery(hql);

				for (int i = 0 ; i < values.length ; i++)
				{
					query.setParameter( i, values[i]);
				}
				List result = query.setFirstResult(offset)
					.setMaxResults(pageSize)
					.list();
				return result;
			}
		});
		return list;
	}

    public int searchResultCount(final String hql)
	{
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
		{

			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{
				Query query = session.createQuery(hql);
				List result = query.list();
				return result;
			}
		});
		return list.size();
	}

    public int searchResultCount(final String hql, final String[] values)
	{
		List list = getHibernateTemplate()
			.executeFind(new HibernateCallback()
		{

			public Object doInHibernate(Session session)
				throws HibernateException, SQLException
			{

				Query query = session.createQuery(hql);

				for (int i = 0 ; i < values.length ; i++)
				{
					query.setParameter( i, values[i]);
				}
				List result = query.list();
				return result;
			}
		});
		return list.size();
	}

    public List searchData(final Class c,final List<SearchCondition> conditions, final int offset, final int pageSize)
    {
        List list = getHibernateTemplate()
                .executeFind(new HibernateCallback()
                {

                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {

                        Criteria query = session.createCriteria(c);
                        query = conditionsQuery(query,conditions);

                        List result = query.setFirstResult(offset)
                                .setMaxResults(pageSize)
                                .list();
                        return result;
                    }
                });
        return list;
    }

    public int searchDataCount(final Class c,final List<SearchCondition> conditions)
    {
        List list = getHibernateTemplate()
                .executeFind(new HibernateCallback()
                {

                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {

                        Criteria query = session.createCriteria(c);
                        query = conditionsQuery(query,conditions);

                        List result = query.list();
                        return result;
                    }
                });
        return list.size();
    }



    private Criteria conditionsQuery(Criteria query,final List<SearchCondition> conditions)
    {
        if(conditions!=null && conditions.size()>0)
        {
            for(SearchCondition c : conditions)
            {
                // add the data filter, we can add more type filters at here
                if(c.getCondition().equals("gt"))
                {
                    query.add(Restrictions.gt(c.getConditionKey(),Integer.parseInt(c.getConditionValue())));
                }
                if(c.getCondition().equals("lt"))
                {
                    query.add(Restrictions.lt(c.getConditionKey(),Integer.parseInt(c.getConditionValue())));
                }
                if(c.getCondition().equals("like"))
                {
                    query.add(Restrictions.like(c.getConditionKey(),"%" + c.getConditionValue() + "%"));
                }
                if(c.getCondition().equals("eq"))
                {
                    query.add(Restrictions.eq(c.getConditionKey(),c.getConditionValue()));
                }
                if(c.getCondition().equals("eqInt"))
                {
                    query.add(Restrictions.eq(c.getConditionKey(),Integer.parseInt(c.getConditionValue())));
                }

                //dymaic add sort at here
                if(c.getCondition().equals("sort"))
                {
                    if(c.getConditionValue().equals("desc"))
                    {
                       query.addOrder(Order.desc(c.getConditionKey()));
                    }
                    if(c.getConditionValue().equals("asc"))
                    {
                       query.addOrder(Order.asc(c.getConditionKey()));
                    }
                }
            }
        }
        
        return query;
    }
}
