package com.jellsoft.mobile.docfin.model;

import java.io.Serializable;

/**
 * Created by atulanand on 9/11/16.
 */
public class MapAddress implements Serializable {

    public final String address;
    public final String iconUrl;
    public final String title;

    public MapAddress(String address, String title, String iconUrl) {
        this.address = address;
        this.title = title;
        this.iconUrl = iconUrl;
    }
}
