package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 27/05/2013
 * Time: 2:59:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Store implements Serializable{
    private String storeId;
	private String storeName;
    private Company company;
    private String storeManager;
    private String compareStores;
    private String storeSales;
    private Date createTime;

    private Set<DailyReport> storeReports = new HashSet<DailyReport>();

    public Store() {
    }

    public Store(String storeId, String storeName, Company company, String storeManager, String compareStores, String storeSales, Date createTime, Set<DailyReport> storeReports) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.company = company;
        this.storeManager = storeManager;
        this.compareStores = compareStores;
        this.storeSales = storeSales;
        this.createTime = createTime;
        this.storeReports = storeReports;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getStoreSales() {
        return storeSales;
    }

    public void setStoreSales(String storeSales) {
        this.storeSales = storeSales;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<DailyReport> getStoreReports() {
        return storeReports;
    }

    public void setStoreReports(Set<DailyReport> storeReports) {
        this.storeReports = storeReports;
    }
}
