package com.cloversystem.domain;
import java.io.Serializable;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 6:34:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class PackagePermission implements Serializable{
    private String packageName;
    private List<Permission> packagePermissions;

    public PackagePermission() {
    }

    public PackagePermission(List<Permission> packagePermissions) {
        this.packagePermissions = packagePermissions;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Permission> getPackagePermissions() {
        return packagePermissions;
    }

    public void setPackagePermissions(List<Permission> packagePermissions) {
        this.packagePermissions = packagePermissions;
    }
}
