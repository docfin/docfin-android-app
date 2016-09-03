package com.jellsoft.mobile.docfin.model;

import java.io.Serializable;

/**
 * Created by atulanand on 9/3/16.
 */
public class DoctorCard implements Serializable{

    private final String nameAndTitle;

    private final String speciality;

    private final String address1;

    private final String address2;

    private  boolean favorite;

    private final double distance;

    private final String imageURL;

    public DoctorCard(String nameAndTitle, String speciality, String address1, String address2, double distance, String imageURL) {
        this(nameAndTitle, speciality, address1, address2, false, distance, imageURL);
    }

    public DoctorCard(String nameAndTitle, String speciality, String address1, String address2, boolean favorite, double distance, String imageURL) {
        this.nameAndTitle = nameAndTitle;
        this.speciality = speciality;
        this.address1 = address1;
        this.address2 = address2;
        this.favorite = favorite;
        this.distance = distance;
        this.imageURL = imageURL;
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
}
