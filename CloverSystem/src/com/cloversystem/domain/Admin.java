package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 25/09/2013
 * Time: 1:04:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Admin implements Serializable{
	private String adminId;
	private String adminName;
	private String password;
    private String email;

	public Admin()
	{
	}

	public Admin(String adminId , String adminName , String password, String email)
	{
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
		this.email = email;
	}

    public void setAdminId(String adminId)
	{
		this.adminId = adminId;
	}
	public String getAdminId()
	{
		return this.adminId;
	}

    public void setAdminName(String adminName)
	{
		this.adminName = adminName;
	}
	public String getAdminName()
	{
		return this.adminName;
	}

    public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return this.password;
	}

    public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}
}
