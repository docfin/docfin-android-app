package com.jellsoft.mobile.docfin.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.jellsoft.mobile.docfin.activity.OnDateSelectedListener;
import com.jellsoft.mobile.docfin.util.UIUtil;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String TYPE_KEY = "TYPE_KEY";
    private DatePickerType datePickerType;

    public DatePickerFragment() {
        this.datePickerType = DatePickerType.Appointment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        datePickerType = savedInstanceState == null || savedInstanceState.getSerializable(TYPE_KEY) == null ? DatePickerType.Appointment : (DatePickerType) savedInstanceState.getSerializable(TYPE_KEY);

        if (datePickerType == DatePickerType.Appointment)
            return new DatePickerDialog(getActivity(), this, year, month, day);
        else
            return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);


    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        ((OnDateSelectedListener) getActivity()).onDateSelected(UIUtil.toDate(year, month, day));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TYPE_KEY, this.datePickerType);
    }

    public enum DatePickerType {
        Appointment, DOB
    }
}