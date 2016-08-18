package com.jellsoft.mobile.docfin.util;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by atulanand on 8/15/16.
 */
public class UIUtil {

    public static Date toDate(int year, int month, int day) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month, day, 0, 0);
        return c.getTime();
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "utf8");
        } catch (Exception e) {
            //ignore
        }
        return str;
    }
}
