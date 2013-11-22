package com.cloversystem.dao;

import java.util.*;
import com.cloversystem.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 26/09/2013
 * Time: 8:10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AdminDao {
    Admin get(String adminId);

    Integer save(Admin admin);

    void update(Admin admin);

	void delete(Admin admin);

	void delete(String adminId);

    List<Admin> findAll();

    List<Admin> findByNameAndPass(String name, String pass);

    Admin findByName(String name);
}
