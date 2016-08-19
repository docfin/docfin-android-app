package com.jellsoft.mobile.docfin.json;

import android.util.Log;

import com.jellsoft.mobile.docfin.maps.GooglePlaceAPI;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atulanand on 8/17/16.
 */
public class DocfinJsonParser {

    public static List<DoctorSpecialization> deserializeDoctorSpecialization(String json) {
        List<DoctorSpecialization> specializations = new ArrayList<>();
        try {
            JSONArray jSpecs = new JSONArray(json);
            for (int i = 0; i < jSpecs.length(); i++) {
                JSONObject jSpec = jSpecs.getJSONObject(i);
                specializations.add(new DoctorSpecialization(jSpec.getString("id"), jSpec.getString("speciality")));
            }
        } catch (Exception e) {
            Log.e("DoctorSpecialization", e.getMessage());
        }
        return specializations;
    }

    public static GooglePlaceAPI.GooglePlaceResult toGooglePlaceResult(String jsonResults) {
        GooglePlaceAPI.GooglePlaceResult result = new GooglePlaceAPI.GooglePlaceResult();
        try {
            JSONObject jResult = new JSONObject(jsonResults);
            result.setStatus(jResult.getString("status"));
            JSONArray jPredictions = jResult.getJSONArray("predictions");
            List<GooglePlaceAPI.GooglePlace> places = new ArrayList<>(jPredictions.length());
            for (int i = 0; i < jPredictions.length(); i++) {
                JSONObject jSpec = jPredictions.getJSONObject(i);
                places.add(new GooglePlaceAPI.GooglePlace(jSpec.getString("place_id"), jSpec.getString("description")));
            }
            result.setPredictions(places);
        } catch (Exception e) {
            Log.e("GooglePlacesSearch", e.getMessage());
        }
        return result;
    }
}
