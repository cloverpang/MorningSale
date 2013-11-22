package com.cloversystem.util;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;


/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 14/10/2013
 * Time: 2:48:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonFilter {
    public static JsonConfig getFilter(final String[] s){
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter(){

            public boolean apply(Object source, String name, Object value) {
             if(juge(s,name)) {
              return true;
             } else {
              return false;
             }
            }

            public boolean juge(String[] s,String s2){
                boolean b = false;
                for(String sl : s){
                    if(s2.equals(sl)){
                        b=true;
                    }
                }
                return b;
            }
           });
        return config;
    }
}
