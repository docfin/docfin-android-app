package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.service.MockDoctorSearchService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDoctorsInBackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctors_in_background);

        Intent intent = getIntent();
        Date searchDate = (Date) intent.getSerializableExtra(IntentConstants.SEARCH_DATE);
        String placeId = intent.getStringExtra(IntentConstants.SEARCH_LOCATION);
        String specialization = intent.getStringExtra(IntentConstants.SEARCH_SPECIALITY);
        //TODO figure out how to load pre-selected provider and plan
        boolean inNetwork = intent.getBooleanExtra(IntentConstants.SEARCH_IN_NETWORK, true);
        String provider = null;
        String plan = null;
        if (inNetwork) {
            //provider =  loadFromSavedDB
            //plan =  loadFromSavedDB
        }
        new SearchDoctors().execute(searchDate, placeId, specialization, provider, plan);
    }

    private class SearchDoctors extends AsyncTask<Object, Void, List<DoctorCard>> {
        @Override
        protected List<DoctorCard> doInBackground(Object... objects) {
            return new MockDoctorSearchService().
                    searchDoctors((Date) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4]);
        }

        @Override
        protected void onPostExecute(List<DoctorCard> doctorCards) {
            super.onPostExecute(doctorCards);
            showResults(doctorCards);
        }
    }

    private void showResults(List<DoctorCard> doctorCards)
    {
        setResult(IntentConstants.COMPLETED_WITH_RESULT, getIntent());
        getIntent().putExtra(IntentConstants.SEARCH_RESULTS, (ArrayList)doctorCards);
        finish();
    }
}
