package com.cloversystem.domain;
import java.io.Serializable;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 28/10/2013
 * Time: 1:29:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DailyReport implements Serializable{
    private String reportId;
    private Integer companyId;
    private String storeId;
    private String storeName;
	private String reportContent;
    private String reportCompareStores;
    private String reportKeyValues;
	private String reportAppendDetail;
    private Integer ourSaleValue;
    private Integer maxSaleValue;
    private String productionCategorys;
    private String productionSaleCount;
	private String productionSaleValue;
    private Date sendTime;
    private String reportDay;
    private String reporter;

    private String sendTimeStr;

    public DailyReport() {
    }

    public DailyReport(String reportId, Integer companyId, String storeName, String storeId, String reportContent, String reportCompareStores, String reportKeyValues, String reportAppendDetail, Integer maxSaleValue, Integer ourSaleValue, String productionCategorys, String productionSaleCount, String productionSaleValue, Date sendTime, String reportDay, String reporter) {
        this.reportId = reportId;
        this.companyId = companyId;
        this.storeName = storeName;
        this.storeId = storeId;
        this.reportContent = reportContent;
        this.reportCompareStores = reportCompareStores;
        this.reportKeyValues = reportKeyValues;
        this.reportAppendDetail = reportAppendDetail;
        this.maxSaleValue = maxSaleValue;
        this.ourSaleValue = ourSaleValue;
        this.productionCategorys = productionCategorys;
        this.productionSaleCount = productionSaleCount;
        this.productionSaleValue = productionSaleValue;
        this.sendTime = sendTime;
        this.reportDay = reportDay;
        this.reporter = reporter;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public String getReportCompareStores() {
        return reportCompareStores;
    }

    public void setReportCompareStores(String reportCompareStores) {
        this.reportCompareStores = reportCompareStores;
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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getReportDay() {
        return reportDay;
    }

    public void setReportDay(String reportDay) {
        this.reportDay = reportDay;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getSendTimeStr() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(getSendTime());
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }
}
