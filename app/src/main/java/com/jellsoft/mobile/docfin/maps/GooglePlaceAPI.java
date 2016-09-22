package com.jellsoft.mobile.docfin.maps;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.network.URLConnector;
import com.jellsoft.mobile.docfin.json.DocfinJsonParser;
import com.jellsoft.mobile.docfin.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atulanand on 8/17/16.
 */
public class GooglePlaceAPI {

    private Context context;

    public GooglePlaceAPI(Context context) {
        this.context = context;
    }

    private static final String TAG = GooglePlaceAPI.class.getSimpleName();

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";

    private static final String API_KEY = "AIzaSyC7PpL7lg8LT0LbAgKsqF59Pp-8cWRiUzQ";

    public List<GooglePlace> autocomplete(String input) {
        List<GooglePlace> resultList = new ArrayList<>();

        String jsonResults = "";

        String googlePlaceURL = Uri.parse(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON).buildUpon()
                .appendQueryParameter("key", context.getString(R.string.google_maps_key))
                .appendQueryParameter("types", "geocode")
                .appendQueryParameter("input" , input).build().toString();

        Log.d("GoogleAPI", googlePlaceURL);

        jsonResults = new URLConnector(context).doGet(googlePlaceURL);

        //Log.d(TAG, jsonResults.toString());

        GooglePlaceResult result = DocfinJsonParser.toGooglePlaceResult(jsonResults);
        if(result.foundResults())
        {
           return result.getPredictions();
        }
        return resultList;
    }

    public static class GooglePlaceResult {

        public GooglePlaceResult() {
        }

        private String status;

        private List<GooglePlace> predictions;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<GooglePlace> getPredictions() {
            return predictions;
        }

        public void setPredictions(List<GooglePlace> predictions) {
            this.predictions = predictions;
        }

        public boolean foundResults() {
            return this.status != null && this.status.equals("OK");
        }
    }

    public static class GooglePlace {
        private String place_id;

        private String description;

        public GooglePlace() {
        }

        public GooglePlace(String place_id, String description) {
            this.place_id = place_id;
            this.description = description;
        }

        public String getPlace_id() {
            return place_id;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GooglePlace that = (GooglePlace) o;

            if (!place_id.equals(that.place_id)) return false;
            return description != null ? description.equals(that.description) : that.description == null;

        }

        @Override
        public int hashCode() {
            int result = place_id.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }
    }
}
