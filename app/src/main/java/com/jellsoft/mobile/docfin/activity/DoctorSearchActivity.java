package com.jellsoft.mobile.docfin.activity;

import android.os.Bundle;

import com.jellsoft.mobile.docfin.R;

public class DoctorSearchActivity extends BaseActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);
        this.attachToolbarMenu();
        // Find the toolbar view inside the activity layout
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/
    }

}
