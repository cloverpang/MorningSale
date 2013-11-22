package com.cloversystem.dao.dto;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 15/10/2013
 * Time: 1:26:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class BaseDataFilter {
       public int noOfItems;

        public int pageSize;
        public int currentPage;
        public String query;
        public String sortName;
        public String sortOrder;

        public Date dateFrom;
        public Date dateTo;

        public String dataSource;

        public boolean shouldGetFromCache;

        public int maxResult;

    public boolean getSingleData;

    public BaseDataFilter() {
    }

    public BaseDataFilter(int noOfItems, int pageSize, int currentPage, String query, String sortName, String sortOrder, Date dateFrom, Date dateTo, String dataSource, boolean shouldGetFromCache, int maxResult, boolean getSingleData) {
        this.noOfItems = noOfItems;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.query = query;
        this.sortName = sortName;
        this.sortOrder = sortOrder;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dataSource = dataSource;
        this.shouldGetFromCache = shouldGetFromCache;
        this.maxResult = maxResult;
        this.getSingleData = getSingleData;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public boolean getShouldGetFromCache() {
        return shouldGetFromCache;
    }

    public void setShouldGetFromCache(boolean shouldGetFromCache) {
        this.shouldGetFromCache = shouldGetFromCache;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public boolean isGetSingleData() {
        return getSingleData;
    }

    public void setGetSingleData(boolean getSingleData) {
        this.getSingleData = getSingleData;
    }
}
