package com.cloversystem.model;

import com.cloversystem.domain.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 4:58:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class PermissionsModel
{
   public List<String> AllPackages;
   public List<Permission> AllPermissions;
   public List<PackagePermission> AllPackagePermissions;

    public PermissionsModel() {
    }

    public PermissionsModel(List<String> allPackages, List<Permission> allPermissions, List<PackagePermission> allPackagePermissions) {
        AllPackages = allPackages;
        AllPermissions = allPermissions;
        AllPackagePermissions = allPackagePermissions;
    }

    public List<Permission> GetPackagePermissions(String packageName)
    {
       List<Permission> list = new ArrayList<Permission>();
        for(Permission p : this.AllPermissions)
        {
          if(p.getPackageName().equals(packageName))
          {
              list.add(p);
          }
        }
        return list;
    }

    public List<Permission> GetPackagePermissions(String packageName, List<Permission> allPermissions)
    {
       List<Permission> list = new ArrayList<Permission>();
        for(Permission p : allPermissions)
        {
          if(p.getPackageName().equals(packageName))
          {
              list.add(p);
          }
        }
        return list;
    }

    public List<String> getAllPackages() {
        return AllPackages;
    }

    public void setAllPackages(List<String> allPackages) {
        AllPackages = allPackages;
    }

    public List<Permission> getAllPermissions() {
        return AllPermissions;
    }

    public void setAllPermissions(List<Permission> allPermissions) {
        AllPermissions = allPermissions;
    }

    public List<PackagePermission> getAllPackagePermissions() {
        return AllPackagePermissions;
    }

    public void setAllPackagePermissions(List<PackagePermission> allPackagePermissions) {
        AllPackagePermissions = allPackagePermissions;
    }
}
