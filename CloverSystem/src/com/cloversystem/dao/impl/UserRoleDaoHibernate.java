package com.cloversystem.dao.impl;

import java.util.*;
import com.cloversystem.domain.*;
import com.cloversystem.dao.base.*;
import com.cloversystem.dao.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 30/09/2013
 * Time: 9:25:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserRoleDaoHibernate extends CloverHibernateDaoSupport implements UserRoleDao{
    public UserRole get(Integer id) {
        return (UserRole)getHibernateTemplate().get(UserRole.class , id);
    }

    public Integer save(UserRole userRole) {
        return (Integer)getHibernateTemplate().save(userRole);
    }

    public void update(UserRole userRole) {
        getHibernateTemplate().update(userRole);
    }

    public void delete(UserRole userRole) {
        getHibernateTemplate().delete(userRole);
    }

    public void delete(Integer id) {
        getHibernateTemplate().delete(get(id));
    }

    public void deleteByIdAndName(String userId, String userName) {
       List<UserRole> r = (List<UserRole>)getHibernateTemplate().find("from UserRole where userId = ? and roleName = ?", new String[]{userId , userName});
       if(r.size() > 0)
       {
           UserRole userRole = r.get(0);
           delete(userRole);
       }
    }

    public List<UserRole> findAll() {
        return (List<UserRole>)getHibernateTemplate().find("from UserRole");
    }

    public List<UserRole> findByUserId(String userId) {
        return (List<UserRole>)getHibernateTemplate().find("from UserRole where userId = ?", userId);
    }

    public List<UserRole> findByUser(SystemUser user)
    {
         return (List<UserRole>)getHibernateTemplate().find("from UserRole where systemUser = ?", user);
    }

    public List<String> findRoleSByUserId(String userId) {
        return (List<String>)getHibernateTemplate().find("select roleName from UserRole where userId = ?", userId);
    }

    public List<UserRole> selectByIdAndName(String userId, String userName) {
        return (List<UserRole>)getHibernateTemplate().find("from UserRole where userId = ? and roleName = ?", new String[]{userId , userName});
    }
}
