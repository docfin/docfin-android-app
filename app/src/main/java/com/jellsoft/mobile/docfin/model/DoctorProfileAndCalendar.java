package com.jellsoft.mobile.docfin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atulanand on 9/10/16.
 */
public class DoctorProfileAndCalendar {

    private final DoctorCard doctorCard;

    private final Profile profile;

    private final List<Day> calendar = new ArrayList<>();

    public DoctorProfileAndCalendar(DoctorCard doctorCard, Profile profile) {
        this.doctorCard = doctorCard;
        this.profile = profile;
    }

    public DoctorProfileAndCalendar addDay(Day day)
    {
        this.calendar.add(day);
        return this;
    }

    public DoctorCard getDoctorCard() {
        return doctorCard;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<Day> getCalendar() {
        return calendar;
    }

    public static class Day
    {
        public final Date date;

        private List<Slot> slots = new ArrayList<>();

        public Day(Date day) {
            this.date = day;
        }

        public Day addSlot(Slot slot)
        {
            this.slots.add(slot);
            return this;
        }

        public Day addSlots(List<Slot> slots)
        {
            this.slots.addAll(slots);
            return this;
        }

        public List<Slot> getSlots() {
            return slots;
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
