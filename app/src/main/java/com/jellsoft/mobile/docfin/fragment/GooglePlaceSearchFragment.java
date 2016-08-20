package com.jellsoft.mobile.docfin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.jellsoft.mobile.docfin.PlacesAutoCompleteAdapter;
import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.activity.BaseDocfinActivity;
import com.jellsoft.mobile.docfin.activity.DoctorSearchActivity;

/**
 * Created by atulanand on 8/19/16.
 */
public class GooglePlaceSearchFragment extends Fragment {


    public GooglePlaceSearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the framework to try to keep this fragment around
        // during a configuration change.
        setRetainInstance(true);
        final AutoCompleteTextView autocompleteView = (AutoCompleteTextView) getActivity().findViewById(R.id.docSearchLoc);

        autocompleteView.setThreshold(2);
        PlacesAutoCompleteAdapter adapter = new PlacesAutoCompleteAdapter(autocompleteView, R.layout.textviewonly);
        autocompleteView.
                setAdapter(adapter);

        autocompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((BaseDocfinActivity) getActivity()).closeKeyboard();
            }
        });
    }
}
