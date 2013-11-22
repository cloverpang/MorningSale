package com.cloversystem.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.cloversystem.service.base.BaseService;
import com.opensymphony.xwork2.*;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.*;

import com.cloversystem.util.*;
import com.cloversystem.service.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import com.cloversystem.dao.dto.*;

import java.util.HashMap;
import java.util.Map;

import java.util.*;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.BeanMorpher;
import org.apache.commons.beanutils.PropertyUtils;


/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 15/10/2013
 * Time: 1:31:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface JsonDataService{
    JSONArray getCompanyJsonList(BaseDataFilter filter);

    JSONArray getUserJsonList(BaseDataFilter filter);

    JSONArray getStoreJsonList(BaseDataFilter filter);

    JSONArray getDailyReportJsonList(BaseDataFilter filter);

    JSONArray getStoresWithReportJsonList(BaseDataFilter filter, String date);

    JSONArray getSalesReportJsonList(Integer companyId,String realName,String date);

    JSONArray getPeriodReportJsonList(String storeId,String startDate,String endDate);

    JSONObject getPeriodReportJsonObject(String storeId,String startDate,String endDate);
}
