package com.cloversystem.service.impl;

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
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import com.cloversystem.dao.dto.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import java.util.*;
import com.cloversystem.domain.*;
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
 * Time: 1:33:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class JsonDataServiceImpl extends BaseService implements JsonDataService{
    protected SalesManagerService salesManagerService;

    public void setSalesManagerService(SalesManagerService salesManagerService) {
        this.salesManagerService = salesManagerService;
    }

    public JSONArray getCompanyJsonList(BaseDataFilter filter) {
        List<Company> companys;
        int totalSearchCount = 0;
        int firstResult = filter.pageSize + (filter.currentPage - 1) * filter.pageSize;
        List<SearchCondition> conditions = getConditions(filter);
        companys = companyDao.searchDataList(conditions,firstResult,filter.pageSize);
        if(filter.getSingleData == false)
        {
           totalSearchCount = companyDao.searchDataListCount(conditions);
        }
        else
        {
           totalSearchCount = 1; 
        }

        SearchResult<Company> r = new SearchResult();
        r.item = companys;
        r.totalRows = totalSearchCount;
        //r.totalRows = 30;
        JsonConfig config = JsonFilter.getFilter(new String[]{"users","stores"});
        JSONArray json = JSONArray.fromObject(r,config);
        return json;
    }

    public JSONArray getUserJsonList(BaseDataFilter filter) {
        List<SystemUser> users;
        int totalSearchCount = 0;
        int firstResult = filter.pageSize + (filter.currentPage - 1) * filter.pageSize;
        List<SearchCondition> conditions = getConditions(filter);
        users = systemUserDao.searchDataList(conditions,firstResult,filter.pageSize);
        if(filter.getSingleData == false)
        {
           totalSearchCount = systemUserDao.searchDataListCount(conditions);
        }
        else
        {
           totalSearchCount = 1;
        }

        SearchResult<SystemUser> r = new SearchResult();

        //manually  setting the user's roles as a string then go to json
        for(SystemUser u:users)
        {
            List<String> userRoles = userRoleDao.findRoleSByUserId(u.getUserId());
            if(userRoles != null)
            {
                u.setUserRolesStr(StringHelper.join(",",userRoles));
            }
        }

        r.item = users;
        r.totalRows = totalSearchCount;

        JsonConfig config = JsonFilter.getFilter(new String[]{"users","stores","userRoles"});
        JSONArray json = JSONArray.fromObject(r,config);
        return json;
    }

    public JSONArray getStoreJsonList(BaseDataFilter filter) {
        List<Store> stores;
        int totalSearchCount = 0;
        int firstResult = filter.pageSize + (filter.currentPage - 1) * filter.pageSize;
        List<SearchCondition> conditions = getConditions(filter);
        stores = storeDao.searchDataList(conditions,firstResult,filter.pageSize);
        if(filter.getSingleData == false)
        {
           totalSearchCount = storeDao.searchDataListCount(conditions);
        }
        else
        {
           totalSearchCount = 1;
        }

        //manually  setting the store's sales person as a string then go to json
        for(Store s:stores)
        {
            List<String> salePersons = systemUserDao.findByCompanyAndStoreStr(s.getCompany().getCompanyId(),s.getStoreName());
            if(salePersons != null)
            {
                s.setStoreSales(StringHelper.join(",",salePersons));
            }
        }

        SearchResult<Store> r = new SearchResult();
        r.item = stores;
        r.totalRows = totalSearchCount;

        JsonConfig config = JsonFilter.getFilter(new String[]{"users","stores","storeReports"});
        JSONArray json = JSONArray.fromObject(r,config);
        return json;
    }

    public JSONArray getDailyReportJsonList(BaseDataFilter filter) {
        List<DailyReport> dailyReports;
        int totalSearchCount = 0;
        int firstResult = filter.pageSize + (filter.currentPage - 1) * filter.pageSize;
        List<SearchCondition> conditions = getConditions(filter);
        dailyReports = dailyReportDao.searchDataList(conditions,firstResult,filter.pageSize);
        if(filter.getSingleData == false)
        {
           totalSearchCount = dailyReportDao.searchDataListCount(conditions);
        }
        else
        {
           totalSearchCount = 1;
        }

        SearchResult<DailyReport> r = new SearchResult();
        r.item = dailyReports;
        r.totalRows = totalSearchCount;

        //JsonConfig config = JsonFilter.getFilter(new String[]{"users","stores","storeReports"});
        JSONArray json = JSONArray.fromObject(r);
        return json;
    }

    public JSONArray getStoresWithReportJsonList(BaseDataFilter filter, String date) {
        List<Store> stores;
        int totalSearchCount = 0;
        int firstResult = filter.pageSize + (filter.currentPage - 1) * filter.pageSize;
        List<SearchCondition> conditions = getConditions(filter);
        stores = storeDao.searchDataList(conditions,firstResult,filter.pageSize);
        if(filter.getSingleData == false)
        {
           totalSearchCount = storeDao.searchDataListCount(conditions);
        }
        else
        {
           totalSearchCount = 1;
        }

        List<StoreDailyReport> storeDailyReportList = new ArrayList<StoreDailyReport>();

        //manually  setting the store's sales person as a string then go to json
        Integer maxValue = 0;
        for(Store s:stores)
        {
            DailyReport report = dailyReportDao.getTopOneReportByStoreId(s.getStoreId(), date);

            StoreDailyReport sr = new StoreDailyReport();
            sr.setStoreId(s.getStoreId());
            sr.setCompareStores(s.getCompareStores());
            sr.setStoreManager(s.getStoreManager());
            sr.setStoreName(s.getStoreName());

            if(report != null)
            {
                if(report.getMaxSaleValue() >= maxValue )
                {
                    maxValue = report.getMaxSaleValue();
                }
              sr.setDailyReport(report);
            }
            else
            {
              DailyReport emptyReport = new DailyReport();
              emptyReport.setReportContent("");
              emptyReport.setReportAppendDetail("");
              emptyReport.setReportKeyValues("");
              emptyReport.setSendTime(new Date());
              emptyReport.setSendTimeStr("");
              sr.setDailyReport(emptyReport);
            }

            //sr.setMaxSaleValue(maxValue);
            storeDailyReportList.add(sr);
        }

        for(StoreDailyReport r : storeDailyReportList)
        {
            r.setMaxSaleValue(maxValue);
        }
        
        SearchResult<StoreDailyReport> r = new SearchResult();
        r.item = storeDailyReportList;
        r.totalRows = totalSearchCount;

        JSONArray json = JSONArray.fromObject(r);
        return json;
    }

    public JSONArray getSalesReportJsonList(Integer companyId, String realName, String date) {
        List<Store> stores = salesManagerService.getStoresByManager(companyId,realName);

        List<StoreDailyReport> storeDailyReportList = new ArrayList<StoreDailyReport>();

        //manually  setting the store's sales person as a string then go to json
        Integer maxValue = 0;
        for(Store s:stores)
        {
            DailyReport report = dailyReportDao.getTopOneReportByStoreId(s.getStoreId(), date);

            StoreDailyReport sr = new StoreDailyReport();
            sr.setStoreId(s.getStoreId());
            sr.setCompareStores(s.getCompareStores());
            sr.setStoreManager(s.getStoreManager());
            sr.setStoreName(s.getStoreName());

            if(report != null)
            {
                if(report.getMaxSaleValue() >= maxValue )
                {
                    maxValue = report.getMaxSaleValue();
                }
                sr.setDailyReport(report);
            }
            else
            {
                DailyReport emptyReport = new DailyReport();
                emptyReport.setReportContent("");
                emptyReport.setReportAppendDetail("");
                emptyReport.setReportKeyValues("");
                emptyReport.setSendTime(new Date());
                emptyReport.setSendTimeStr("");
                sr.setDailyReport(emptyReport);
            }

            //sr.setMaxSaleValue(maxValue);
            storeDailyReportList.add(sr);
        }

        for(StoreDailyReport r : storeDailyReportList)
        {
            r.setMaxSaleValue(maxValue);
        }

        SearchResult<StoreDailyReport> r = new SearchResult();
        r.item = storeDailyReportList;
        r.totalRows = stores.size();

        JSONArray json = JSONArray.fromObject(r);
        return json;
    }

    public JSONArray getPeriodReportJsonList(String storeId, String startDate, String endDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public JSONObject getPeriodReportJsonObject(String storeId, String startDate, String endDate) {
        StorePeriodReport report = new StorePeriodReport();
        Store store = storeDao.get(storeId);
        report.setStoreId(storeId);
        if(store != null)
        {
            report.setStoreName(store.getStoreName());
            report.setCompareStores(store.getCompareStores());
            report.setStoreManager(store.getStoreManager());

            String companyProductionCategorys = store.getCompany().getProductionCategoys();
            
            report.setProductionCategory(companyProductionCategorys);

            List<Date> dates = DateTimeHelper.getDatesBetween(startDate, endDate);
            List<String> datesList = getDatesBetweenStr(dates,startDate,endDate);
            String datesStr = StringHelper.join(",",datesList);
            report.setDatesArrayStr(datesStr);

            List<String> productionCategorySaleValueList = new ArrayList<String>();
            List<String> compareStoresSaleValueList = new ArrayList<String>();
            List<String> datesSaleValueValueList = new ArrayList<String>();

            String[] productionCategorysArray = companyProductionCategorys.split(",");
            String[] compareStoresArray = store.getCompareStores().split(",");
            int categorysCount = productionCategorysArray.length;
            int compareStorsCount = compareStoresArray.length;

            int[] categorysIntArray = new int[categorysCount];
            int[] compareStoresIntArray = new int[compareStorsCount];
            
            for(String date:datesList)
            {
                DailyReport dailyReport = dailyReportDao.getTopOneReportByStoreId(storeId,date);
                if(dailyReport != null)
                {
                    datesSaleValueValueList.add(dailyReport.getOurSaleValue().toString());

                    //?????????????
                    String[] reportProductionCategorysArray = dailyReport.getProductionCategorys().split(",");
                    String[] reportProductionSaleValueArray = dailyReport.getProductionSaleValue().split(",");
                    if(reportProductionCategorysArray.length > 0)
                    {
                        for(int i=0;i<reportProductionCategorysArray.length;i++)
                        {
                            if(companyProductionCategorys.contains(reportProductionCategorysArray[i]))
                            {
                                categorysIntArray[i] += Integer.parseInt(reportProductionSaleValueArray[i]);
                            }
                        }
                    }

                    //????????store??????
                    String[] reportCompareStoresArray = dailyReport.getReportCompareStores().split(",");
                    String[] reportKeyValuesArray = dailyReport.getReportKeyValues().split(",");
                    if(reportCompareStoresArray.length > 0)
                    {
                        for(int i=0;i<reportCompareStoresArray.length;i++)
                        {
                            if(store.getCompareStores().contains(reportCompareStoresArray[i]))
                            {
                                compareStoresIntArray[i] += Integer.parseInt(reportKeyValuesArray[i]);
                            }
                        }
                    }
                }
                else
                {
                    datesSaleValueValueList.add("");
                }
            }

            for(int i : compareStoresIntArray)
            {
                if(i>0)
                {
                   compareStoresSaleValueList.add(String.valueOf(i));
                }
            }

            for(int j : categorysIntArray)
            {
                if(j>0)
                {
                    productionCategorySaleValueList.add(String.valueOf(j));
                }
            }

            String productionCategorySaleValueArrayStr = StringHelper.join(",",productionCategorySaleValueList);
            String compareStoresSaleValueArrayStr = StringHelper.join(",",compareStoresSaleValueList);
            String datesSaleValueArrayStr = StringHelper.join(",",datesSaleValueValueList);

            report.setProductionCategorySaleValueArrayStr(productionCategorySaleValueArrayStr);
            report.setCompareStoresSaleValueArrayStr(compareStoresSaleValueArrayStr);
            report.setDatesSaleValueArrayStr(datesSaleValueArrayStr);

            JSONObject json =JSONObject.fromObject(report);
            return json;
        }
        else
        {
            return null;
        }
    }

    private List<String> getDatesBetweenStr(List<Date> dates,String startDate, String endDate)
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<String> datesList = new ArrayList<String>();

        if(dates.size() > 0)
        {
            datesList.add(startDate);
            for(Date date : dates)
            {
                datesList.add(format.format(date));
            }
            datesList.add(endDate);
        }
        else
        {
            if(startDate.equals(endDate))
            {
               datesList.add(startDate);
            }
            else
            {
              datesList.add(startDate);
              datesList.add(endDate);
            }
        }
        return datesList;
    }

    private List<SearchCondition> getConditions(BaseDataFilter filter)
    {
        List<SearchCondition> conditions = new ArrayList<SearchCondition>();
        
        if(filter.query != null && !filter.query.equals(""))
        {
            String[] queryStr = filter.query.split(":");

            for(String q : queryStr)
            {
                String[] conditionStr =  q.split("=");
                SearchCondition condition = new SearchCondition();
                String[] conditionLabel = conditionStr[0].split("_");
                if(conditionLabel.length == 2)
                {
                    condition.condition = conditionLabel[0].toString();
                    condition.conditionKey = conditionLabel[1].toString();
                    if(conditionStr.length > 1)
                    {
                        condition.conditionValue = conditionStr[1].toString();
                        if(!condition.conditionValue.equals(""))
                        {
                            conditions.add(condition);
                        }
                    }
                }
            }
        }

        if(filter.sortName != null && filter.sortOrder != null)
        {
            if(!filter.sortName.equals("") && !filter.sortOrder.equals(""))
            {
                SearchCondition condition = new SearchCondition();
                condition.condition = "sort";
                condition.conditionKey = filter.sortName;
                condition.conditionValue = filter.sortOrder;
                conditions.add(condition);
            }
        }
        return conditions;
    }
}
