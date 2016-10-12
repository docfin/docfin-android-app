package com.jellsoft.mobile.docfin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atulanand on 9/10/16.
 */
public class DoctorProfileAndCalendar {

    private final DoctorCard doctorCard;

    private final Profile profile;

    private final ArrayList<Day> calendar = new ArrayList<>();

    public DoctorProfileAndCalendar(DoctorCard doctorCard, Profile profile) {
        this.doctorCard = doctorCard;
        this.profile = profile;
    }

    public DoctorProfileAndCalendar addDay(Day day) {
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

    public static class Day implements Serializable {
        public final Date date;

        private List<Slot> slots = new ArrayList<>();

        public Day(Date day) {
            this.date = day;
        }

        public Day addSlot(Slot slot) {
            this.slots.add(slot);
            return this;
        }

        public Day addSlots(List<Slot> slots) {
            this.slots.addAll(slots);
            return this;
        }

        public List<Slot> getSlots() {
            return slots;
        }

        public static class Slot implements Serializable {
            private final Day day;

            private final int hour;

            private final int minute;

            private PartOfDay partOfDay;

            public Slot(Day day, int hourOfDay, int minute) {
                this.day = day;
                this.hour = hourOfDay;
                this.minute = minute;
                this.setPartOfDay(this.hour);
            }

            private void setPartOfDay(int i) {
                this.partOfDay = i < 12 ?
                        DoctorProfileAndCalendar.Day.Slot.PartOfDay.AM : DoctorProfileAndCalendar.Day.Slot.PartOfDay.PM;
            }

            public enum PartOfDay {
                AM, PM
            }

            @Override
            public String toString() {
                return (this.hour == 12 ? this.hour : (this.hour % 12)) + (this.minute > 0 ? ":" + this.minute : "") + " " + partOfDay;
            }

            public Day getDay() {
                return day;
            }



            public int getHour() {
                return hour;
            }

            public int getMinute() {
                return minute;
            }

            public PartOfDay getPartOfDay() {
                return partOfDay;
            }
        }

    }

    public static class Profile implements Serializable {

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
