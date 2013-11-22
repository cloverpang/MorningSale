package com.cloversystem.dao.impl;

import java.util.*;
import com.cloversystem.domain.*;
import com.cloversystem.dao.base.*;
import com.cloversystem.dao.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 26/09/2013
 * Time: 8:28:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdminDaoHibernate extends CloverHibernateDaoSupport implements AdminDao{
    public Admin get(String adminId)
    {
        return (Admin)getHibernateTemplate().get(Admin.class , adminId);
    }

    public Integer save(Admin admin)
    {
        return (Integer)getHibernateTemplate().save(admin);
    }

    public void update(Admin admin)
    {
        getHibernateTemplate().update(admin);
    }

	public void delete(Admin admin)
    {
        getHibernateTemplate().delete(admin);
    }

	public void delete(String adminId)
    {
        getHibernateTemplate().delete(get(adminId));
    }

    public List<Admin> findAll()
    {
        return (List<Admin>)getHibernateTemplate().find("from Admin");
    }

    public List<Admin> findByNameAndPass(String name, String pass)
    {
          return (List<Admin>)getHibernateTemplate().find("from Admin where adminName = ? and password = ?", new String[]{name , pass});
    }

    public Admin findByName(String name)
    {
        List<Admin> admins = (List<Admin>)getHibernateTemplate().find("from Admin where adminName = ? " , name);
		if (admins!= null && admins.size() >= 1)
		{
			return admins.get(0);
		}
		return null;
    }
}
