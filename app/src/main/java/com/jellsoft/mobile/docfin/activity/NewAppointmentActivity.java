package com.jellsoft.mobile.docfin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jellsoft.mobile.docfin.R;
import com.jellsoft.mobile.docfin.model.Date;
import com.jellsoft.mobile.docfin.model.DoctorCard;
import com.jellsoft.mobile.docfin.model.DoctorProfileAndCalendar;
import com.jellsoft.mobile.docfin.model.IntentConstants;

public class NewAppointmentActivity extends AppCompatActivity {

    DoctorCard doctorCard;
    DoctorProfileAndCalendar.Day.Slot appointmentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appoinment);

        ((TextView)findViewById(R.id.toolbar_sr_title)).setText("Book Appointment");

        Intent intent = getIntent();
        this.doctorCard = (DoctorCard) intent.getSerializableExtra(IntentConstants.BOOK_APPOINTMENT_DOCTOR_CARD);
        this.appointmentTime = (DoctorProfileAndCalendar.Day.Slot) intent.getSerializableExtra(IntentConstants.BOOK_APPOINTMENT_TIME);
    }
}
