package com.jellsoft.mobile.docfin.model;

/**
 * Created by atulanand on 8/13/16.
 */
public class DoctorSpecialization {

    private  String id;
    private String speciality;


    public DoctorSpecialization() {
    }

    public DoctorSpecialization(String id, String specialization) {
        this.id = id;
        this.speciality = specialization;
    }

    @Override
    public String toString() {
        return this.speciality;
    }

    public String getId() {
        return id;
    }

    public String getSpeciality() {
        return speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorSpecialization that = (DoctorSpecialization) o;

        if (!id.equals(that.id)) return false;
        return speciality.equals(that.speciality);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + speciality.hashCode();
        return result;
    }
}
