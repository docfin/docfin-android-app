package com.jellsoft.mobile.docfin.service;

import com.jellsoft.mobile.docfin.model.DoctorCard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by atulanand on 9/3/16.
 */
public class MockDoctorSearchService implements DoctorSearchService
{
    @Override
    public List<DoctorCard> searchDoctors(Date date, String placeId, String specialization, String provider, String plan) {

        List<DoctorCard> doctors = new ArrayList<>(10);
        doctors.add(new DoctorCard("Dr. Rebecca Lu, MD", "Dermatologist","7 Mount Bethel Road", "Warren, NJ, 07059", true, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg"));
        doctors.add(new DoctorCard("Dr. Jon Wininger, MD, FAAD", "Dermatologist","1125 Saint Georges Ave", "Rahway, NJ, 07065", false, 1.2, "https://d2t808ag5aqhkh.cloudfront.net/e525d95a-a315-4300-ad08-3d08b78912afzoom.jpg"));
        doctors.add(new DoctorCard("Dr. Judy Y. Hu, MD", "Dermatologist","33 Overlook Rd Ste 405", "Warren, NJ, 07059", true, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/3/G/5/3G5JR_w120h160_v9244.jpg"));
        doctors.add(new DoctorCard("Dr. Carolin T. Penrose, MD", "Dermatologist","1110 South Ave Ste 400", "Staten Island, NY 10314", false, 3.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/2/P/M/2PMGW_w120h160_v10711.jpg"));
        doctors.add(new DoctorCard("Dr. Michael Ehrenreich, MD", "Dermatologist","90 Millburn Ave Ste 206", ", Millburn, NJ 07041", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/2/P/M/2PMGW_w120h160_v10711.jpg"));
        doctors.add(new DoctorCard("Dr. Adam Smith, MD", "Dermatologist","7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg"));
        doctors.add(new DoctorCard("Dr. Adam Smith, MD", "Dermatologist","7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg"));
        doctors.add(new DoctorCard("Dr. Adam Smith, MD", "Dermatologist","7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg"));
        doctors.add(new DoctorCard("Dr. Adam Smith, MD", "Dermatologist","7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg"));
        doctors.add(new DoctorCard("Dr. Adam Smith, MD", "Dermatologist","7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg"));
        return doctors;
    }

}
