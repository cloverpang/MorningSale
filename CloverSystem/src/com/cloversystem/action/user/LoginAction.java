package com.cloversystem.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.cloversystem.domain.UserRole;
import com.opensymphony.xwork2.*;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import static com.cloversystem.service.CommonUserService.*;
import com.cloversystem.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 7:03:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginAction extends UserBaseAction {
    private static final Logger log = Logger.getLogger(LoginAction.class);

	private String username;

	private String password;

    private UserInfoModel userInfoModel;

    public String commonLogin() throws Exception
    {
        ActionContext ctx = ActionContext.getContext();
        boolean validLogin = userService.validLogin(getUsername(),getPassword());
        if(validLogin == true)
        {
             String curentUserId = userService.getUserId(getUsername(),getPassword());
             UserInfoModel userInfoModel = userService.getCurrentUserInfo(curentUserId);
             setUserInfoModel(userInfoModel);

            if(userInfoModel.getCurrentUser().getCompany().getIsLock() == true || userInfoModel.getCurrentUser().getIsLock() == true)
            {
//                errorLogger.error(getUsername() + "lock user login failed!");
                setTip("Sorry, your account is locked or your company is locked!");
                return ERROR;
            }
            else
            {
                putSessions(userInfoModel);

                String userCompanyAndName = userInfoModel.currentUser.getCompany().getSimpleName() + userInfoModel.currentUser.getRealName();

                log.info(userCompanyAndName + " login success!");
                return LOGIN;
            }
        }
        else
        {
            log.info(getUsername() + " login failed!");
            setTip("login falied!");
            return ERROR;
        }
    }

    //put something into sessions
    private void putSessions(UserInfoModel userInfoModel)
    {
       ActionContext ctx = ActionContext.getContext();
       ctx.getSession().put(WebConstant.USERNAME, getUsername());
       ctx.getSession().put(WebConstant.REALNAME, userInfoModel.currentUser.getRealName());
       ctx.getSession().put(WebConstant.LEVEL, WebConstant.USERLEVEL);

       //put permissions string to session
       ctx.getSession().put(WebConstant.USERPERMISSIONS, userInfoModel.userPermissions);

       //menu tabs
       ctx.getSession().put(WebConstant.MENUTABS, userInfoModel.menuTabs);

       ctx.getSession().put(WebConstant.CURRENTUSER, userInfoModel.currentUser);

       ctx.getSession().put(WebConstant.CURRENTUSERCOMPANY, userInfoModel.currentUser.getCompany());

       ctx.getSession().put(WebConstant.USERROLES, userInfoModel.userRolesStr);

       ctx.getSession().put(WebConstant.USERROLESLIST, userInfoModel.userRoles);
    }

	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return this.password;
	}

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }
}
