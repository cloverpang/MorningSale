package com.cloversystem.util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 27/09/2013
 * Time: 11:43:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringHelper {

    public static String join(String join,String[] strAry){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<strAry.length;i++){
             if(i==(strAry.length-1)){
                 sb.append(strAry[i].trim());
             }else{
                 sb.append(strAry[i].trim()).append(join);
             }
        }

        return new String(sb);
    }

    public static String join(String join,List<String> strAry){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<strAry.size();i++){
             if(i==(strAry.size()-1)){
                 sb.append(strAry.get(i).trim());
             }else{
                 sb.append(strAry.get(i).trim()).append(join);
             }
        }

        return new String(sb);
    }

    public static List<String> removeDuplicate(List<String> list){
        HashSet<String> hashSet = new HashSet<String>(list);
        list.clear();
        list.addAll(hashSet);
        return list;  
    }

    public static List<String> removeDuplicateWithOrder(List<String> list)
    {
        HashSet<String> hashSet = new HashSet<String>();
        List<String> newlist = new ArrayList<String>();
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            String element = (String) iterator.next();
            if (hashSet.add(element))
            {
                newlist.add(element);
            }
        }
        list.clear();
        list.addAll(newlist);
        return list;
    }

}
