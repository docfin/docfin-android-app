package com.jellsoft.mobile.docfin.util;

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
}
