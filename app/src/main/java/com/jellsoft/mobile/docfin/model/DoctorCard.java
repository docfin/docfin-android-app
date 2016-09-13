package com.jellsoft.mobile.docfin.model;

import java.io.Serializable;

/**
 * Created by atulanand on 9/3/16.
 */
public class DoctorCard implements Serializable{

    private final String docId;

    private final String nameAndTitle;

    private final String speciality;

    private final String address1;

    private final String address2;

    private  boolean favorite;

    private final double distance;

    private final String imageURL;

    private final double lat;
    private final double lng;


    public DoctorCard(String docId, String nameAndTitle, String speciality, String address1,
                      String address2, boolean favorite, double distance, String imageURL, double lat, double lng) {
        this.docId = docId;
        this.nameAndTitle = nameAndTitle;
        this.speciality = speciality;
        this.address1 = address1;
        this.address2 = address2;
        this.favorite = favorite;
        this.distance = distance;
        this.imageURL = imageURL;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNameAndTitle() {
        return nameAndTitle;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public double getDistance() {
        return distance;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDocId() {
        return docId;
    }

    public void toggleFavoriteStatus() {
        this.favorite = !this.favorite;
    }

    public String fullAddress()
    {
        return this.address1 + ", " + this.address2;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lng;
    }
}
