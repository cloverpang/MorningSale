package com.cloversystem.service.impl;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;
import com.cloversystem.service.*;
import com.cloversystem.dao.*;
import com.cloversystem.service.base.*;

import java.text.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 8:46:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class StoreSalePersonServiceImpl extends BaseService implements StoreSalePersonService{
    public Store getSalePersonDefaultStoreByStoreName(Integer companyId, String storeName) {
        return storeDao.findByNameAndCompanyId(storeName,companyId).get(0);
    }

    public boolean submitTodaySaleReport(Integer companyId, String storeId,String storeName, String reportContent, String reportCompareStores, String reportKeyValues, String reportAppendDetail, Integer ourSaleValue, Integer maxSaleValue,String productionCategorys,String productionSaleCount,String productionSaleValue, String reporter,String reportDate) throws CrException {
        DailyReport report = new DailyReport();
        String newGuid = UUID.randomUUID().toString();
        Date nowTime = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(nowTime);

        report.setReportId(newGuid);
        report.setCompanyId(companyId);
        report.setReportCompareStores(reportCompareStores);
        report.setReportContent(reportContent);
        report.setReportKeyValues(reportKeyValues);
        report.setReportAppendDetail(reportAppendDetail);
        report.setOurSaleValue(ourSaleValue);
        report.setMaxSaleValue(maxSaleValue);
        report.setProductionCategorys(productionCategorys);
        report.setProductionSaleCount(productionSaleCount);
        report.setProductionSaleValue(productionSaleValue);
        report.setReporter(reporter);
        report.setSendTime(nowTime);
        report.setStoreId(storeId);
        report.setStoreName(storeName);
        if(reportDate.equals(""))
        {
            report.setReportDay(today);
        }
        else
        {
            report.setReportDay(reportDate);
        }
        try
        {
           String add = dailyReportDao.save(report);
            if(add != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
           System.out.println(ex.toString());
           return false;
        }
    }
}
