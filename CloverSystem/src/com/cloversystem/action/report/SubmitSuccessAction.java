package com.cloversystem.action.report;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.cloversystem.util.StringHelper;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import static com.cloversystem.service.CommonUserService.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.cloversystem.domain.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 09/11/2013
 * Time: 2:15:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubmitSuccessAction extends UserBaseAction{
    private String sendSuccessTipMessage;
    public String execute() throws Exception
    {
        return SUCCESS;
    }

    public String getSendSuccessTipMessage() {
        return sendSuccessTipMessage;
    }

    public void setSendSuccessTipMessage(String sendSuccessTipMessage) {
        this.sendSuccessTipMessage = sendSuccessTipMessage;
    }
}
