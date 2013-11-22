package com.cloversystem.action.user;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.cloversystem.domain.UserRole;
import com.opensymphony.xwork2.*;
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
 * Date: 09/11/2013
 * Time: 2:41:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpdatePasswordAction extends UserBaseAction {
    String oldPassword;
    String newPassword;
    String repeatPassword;

    public String execute() throws Exception
    {
        boolean userLogin = userService.validLogin(getCurrentUser().getUserName(),getOldPassword());
        if(userLogin != true)
        {
            setAlert("You need to input the old password correct!");
            return ERROR;
        }
        if(getNewPassword().equals("") || !getNewPassword().equals(getRepeatPassword()))
        {
            setAlert("The new password and confirm password not equal!");
            return ERROR;
        }

        if(userLogin == true && getNewPassword().equals(getRepeatPassword()))
        {
            boolean updatePassword = userService.updatePassword(getCurrentUser().getUserName(),getOldPassword(),getNewPassword());
            if(updatePassword)
            {
               setTip("password updated success!");
            }            
            return SUCCESS;
        }

        return SUCCESS;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
