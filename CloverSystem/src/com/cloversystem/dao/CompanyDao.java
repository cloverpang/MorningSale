package com.cloversystem.dao;

import java.util.*;

import com.cloversystem.dao.dto.*;
import com.cloversystem.domain.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 10:10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyDao {
    Company get(Integer id);

    Integer save(Company company);

    void update(Company company);

	void delete(Company company);

	void delete(Integer id);

    List<Company> findAll();

    List<Company> searchByNameAndCode(String name, String code);

    List<Company> searchDataList(final List<SearchCondition> conditions, final int offset, final int pageSize);

    int searchDataListCount(final List<SearchCondition> conditions);

    //don't use now
    List<Company> findListByPage(final String hql,final String[] values, final int offset, final int pageSize);
    int dymicSearchResultCount(final String hql,final String[] values);
}
