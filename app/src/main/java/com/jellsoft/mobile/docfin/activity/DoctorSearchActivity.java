package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.DisplayPopupMenu;
import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.fragment.DatePickerFragment;
import com.jellsoft.mobile.docfin.fragment.GooglePlaceSearchFragment;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.service.DoctorSpecializationService;

import java.text.ParseException;
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

        Button searchButton = (Button) findViewById(R.id.docSearchSubmitButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDoctors();
            }
        });

    }


    private void searchDoctors()
    {
        Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
        intent.putExtra(IntentConstants.SEARCH_SPECIALITY, ((AutoCompleteTextView)findViewById(R.id.selectDoctorSpeciality)).getText().toString());
        intent.putExtra(IntentConstants.SEARCH_LOCATION, ((AutoCompleteTextView)findViewById(R.id.docSearchLoc)).getText().toString());
        intent.putExtra(IntentConstants.SEARCH_DATE, getDateSelected());
        intent.putExtra(IntentConstants.SEARCH_IN_NETWORK,  ((CheckBox) findViewById(R.id.docSearchInNetwork)).isChecked());
        startActivity(intent);
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

    private Date getDateSelected()
    {
        Button dateButton = (Button) findViewById(R.id.docSearchDateButton);
        try {
            return sdf.parse(dateButton.getText().toString());
        } catch (ParseException e) {
            return new Date();
        }
    }
}
