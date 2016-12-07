package com.jellsoft.mobile.docfin.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by atulanand on 9/4/16.
 */
public class Date extends java.util.Date implements Serializable{

    private SimpleDateFormat sdf;

    private java.util.Date date;

    public Date(String format, java.util.Date date) {
        this.sdf = new SimpleDateFormat(format);
        this.date = date;
    }

    public Date(java.util.Date date) {
        this("EEE, MMM d", date);
    }

    public Date() {
        this("EEE, MMM d", new java.util.Date());
    }

    public Date(String format) {
        this(format, new java.util.Date());
    }

    @Override
    public String toString()
    {
        return this.sdf.format(this.date);
    }
}
