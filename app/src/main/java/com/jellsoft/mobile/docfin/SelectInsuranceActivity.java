package com.jellsoft.mobile.docfin;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jellsoft.mobile.docfin.model.Insurance;

public class SelectInsuranceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_insurance);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.select_insurance_container);
        if(fragment == null)
        {
            fragment = new SelectInsuranceFragment();
            fm.beginTransaction().add(R.id.select_insurance_container, fragment).commit();
        }
    }
}
