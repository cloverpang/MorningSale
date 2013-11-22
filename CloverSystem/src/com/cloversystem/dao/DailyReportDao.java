package com.cloversystem.dao;

import java.sql.SQLException;
import java.util.*;

import com.cloversystem.dao.dto.SearchCondition;
import com.cloversystem.domain.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 28/10/2013
 * Time: 1:50:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DailyReportDao {
    DailyReport get(String id);

    String save(DailyReport dailyReport);

    void update(DailyReport dailyReport);

	void delete(DailyReport dailyReport);

	void delete(String id);

    List<DailyReport> findAll();

    List<DailyReport> findByCompanyId(Integer companyId);

    List<DailyReport> findByStoreId(String storeId);

    DailyReport getTopOneReportByStoreId(String storeId, String date);

    List<DailyReport> searchDataList(final List<SearchCondition> conditions, final int offset, final int pageSize);

    int searchDataListCount(final List<SearchCondition> conditions);
}
