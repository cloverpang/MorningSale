package com.cloversystem.util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 22/10/2013
 * Time: 7:01:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class ListHelper{
    public static List removeDuplicate(List list){
        HashSet hashSet = new HashSet(list);
        list.clear();
        list.addAll(hashSet);
        return list;
    }
}
