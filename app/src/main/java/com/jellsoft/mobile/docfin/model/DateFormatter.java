package com.jellsoft.mobile.docfin.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by atulanand on 12/11/16.
 */
public class DateFormatter {

    private final String format;

    private final SimpleDateFormat sdf;

    private DateFormatter(String format)
    {
        this.format = format;
        this.sdf = new SimpleDateFormat(format);
    }

    public static DateFormatter withFormat(String format)
    {
        return new DateFormatter(format);
    }

    public Date toDate(String dateStr)
    {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString(Date date)
    {
        return sdf.format(date);
    }
}
