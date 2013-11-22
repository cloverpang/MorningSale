package com.cloversystem.dao.dto;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 14/10/2013
 * Time: 3:14:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class PageDataObject {
     // pagination
     public int pageSize;
     public int totalRows;
     public int currentPage;
    // Data URL
     public String url;
        // Sorting
     public String sortField;
     public String sortOrder;
        // Filtering
     public String filterField;
     public String filterValue;

    public Object[] item;

    public PageDataObject() {
    }

    public PageDataObject(int pageSize, int totalRows, int currentPage, String sortField, String url, String sortOrder, String filterField, String filterValue, Object[] item) {
        this.pageSize = pageSize;
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.sortField = sortField;
        this.url = url;
        this.sortOrder = sortOrder;
        this.filterField = filterField;
        this.filterValue = filterValue;
        this.item = item;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getFilterField() {
        return filterField;
    }

    public void setFilterField(String filterField) {
        this.filterField = filterField;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }


}
