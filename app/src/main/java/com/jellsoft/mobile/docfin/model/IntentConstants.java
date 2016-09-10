package com.jellsoft.mobile.docfin.model;

/**
 * Created by atulanand on 8/7/16.
 */
public class IntentConstants {


    public static final int SELECT_INSURANCE_PLAN = 1000;
    public static final int SELECT_INSURANCE_PROVIDER = 1001;
    public static final int SEARCH_DOCTORS_IN_BACKGROUND = 1002;
    public static final String BOOK_APPOINTMENT_DOCTOR_CARD = "BOOK_APPOINTMENT_DOCTOR_CARD";

    private IntentConstants(){}

    public static final int COMPLETED_WITH_RESULT = 200;
    public static final int COMPLETED_WITHOUT_RESULT = 100;

    public static final String INSURANCE_PROVIDER = "insuranceProvider";
    public static final String INSURANCE_PLAN = "insurancePlan";

    public static final String SEARCH_SPECIALITY = "SEARCH_SPECIALITY";
    public static final String SEARCH_DATE = "SEARCH_DATE";
    public static final String SEARCH_LOCATION = "SEARCH_LOCATION";
    public static final String SEARCH_IN_NETWORK = "SEARCH_IN_NETWORK";
    public static final String SEARCH_RESULTS = "SEARCH_RESULTS";
}
