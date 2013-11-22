package com.cloversystem.dao.dto;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 14/10/2013
 * Time: 3:19:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchResult<T> {

    public int pageSize;
    public int totalRows;
    public int currentPage;
    public List<T> item;

    public SearchResult() {
    }
    
    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
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
}
