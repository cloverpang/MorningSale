package com.cloversystem.dao;

import java.util.*;
import com.cloversystem.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 4:22:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionDao
{
    boolean add(Permission permission);

    void delete(Permission permission);

	boolean delete(String packageName, String action);

    List<Permission> findAll();

    List<String> findRolePermissionsId(String roleName);

    List<Permission> findRolesPermissions(List<Role> roles);

    List<String> findAllPackages();

    List<PackagePermission> findAllPackagePermission();

    boolean updateRolePermissions(String roleName, String pemissionCheckedList);

    boolean deleteRoleInPermissions(String roleName);
}
