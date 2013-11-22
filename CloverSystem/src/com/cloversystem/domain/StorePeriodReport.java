package com.cloversystem.domain;
import java.io.Serializable;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 06/11/2013
 * Time: 2:13:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class StorePeriodReport implements Serializable{
    private String storeId;
	private String storeName;
    private String storeManager;
    private String compareStores;
    private String productionCategory;
    private String datesArrayStr;
    private String datesSaleValueArrayStr;
    private String productionCategorySaleValueArrayStr;
    private String compareStoresSaleValueArrayStr;

    public StorePeriodReport() {
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

    public String getDatesArrayStr() {
        return datesArrayStr;
    }

    public void setDatesArrayStr(String datesArrayStr) {
        this.datesArrayStr = datesArrayStr;
    }

    public String getDatesSaleValueArrayStr() {
        return datesSaleValueArrayStr;
    }

    public void setDatesSaleValueArrayStr(String datesSaleValueArrayStr) {
        this.datesSaleValueArrayStr = datesSaleValueArrayStr;
    }

    public String getProductionCategorySaleValueArrayStr() {
        return productionCategorySaleValueArrayStr;
    }

    public void setProductionCategorySaleValueArrayStr(String productionCategorySaleValueArrayStr) {
        this.productionCategorySaleValueArrayStr = productionCategorySaleValueArrayStr;
    }

    public String getCompareStoresSaleValueArrayStr() {
        return compareStoresSaleValueArrayStr;
    }

    public void setCompareStoresSaleValueArrayStr(String compareStoresSaleValueArrayStr) {
        this.compareStoresSaleValueArrayStr = compareStoresSaleValueArrayStr;
    }

    public String getProductionCategory() {
        return productionCategory;
    }

    public void setProductionCategory(String productionCategory) {
        this.productionCategory = productionCategory;
    }
}
