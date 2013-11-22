package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 31/10/2013
 * Time: 10:16:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class StoreDailyReport implements Serializable{
    private String storeId;
	private String storeName;
    private String storeManager;
    private String compareStores;
    private Integer maxSaleValue;
    private DailyReport dailyReport;

    public StoreDailyReport() {
    }

    public StoreDailyReport(String storeId, String storeName, String storeManager, String compareStores, Integer maxSaleValue, DailyReport dailyReport) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeManager = storeManager;
        this.compareStores = compareStores;
        this.maxSaleValue = maxSaleValue;
        this.dailyReport = dailyReport;
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

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getCompareStores() {
        return compareStores;
    }

    public void setCompareStores(String compareStores) {
        this.compareStores = compareStores;
    }

    public DailyReport getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReport dailyReport) {
        this.dailyReport = dailyReport;
    }

    public Integer getMaxSaleValue() {
        return maxSaleValue;
    }

    public void setMaxSaleValue(Integer maxSaleValue) {
        this.maxSaleValue = maxSaleValue;
    }
}
