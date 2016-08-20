package com.jellsoft.mobile.docfin.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.DisplayPopupMenu;
import com.jellsoft.mobile.docfin.PlacesAutoCompleteAdapter;
import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.fragment.DatePickerFragment;
import com.jellsoft.mobile.docfin.fragment.GooglePlaceSearchFragment;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;
import com.jellsoft.mobile.docfin.service.DoctorSpecializationService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DoctorSearchActivity extends BaseDocfinActivity implements OnDateSelectedListener {

    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);
        findViewById(R.id.toolbar_main_menu).setOnClickListener(new DisplayPopupMenu());
        ((TextView) findViewById(R.id.toolbar_screen_title)).setText("Doctor Search");


        ((CheckBox) findViewById(R.id.docSearchInNetwork)).setChecked(true);

        FragmentManager fm = getSupportFragmentManager();
        // Check to see if we have retained the worker fragment.
        GooglePlaceSearchFragment searchFragment = (GooglePlaceSearchFragment) fm.findFragmentByTag("googlePlaceSearch");
        // If not retained (or first time running), we need to create it.
        if (searchFragment == null) {
            searchFragment = new GooglePlaceSearchFragment();
            fm.beginTransaction().add(searchFragment, "googlePlaceSearch").commit();
        }

        populateDoctorSpecialities();
        sdf =  new SimpleDateFormat(getResources().getString(R.string.dateFormat));

        this.onDateSelected(Calendar.getInstance().getTime());

    }


    private void populateDoctorSpecialities() {

        ArrayAdapter<DoctorSpecialization> arrayAdapter = new ArrayAdapter<>(
                this, R.layout.textviewonly,
                DoctorSpecializationService.getSpecializations(getResources()));

        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.selectDoctorSpeciality);
        textView.setAdapter(arrayAdapter);
        textView.setThreshold(1);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "docSearchDatePicker");
    }

    @Override
    public void onDateSelected(Date date)
    {
        Button dateButton = (Button) findViewById(R.id.docSearchDateButton);
        dateButton.setText(sdf.format(date));
        this.closeKeyboard();
    }
}
