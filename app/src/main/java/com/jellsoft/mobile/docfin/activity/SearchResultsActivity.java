package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.IntentConstants;
import com.jellsoft.mobile.docfin.service.MockDoctorSearchService;

import java.util.Date;
import java.util.List;

public class SearchResultsActivity extends BaseDocfinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();

        List<DoctorCard> searchResults = (List<DoctorCard>) intent.getSerializableExtra(IntentConstants.SEARCH_RESULTS);
    }

}
