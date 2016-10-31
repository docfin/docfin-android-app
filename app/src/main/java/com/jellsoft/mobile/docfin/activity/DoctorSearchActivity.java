package com.jellsoft.mobile.docfin.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.fragment.DatePickerFragment;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.popupmenu.PopupMenuManager;
import com.jellsoft.mobile.docfin.service.DoctorSpecializationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DoctorSearchActivity extends BaseDocfinActivity implements OnDateSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = DoctorSearchActivity.class.getSimpleName();

    SimpleDateFormat sdf;

    private GoogleApiClient mGoogleApiClient;

    EditText searchLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);
        findViewById(R.id.toolbar_main_menu).setOnClickListener(new PopupMenuManager());
        ((TextView) findViewById(R.id.toolbar_screen_title)).setText("Doctor Search");


        ((CheckBox) findViewById(R.id.docSearchInNetwork)).setChecked(true);

        populateDoctorSpecialities();
        sdf = new SimpleDateFormat(getResources().getString(R.string.dateFormat));

        Button dateButton = (Button) findViewById(R.id.docSearchDateButton);
        dateButton.setText(sdf.format(Calendar.getInstance().getTime()));

        Button searchButton = (Button) findViewById(R.id.docSearchSubmitButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDoctors();
            }
        });

        this.searchLocation = (EditText) findViewById(R.id.docSearchLoc);
        this.searchLocation.setOnClickListener(new PlacesAutocompleteListener(this));

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Looking for current location");
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                    .getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                    Log.d(TAG, "Found current location");
                    for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                        Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));
                        searchLocation.setText(placeLikelihood.getPlace().getAddress());
                    }
                    likelyPlaces.release();
                }
            });
        }
        else
        {
            requestAccessLocationPermission();
        }

    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Can't determine your location. Make sure you have internet connection", Toast.LENGTH_LONG);
    }


    class PlacesAutocompleteListener implements View.OnClickListener
    {
        private Activity parent;

        public PlacesAutocompleteListener(Activity parent) {
            this.parent = parent;
        }
        @Override
        public void onClick(View view) {
            try {
                Intent intent =
                        new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                .build(this.parent);
                startActivityForResult(intent, IntentConstants.PLACE_AUTOCOMPLETE_REQUEST_CODE);
            } catch (GooglePlayServicesRepairableException e) {
                Log.e(TAG, e.getMessage(), e);

            } catch (GooglePlayServicesNotAvailableException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    private void searchDoctors() {
        Intent intent = new Intent(getApplicationContext(), SearchDoctorsInBackgroundActivity.class);
        intent.putExtra(IntentConstants.SEARCH_SPECIALITY, ((AutoCompleteTextView) findViewById(R.id.selectDoctorSpeciality)).getText().toString());
        intent.putExtra(IntentConstants.SEARCH_LOCATION, ((EditText) findViewById(R.id.docSearchLoc)).getText().toString());
        intent.putExtra(IntentConstants.SEARCH_DATE, getDateSelected());
        intent.putExtra(IntentConstants.SEARCH_IN_NETWORK, ((CheckBox) findViewById(R.id.docSearchInNetwork)).isChecked());
        startActivityForResult(intent, IntentConstants.SEARCH_DOCTORS_IN_BACKGROUND);
    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.SEARCH_DOCTORS_IN_BACKGROUND) {
            if (resultCode == IntentConstants.COMPLETED_WITH_RESULT) {
                Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
                intent.putExtra(IntentConstants.SEARCH_RESULTS, data.getSerializableExtra(IntentConstants.SEARCH_RESULTS));
                startActivity(intent);
            }
        }
        if (requestCode == IntentConstants.PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place: " + place.getName());
                searchLocation.setText(place.getAddress());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());
                Toast.makeText(getApplicationContext(), "Unable to find the location", Toast.LENGTH_SHORT);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
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
        newFragment.show(getFragmentManager(), "docSearchDatePicker");
    }

    @Override
    public void onDateSelected(Date date) {
        Button dateButton = (Button) findViewById(R.id.docSearchDateButton);
        dateButton.setText(sdf.format(date));
    }

    private Date getDateSelected() {
        Button dateButton = (Button) findViewById(R.id.docSearchDateButton);
        try {
            return sdf.parse(dateButton.getText().toString());
        } catch (ParseException e) {
            return new Date();
        }
    }
}
