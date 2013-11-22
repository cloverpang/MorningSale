package com.cloversystem.action.report;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cloversystem.action.WebConstant;
import com.opensymphony.xwork2.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.*;

import com.cloversystem.service.*;
import com.cloversystem.exception.*;
import com.cloversystem.action.UserBaseAction;
import com.cloversystem.service.CommonUserService;
import java.util.HashMap;
import java.util.Map;


import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.cloversystem.service.CommonUserService.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 01/11/2013
 * Time: 6:22:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class SubmitTodaySaleReportAction extends UserBaseAction{
    private String storeId;
    private String storeName;
    private String reportCompareStores;
    private String reportContent;
    private String reportKeyValues;
    private String reportAppendDetail;

    private Integer ourSaleValue;
    private Integer maxSaleValue;

    private String productionCategorys;
    private String productionSaleCount;
    private String productionSaleValue;
    private String reportDate;

    private String result;

    public String execute() throws Exception
    {
        Integer companyId = getCurrentUserCompany().getCompanyId();
        String storeId = getStoreId();
        String storeName = getStoreName();
        String reportContent = getReportContent();
        String reportCompareStores = getReportCompareStores();
        String reportKeyValues =  getReportKeyValues();
        String reportAppendDetail =  getReportAppendDetail();
        Integer ourSaleValue = getOurSaleValue();
        Integer maxSaleValue = getMaxSaleValue();

        String productionCategorys = getProductionCategorys();
        String productionSaleCount = getProductionSaleCount();
        String productionSaleValue = getProductionSaleValue();

        String reporter = getCurrentUser().getRealName();
        String reportDate = getReportDate();

        boolean operate = storeSalePersonService.submitTodaySaleReport(companyId, storeId,storeName, reportContent, reportCompareStores, reportKeyValues, reportAppendDetail, ourSaleValue, maxSaleValue,productionCategorys, productionSaleCount, productionSaleValue, reporter, reportDate);

        if(operate == true)
        {
          this.result = "true";
          logger.info(reporter + " send " + getCurrentUserCompany().getCompanyName() + "-" + storeName + " 's daily sale report.");
        }
        else
        {
          this.result = "false";
        }

        return SUCCESS;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReportCompareStores() {
        return reportCompareStores;
    }

    public void setReportCompareStores(String reportCompareStores) {
        this.reportCompareStores = reportCompareStores;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getReportKeyValues() {
        return reportKeyValues;
    }

    public void setReportKeyValues(String reportKeyValues) {
        this.reportKeyValues = reportKeyValues;
    }

    public String getReportAppendDetail() {
        return reportAppendDetail;
    }

    public void setReportAppendDetail(String reportAppendDetail) {
        this.reportAppendDetail = reportAppendDetail;
    }

    public Integer getOurSaleValue() {
        return ourSaleValue;
    }

    public void setOurSaleValue(Integer ourSaleValue) {
        this.ourSaleValue = ourSaleValue;
    }

    public Integer getMaxSaleValue() {
        return maxSaleValue;
    }

    public void setMaxSaleValue(Integer maxSaleValue) {
        this.maxSaleValue = maxSaleValue;
    }

    public String getProductionCategorys() {
        return productionCategorys;
    }

    public void setProductionCategorys(String productionCategorys) {
        this.productionCategorys = productionCategorys;
    }

    public String getProductionSaleCount() {
        return productionSaleCount;
    }

    public void setProductionSaleCount(String productionSaleCount) {
        this.productionSaleCount = productionSaleCount;
    }

    public String getProductionSaleValue() {
        return productionSaleValue;
    }

    public void setProductionSaleValue(String productionSaleValue) {
        this.productionSaleValue = productionSaleValue;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
}
