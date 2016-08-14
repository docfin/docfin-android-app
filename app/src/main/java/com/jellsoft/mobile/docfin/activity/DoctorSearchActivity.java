package com.jellsoft.mobile.docfin.activity;

import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;

import com.jellsoft.mobile.docfin.DisplayPopupMenu;
import com.jellsoft.mobile.docfin.R;

public class DoctorSearchActivity extends BaseActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);
        //this.attachToolbarMenu();
        findViewById(R.id.toolbar_main_menu).setOnClickListener(new DisplayPopupMenu());
    }

}
