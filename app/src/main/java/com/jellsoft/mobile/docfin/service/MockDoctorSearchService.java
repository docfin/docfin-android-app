package com.jellsoft.mobile.docfin.service;

import com.jellsoft.mobile.docfin.model.DocfinDate;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.DoctorProfileAndCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by atulanand on 9/3/16.
 */
public class MockDoctorSearchService implements DoctorSearchService {

    DoctorCard doc1 = new DoctorCard("doc1", "Dr. Rebecca Lu, MD", "Dermatologist", "7 Mount Bethel Road", "Warren, NJ, 07059", true, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg", 40.616410, -74.495565);
    DoctorCard doc2 = new DoctorCard("doc2", "Dr. Jon Wininger, MD, FAAD", "Dermatologist", "1125 Saint Georges Ave", "Rahway, NJ, 07065", false, 1.2, "https://d2t808ag5aqhkh.cloudfront.net/e525d95a-a315-4300-ad08-3d08b78912afzoom.jpg", 40.608430, -74.286695);
    DoctorCard doc3 = new DoctorCard("doc3", "Dr. Judy Y. Hu, MD", "Dermatologist", "33 Overlook Rd Ste 405", "Warren, NJ, 07059", true, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/3/G/5/3G5JR_w120h160_v9244.jpg", 40.642552, -74.493335);
    DoctorCard doc4 = new DoctorCard("doc4", "Dr. Carolin T. Penrose, MD", "Dermatologist", "1110 South Ave Ste 400", "Staten Island, NY 10314", false, 3.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/2/P/M/2PMGW_w120h160_v10711.jpg", 40.613973, -74.176963);
    DoctorCard doc5 = new DoctorCard("doc5", "Dr. Michael Ehrenreich, MD", "Dermatologist", "90 Millburn Ave Ste 206", ", Millburn, NJ 07041", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/2/P/M/2PMGW_w120h160_v10711.jpg", 40.722492, -74.286444);
    DoctorCard doc6 = new DoctorCard("doc6", "Dr. Adam Smith, MD", "Dermatologist", "7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg", 40.616410, -74.495565);
    DoctorCard doc7 = new DoctorCard("doc7", "Dr. Adam Smith, MD", "Dermatologist", "7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg", 40.616410, -74.495565);
    DoctorCard doc8 = new DoctorCard("doc8", "Dr. Adam Smith, MD", "Dermatologist", "7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg", 40.616410, -74.495565);
    DoctorCard doc9 = new DoctorCard("doc9", "Dr. Adam Smith, MD", "Dermatologist", "7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg", 40.616410, -74.495565);
    DoctorCard doc10 = new DoctorCard("doc10", "Dr. Adam Smith, MD", "Dermatologist", "7 Mount Bethel Road", "Warren, NJ, 07059", false, 1.2, "https://d1ffafozi03i4l.cloudfront.net/img/prov/Y/S/X/YSX6B_w120h160_v20589.jpg", 40.616410, -74.495565);

    @Override
    public List<DoctorCard> searchDoctors(Date date, String placeId, String specialization, String provider, String plan) {

        List<DoctorCard> doctors = new ArrayList<>(10);
        doctors.add(doc1);
        doctors.add(doc2);
        doctors.add(doc3);
        doctors.add(doc4);
        doctors.add(doc5);
        doctors.add(doc6);
        doctors.add(doc7);
        doctors.add(doc8);
        doctors.add(doc9);
        doctors.add(doc10);
        return doctors;
    }

    @Override
    public DoctorProfileAndCalendar getDoctorProfileAndCalendar(Date date, String docId) {
        if ("doc1".equals(docId)) {
            return mockDoctor1();
        }
        return mockDoctor1();
    }

    private DoctorProfileAndCalendar mockDoctor1() {
        String cv = "Dr. Rebecca Lu is a highly skilled board-certified dermatologist and fellowship-trained Mohs surgeon. She received her undergraduate degree with honors from the University of Pennsylvania before graduating with her medical degree from Baylor College of Medicine. Dr. Lu then completed her residency training in dermatology at St. Luke’s-Roosevelt Hospital Center through Columbia University, where she also served as chief resident.";
        String education = "Medical School - Baylor College of Medicine\n" +
                "NewYork-Presbyterian Hospital / Columbia University Medical Center, Residency in Dermatology\n" +
                "Columbia University, Saint Luke’s-Roosevelt Hospital Center, Residency in Dermatology";
        String affiliations = "New York-Presbyterian Hospital / Columbia University Medical Center";
        String languages = "English";
        String certification = "American Board of Dermatology";
        String practice = "Elite Skin MD";
        DoctorProfileAndCalendar.Profile profile = new DoctorProfileAndCalendar.Profile(cv, education, affiliations, certification, languages, practice);

        DoctorProfileAndCalendar d = new DoctorProfileAndCalendar(this.doc1, profile);

        Calendar calendar = Calendar.getInstance();

        for (int j = 0; j < 7; j++) {
            calendar.add(Calendar.DAY_OF_MONTH, j);
            DoctorProfileAndCalendar.Day day = new DoctorProfileAndCalendar.Day(new DocfinDate(calendar.getTime()));
            for (int i = 9; i < 19; i++) {
                day.addSlot(new DoctorProfileAndCalendar.Day.Slot(day, i, 0));
            }
            d.addDay(day);
        }

        return d;
    }

}
