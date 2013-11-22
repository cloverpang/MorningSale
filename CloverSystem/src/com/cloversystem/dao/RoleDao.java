package com.cloversystem.dao;

import java.util.*;
import com.cloversystem.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 7:55:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RoleDao
{
    boolean add(Role role);

    void delete(Role role);

	boolean delete(String name);

    List<Role> findAll();

    List<String> findAllRoles();

    Role getRole(String name);
}
