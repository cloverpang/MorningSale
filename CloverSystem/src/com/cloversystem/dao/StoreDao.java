package com.cloversystem.dao;
import java.sql.SQLException;
import java.util.*;

import com.cloversystem.dao.dto.SearchCondition;
import com.cloversystem.domain.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 23/10/2013
 * Time: 2:45:36 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StoreDao {
    Store get(String id);

    String save(Store store);

    void update(Store store);

	void delete(Store store);

	void delete(String id);

    List<Store> findAll();

    List<Store> findByCompanyId(Integer companyId);

    List<Store> findByNameAndCompanyId(String name, Integer companyId);

    List<Store> findByManagerAndCompanyId(String storeManager, Integer companyId);

    List<Store> searchDataList(final List<SearchCondition> conditions, final int offset, final int pageSize);

    int searchDataListCount(final List<SearchCondition> conditions);
}
