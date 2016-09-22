package com.jellsoft.mobile.docfin.service;

/**
 * Created by atulanand on 9/21/16.
 */
public class MockUserRegistrationService implements UserRegistrationService {

    @Override
    public boolean registerUser(String firstName, String lastName, String email, String insuranceProvider, String insurancePlan) {
        return false;
    }
}
