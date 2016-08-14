package com.jellsoft.mobile.docfin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jellsoft.mobile.docfin.DisplayPopupMenu;
import com.jellsoft.mobile.docfin.R;

/**
 * Created by atulanand on 8/11/16.
 */
public class BaseActivityWithMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Find the toolbar view inside the activity layout
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/
    }

}
