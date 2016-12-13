package com.jellsoft.mobile.docfin.model;

import com.jellsoft.mobile.docfin.model.realm.RealmUser;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by atulanand on 9/27/16.
 */
public class User implements Serializable {

    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String insuranceProvider;
    private String insurancePlan;
    private String dentalInsurancePlan;
    private String dentalInsuranceProvider;
    private String visionInsurancePlan;
    private String visionInsuranceProvider;


    public User() {
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(RealmUser rUser)
    {
        this.email = rUser.getEmail();
        this.firstName = rUser.getFirstName();
        this.lastName = rUser.getLastName();
        this.insuranceProvider = rUser.getInsuranceProvider();
        this.insurancePlan = rUser.getInsurancePlan();
        this.dateOfBirth = rUser.getDob();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public String getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(String insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDentalInsurancePlan() {
        return dentalInsurancePlan;
    }

    public void setDentalInsurancePlan(String dentalInsurancePlan) {
        this.dentalInsurancePlan = dentalInsurancePlan;
    }

    public String getDentalInsuranceProvider() {
        return dentalInsuranceProvider;
    }

    public void setDentalInsuranceProvider(String dentalInsuranceProvider) {
        this.dentalInsuranceProvider = dentalInsuranceProvider;
    }

    public String getVisionInsurancePlan() {
        return visionInsurancePlan;
    }

    public void setVisionInsurancePlan(String visionInsurancePlan) {
        this.visionInsurancePlan = visionInsurancePlan;
    }

    public String getVisionInsuranceProvider() {
        return visionInsuranceProvider;
    }

    public void setVisionInsuranceProvider(String visionInsuranceProvider) {
        this.visionInsuranceProvider = visionInsuranceProvider;
    }
}
