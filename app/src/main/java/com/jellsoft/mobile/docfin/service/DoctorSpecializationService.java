package com.jellsoft.mobile.docfin.service;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;
import com.jellsoft.mobile.docfin.util.StreamReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by atulanand on 8/14/16.
 */
public class DoctorSpecializationService {

    public static List<DoctorSpecialization> getSpecializations(Resources resources) {
        String json = StreamReader.readStream(resources.openRawResource(R.raw.doctor_specialities));
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<DoctorSpecialization>>() {
        }.getType();
        Collection<DoctorSpecialization> specializations = gson.fromJson(json, collectionType);
        return new ArrayList(specializations);
    }
}
