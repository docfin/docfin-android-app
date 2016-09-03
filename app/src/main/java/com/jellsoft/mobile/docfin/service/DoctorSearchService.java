package com.jellsoft.mobile.docfin.service;

import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.DoctorSpecialization;
import com.jellsoft.mobile.docfin.model.Insurance;

import java.util.Date;
import java.util.List;

/**
 * Created by atulanand on 9/3/16.
 */
public interface DoctorSearchService {

    List<DoctorCard> searchDoctors(Date date, String placeId, String specialization, String provider, String plan);

}
