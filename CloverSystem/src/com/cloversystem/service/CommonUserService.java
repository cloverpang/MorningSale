package com.cloversystem.service;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 8:46:27 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonUserService {
    public static final int LOGIN_FAIL = 0;
    public static final int LOGIN_SUCCESS = 1;

    boolean validLogin(String user , String pass);

    boolean userIsExisting(String userName);

    boolean updatePassword(String userName, String oldPassword, String newPassword);

    String getUserId(String user , String pass);

    UserInfoModel getCurrentUserInfo(String userId);
}
