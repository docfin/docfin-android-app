package com.jellsoft.mobile.docfin;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;

import com.jellsoft.mobile.docfin.maps.GooglePlaceAPI;

import java.util.List;

/**
 * Created by atulanand on 8/17/16.
 */
public class PlacesAutoCompleteAdapter extends ArrayAdapter<GooglePlaceAPI.GooglePlace> implements Filterable {

    AutoCompleteTextView acView;
    int mResource;

    public List<GooglePlaceAPI.GooglePlace> resultList;

    public GooglePlaceAPI mPlaceAPI;

    public PlacesAutoCompleteAdapter(AutoCompleteTextView view, int resource) {
        super(view.getContext(), resource);

        acView = view;
        mResource = resource;
        mPlaceAPI = new GooglePlaceAPI(acView.getContext());
    }

    @Override
    public Filter getFilter() {
        return new GooglePlacesSearchAdapterFilter(this);
    }

    @Override
    public int getCount() {
        // Last item will be the footer
        return resultList.size();
    }

    @Override
    public GooglePlaceAPI.GooglePlace getItem(int position) {
        return resultList.get(position);
    }

    public static class GooglePlacesSearchAdapterFilter extends Filter {
        PlacesAutoCompleteAdapter adapter;

        public GooglePlacesSearchAdapterFilter(PlacesAutoCompleteAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                adapter.resultList = adapter.mPlaceAPI.autocomplete(constraint.toString());

                filterResults.values = adapter.resultList;
                filterResults.count = adapter.resultList.size();
            }
            return filterResults;
           /* if (constraint != null && constraint.length() > 1)
                new GooglePlacesSearchAsyncTask(adapter, adapter.acView).execute(constraint.toString());
            return null;*/
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null && results.count > 0) {
                adapter.notifyDataSetChanged();
            } else {
                adapter.notifyDataSetInvalidated();
            }
        }
    }
}
