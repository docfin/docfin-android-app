package com.jellsoft.mobile.docfin.service;

import android.content.res.Resources;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;
import com.jellsoft.mobile.docfin.json.DocfinJsonParser;
import com.jellsoft.mobile.docfin.util.StreamReader;

import java.util.List;

/**
 * Created by atulanand on 8/14/16.
 */
public class DoctorSpecializationService {

    public static List<DoctorSpecialization> getSpecializations(Resources resources) {
        String json = StreamReader.readStream(resources.openRawResource(R.raw.doctor_specialities));
        return  DocfinJsonParser.deserializeDoctorSpecialization(json);
    }
}
