package com.jellsoft.mobile.docfin.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by atulanand on 9/4/16.
 */
public class DocfinDate extends java.util.Date implements Serializable{

    private SimpleDateFormat sdf;

    private java.util.Date date;

    public DocfinDate(String format, java.util.Date date) {
        this.sdf = new SimpleDateFormat(format);
        this.date = date;
    }

    public DocfinDate(java.util.Date date) {
        this("EEE, MMM d", date);
    }

    public DocfinDate() {
        this("EEE, MMM d", new java.util.Date());
    }

    public DocfinDate(String format) {
        this(format, new java.util.Date());
    }

    @Override
    public String toString()
    {
        return this.sdf.format(this.date);
    }

    public static Date from(String dateStr, String format)
    {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String asString(Date date, String format)
    {
        return new SimpleDateFormat(format).format(date);
    }
}
