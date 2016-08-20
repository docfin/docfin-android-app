package com.jellsoft.mobile.docfin.network;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.jellsoft.mobile.docfin.maps.GooglePlaceAPI;

import java.util.List;

/**
 * Created by atulanand on 8/19/16.
 */
public class GooglePlacesSearchAsyncTask extends AsyncTask<String, Void, List<GooglePlaceAPI.GooglePlace>> {

    private ArrayAdapter adapter;
    private GooglePlaceAPI api;
    private AutoCompleteTextView autoCompleteView;

    public GooglePlacesSearchAsyncTask(ArrayAdapter adapter, AutoCompleteTextView view) {
        this.adapter = adapter;
        api = new GooglePlaceAPI(adapter.getContext());
        this.autoCompleteView = view;
    }

    @Override
    protected List<GooglePlaceAPI.GooglePlace> doInBackground(String... strings) {
        List<GooglePlaceAPI.GooglePlace> resultList = api.autocomplete(strings[0]);
        return resultList;
    }

    @Override
    protected void onPostExecute(List<GooglePlaceAPI.GooglePlace> results) {
        int size = results == null ? 0 : results.size();
        if (size > 0) {
            adapter.clear();
            adapter.addAll(results);
            adapter.notifyDataSetChanged();
            this.autoCompleteView.showDropDown();
        } else {
            adapter.notifyDataSetInvalidated();
        }
        super.onPostExecute(results);
    }
}
