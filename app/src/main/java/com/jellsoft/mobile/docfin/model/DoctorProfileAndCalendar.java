package com.jellsoft.mobile.docfin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atulanand on 9/10/16.
 */
public class DoctorProfileAndCalendar {

    private final DoctorCard doctorCard;

    private final Profile profile;

    private final Calendar calendar;

    public DoctorProfileAndCalendar(DoctorCard doctorCard, Profile profile, Calendar calendar) {
        this.doctorCard = doctorCard;
        this.profile = profile;
        this.calendar = calendar;
    }

    public static class Calendar
    {
        private final Date day;

        private List<Slot> slots = new ArrayList<>();

        public Calendar(Date day) {
            this.day = day;
        }

        public Calendar addSlot(Slot slot)
        {
            this.slots.add(slot);
            return this;
        }

        public Calendar addSlots(List<Slot> slots)
        {
            this.slots.addAll(slots);
            return this;
        }

        public static class Slot
        {
            public final String time;

            public  final PartOfDay partOfDay;

            public Slot(String time, PartOfDay partOfDay) {
                this.time = time;
                this.partOfDay = partOfDay;
            }
            public enum PartOfDay
            {
                AM, PM
            }

            @Override
            public String toString() {
                return time + " " + partOfDay;
            }
        }

    }

    public static class Profile{

        public final String cv;

        public final String education;

        public final String affiliations;

        public final String certifications;

        public final String languages;

        public final String practice;

        public Profile(String cv, String education, String affiliations, String certifications, String languages, String practice) {
            this.cv = cv;
            this.education = education;
            this.affiliations = affiliations;
            this.certifications = certifications;
            this.languages = languages;
            this.practice = practice;
        }
    }
}
