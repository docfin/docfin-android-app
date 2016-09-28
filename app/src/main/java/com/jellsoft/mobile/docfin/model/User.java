package com.jellsoft.mobile.docfin.model;

import java.io.Serializable;

/**
 * Created by atulanand on 9/27/16.
 */
public class User implements Serializable {

    private String email;
    private String firstName;
    private String lastName;
    private String insuranceProvider;
    private String insurancePlan;

    public User() {
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(com.jellsoft.mobile.docfin.model.realm.User rUser)
    {
        this.email = rUser.getEmail();
        this.firstName = rUser.getFirstName();
        this.lastName = rUser.getLastName();
        this.insuranceProvider = rUser.getInsuranceProvider();
        this.insurancePlan = rUser.getInsurancePlan();
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
}
