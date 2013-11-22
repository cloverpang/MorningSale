package com.cloversystem.dao.dto;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 15/10/2013
 * Time: 3:43:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class SearchCondition {
    public String conditionKey;
    public String condition;
    public String conditionValue;

    public SearchCondition() {
    }

    public SearchCondition(String conditionKey, String condition, String conditionValue) {
        this.conditionKey = conditionKey;
        this.condition = condition;
        this.conditionValue = conditionValue;
    }

    public String getConditionKey() {
        return conditionKey;
    }

    public void setConditionKey(String conditionKey) {
        this.conditionKey = conditionKey;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }
}
