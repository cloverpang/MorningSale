package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 4:23:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Permission implements Serializable{
    private String id;
    private String packageName;
    private String action;
    private String name;
    private String[] roles;

    public Permission() {
    }

    public Permission(String id, String packageName, String action, String name, String[] roles) {
        this.id = id;
        this.packageName = packageName;
        this.action = action;
        this.name = name;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
