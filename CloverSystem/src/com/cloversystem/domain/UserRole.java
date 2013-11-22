package com.cloversystem.domain;
import java.io.Serializable;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 30/09/2013
 * Time: 7:54:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserRole implements Serializable{
    private Integer userRoleId;
	private String userId;
    private SystemUser user;
	private String roleName;

    public UserRole(){}

    public UserRole(Integer userRoleId,String userId,SystemUser user, String roleName)
    {
        this.userRoleId = userRoleId;
        this.userId = userId;
        this.user = user;
        this.roleName = roleName;
    }

    public void setUserRoleId(Integer userRoleId)
    {
       this.userRoleId = userRoleId;
    }

    public Integer getUserRoleId()
    {
       return this.userRoleId;
    }

    public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getUserId()
	{
		return this.userId;
	}

    public SystemUser getSystemUser() {
        return user;
    }

    public void setSystemUser(SystemUser user) {
        this.user = user;
    }

    public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	public String getRoleName()
	{
		return this.roleName;
	}
}
