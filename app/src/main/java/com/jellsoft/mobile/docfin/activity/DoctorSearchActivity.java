package com.jellsoft.mobile.docfin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;


import com.jellsoft.mobile.docfin.DisplayPopupMenu;
import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;
import com.jellsoft.mobile.docfin.service.DoctorSpecializationService;

public class DoctorSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);
        findViewById(R.id.toolbar_main_menu).setOnClickListener(new DisplayPopupMenu());
        ((TextView) findViewById(R.id.toolbar_screen_title)).setText("Doctor Search");

        populateDoctorSpecialities();
    }

    private void populateDoctorSpecialities() {

        ArrayAdapter<DoctorSpecialization> arrayAdapter = new ArrayAdapter<>(
                this, R.layout.textviewonly,
                DoctorSpecializationService.getSpecializations(getResources()));

        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.selectDoctorSpeciality);
        textView.setAdapter(arrayAdapter);
        textView.setThreshold(1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
            }
        });
    }


}
