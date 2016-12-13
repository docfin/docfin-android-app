package com.jellsoft.mobile.docfin.model.realm;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by atulanand on 9/21/16.
 */
public class RealmUser extends RealmObject {

    private String firstName;

    private String lastName;

    private Date dob;

    @PrimaryKey
    private String email;

    private String insuranceProvider;

    private String insurancePlan;

    private String dentalInsuranceProvider;

    private String dentalInsurancePlan;

    private String visionInsuranceProvider;

    private String visionInsurancePlan;

    private boolean registered;


    public RealmUser() {
    }

    public RealmUser(String email) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDentalInsuranceProvider() {
        return dentalInsuranceProvider;
    }

    public void setDentalInsuranceProvider(String dentalInsuranceProvider) {
        this.dentalInsuranceProvider = dentalInsuranceProvider;
    }

    public String getDentalInsurancePlan() {
        return dentalInsurancePlan;
    }

    public void setDentalInsurancePlan(String dentalInsurancePlan) {
        this.dentalInsurancePlan = dentalInsurancePlan;
    }

    public String getVisionInsuranceProvider() {
        return visionInsuranceProvider;
    }

    public void setVisionInsuranceProvider(String visionInsuranceProvider) {
        this.visionInsuranceProvider = visionInsuranceProvider;
    }

    public String getVisionInsurancePlan() {
        return visionInsurancePlan;
    }

    public void setVisionInsurancePlan(String visionInsurancePlan) {
        this.visionInsurancePlan = visionInsurancePlan;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
