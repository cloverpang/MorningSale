package com.cloversystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 07/11/2013
 * Time: 9:30:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeHelper {
    public static List<Date> getDates(Calendar p_start, Calendar p_end) {
        List<Date> result = new ArrayList<Date>();
        Calendar temp = p_start;
        temp.add(Calendar.DAY_OF_YEAR, 1);
        while (temp.before(p_end)) {
            result.add(temp.getTime());
            temp.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    public static List<Date> getDatesBetween(String startDate, String endDate)
    {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.clear();
        start.setTime(DateTimeHelper.stringToDate(startDate,"yyyy-MM-dd"));

        end.clear();
        end.setTime(DateTimeHelper.stringToDate(endDate,"yyyy-MM-dd"));

        List<Date> dates = getDates(start, end);
        return dates;
    }

    public static Date stringToDate(String dateString,String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateString);
            return date;
        }
        catch (ParseException e)
        {
            return new Date();
        }
    }
}
