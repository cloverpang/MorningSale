package com.cloversystem.dao;
import java.util.*;
import com.cloversystem.domain.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 30/09/2013
 * Time: 8:33:44 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserRoleDao {
    UserRole get(Integer id);

    Integer save(UserRole userRole);

    void update(UserRole userRole);

	void delete(UserRole userRole);

	void delete(Integer id);

    void deleteByIdAndName(String userId, String userName);

    List<UserRole> findAll();

    List<UserRole> findByUserId(String userId);

    List<UserRole> findByUser(SystemUser user);

    List<String> findRoleSByUserId(String userId);

    List<UserRole> selectByIdAndName(String userId, String userName);
}
