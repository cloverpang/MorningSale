package com.cloversystem.service;

import com.cloversystem.model.*;
import com.cloversystem.domain.*;
import com.cloversystem.exception.*;

import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 24/09/2013
 * Time: 8:38:29 AM
 * To change this template use File | Settings | File Templates.
 */
public interface StoreSalePersonService {
    Store getSalePersonDefaultStoreByStoreName(Integer companyId,String storeName);

    boolean submitTodaySaleReport(Integer companyId,String storeId,String storeName,String reportContent, String reportCompareStores, String reportKeyValues, String reportAppendDetail,Integer ourSaleValue,Integer maxSaleValue, String productionCategorys,String productionSaleCount,String productionSaleValue,String reporter,String reportDate) throws CrException;
}
