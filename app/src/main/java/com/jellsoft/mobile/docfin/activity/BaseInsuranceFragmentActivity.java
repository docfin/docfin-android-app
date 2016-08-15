package com.jellsoft.mobile.docfin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.jellsoft.mobile.docfin.R;

/**
 * Created by atulanand on 8/6/16.
 */
public abstract class BaseInsuranceFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_insurance);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.single_frame_container);
        if(fragment == null)
        {
            fragment = this.createFragment();
            fm.beginTransaction().add(R.id.single_frame_container, fragment).commit();
        }
    }


}
